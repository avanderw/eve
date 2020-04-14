package net.avdw.eve.domain.region;

public class Region {
    public Long id;
    public String name;

    @Override
    public String toString() {
        return String.format("(%s) %s", id, name);
    }
}
