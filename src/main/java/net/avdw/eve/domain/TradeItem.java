package net.avdw.eve.domain;

import com.google.gson.Gson;
import org.tinylog.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TradeItem {
    public Long typeId;
    public Long regionId;
    public Long systemId;
    public TradeStatistic buy;
    public TradeStatistic sell;
    public String name;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public TradeItem() {
    }

    public TradeItem(ResultSet resultSet) {
        try {
            typeId = resultSet.getLong("typeId");
            name = resultSet.getString("typeName");
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            Logger.debug(e);
        }
    }
}
