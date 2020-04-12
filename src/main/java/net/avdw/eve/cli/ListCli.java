package net.avdw.eve.cli;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;
import com.google.inject.Inject;
import net.avdw.eve.cache.TradeStationCache;
import net.avdw.eve.domain.Region;
import net.avdw.eve.domain.SolarSystem;
import net.avdw.eve.domain.Station;
import net.avdw.eve.repository.region.RegionByIdSpecification;
import net.avdw.eve.repository.solarsystem.SolarSystemByIdSpecification;
import net.avdw.eve.repository.station.StationByIdSpecification;
import net.avdw.repository.Repository;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CommandLine.Command(name = "list", description = "List data")
public class ListCli implements Runnable {
    @Inject
    private Repository<Station> stationRepository;
    @Inject
    private Repository<SolarSystem> solarSystemRepository;
    @Inject
    private Repository<Region> regionRepository;

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        List<Station> majorHubStationList = TradeStationCache.MAJOR_TRADE_HUBS.stream().map(stationId -> {
            List<Station> stationList = stationRepository.query(new StationByIdSpecification(stationId));
            Station station = new SelectOption<Station>().select(stationList);
            List<SolarSystem> solarSystemList = solarSystemRepository.query(new SolarSystemByIdSpecification(station.solarSystem.id));
            station.solarSystem = new SelectOption<SolarSystem>().select(solarSystemList);
            List<Region> regionList = regionRepository.query(new RegionByIdSpecification(station.region.id));
            station.region = new SelectOption<Region>().select(regionList);
            return station;
        }).collect(Collectors.toList());

        int headerWidth = 100;
        System.out.println(StringUtils.center("[ Major Trading Hubs ]", headerWidth));
        System.out.println(AsciiTable.getTable(AsciiTable.BASIC_ASCII_NO_DATA_SEPARATORS, majorHubStationList, Arrays.asList(
                new Column().headerAlign(HorizontalAlign.CENTER).header("Region").with(station -> station.region.name != null ? station.region.name : ""),
                new Column().headerAlign(HorizontalAlign.CENTER).header("System").with(station -> station.solarSystem.name != null ? station.solarSystem.name : ""),
                new Column().headerAlign(HorizontalAlign.CENTER).header("Station").dataAlign(HorizontalAlign.LEFT).with(station -> station.name != null ? station.name : "")
        )));
    }
}
