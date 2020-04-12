package net.avdw.eve.repository.region;

import net.avdw.repository.DatabaseSpecification;

public class RegionLikeNameSpecification implements DatabaseSpecification {
    private String regionName;

    public RegionLikeNameSpecification(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM mapRegions WHERE regionName like '%%%s%%'", regionName);
    }
}
