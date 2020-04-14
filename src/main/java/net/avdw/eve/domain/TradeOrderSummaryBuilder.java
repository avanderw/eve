package net.avdw.eve.domain;

import net.avdw.eve.domain.tradeitem.TradeItem;
import org.tinylog.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class TradeOrderSummaryBuilder {
    public TradeOrderSummary build(List<TradeItem> list, Function<TradeItem, TradeOrder> tradeStatisticGetter) {
        BigDecimal zero = new BigDecimal("0.0");
        TradeOrderSummary tradeSummary = new TradeOrderSummary();
        tradeSummary.lowestMinPrice = list.stream().min(Comparator.comparing(item -> tradeStatisticGetter.apply(item).minPrice.equals(zero) ? new BigDecimal(Double.MAX_VALUE) : tradeStatisticGetter.apply(item).minPrice)).orElseThrow(UnsupportedOperationException::new);
        tradeSummary.highestMinPrice = list.stream().max(Comparator.comparing(item -> tradeStatisticGetter.apply(item).minPrice)).orElseThrow(UnsupportedOperationException::new);
        tradeSummary.lowestMaxPrice = list.stream().min(Comparator.comparing(item -> tradeStatisticGetter.apply(item).maxPrice.equals(zero) ? new BigDecimal(Double.MAX_VALUE) : tradeStatisticGetter.apply(item).maxPrice)).orElseThrow(UnsupportedOperationException::new);
        tradeSummary.highestMaxPrice = list.stream().max(Comparator.comparing(item -> tradeStatisticGetter.apply(item).maxPrice)).orElseThrow(UnsupportedOperationException::new);
        tradeSummary.lowestMedianPrice = list.stream().min(Comparator.comparing(item -> tradeStatisticGetter.apply(item).median.equals(zero) ? new BigDecimal(Double.MAX_VALUE) : tradeStatisticGetter.apply(item).median)).orElseThrow(UnsupportedOperationException::new);
        tradeSummary.highestMedianPrice = list.stream().max(Comparator.comparing(item -> tradeStatisticGetter.apply(item).median)).orElseThrow(UnsupportedOperationException::new);
        tradeSummary.lowestAveragePrice = list.stream().min(Comparator.comparing(item -> tradeStatisticGetter.apply(item).average.equals(zero) ? new BigDecimal(Double.MAX_VALUE) : tradeStatisticGetter.apply(item).average)).orElseThrow(UnsupportedOperationException::new);
        tradeSummary.highestAveragePrice = list.stream().max(Comparator.comparing(item -> tradeStatisticGetter.apply(item).average)).orElseThrow(UnsupportedOperationException::new);
        tradeSummary.lowestVolume = list.stream().min(Comparator.comparing(item -> tradeStatisticGetter.apply(item).volume.equals(BigInteger.ZERO) ? new BigInteger("" + Long.MAX_VALUE) : tradeStatisticGetter.apply(item).volume)).orElseThrow(UnsupportedOperationException::new);
        tradeSummary.highestVolume = list.stream().max(Comparator.comparing(item -> tradeStatisticGetter.apply(item).volume)).orElseThrow(UnsupportedOperationException::new);
        tradeSummary.lowestStandardDeviation = list.stream().min(Comparator.comparing(item -> tradeStatisticGetter.apply(item).standardDeviation.equals(zero) ? new BigDecimal(Double.MAX_VALUE) : tradeStatisticGetter.apply(item).standardDeviation)).orElseThrow(UnsupportedOperationException::new);
        tradeSummary.highestStandardDeviation = list.stream().max(Comparator.comparing(item -> tradeStatisticGetter.apply(item).standardDeviation)).orElseThrow(UnsupportedOperationException::new);
        tradeSummary.lowestWeightedAverage = list.stream().min(Comparator.comparing(item -> tradeStatisticGetter.apply(item).weightedAverage.equals(zero) ? new BigDecimal(Double.MAX_VALUE) : tradeStatisticGetter.apply(item).weightedAverage)).orElseThrow(UnsupportedOperationException::new);
        tradeSummary.highestWeightedAverage = list.stream().max(Comparator.comparing(item -> tradeStatisticGetter.apply(item).weightedAverage)).orElseThrow(UnsupportedOperationException::new);
        return tradeSummary;
    }
}
