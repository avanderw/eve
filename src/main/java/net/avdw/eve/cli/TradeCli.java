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
import net.avdw.eve.repository.*;
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
    @CommandLine.Parameters(description = "Trade item", arity = "1..*")
    private List<String> tradeItemList;
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

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        List<TradeItem> searchTradeItemList = this.tradeItemList.stream().map(good -> {
            Logger.debug("Looking up good: {}", good);
            TradeItem tradeItem;
            try {
                Integer goodId = Integer.parseInt(good);
                List<TradeItem> tradeItemByIdList = tradeItemRepository.query(new TradeItemByIdSpecification(goodId));
                tradeItem = new SelectOption<TradeItem>().select(tradeItemByIdList);
            } catch (RuntimeException e) {
                List<TradeItem> tradeItemByNameList = tradeItemRepository.query(new TradeItemLikeNameSpecification(good));
                tradeItem = new SelectOption<TradeItem>().select(tradeItemByNameList);
            }
            Logger.debug("Found: {}", tradeItem);
            return tradeItem;
        }).collect(Collectors.toList());

        if (addMajorHubs) {
            if (solarSystemList == null) {
                solarSystemList = new ArrayList<>();
            }
            TradeStationCache.MAJOR_TRADE_HUBS.forEach(stationId->{
                List<Station> stationList = stationRepository.query(new StationByIdSpecification(stationId));
                Station station = new SelectOption<Station>().select(stationList);
                solarSystemList.add(station.solarSystemId.toString());
            });
        }

        if (addNullSecHubs) {
            if (solarSystemList == null) {
                solarSystemList = new ArrayList<>();
            }
            TradeStationCache.NPC_NULLSEC_HUBS.forEach(stationId->{
                List<Station> stationList = stationRepository.query(new StationByIdSpecification(stationId));
                Station station = new SelectOption<Station>().select(stationList);
                solarSystemList.add(station.solarSystemId.toString());
            });
        }

        List<MarketerRequest> marketerRequestList = new ArrayList<>();
        List<Long> tradeItemIdList = searchTradeItemList.stream().map(tradeItem -> tradeItem.typeId).collect(Collectors.toList());
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
                        .filter(item -> item.typeId.equals(tradeItemStatistic.typeId))
                        .findAny()
                        .orElseThrow(UnsupportedOperationException::new);
                DomainMapper.INSTANCE.toTradeItem(searchTradeItem, tradeItemStatistic, marketerRequest);
                completeTradeItemList.add(tradeItemStatistic);
            });
        });
        completeTradeItemList.forEach(tradeItem -> Logger.trace(gson.toJson(tradeItem)));

        Map<Long, List<TradeItem>> groupByTypeId = completeTradeItemList.stream()
                .collect(Collectors.groupingBy(tradeItem -> tradeItem.typeId != null ? tradeItem.typeId : -1));
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
                    new Column().header("Avg").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.buy.average.setScale(0, RoundingMode.HALF_UP))),
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
                    new Column().header("Avg").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.sell.average.setScale(0, RoundingMode.HALF_UP))),
                    new Column().header("Max").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.sell.maxPrice.setScale(0, RoundingMode.HALF_UP))),
                    new Column().header("StdDev").dataAlign(HorizontalAlign.RIGHT).with(tradeItem -> NumberFormat.getInstance().format(tradeItem.sell.standardDeviation.setScale(0, RoundingMode.HALF_UP)))
            )));
        });
    }
}
