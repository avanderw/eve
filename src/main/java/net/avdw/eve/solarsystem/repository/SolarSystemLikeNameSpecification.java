package net.avdw.eve.solarsystem.repository;

import net.avdw.repository.DatabaseSpecification;

public class SolarSystemLikeNameSpecification implements DatabaseSpecification {
    private String systemName;

    public SolarSystemLikeNameSpecification(String systemName) {
        this.systemName = systemName;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM mapSolarSystems WHERE solarSystemName like '%%%s%%'", systemName);
    }
}
