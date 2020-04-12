package net.avdw.eve.domain;

import net.avdw.database.TableBuilder;
import org.tinylog.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TradeItemBuilder implements TableBuilder<TradeItem> {
    @Override
    public TradeItem build(ResultSet resultSet) {
        TradeItem tradeItem = new TradeItem();
        try {
            tradeItem.typeId = resultSet.getLong("typeId");
            tradeItem.groupId = resultSet.getLong("groupId");
            tradeItem.name = resultSet.getString("typeName");
            tradeItem.volume = resultSet.getFloat("volume");
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            Logger.debug(e);
        }
        return tradeItem;
    }
}
