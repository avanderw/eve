package net.avdw.eve.tradeitemgroup.repository;

import net.avdw.repository.DatabaseSpecification;

public class TradeItemGroupByNameSpecification implements DatabaseSpecification {
    private final String searchTerm;

    public TradeItemGroupByNameSpecification(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM invGroups WHERE groupName = '%s'", searchTerm.replace("'", "''"));
    }
}
