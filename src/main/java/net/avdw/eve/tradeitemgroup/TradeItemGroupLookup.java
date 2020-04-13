package net.avdw.eve.tradeitemgroup;

import com.google.inject.Inject;
import net.avdw.eve.cli.SelectOption;
import net.avdw.eve.tradeitemgroup.repository.TradeItemGroupByIdSpecification;
import net.avdw.eve.tradeitemgroup.repository.TradeItemGroupByNameSpecification;
import net.avdw.eve.tradeitemgroup.repository.TradeItemGroupLikeNameSpecification;
import net.avdw.repository.Repository;

import java.util.List;

public class TradeItemGroupLookup {
    private Repository<TradeItemGroup> tradeItemGroupRepository;
    private SelectOption<TradeItemGroup> tradeItemGroupSelectOption;

    @Inject
    TradeItemGroupLookup(final Repository<TradeItemGroup> tradeItemGroupRepository, final SelectOption<TradeItemGroup> tradeItemGroupSelectOption) {
        this.tradeItemGroupRepository = tradeItemGroupRepository;
        this.tradeItemGroupSelectOption = tradeItemGroupSelectOption;
    }

    public TradeItemGroup lookup(String searchTerm) {
        TradeItemGroup tradeItemGroup;
        try {
            List<TradeItemGroup> tradeItemGroupByIdList = tradeItemGroupRepository.query(new TradeItemGroupByIdSpecification(Long.parseLong(searchTerm)));
            tradeItemGroup = tradeItemGroupSelectOption.select(tradeItemGroupByIdList);
        } catch (RuntimeException e) {
            List<TradeItemGroup> tradeItemGroupByNameList = tradeItemGroupRepository.query(new TradeItemGroupByNameSpecification(searchTerm));
            if (tradeItemGroupByNameList.isEmpty()) {
                List<TradeItemGroup> tradeItemGroupLikeNameList = tradeItemGroupRepository.query(new TradeItemGroupLikeNameSpecification(searchTerm));
                tradeItemGroup = tradeItemGroupSelectOption.select(tradeItemGroupLikeNameList);
            } else {
                tradeItemGroup = tradeItemGroupSelectOption.select(tradeItemGroupByNameList);
            }
        }

        return tradeItemGroup;
    }
}