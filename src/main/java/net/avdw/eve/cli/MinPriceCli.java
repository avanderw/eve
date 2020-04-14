package net.avdw.eve.cli;

import net.avdw.eve.domain.AsciiTableBuilder;
import net.avdw.eve.domain.TradeItemSummary;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;

import java.util.List;
import java.util.Scanner;

@CommandLine.Command(name = "min", description = "Show min prices for trade orders")
public class MinPriceCli extends ReusableOptions {

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        List<TradeItemSummary> tradeItemSummaryList = buildTradeItemSummaryList();

        AsciiTableBuilder asciiTableBuilder = new AsciiTableBuilder(tradeItemSummaryList);
        String table = asciiTableBuilder.build(item -> item.globalTradeOrder.buy.minPrice,
                item -> item.buy.minPrice,
                item -> item.buyOrderSummary,
                item -> item.highestMinPrice,
                item -> item.lowestMinPrice);
        int tableWidth = new Scanner(table).nextLine().length();
        System.out.println(StringUtils.center("[ Min Buy Order ]", tableWidth));
        System.out.println(table);

        table = asciiTableBuilder.build(item -> item.globalTradeOrder.sell.minPrice,
                item -> item.sell.minPrice,
                item -> item.sellOrderSummary,
                item -> item.highestMinPrice,
                item -> item.lowestMinPrice);
        tableWidth = new Scanner(table).nextLine().length();
        System.out.println(StringUtils.center("[ Min Sell Order ]", tableWidth));
        System.out.println(table);
    }
}
