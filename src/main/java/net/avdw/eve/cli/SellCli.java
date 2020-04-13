package net.avdw.eve.cli;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;
import com.github.freva.asciitable.HorizontalAlign;
import com.google.inject.Inject;
import net.avdw.eve.cache.TradeStationCache;
import net.avdw.eve.domain.DomainMapper;
import net.avdw.eve.marketer.MarketerApi;
import net.avdw.eve.marketer.MarketerClient;
import net.avdw.eve.marketer.MarketerRequest;
import net.avdw.eve.region.Region;
import net.avdw.eve.region.RegionLookup;
import net.avdw.eve.region.repository.RegionByIdSpecification;
import net.avdw.eve.solarsystem.SolarSystemLookup;
import net.avdw.eve.station.Station;
import net.avdw.eve.station.repository.StationByIdSpecification;
import net.avdw.eve.tradeitem.TradeItem;
import net.avdw.eve.tradeitem.TradeItemInteractiveParser;
import net.avdw.eve.tradeitem.TradeItemLookup;
import net.avdw.eve.tradeitem.TradeItemMarketClipboardParser;
import net.avdw.eve.tradeitem.repository.TradeItemByGroupIdSpecification;
import net.avdw.eve.tradeitem.repository.TradeItemRepository;
import net.avdw.eve.tradeitemgroup.TradeItemGroup;
import net.avdw.eve.tradeitemgroup.TradeItemGroupLookup;
import net.avdw.repository.Repository;
import picocli.CommandLine;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@CommandLine.Command(name = "sell", description = "Show best places to sell goods")
public class SellCli implements Runnable {
    @CommandLine.Parameters(description = "Trade item", arity = "0..*")
    private List<String> tradeItemSearchList;
    @CommandLine.Option(names = "--clipboard", description = "Use clipboard to retrieve trade items")
    private boolean useClipboard = false;
    @CommandLine.Option(names = "--interactive", description = "Use system input to retrieve trade items")
    private boolean useInteractive = false;
    @CommandLine.Option(names = "--group", description = "Add all items from a group")
    private List<String> groupItemSearchList;
    @CommandLine.Option(names = "--region", description = "Region to check")
    private List<String> regionSearchTermList;
    @CommandLine.Option(names = "--system", description = "System to check")
    private List<String> solarSystemSearchList;
    @CommandLine.Option(names = "--major-hubs", description = "Check major hubs")
    private boolean addMajorHubs = false;

    @Inject
    private TradeItemMarketClipboardParser marketClipboardParser;
    @Inject
    private TradeItemInteractiveParser tradeItemInteractiveParser;
    @Inject
    private TradeItemLookup tradeItemLookup;
    @Inject
    private SolarSystemLookup solarSystemLookup;
    @Inject
    private RegionLookup regionLookup;
    @Inject
    private TradeItemRepository tradeItemRepository;
    @Inject
    private TradeItemGroupLookup tradeItemGroupLookup;
    @Inject
    private Repository<Station> stationRepository;
    @Inject
    private Repository<Region> regionRepository;

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        List<TradeItem> tradeItemRequestList = new ArrayList<>();
        if (useClipboard) {
            tradeItemRequestList.addAll(marketClipboardParser.parse());
        }

        if (useInteractive) {
            tradeItemRequestList.addAll(tradeItemInteractiveParser.parse());
        }

        if (tradeItemSearchList != null) {
            tradeItemRequestList.addAll(tradeItemSearchList.stream()
                    .map(searchTerm -> tradeItemLookup.lookup(searchTerm))
                    .collect(Collectors.toList()));
        }

        if (groupItemSearchList != null) {
            groupItemSearchList.forEach(searchTerm -> {
                TradeItemGroup tradeItemGroup = tradeItemGroupLookup.lookup(searchTerm);
                tradeItemRequestList.addAll(tradeItemRepository.query(new TradeItemByGroupIdSpecification(tradeItemGroup.id)));
            });
        }

        if (addMajorHubs) {
            if (solarSystemSearchList == null) {
                solarSystemSearchList = new ArrayList<>();
            }
            TradeStationCache.MAJOR_TRADE_HUBS.forEach(stationId -> {
                List<Station> stationList = stationRepository.query(new StationByIdSpecification(stationId));
                Station station = new SelectOption<Station>().select(stationList);
                solarSystemSearchList.add(station.solarSystem.id.toString());
            });
        }

        List<MarketerRequest> marketerRequestList = new ArrayList<>();
        List<Long> tradeItemIdList = tradeItemRequestList.stream().map(tradeItem -> tradeItem.id).collect(Collectors.toList());
        if (solarSystemSearchList != null && !solarSystemSearchList.isEmpty()) {
            solarSystemSearchList.forEach(searchTerm -> {
                MarketerRequest marketerRequest = new MarketerRequest();
                marketerRequest.tradeItemIdList = tradeItemIdList;
                marketerRequest.solarSystem = solarSystemLookup.lookup(searchTerm);
                marketerRequestList.add(marketerRequest);
            });
        }

        if (regionSearchTermList != null && !regionSearchTermList.isEmpty()) {
            regionSearchTermList.forEach(searchTerm -> {
                MarketerRequest marketerRequest = new MarketerRequest();
                marketerRequest.tradeItemIdList = tradeItemIdList;
                marketerRequest.region = regionLookup.lookup(searchTerm);
                marketerRequestList.add(marketerRequest);
            });
        }

        MarketerRequest globalMarketerRequest = new MarketerRequest();
        globalMarketerRequest.tradeItemIdList = tradeItemIdList;
        marketerRequestList.add(globalMarketerRequest);

        List<TradeItem> tradeItemCompleteList = new ArrayList<>();
        MarketerApi marketerApi = new MarketerClient();
        marketerRequestList.forEach(marketerRequest -> {
            List<TradeItem> tradeItemResponseList = marketerApi.request(marketerRequest);
            tradeItemResponseList.forEach(tradeItemResponse -> {
                TradeItem tradeItemRequest = tradeItemRequestList.stream()
                        .filter(item -> item.id.equals(tradeItemResponse.id))
                        .findAny()
                        .orElseThrow(UnsupportedOperationException::new);
                DomainMapper.INSTANCE.toTradeItem(tradeItemRequest, tradeItemResponse, marketerRequest);
                tradeItemCompleteList.add(tradeItemResponse);
            });
        });
        Map<Long, List<TradeItem>> groupBySystemId = tradeItemCompleteList.stream()
                .collect(Collectors.groupingBy(tradeItem -> tradeItem.solarSystem.id != null ? tradeItem.solarSystem.id : -1));
        groupBySystemId.forEach((solarSystemId, tradeItemList) -> {
            if (solarSystemId != -1) {
                List<Region> regionList = regionRepository.query(new RegionByIdSpecification(tradeItemList.get(0).region.id));
                Region region = new SelectOption<Region>().select(regionList);
                tradeItemList.forEach(tradeItem -> tradeItem.region.name = region.name);
            }
        });

        Map<Long, List<TradeItem>> groupByTradeItemId = tradeItemCompleteList.stream()
                .collect(Collectors.groupingBy(tradeItem -> tradeItem.id != null ? tradeItem.id : -1));

        List<TableData> tableDataList = new ArrayList<>();
        AtomicReference<List<TradeItem>> lastTradeItemList = new AtomicReference<>();
        groupByTradeItemId.forEach((tradeItemId, tradeItemList) -> {
            TradeItem tradeItem = tradeItemList.get(0);
            TableData tableData = new TableData();
            tableData.itemName = tradeItem.name;
            tableData.valueList = new ArrayList<>();
            tradeItemList.forEach(tradeItemData -> tableData.valueList.add(NumberFormat.getInstance().format(tradeItemData.buy.median.setScale(0, RoundingMode.HALF_UP))));
            tableDataList.add(tableData);
            lastTradeItemList.set(tradeItemList);
        });
        tableDataList.sort(Comparator.comparing(item->item.valueList.get(item.valueList.size()-1).length()));

        List<String> headerList = new ArrayList<>();
        lastTradeItemList.get().forEach(tradeItem -> {
            if (tradeItem.solarSystem.id != null && tradeItem.solarSystem.id != -1) {
                headerList.add(tradeItem.solarSystem.name);
            } else if (tradeItem.region.id != null && tradeItem.region.id != -1) {
                headerList.add(tradeItem.region.name);
            } else {
                headerList.add("Global");
            }
        });

        List<ColumnData<TableData>> columnData = new ArrayList<>();
        columnData.add(new Column().header("Name").with(item -> item.itemName));
        for (int i = 0; i < headerList.size(); i++) {
            int finalI = i;
            columnData.add(new Column().headerAlign(HorizontalAlign.CENTER).header(headerList.get(i)).with(item -> item.valueList.get(finalI)));
        }

        System.out.println(AsciiTable.getTable(AsciiTable.BASIC_ASCII_NO_DATA_SEPARATORS, tableDataList, columnData));
    }

    static class TableData {
        String itemName;
        List<String> valueList;
    }
}
