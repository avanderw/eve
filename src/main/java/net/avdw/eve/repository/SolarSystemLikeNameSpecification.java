package net.avdw.eve.repository;

import net.avdw.repository.DatabaseSpecification;
import net.avdw.repository.Specification;

public class SolarSystemLikeNameSpecification implements DatabaseSpecification {
    public SolarSystemLikeNameSpecification(String system) {
    }

    @Override
    public String toSqlQuery() {
        throw new UnsupportedOperationException();
    }
}
