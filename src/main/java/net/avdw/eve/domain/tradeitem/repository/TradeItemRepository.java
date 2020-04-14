package net.avdw.eve.domain.tradeitem.repository;

import com.google.inject.Inject;
import net.avdw.database.TableQuery;
import net.avdw.eve.domain.tradeitem.TradeItem;
import net.avdw.repository.DatabaseSpecification;
import net.avdw.repository.Repository;
import net.avdw.repository.Specification;

import java.util.Collection;
import java.util.List;

public class TradeItemRepository implements Repository<TradeItem> {
    private TableQuery<TradeItem> tradeItemTableQuery;

    @Inject
    TradeItemRepository(TableQuery<TradeItem> tradeItemTableQuery) {
        this.tradeItemTableQuery = tradeItemTableQuery;
    }

    @Override
    public void add(TradeItem item) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void add(Collection<TradeItem> items) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void update(TradeItem item) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void remove(TradeItem item) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void remove(Specification specification) {
        throw new UnsupportedOperationException();

    }

    @Override
    public List<TradeItem> query(Specification specification) {
        return tradeItemTableQuery.query((DatabaseSpecification) specification);
    }
}


