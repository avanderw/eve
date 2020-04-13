package net.avdw.eve.solarsystem;

public class SolarSystem {
    public Long id;
    public String name;
    public Float security;

    @Override
    public String toString() {
        return String.format("(%s) %s", id, name);
    }
}
