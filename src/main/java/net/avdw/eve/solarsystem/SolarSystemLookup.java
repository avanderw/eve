package net.avdw.eve.solarsystem;

import com.google.inject.Inject;
import net.avdw.eve.cli.SelectOption;
import net.avdw.eve.solarsystem.repository.SolarSystemByIdSpecification;
import net.avdw.eve.solarsystem.repository.SolarSystemByNameSpecification;
import net.avdw.eve.solarsystem.repository.SolarSystemLikeNameSpecification;
import net.avdw.repository.Repository;

import java.util.List;

public class SolarSystemLookup {
    private Repository<SolarSystem> solarSystemRepository;
    private SelectOption<SolarSystem> solarSystemSelectOption;

    @Inject
    SolarSystemLookup(final Repository<SolarSystem> solarSystemRepository, final SelectOption<SolarSystem> solarSystemSelectOption) {
        this.solarSystemRepository = solarSystemRepository;
        this.solarSystemSelectOption = solarSystemSelectOption;
    }

    public SolarSystem lookup(String searchTerm) {
        SolarSystem solarSystem;
        try {
            List<SolarSystem> solarSystemByIdList = solarSystemRepository.query(new SolarSystemByIdSpecification(Long.parseLong(searchTerm)));
            solarSystem = solarSystemSelectOption.select(solarSystemByIdList);
        } catch (RuntimeException e) {
            List<SolarSystem> solarSystemByNameList = solarSystemRepository.query(new SolarSystemByNameSpecification(searchTerm));
            if (solarSystemByNameList.isEmpty()) {
                List<SolarSystem> solarSystemLikeNameList = solarSystemRepository.query(new SolarSystemLikeNameSpecification(searchTerm));
                solarSystem = solarSystemSelectOption.select(solarSystemLikeNameList);
            } else {
                solarSystem = solarSystemSelectOption.select(solarSystemByNameList);
            }
        }

        return solarSystem;
    }
}