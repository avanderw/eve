package net.avdw.eve.domain;

public class TradeItem {
    public Long id;
    public Long groupId;
    public Region region;
    public SolarSystem solarSystem;
    public TradeStatistic buy;
    public TradeStatistic sell;
    public String name;
    public Float volume;

    @Override
    public String toString() {
        return String.format("(%s) %s", id, name);

    }
}
