package net.avdw.eve.repository;

import net.avdw.repository.DatabaseSpecification;
import net.avdw.repository.Specification;

public class RegionByIdSpecification implements DatabaseSpecification {
    public RegionByIdSpecification(Integer regionId) {
    }

    @Override
    public String toSqlQuery() {
        throw new UnsupportedOperationException();
    }
}
