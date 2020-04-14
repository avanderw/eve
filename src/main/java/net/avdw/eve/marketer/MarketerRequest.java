package net.avdw.eve.marketer;

import net.avdw.eve.domain.region.Region;
import net.avdw.eve.domain.solarsystem.SolarSystem;

import java.util.List;

public class MarketerRequest {
    public List<Long> tradeItemIdList;
    public Region region;
    public SolarSystem solarSystem;
}
