package net.avdw.eve.domain.tradeitem;

import net.avdw.eve.domain.TradeOrder;
import net.avdw.eve.domain.region.Region;
import net.avdw.eve.domain.solarsystem.SolarSystem;

public class TradeItem {
    public Long id;
    public Long groupId;
    public Region region;
    public SolarSystem solarSystem;
    public TradeOrder buy;
    public TradeOrder sell;
    public String name;
    public Float volume;
    public String locationName;

    @Override
    public String toString() {
        return String.format("(%s) %s", id, name);

    }
}
