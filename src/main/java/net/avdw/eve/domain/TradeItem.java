package net.avdw.eve.domain;

public class TradeItem {
    public Long typeId;
    public Long groupId;
    public Region region;
    public SolarSystem solarSystem;
    public TradeStatistic buy;
    public TradeStatistic sell;
    public String name;
    public Float volume;

    @Override
    public String toString() {
        return name;
    }
}
