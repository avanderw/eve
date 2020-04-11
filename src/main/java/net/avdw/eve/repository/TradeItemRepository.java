package net.avdw.eve.repository;

import com.google.inject.Inject;
import net.avdw.database.DbConnection;
import net.avdw.eve.domain.TradeItem;
import net.avdw.repository.DatabaseSpecification;
import net.avdw.repository.Repository;
import net.avdw.repository.Specification;
import org.tinylog.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TradeItemRepository implements Repository<TradeItem> {
    private Connection connection;

    @Inject
    TradeItemRepository(@DbConnection final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(TradeItem item) {

    }

    @Override
    public void add(Collection<TradeItem> items) {

    }

    @Override
    public void update(TradeItem item) {

    }

    @Override
    public void remove(TradeItem item) {

    }

    @Override
    public void remove(Specification specification) {

    }

    @Override
    public List<TradeItem> query(Specification specification) {
        List<TradeItem> tradeItemList = new ArrayList<>();
        if (specification instanceof DatabaseSpecification) {
            DatabaseSpecification databaseSpecification = (DatabaseSpecification) specification;
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(databaseSpecification.toSqlQuery())) {
                while (resultSet.next()) {
                    tradeItemList.add(new TradeItem(resultSet));
                }
            } catch (SQLException e) {
                Logger.error(e.getMessage());
                Logger.debug(e);
            }
        } else {
            throw new UnsupportedOperationException();
        }
        return tradeItemList;
    }
}


