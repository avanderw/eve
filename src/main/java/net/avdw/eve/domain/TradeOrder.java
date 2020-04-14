package net.avdw.eve.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TradeOrder {
    public BigInteger volume;
    public BigDecimal minPrice;
    public BigDecimal fivePercentPrice;
    public BigDecimal maxPrice;
    public BigDecimal median;
    public BigDecimal average;
    public BigDecimal weightedAverage;
    public BigDecimal variance;
    public BigDecimal standardDeviation;
}
