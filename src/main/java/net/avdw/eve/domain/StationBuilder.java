package net.avdw.eve.domain;

import net.avdw.database.TableBuilder;
import org.tinylog.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StationBuilder implements TableBuilder<Station> {
    @Override
    public Station build(ResultSet resultSet) {
        Station station = new Station();
        station.solarSystem = new SolarSystem();
        station.region = new Region();
        try {
            station.stationId = resultSet.getLong("stationID");
            station.stationName = resultSet.getString("stationName");
            station.solarSystem.id = resultSet.getLong("solarSystemId");
            station.region.id = resultSet.getLong("regionId");
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            Logger.debug(e);
        }
        return station;
    }
}
