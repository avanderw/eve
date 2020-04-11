package net.avdw.eve.repository;

import net.avdw.repository.DatabaseSpecification;
import net.avdw.repository.Specification;

public class SolarSystemByIdSpecification implements DatabaseSpecification {
    public SolarSystemByIdSpecification(Integer systemId) {
    }

    @Override
    public String toSqlQuery() {
        throw new UnsupportedOperationException();
    }
}
