package net.avdw.eve.marketer.domain;

public class TypeStat {
    public ForQuery forQuery;
    public Long volume;
    public Double min;
    public Double fivePercent;
    public Double max;
    public Double median;
    public Double avg;
    public Double wavg;
    public Double variance;
    public Double stdDev;

    @Override
    public String toString() {
        return "TypeStat{" +
                "forQuery=" + forQuery +
                ", volume=" + volume +
                ", min=" + min +
                ", fivePercent=" + fivePercent +
                ", max=" + max +
                ", median=" + median +
                ", avg=" + avg +
                ", wavg=" + wavg +
                ", variance=" + variance +
                ", stdDev=" + stdDev +
                '}';
    }
}
