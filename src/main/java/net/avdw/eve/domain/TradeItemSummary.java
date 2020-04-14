package net.avdw.eve.domain;

import net.avdw.eve.domain.tradeitem.TradeItem;

import java.util.List;

public class TradeItemSummary {
    public TradeItem tradeItem;
    public TradeOrderSummary buyOrderSummary;
    public TradeOrderSummary sellOrderSummary;
    public List<TradeItem> tradeOrderList;
    public TradeItem globalTradeOrder;
}
