package net.avdw.eve.solarsystem.repository;

import net.avdw.repository.DatabaseSpecification;

public class SolarSystemByIdSpecification implements DatabaseSpecification {
    private Long systemId;

    public SolarSystemByIdSpecification(Long systemId) {
        this.systemId = systemId;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM mapSolarSystems WHERE solarSystemID = %s", systemId);
    }
}
