package net.avdw.eve.solarsystem.repository;

import net.avdw.repository.DatabaseSpecification;

public class SolarSystemByNameSpecification implements DatabaseSpecification {
    private final String solarSystemName;

    public SolarSystemByNameSpecification(String solarSystemName) {
        this.solarSystemName = solarSystemName;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM mapSolarSystems WHERE solarSystemName = '%s'", solarSystemName.replace("'", "''"));
    }
}
