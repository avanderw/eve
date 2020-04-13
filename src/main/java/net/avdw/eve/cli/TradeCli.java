package net.avdw.eve.cli;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import net.avdw.eve.domain.*;
import net.avdw.eve.cache.TradeStationCache;
import net.avdw.eve.marketer.MarketerApi;
import net.avdw.eve.marketer.MarketerClient;
import net.avdw.eve.marketer.MarketerRequest;
import net.avdw.eve.region.Region;
import net.avdw.eve.region.repository.RegionByIdSpecification;
import net.avdw.eve.region.repository.RegionLikeNameSpecification;
import net.avdw.eve.solarsystem.SolarSystem;
import net.avdw.eve.solarsystem.repository.SolarSystemByIdSpecification;
import net.avdw.eve.solarsystem.repository.SolarSystemLikeNameSpecification;
import net.avdw.eve.station.Station;
import net.avdw.eve.station.repository.StationByIdSpecification;
import net.avdw.eve.tradeitem.TradeItem;
import net.avdw.eve.tradeitem.TradeItemLookup;
import net.avdw.eve.tradeitem.repository.TradeItemByGroupIdSpecification;
import net.avdw.eve.tradeitemgroup.TradeItemGroup;
import net.avdw.eve.tradeitemgroup.repository.TradeItemGroupLikeNameSpecification;
import net.avdw.repository.Repository;
import org.apache.commons.lang3.StringUtils;
import org.tinylog.Logger;
import picocli.CommandLine;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CommandLine.Command(name = "trade", description = "Show price statistics for trade items")
public class TradeCli implements Runnable {
    @CommandLine.Parameters(description = "Trade item", arity = "0..*")
    private List<String> tradeItemSearchList;
    @CommandLine.Option(names = "--group", description = "Add all items from a group")
    private List<String> groupItemList;
    @CommandLine.Option(names = "--region", description = "Region to check")
    private List<String> regionList;
    @CommandLine.Option(names = "--system", description = "System to check")
    private List<String> solarSystemList;
    @CommandLine.Option(names = "--major-hubs", description = "Check major hubs")
    private boolean addMajorHubs = false;
    @CommandLine.Option(names = "--nullsec-hubs", description = "Check nullsec hubs")
    private boolean addNullSecHubs = false;

    @Inject
    private Repository<TradeItem> tradeItemRepository;
    @Inject
    private Repository<SolarSystem> solarSystemRepository;
    @Inject
    private Repository<Region> regionRepository;
    @Inject
    private Repository<Station> stationRepository;
    @Inject
    private Repository<TradeItemGroup> tradeItemGroupRepository;
    @Inject
    private TradeItemLookup tradeItemLookup;

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        List<TradeItem> searchTradeItemList = new ArrayList<>();
        if (groupItemList != null) {
            groupItemList.forEach(groupItem -> {
                List<TradeItemGroup> tradeItemGroupList = tradeItemGroupRepository.query(new TradeItemGroupLikeNameSpecification(groupItem));
                TradeItemGroup tradeItemGroup = new SelectOption<TradeItemGroup>().select(tradeItemGroupList);
                searchTradeItemList.addAll(tradeItemRepository.query(new TradeItemByGroupIdSpecification(tradeItemGroup.id)));
            });
        }

        if (tradeItemSearchList != null) {
            searchTradeItemList.addAll(this.tradeItemSearchList.stream()
                    .map(searchTerm -> tradeItemLookup.lookup(searchTerm))
                    .collect(Collectors.toList()));
        }

        if (addMajorHubs) {
            if (solarSystemList == null) {
                solarSystemList = new ArrayList<>();
            }
            TradeStationCache.MAJOR_TRADE_HUBS.forEach(stationId->{
                List<Station> stationList = stationRepository.query(new StationByIdSpecification(stationId));
                Station station = new SelectOption<Station>().select(stationList);
                solarSystemList.add(station.solarSystem.id.toString());
            });
        }

        if (addNullSecHubs) {
            if (solarSystemList == null) {
                solarSystemList = new ArrayList<>();
            }
            TradeStationCache.NPC_NULLSEC_HUBS.forEach(stationId->{
                List<Station> stationList = stationRepository.query(new StationByIdSpecification(stationId));
                Station station = new SelectOption<Station>().select(stationList);
                solarSystemList.add(station.solarSystem.id.toString());
            });
        }

        List<MarketerRequest> marketerRequestList = new ArrayList<>();
        List<Long> tradeItemIdList = searchTradeItemList.stream().map(tradeItem -> tradeItem.id).collect(Collectors.toList());
        if (solarSystemList != null && !solarSystemList.isEmpty()) {
            solarSystemList.forEach(solarSystem -> {
                MarketerRequest marketerRequest = new MarketerRequest();
                marketerRequest.tradeItemIdList = tradeItemIdList;
                try {
                    List<SolarSystem> systemList = solarSystemRepository.query(new SolarSystemByIdSpecification(Long.parseLong(solarSystem)));
                    marketerRequest.solarSystem = new SelectOption<SolarSystem>().select(systemList);
                } catch (RuntimeException e) {
                    List<SolarSystem> systemList = solarSystemRepository.query(new SolarSystemLikeNameSpecification(solarSystem));
                    marketerRequest.solarSystem = new SelectOption<SolarSystem>().select(systemList);
                }
                marketerRequestList.add(marketerRequest);
            });
        }
        if (regionList != null && !regionList.isEmpty()) {
            regionList.forEach(region -> {
                MarketerRequest marketerRequest = new MarketerRequest();
                marketerRequest.tradeItemIdList = tradeItemIdList;
                try {
                    List<Region> regionList = regionRepository.query(new RegionByIdSpecification(Long.parseLong(region)));
                    marketerRequest.region = new SelectOption<Region>().select(regionList);
                } catch (RuntimeException e) {
                    List<Region> regionList = regionRepository.query(new RegionLikeNameSpecification(region));
                    marketerRequest.region = new SelectOption<Region>().select(regionList);
                }
                marketerRequestList.add(marketerRequest);
            });
        }
        MarketerRequest globalMarketerRequest = new MarketerRequest();
        globalMarketerRequest.tradeItemIdList = tradeItemIdList;
        marketerRequestList.add(globalMarketerRequest);

        List<TradeItem> completeTradeItemList = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        MarketerApi marketerApi = new MarketerClient();
        marketerRequestList.forEach(marketerRequest -> {
            List<TradeItem> tradeItemStatisticList = marketerApi.request(marketerRequest);
            tradeItemStatisticList.forEach(tradeItemStatistic -> {
                TradeItem searchTradeItem = searchTradeItemList.stream()
                        .filter(item -> item.id.equals(tradeItemStatistic.id))
                        .findAny()
                        .orElseThrow(UnsupportedOperationException::new);
                DomainMapper.INSTANCE.toTradeItem(searchTradeItem, tradeItemStatistic, marketerRequest);
                completeTradeItemList.add(tradeItemStatistic);
            });
        });
        completeTradeItemList.forEach(tradeItem -> Logger.trace(gson.toJson(tradeItem)));

        Map<Long, List<TradeItem>> groupByTypeId = completeTradeItemList.stream()
                .collect(Collectors.groupingBy(tradeItem -> tradeItem.id != null ? tradeItem.id : -1));
        Map<Long, List<TradeItem>> groupBySystemId = completeTradeItemList.stream()
                .collect(Collectors.groupingBy(tradeItem -> tradeItem.solarSystem.id != null ? tradeItem.solarSystem.id : -1));
        groupBySystemId.forEach((solarSystemId, tradeItemList) -> {
            if (solarSystemId != -1) {
                List<Region> regionList = regionRepository.query(new RegionByIdSpecification(tradeItemList.get(0).region.id));
                Region region = new SelectOption<Region>().select(regionList);
                tradeItemList.forEach(tradeItem -> tradeItem.region.name = region.name);
            }
        });

        int headerWidth = 100;
        groupByTypeId.forEach((typeId, tradeItemList) -> {
            System.out.println(StringUtils.center(String.format("[ Buy Order : %s ]", tradeItemList.get(0).name), headerWidth));
            System.out.println(AsciiTable.getTable(AsciiTable.BASIC_ASCII_NO_DATA_SEPARATORS, tradeItemList, Arrays.asList(
                    new Column().headerAlign(HorizontalAlign.CENTER).header("Region").with(tradeItem -> tradeItem.region.name != null ? tradeItem.region.name : ""),
                    new Column().headerAlign(HorizontalAlign.CENTER).header("System").with(tradeItem -> tradeItem.solarSystem.name != null ? tradeItem.solarSystem.name : ""),
                    new Column().header("Volume").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.buy.volume)),
                    new Column().header("Min").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.buy.minPrice.setScale(0, RoundingMode.HALF_UP))),
                    new Column().header("Median").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.buy.median.setScale(0, RoundingMode.HALF_UP))),
                    new Column().header("WAvg").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.buy.weightedAverage.setScale(0, RoundingMode.HALF_UP))),
                    new Column().header("Mean").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.buy.average.setScale(0, RoundingMode.HALF_UP))),
                    new Column().header("Max").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.buy.maxPrice.setScale(0, RoundingMode.HALF_UP))),
                    new Column().header("StdDev").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.buy.standardDeviation.setScale(0, RoundingMode.HALF_UP)))
            )));
            System.out.println(StringUtils.center(String.format("[ Sell Order : %s ]", tradeItemList.get(0).name), headerWidth));
            System.out.println(AsciiTable.getTable(AsciiTable.BASIC_ASCII_NO_DATA_SEPARATORS, tradeItemList, Arrays.asList(
                    new Column().headerAlign(HorizontalAlign.CENTER).header("Region").with(tradeItem -> tradeItem.region.name != null ? tradeItem.region.name : ""),
                    new Column().headerAlign(HorizontalAlign.CENTER).header("System").with(tradeItem -> tradeItem.solarSystem.name != null ? tradeItem.solarSystem.name : ""),
                    new Column().header("Volume").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.sell.volume)),
                    new Column().header("Min").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.sell.minPrice.setScale(0, RoundingMode.HALF_UP))),
                    new Column().header("Median").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.sell.median.setScale(0, RoundingMode.HALF_UP))),
                    new Column().header("WAvg").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.sell.weightedAverage.setScale(0, RoundingMode.HALF_UP))),
                    new Column().header("Mean").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.sell.average.setScale(0, RoundingMode.HALF_UP))),
                    new Column().header("Max").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.sell.maxPrice.setScale(0, RoundingMode.HALF_UP))),
                    new Column().header("StdDev").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.sell.standardDeviation.setScale(0, RoundingMode.HALF_UP)))
            )));
        });
    }
}
