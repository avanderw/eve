package net.avdw.eve.cli;

import net.avdw.eve.domain.AsciiTableBuilder;
import net.avdw.eve.domain.TradeItemSummary;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;

import java.util.List;
import java.util.Scanner;

@CommandLine.Command(name = "average", description = "Show average prices for trade orders")
public class AverageCli extends ReusableOptions {

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        List<TradeItemSummary> tradeItemSummaryList = buildTradeItemSummaryList();

        AsciiTableBuilder asciiTableBuilder = new AsciiTableBuilder(tradeItemSummaryList);
        String table = asciiTableBuilder.build(item -> item.globalTradeOrder.buy.average,
                item -> item.buy.average,
                item -> item.buyOrderSummary,
                item -> item.highestAveragePrice,
                item -> item.lowestAveragePrice);
        int tableWidth = new Scanner(table).nextLine().length();
        System.out.println(StringUtils.center("[ Average Buy Order ]", tableWidth));
        System.out.println(table);

        table = asciiTableBuilder.build(item -> item.globalTradeOrder.sell.average,
                item -> item.sell.average,
                item -> item.sellOrderSummary,
                item -> item.highestAveragePrice,
                item -> item.lowestAveragePrice);
        tableWidth = new Scanner(table).nextLine().length();
        System.out.println(StringUtils.center("[ Average Sell Order ]", tableWidth));
        System.out.println(table);
    }
}
