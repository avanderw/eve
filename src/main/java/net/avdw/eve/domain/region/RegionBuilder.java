package net.avdw.eve.domain.region;

import net.avdw.database.TableBuilder;
import org.tinylog.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegionBuilder implements TableBuilder<Region> {

    @Override
    public Region build(ResultSet resultSet) {
        Region region = new Region();
        try {
            region.id = resultSet.getLong("regionId");
            region.name = resultSet.getString("regionName");
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            Logger.debug(e);
        }
        return region;
    }
}
