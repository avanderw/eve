package net.avdw.repository;

public interface DatabaseSpecification extends Specification {
    String toSqlQuery();
}
