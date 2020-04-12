package net.avdw.eve.domain;

import net.avdw.database.TableBuilder;
import org.tinylog.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SolarSystemBuilder implements TableBuilder<SolarSystem> {
    @Override
    public SolarSystem build(ResultSet resultSet) {
        SolarSystem solarSystem = new SolarSystem();
        try {
            solarSystem.id = resultSet.getLong("solarSystemID");
            solarSystem.name = resultSet.getString("solarSystemName");
            solarSystem.security = resultSet.getFloat("security");
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            Logger.debug(e);
        }
        return solarSystem;
    }
}