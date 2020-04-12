package net.avdw.eve.repository.tradeitem;

import net.avdw.repository.DatabaseSpecification;

public class TradeItemByGroupIdSpecification implements DatabaseSpecification {
    private Long groupId;

    public TradeItemByGroupIdSpecification(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toSqlQuery() {
        return String.format("SELECT * FROM invTypes WHERE groupID = %s", groupId);
    }
}
