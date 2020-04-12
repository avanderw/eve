package net.avdw.eve;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import net.avdw.database.DbConnection;
import net.avdw.database.TableBuilder;
import net.avdw.eve.domain.*;
import net.avdw.eve.repository.RegionRepository;
import net.avdw.eve.repository.SolarSystemRepository;
import net.avdw.eve.repository.StationRepository;
import net.avdw.eve.repository.TradeItemRepository;
import net.avdw.property.AbstractPropertyModule;
import net.avdw.repository.Repository;
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

        bind(new TypeLiteral<Repository<TradeItem>>() {
        }).to(TradeItemRepository.class);
        bind(new TypeLiteral<Repository<SolarSystem>>() {
        }).to(SolarSystemRepository.class);
        bind(new TypeLiteral<Repository<Region>>() {
        }).to(RegionRepository.class);
        bind(new TypeLiteral<Repository<Station>>() {
        }).to(StationRepository.class);

        bind(new TypeLiteral<TableBuilder<Region>>() {
        }).to(RegionBuilder.class);
        bind(new TypeLiteral<TableBuilder<SolarSystem>>() {
        }).to(SolarSystemBuilder.class);
        bind(new TypeLiteral<TableBuilder<TradeItem>>() {
        }).to(TradeItemBuilder.class);
        bind(new TypeLiteral<TableBuilder<Station>>() {
        }).to(StationBuilder.class);


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
