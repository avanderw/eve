package net.avdw.eve.repository;

import net.avdw.repository.DatabaseSpecification;
import net.avdw.repository.Specification;

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
