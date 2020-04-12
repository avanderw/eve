package net.avdw.eve.repository.tradeitemgroup;

import net.avdw.repository.DatabaseSpecification;

public class TradeItemGroupLikeNameSpecification implements DatabaseSpecification {
    private final String name;

    public TradeItemGroupLikeNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM invGroups WHERE groupName like '%%%s%%'", name);
    }
}
