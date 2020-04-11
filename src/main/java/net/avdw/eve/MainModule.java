package net.avdw.eve;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import net.avdw.database.DbConnection;
import net.avdw.property.AbstractPropertyModule;
import org.tinylog.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

class MainModule extends AbstractPropertyModule {
    @Override
    protected void configure() {
        Properties properties = configureProperties();
        Names.bindProperties(binder(), properties);
        bind(List.class).to(LinkedList.class);
    }

    @Provides
    @Singleton
    @DbConnection
    Connection dbConnection(@Named(EveProperty.JDBC_URL) final String jdbcUrl) {
        try {
            return DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            Logger.debug(e);
            throw new UnsupportedOperationException();
        }
    }

    @Override
    protected Properties defaultProperties() {
        Properties properties = new Properties();
        properties.put(EveProperty.JDBC_URL, "jdbc:sqlite:eve.sqlite");
        return properties;
    }
}
