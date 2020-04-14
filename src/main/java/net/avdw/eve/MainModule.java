package net.avdw.eve;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import net.avdw.database.DbConnection;
import net.avdw.database.TableBuilder;
import net.avdw.eve.domain.region.Region;
import net.avdw.eve.domain.region.RegionBuilder;
import net.avdw.eve.domain.region.repository.RegionRepository;
import net.avdw.eve.domain.solarsystem.SolarSystem;
import net.avdw.eve.domain.solarsystem.SolarSystemBuilder;
import net.avdw.eve.domain.solarsystem.repository.SolarSystemRepository;
import net.avdw.eve.domain.station.Station;
import net.avdw.eve.domain.station.StationBuilder;
import net.avdw.eve.domain.station.repository.StationRepository;
import net.avdw.eve.domain.tradeitem.TradeItem;
import net.avdw.eve.domain.tradeitem.TradeItemBuilder;
import net.avdw.eve.domain.tradeitem.repository.TradeItemRepository;
import net.avdw.eve.domain.tradeitemgroup.TradeItemGroup;
import net.avdw.eve.domain.tradeitemgroup.TradeItemGroupBuilder;
import net.avdw.eve.domain.tradeitemgroup.repository.TradeItemGroupRepository;
import net.avdw.eve.marketer.MarketerApi;
import net.avdw.eve.marketer.MarketerClient;
import net.avdw.property.AbstractPropertyModule;
import net.avdw.repository.Repository;
import org.tinylog.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class MainModule extends AbstractPropertyModule {
    @Override
    protected void configure() {
        Properties properties = configureProperties();
        Names.bindProperties(binder(), properties);
        bind(List.class).to(LinkedList.class);

        bind(MarketerApi.class).to(MarketerClient.class);

        bind(new TypeLiteral<Repository<TradeItem>>() {
        }).to(TradeItemRepository.class);
        bind(new TypeLiteral<Repository<SolarSystem>>() {
        }).to(SolarSystemRepository.class);
        bind(new TypeLiteral<Repository<Region>>() {
        }).to(RegionRepository.class);
        bind(new TypeLiteral<Repository<Station>>() {
        }).to(StationRepository.class);
        bind(new TypeLiteral<Repository<TradeItemGroup>>() {
        }).to(TradeItemGroupRepository.class);

        bind(new TypeLiteral<TableBuilder<Region>>() {
        }).to(RegionBuilder.class);
        bind(new TypeLiteral<TableBuilder<SolarSystem>>() {
        }).to(SolarSystemBuilder.class);
        bind(new TypeLiteral<TableBuilder<TradeItem>>() {
        }).to(TradeItemBuilder.class);
        bind(new TypeLiteral<TableBuilder<Station>>() {
        }).to(StationBuilder.class);
        bind(new TypeLiteral<TableBuilder<TradeItemGroup>>() {
        }).to(TradeItemGroupBuilder.class);


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
