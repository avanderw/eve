package net.avdw.eve.repository.tradeitem;

import net.avdw.repository.DatabaseSpecification;

public class TradeItemByIdSpecification implements DatabaseSpecification {
    private Integer goodId;

    public TradeItemByIdSpecification(Integer goodId) {
        this.goodId = goodId;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM invtypes WHERE typeId = %s", goodId);
    }
}
