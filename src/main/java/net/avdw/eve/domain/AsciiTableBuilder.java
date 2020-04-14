package net.avdw.eve.domain;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;
import com.github.freva.asciitable.HorizontalAlign;
import net.avdw.eve.domain.tradeitem.TradeItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class AsciiTableBuilder {
    private List<TradeItemSummary> tradeItemSummaryList;

    public AsciiTableBuilder(final List<TradeItemSummary> tradeItemSummaryList) {
        this.tradeItemSummaryList = tradeItemSummaryList;
    }

    public String build(Function<TradeItemSummary, BigDecimal> sortValue, Function<TradeItem, BigDecimal> valueGetter, Function<TradeItemSummary, TradeOrderSummary> orderGetter, Function<TradeOrderSummary, TradeItem> highestGetter, Function<TradeOrderSummary, TradeItem> lowestGetter) {
        tradeItemSummaryList.sort(Comparator.comparing(sortValue).reversed());
        List<ColumnData<TradeItemSummary>> columnData = new ArrayList<>();
        columnData.add(new Column().header("Name").with(summary -> summary.tradeItem.name));
        TradeItemSummary tradeItemSummary = tradeItemSummaryList.get(0);
        for (int i = 0; i < tradeItemSummary.tradeOrderList.size(); i++) {
            int finalI = i;
            columnData.add(new Column().headerAlign(HorizontalAlign.CENTER).header(tradeItemSummary.tradeOrderList.get(i).locationName).with(summary -> NumberFormat.getInstance().format(valueGetter.apply(summary.tradeOrderList.get(finalI)).setScale(0, RoundingMode.HALF_UP))));
        }
        columnData.add(new Column().header("Highest").with(summary -> String.format("%s (%s)",
                highestGetter.apply(orderGetter.apply(summary)).locationName,
                NumberFormat.getInstance().format(valueGetter.apply(highestGetter.apply(orderGetter.apply(summary))).setScale(0, RoundingMode.HALF_UP)))));
        columnData.add(new Column().header("Lowest").with(summary -> String.format("%s (%s)",
                lowestGetter.apply(orderGetter.apply(summary)).locationName,
                NumberFormat.getInstance().format(valueGetter.apply(lowestGetter.apply(orderGetter.apply(summary))).setScale(0, RoundingMode.HALF_UP)))));

        return AsciiTable.getTable(AsciiTable.BASIC_ASCII_NO_DATA_SEPARATORS, tradeItemSummaryList, columnData);
    }
}
