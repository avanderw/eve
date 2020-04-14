package net.avdw.eve.domain.station.repository;

import com.google.inject.Inject;
import net.avdw.database.TableQuery;
import net.avdw.eve.domain.station.Station;
import net.avdw.repository.DatabaseSpecification;
import net.avdw.repository.Repository;
import net.avdw.repository.Specification;

import java.util.Collection;
import java.util.List;

public class StationRepository implements Repository<Station> {
    private TableQuery<Station> stationTableQuery;

    @Inject
    StationRepository(final TableQuery<Station> stationTableQuery) {
        this.stationTableQuery = stationTableQuery;
    }

    @Override
    public void add(Station item) {

    }

    @Override
    public void add(Collection<Station> items) {

    }

    @Override
    public void update(Station item) {

    }

    @Override
    public void remove(Station item) {

    }

    @Override
    public void remove(Specification specification) {

    }

    @Override
    public List<Station> query(Specification specification) {
        return stationTableQuery.query((DatabaseSpecification) specification);
    }
}
