package net.avdw.eve.domain;

import com.google.inject.Inject;
import net.avdw.eve.domain.tradeitem.TradeItem;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TradeItemSummaryBuilder {
    private TradeOrderSummaryBuilder tradeOrderSummaryBuilder;

    @Inject
    public TradeItemSummaryBuilder(TradeOrderSummaryBuilder tradeOrderSummaryBuilder) {
        this.tradeOrderSummaryBuilder = tradeOrderSummaryBuilder;
    }

    public List<TradeItemSummary> build(List<TradeItem> tradeItemList) {
        List<TradeItemSummary> tradeItemSummaryList = new ArrayList<>();
        Map<Long, List<TradeItem>> groupByTradeItemId = tradeItemList.stream()
                .collect(Collectors.groupingBy(tradeItem -> tradeItem.id != null ? tradeItem.id : -1));

        groupByTradeItemId.forEach((id, list) -> {
            TradeItemSummary tradeItemSummary = new TradeItemSummary();
            tradeItemSummary.tradeItem = list.get(0);
            tradeItemSummary.buyOrderSummary = tradeOrderSummaryBuilder.build(list, item -> item.buy);
            tradeItemSummary.sellOrderSummary = tradeOrderSummaryBuilder.build(list, item -> item.sell);
            tradeItemSummary.globalTradeOrder = list.stream().filter(item -> {
                Logger.trace("Region={}, System={}, Location={}", item.region, item.solarSystem, item.locationName);
                return item.solarSystem.id == null && item.region.id == null;
            }).findAny().orElseThrow(UnsupportedOperationException::new);
            tradeItemSummary.tradeOrderList = list;
            tradeItemSummaryList.add(tradeItemSummary);
        });

        return tradeItemSummaryList;
    }
}
