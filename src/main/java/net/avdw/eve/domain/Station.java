package net.avdw.eve.domain;

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
