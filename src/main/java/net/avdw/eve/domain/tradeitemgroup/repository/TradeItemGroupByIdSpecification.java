package net.avdw.eve.domain.tradeitemgroup.repository;

import net.avdw.repository.DatabaseSpecification;

public class TradeItemGroupByIdSpecification implements DatabaseSpecification {
    private Long tradeItemGroupId;

    public TradeItemGroupByIdSpecification(Long tradeItemGroupId) {
        this.tradeItemGroupId = tradeItemGroupId;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM invGroups WHERE groupId = %s", tradeItemGroupId);
    }
}
