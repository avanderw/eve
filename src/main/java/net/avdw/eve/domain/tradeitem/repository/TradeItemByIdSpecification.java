package net.avdw.eve.domain.tradeitem.repository;

import net.avdw.repository.DatabaseSpecification;

public class TradeItemByIdSpecification implements DatabaseSpecification {
    private Long tradeItemId;

    public TradeItemByIdSpecification(Long tradeItemId) {
        this.tradeItemId = tradeItemId;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM invtypes WHERE typeId = %s", tradeItemId);
    }
}
