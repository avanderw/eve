package net.avdw.eve.domain;

public class SolarSystem {
    public Long id;
    public String name;
    public Float security;

    @Override
    public String toString() {
        return name;
    }
}
