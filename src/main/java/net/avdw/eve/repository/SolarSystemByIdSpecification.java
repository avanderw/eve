package net.avdw.eve.repository;

import net.avdw.repository.DatabaseSpecification;

public class SolarSystemByIdSpecification implements DatabaseSpecification {
    private Integer systemId;

    public SolarSystemByIdSpecification(Integer systemId) {
        this.systemId = systemId;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM mapSolarSystems WHERE solarSystemID = %s", systemId);
    }
}
