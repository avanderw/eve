package net.avdw.eve.repository;

import net.avdw.repository.DatabaseSpecification;
import net.avdw.repository.Specification;

public class TradeItemLikeNameSpecification implements DatabaseSpecification {
    private final String goodName;

    public TradeItemLikeNameSpecification(String goodName) {
        this.goodName = goodName;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM invtypes WHERE typeName like '%s%%'", goodName);
    }
}
