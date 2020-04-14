package net.avdw.eve.domain;

import net.avdw.eve.domain.tradeitem.TradeItem;

public class TradeOrderSummary {
    public TradeItem lowestMinPrice;
    public TradeItem highestMinPrice;
    public TradeItem lowestMaxPrice;
    public TradeItem highestMaxPrice;
    public TradeItem highestMedianPrice;
    public TradeItem lowestMedianPrice;
    public TradeItem highestAveragePrice;
    public TradeItem lowestAveragePrice;
    public TradeItem highestVolume;
    public TradeItem lowestVolume;
    public TradeItem highestStandardDeviation;
    public TradeItem lowestStandardDeviation;
    public TradeItem highestWeightedAverage;
    public TradeItem lowestWeightedAverage;
}
