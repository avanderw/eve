package net.avdw.eve.domain;

import net.avdw.database.TableBuilder;
import org.tinylog.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TradeItemGroupBuilder implements TableBuilder<TradeItemGroup> {
    @Override
    public TradeItemGroup build(ResultSet resultSet) {
        TradeItemGroup tradeItemGroup = new TradeItemGroup();
        try {
            tradeItemGroup.id = resultSet.getLong("groupId");
            tradeItemGroup.name = resultSet.getString("groupName");
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            Logger.debug(e);
        }
        return tradeItemGroup;
    }
}
