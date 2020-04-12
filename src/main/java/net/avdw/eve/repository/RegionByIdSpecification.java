package net.avdw.eve.repository;

import net.avdw.repository.DatabaseSpecification;

public class RegionByIdSpecification implements DatabaseSpecification {
    private Integer regionId;

    public RegionByIdSpecification(Integer regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM mapRegions WHERE regionId = %s", regionId);
    }
}
