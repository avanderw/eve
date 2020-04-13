package net.avdw.eve.region.repository;

import com.google.inject.Inject;
import net.avdw.database.TableQuery;
import net.avdw.eve.region.Region;
import net.avdw.repository.DatabaseSpecification;
import net.avdw.repository.Repository;
import net.avdw.repository.Specification;

import java.util.Collection;
import java.util.List;

public class RegionRepository implements Repository<Region> {
    private TableQuery<Region> regionTableQuery;

    @Inject
    RegionRepository(final TableQuery<Region> regionTableQuery) {
        this.regionTableQuery = regionTableQuery;
    }

    @Override
    public void add(Region item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Collection<Region> items) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void update(Region item) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void remove(Region item) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void remove(Specification specification) {
        throw new UnsupportedOperationException();

    }

    @Override
    public List<Region> query(Specification specification) {
        return regionTableQuery.query((DatabaseSpecification) specification);
    }
}
