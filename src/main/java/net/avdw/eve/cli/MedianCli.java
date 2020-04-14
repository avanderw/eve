package net.avdw.eve.cli;

import net.avdw.eve.domain.AsciiTableBuilder;
import net.avdw.eve.domain.TradeItemSummary;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;

import java.util.List;
import java.util.Scanner;

@CommandLine.Command(name = "median", description = "Show median prices for trade orders")
public class MedianCli extends ReusableOptions {

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        List<TradeItemSummary> tradeItemSummaryList = buildTradeItemSummaryList();

        AsciiTableBuilder asciiTableBuilder = new AsciiTableBuilder(tradeItemSummaryList);
        String table = asciiTableBuilder.build(item -> item.globalTradeOrder.buy.median,
                item -> item.buy.median,
                item -> item.buyOrderSummary,
                item -> item.highestMedianPrice,
                item -> item.lowestMedianPrice);
        int tableWidth = new Scanner(table).nextLine().length();
        System.out.println(StringUtils.center("[ Median Buy Order ]", tableWidth));
        System.out.println(table);

        table = asciiTableBuilder.build(item -> item.globalTradeOrder.sell.median,
                item -> item.sell.median,
                item -> item.sellOrderSummary,
                item -> item.highestMedianPrice,
                item -> item.lowestMedianPrice);
        tableWidth = new Scanner(table).nextLine().length();
        System.out.println(StringUtils.center("[ Median Sell Order ]", tableWidth));
        System.out.println(table);
    }
}
