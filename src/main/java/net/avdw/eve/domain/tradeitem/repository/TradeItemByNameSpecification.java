package net.avdw.eve.domain.tradeitem.repository;

import net.avdw.repository.DatabaseSpecification;

public class TradeItemByNameSpecification implements DatabaseSpecification {
    private final String tradeItemName;

    public TradeItemByNameSpecification(String tradeItemName) {
        this.tradeItemName = tradeItemName;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM invtypes WHERE typeName = '%s'", tradeItemName.replace("'", "''"));
    }
}
