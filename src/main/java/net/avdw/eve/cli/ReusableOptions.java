package net.avdw.eve.cli;

import com.google.inject.Inject;
import net.avdw.eve.cache.TradeStationCache;
import net.avdw.eve.domain.TradeItemSummary;
import net.avdw.eve.domain.region.RegionLookup;
import net.avdw.eve.domain.solarsystem.SolarSystemLookup;
import net.avdw.eve.domain.station.Station;
import net.avdw.eve.domain.station.repository.StationByIdSpecification;
import net.avdw.eve.domain.tradeitem.TradeItem;
import net.avdw.eve.domain.tradeitem.TradeItemInteractiveParser;
import net.avdw.eve.domain.tradeitem.TradeItemLookup;
import net.avdw.eve.domain.tradeitem.TradeItemMarketClipboardParser;
import net.avdw.eve.domain.tradeitem.repository.TradeItemByGroupIdSpecification;
import net.avdw.eve.domain.tradeitem.repository.TradeItemRepository;
import net.avdw.eve.domain.tradeitemgroup.TradeItemGroup;
import net.avdw.eve.domain.tradeitemgroup.TradeItemGroupLookup;
import net.avdw.eve.marketer.MarketerApi;
import net.avdw.eve.marketer.MarketerRequest;
import net.avdw.repository.Repository;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ReusableOptions implements Runnable {
    @CommandLine.Parameters(description = "Trade item", arity = "0..*")
    protected List<String> tradeItemSearchList;
    @CommandLine.Option(names = "--clipboard", description = "Use clipboard to retrieve trade items")
    protected boolean useClipboard = false;
    @CommandLine.Option(names = "--interactive", description = "Use system input to retrieve trade items")
    protected boolean useInteractive = false;
    @CommandLine.Option(names = "--group", description = "Add all items from a group")
    protected List<String> groupItemSearchList;
    @CommandLine.Option(names = "--region", description = "Region to check")
    protected List<String> regionSearchTermList;
    @CommandLine.Option(names = "--system", description = "System to check")
    protected List<String> solarSystemSearchList;
    @CommandLine.Option(names = "--major-hubs", description = "Check major hubs")
    protected boolean addMajorHubs = false;

    @Inject
    private TradeItemMarketClipboardParser marketClipboardParser;
    @Inject
    private TradeItemInteractiveParser tradeItemInteractiveParser;
    @Inject
    private TradeItemLookup tradeItemLookup;
    @Inject
    private TradeItemRepository tradeItemRepository;
    @Inject
    private TradeItemGroupLookup tradeItemGroupLookup;
    @Inject
    private Repository<Station> stationRepository;
    @Inject
    private SolarSystemLookup solarSystemLookup;
    @Inject
    private RegionLookup regionLookup;
    @Inject
    private MarketerApi marketerApi;

    private List<TradeItem> buildTradeItemRequestList() {
        List<TradeItem> tradeItemList = new ArrayList<>();
        if (useClipboard) {
            tradeItemList.addAll(marketClipboardParser.parse());
        }

        if (useInteractive) {
            tradeItemList.addAll(tradeItemInteractiveParser.parse());
        }

        if (tradeItemSearchList != null) {
            tradeItemList.addAll(tradeItemSearchList.stream()
                    .map(searchTerm -> tradeItemLookup.lookup(searchTerm))
                    .collect(Collectors.toList()));
        }

        if (groupItemSearchList != null) {
            groupItemSearchList.forEach(searchTerm -> {
                TradeItemGroup tradeItemGroup = tradeItemGroupLookup.lookup(searchTerm);
                tradeItemList.addAll(tradeItemRepository.query(new TradeItemByGroupIdSpecification(tradeItemGroup.id)));
            });
        }

        return tradeItemList;
    }

    private List<MarketerRequest> buildMarketerRequestList(List<TradeItem> tradeItemRequestList) {
        List<MarketerRequest> marketerRequestList = new ArrayList<>();
        List<Long> tradeItemIdList = tradeItemRequestList.stream().map(tradeItem -> tradeItem.id).collect(Collectors.toList());
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
        return marketerRequestList;
    }

    protected List<TradeItemSummary> buildTradeItemSummaryList() {
        List<TradeItem> tradeItemList = buildTradeItemRequestList();
        return marketerApi.request(buildMarketerRequestList(tradeItemList), tradeItemList);
    }
}
