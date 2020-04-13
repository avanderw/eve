package net.avdw.eve.tradeitem;

import com.google.inject.Inject;
import net.avdw.eve.cli.SelectOption;
import net.avdw.eve.tradeitem.repository.TradeItemByIdSpecification;
import net.avdw.eve.tradeitem.repository.TradeItemByNameSpecification;
import net.avdw.eve.tradeitem.repository.TradeItemLikeNameSpecification;
import net.avdw.repository.Repository;

import java.util.List;

public class TradeItemLookup {
    private Repository<TradeItem> tradeItemRepository;
    private SelectOption<TradeItem> tradeItemSelectOption;

    @Inject
    TradeItemLookup(final Repository<TradeItem> tradeItemRepository, final SelectOption<TradeItem> tradeItemSelectOption) {
        this.tradeItemRepository = tradeItemRepository;
        this.tradeItemSelectOption = tradeItemSelectOption;
    }

    public TradeItem lookup(String searchTerm) {
        TradeItem tradeItem;
        try {
            List<TradeItem> tradeItemByIdList = tradeItemRepository.query(new TradeItemByIdSpecification(Long.parseLong(searchTerm)));
            tradeItem = tradeItemSelectOption.select(tradeItemByIdList);
        } catch (RuntimeException e) {
            List<TradeItem> tradeItemByNameList = tradeItemRepository.query(new TradeItemByNameSpecification(searchTerm));
            if (tradeItemByNameList.isEmpty()) {
                List<TradeItem> tradeItemLikeNameList = tradeItemRepository.query(new TradeItemLikeNameSpecification(searchTerm));
                tradeItem = tradeItemSelectOption.select(tradeItemLikeNameList);
            } else {
                tradeItem = tradeItemSelectOption.select(tradeItemByNameList);
            }
        }

        return tradeItem;
    }
}