package net.avdw.eve.cli;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import net.avdw.eve.domain.DomainMapper;
import net.avdw.eve.domain.Region;
import net.avdw.eve.domain.SolarSystem;
import net.avdw.eve.domain.TradeItem;
import net.avdw.eve.marketer.MarketerApi;
import net.avdw.eve.marketer.MarketerClient;
import net.avdw.eve.marketer.MarketerRequest;
import net.avdw.eve.repository.*;
import net.avdw.repository.Repository;
import org.tinylog.Logger;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CommandLine.Command(name = "trade", description = "Show price statistics for trade items")
public class TradeCli implements Runnable {
    @CommandLine.Parameters(description = "Trade item", arity = "1..*")
    private List<String> tradeItemList;
    @CommandLine.Option(names = "--region", description = "Region to check")
    private List<String> regionList;
    @CommandLine.Option(names = "--system", description = "System to check")
    private List<String> solarSystemList;

    @Inject
    private Repository<TradeItem> tradeItemRepository;
    @Inject
    private Repository<SolarSystem> solarSystemRepository;
    @Inject
    private Repository<Region> regionRepository;

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

//        marketerRequest.regionId = 10000002; // The Forge
//        marketerRequest.systemId = 30000142; // Jita

        List<MarketerRequest> marketerRequestList = new ArrayList<>();
        List<Long> tradeItemIdList = searchTradeItemList.stream().map(tradeItem -> tradeItem.typeId).collect(Collectors.toList());
        if (solarSystemList != null && !solarSystemList.isEmpty()) {
            solarSystemList.forEach(solarSystem -> {
                MarketerRequest marketerRequest = new MarketerRequest();
                marketerRequest.tradeItemIdList = tradeItemIdList;
                try {
                    Integer systemId = Integer.parseInt(solarSystem);
                    List<SolarSystem> systemList = solarSystemRepository.query(new SolarSystemByIdSpecification(systemId));
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
                    Integer regionId = Integer.parseInt(region);
                    List<Region> regionList = regionRepository.query(new RegionByIdSpecification(regionId));
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

        List<TradeItem> completeTradeItem = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        MarketerApi marketerApi = new MarketerClient();
        marketerRequestList.forEach(marketerRequest -> {
            List<TradeItem> tradeItemStatisticList = marketerApi.request(marketerRequest);
            tradeItemStatisticList.forEach(tradeItemStatistic -> {
                TradeItem searchTradeItem = searchTradeItemList.stream()
                        .filter(item -> item.typeId.equals(tradeItemStatistic.typeId))
                        .findAny()
                        .orElseThrow(UnsupportedOperationException::new);
                DomainMapper.INSTANCE.toTradeItem(searchTradeItem, tradeItemStatistic);
                completeTradeItem.add(tradeItemStatistic);
            });
        });
        completeTradeItem.forEach(tradeItem -> Logger.trace(gson.toJson(tradeItem)));
    }
}
