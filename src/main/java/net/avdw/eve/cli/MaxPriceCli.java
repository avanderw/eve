package net.avdw.eve.cli;

import net.avdw.eve.domain.AsciiTableBuilder;
import net.avdw.eve.domain.TradeItemSummary;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;

import java.util.List;
import java.util.Scanner;

@CommandLine.Command(name = "max", description = "Show max prices for trade orders")
public class MaxPriceCli extends ReusableOptions {

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        List<TradeItemSummary> tradeItemSummaryList = buildTradeItemSummaryList();

        AsciiTableBuilder asciiTableBuilder = new AsciiTableBuilder(tradeItemSummaryList);
        String table = asciiTableBuilder.build(item -> item.globalTradeOrder.buy.maxPrice,
                item -> item.buy.maxPrice,
                item -> item.buyOrderSummary,
                item -> item.highestMaxPrice,
                item -> item.lowestMaxPrice);
        int tableWidth = new Scanner(table).nextLine().length();
        System.out.println(StringUtils.center("[ Max Buy Order ]", tableWidth));
        System.out.println(table);

        table = asciiTableBuilder.build(item -> item.globalTradeOrder.sell.maxPrice,
                item -> item.sell.maxPrice,
                item -> item.sellOrderSummary,
                item -> item.highestMaxPrice,
                item -> item.lowestMaxPrice);
        tableWidth = new Scanner(table).nextLine().length();
        System.out.println(StringUtils.center("[ Max Sell Order ]", tableWidth));
        System.out.println(table);
    }
}
