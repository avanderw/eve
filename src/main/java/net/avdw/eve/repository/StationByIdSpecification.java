package net.avdw.eve.repository;

import net.avdw.repository.DatabaseSpecification;

public class StationByIdSpecification implements DatabaseSpecification {
    private Long stationId;

    public StationByIdSpecification(Long stationId) {
        this.stationId = stationId;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM staStations WHERE stationId = %s", stationId);
    }
}
