package net.avdw.eve.marketer;

import net.avdw.eve.domain.TradeItem;

import java.util.List;

public interface MarketerApi {
    List<TradeItem> request(MarketerRequest marketerRequest);
}
