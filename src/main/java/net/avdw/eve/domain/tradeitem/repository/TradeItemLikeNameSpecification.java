package net.avdw.eve.domain.tradeitem.repository;

import net.avdw.repository.DatabaseSpecification;

public class TradeItemLikeNameSpecification implements DatabaseSpecification {
    private final String tradeItemName;

    public TradeItemLikeNameSpecification(String tradeItemName) {
        this.tradeItemName = tradeItemName;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM invtypes WHERE typeName like '%%%s%%'", tradeItemName.replace("'", "''"));
    }
}
