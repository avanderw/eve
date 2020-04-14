package net.avdw.eve.domain.region;

import com.google.inject.Inject;
import net.avdw.eve.cli.SelectOption;
import net.avdw.eve.domain.tradeitem.repository.TradeItemByIdSpecification;
import net.avdw.eve.domain.tradeitem.repository.TradeItemByNameSpecification;
import net.avdw.eve.domain.tradeitem.repository.TradeItemLikeNameSpecification;
import net.avdw.repository.Repository;

import java.util.List;

public class RegionLookup {
    private Repository<Region> regionRepository;
    private SelectOption<Region> regionSelectOption;

    @Inject
    RegionLookup(final Repository<Region> regionRepository, final SelectOption<Region> regionSelectOption) {
        this.regionRepository = regionRepository;
        this.regionSelectOption = regionSelectOption;
    }

    public Region lookup(String searchTerm) {
        Region region;
        try {
            List<Region> regionByIdList = regionRepository.query(new TradeItemByIdSpecification(Long.parseLong(searchTerm)));
            region = regionSelectOption.select(regionByIdList);
        } catch (RuntimeException e) {
            List<Region> regionByNameList = regionRepository.query(new TradeItemByNameSpecification(searchTerm));
            if (regionByNameList.isEmpty()) {
                List<Region> regionLikeNameList = regionRepository.query(new TradeItemLikeNameSpecification(searchTerm));
                region = regionSelectOption.select(regionLikeNameList);
            } else {
                region = regionSelectOption.select(regionByNameList);
            }
        }

        return region;
    }
}