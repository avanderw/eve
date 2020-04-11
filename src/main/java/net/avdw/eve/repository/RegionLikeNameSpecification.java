package net.avdw.eve.repository;

import net.avdw.repository.DatabaseSpecification;
import net.avdw.repository.Specification;

public class RegionLikeNameSpecification implements DatabaseSpecification {
    public RegionLikeNameSpecification(String region) {
    }

    @Override
    public String toSqlQuery() {
        throw new UnsupportedOperationException();
    }
}
