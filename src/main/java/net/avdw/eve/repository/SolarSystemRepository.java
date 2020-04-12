package net.avdw.eve.repository;

import com.google.inject.Inject;
import net.avdw.database.TableQuery;
import net.avdw.eve.domain.SolarSystem;
import net.avdw.repository.DatabaseSpecification;
import net.avdw.repository.Repository;
import net.avdw.repository.Specification;

import java.util.Collection;
import java.util.List;

public class SolarSystemRepository implements Repository<SolarSystem> {
    private TableQuery<SolarSystem> solarSystemTableQuery;

    @Inject
    SolarSystemRepository(final TableQuery<SolarSystem> solarSystemTableQuery) {
        this.solarSystemTableQuery = solarSystemTableQuery;
    }

    @Override
    public void add(SolarSystem item) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void add(Collection<SolarSystem> items) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void update(SolarSystem item) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void remove(SolarSystem item) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void remove(Specification specification) {
        throw new UnsupportedOperationException();

    }

    @Override
    public List<SolarSystem> query(Specification specification) {
        return solarSystemTableQuery.query((DatabaseSpecification) specification);
    }
}
