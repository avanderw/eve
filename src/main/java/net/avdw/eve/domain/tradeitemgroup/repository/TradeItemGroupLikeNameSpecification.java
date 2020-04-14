package net.avdw.eve.domain.tradeitemgroup.repository;

import net.avdw.repository.DatabaseSpecification;

public class TradeItemGroupLikeNameSpecification implements DatabaseSpecification {
    private final String searchTerm;

    public TradeItemGroupLikeNameSpecification(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM invGroups WHERE groupName like '%%%s%%'", searchTerm.replace("'", "''"));
    }
}
