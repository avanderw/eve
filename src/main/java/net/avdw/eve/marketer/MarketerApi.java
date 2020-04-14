package net.avdw.eve.marketer;

import net.avdw.eve.domain.TradeItemSummary;
import net.avdw.eve.domain.tradeitem.TradeItem;

import java.util.List;

public interface MarketerApi {
    List<TradeItem> request(MarketerRequest marketerRequest);
    List<TradeItemSummary> request(List<MarketerRequest> marketerRequestList, List<TradeItem> tradeItemRequestList);
}
