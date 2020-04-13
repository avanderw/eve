package net.avdw.eve.region.repository;

import net.avdw.repository.DatabaseSpecification;

public class RegionByIdSpecification implements DatabaseSpecification {
    private Long regionId;

    public RegionByIdSpecification(Long regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM mapRegions WHERE regionId = %s", regionId);
    }
}
