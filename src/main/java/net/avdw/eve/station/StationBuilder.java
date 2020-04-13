package net.avdw.eve.station;

import net.avdw.database.TableBuilder;
import net.avdw.eve.region.Region;
import net.avdw.eve.solarsystem.SolarSystem;
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
            station.id = resultSet.getLong("stationID");
            station.name = resultSet.getString("stationName");
            station.solarSystem.id = resultSet.getLong("solarSystemId");
            station.region.id = resultSet.getLong("regionId");
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            Logger.debug(e);
        }
        return station;
    }
}
