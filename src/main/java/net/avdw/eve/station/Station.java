package net.avdw.eve.station;

import net.avdw.eve.region.Region;
import net.avdw.eve.solarsystem.SolarSystem;

public class Station {
    public SolarSystem solarSystem;
    public String name;
    public Long id;
    public Region region;

    @Override
    public String toString() {
        return String.format("(%s) %s", id, name);
    }
}
