package net.avdw.eve.domain.tradeitemgroup.repository;

import com.google.inject.Inject;
import net.avdw.database.TableQuery;
import net.avdw.eve.domain.tradeitemgroup.TradeItemGroup;
import net.avdw.repository.DatabaseSpecification;
import net.avdw.repository.Repository;
import net.avdw.repository.Specification;

import java.util.Collection;
import java.util.List;

public class TradeItemGroupRepository implements Repository<TradeItemGroup> {
    private TableQuery<TradeItemGroup> tradeItemGroupTableQuery;

    @Inject
    TradeItemGroupRepository(final TableQuery<TradeItemGroup> tradeItemGroupTableQuery) {
        this.tradeItemGroupTableQuery = tradeItemGroupTableQuery;
    }

    @Override
    public void add(TradeItemGroup item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Collection<TradeItemGroup> items) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void update(TradeItemGroup item) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void remove(TradeItemGroup item) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void remove(Specification specification) {
        throw new UnsupportedOperationException();

    }

    @Override
    public List<TradeItemGroup> query(Specification specification) {
        return tradeItemGroupTableQuery.query((DatabaseSpecification) specification);
    }
}
