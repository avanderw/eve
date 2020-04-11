package net.avdw.repository;

import net.avdw.repository.Specification;

public interface DatabaseSpecification extends Specification {
    String toSqlQuery();
}
