package net.avdw.eve.cli;

import net.avdw.eve.domain.AsciiTableBuilder;
import net.avdw.eve.domain.TradeItemSummary;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;

import java.util.List;
import java.util.Scanner;

@CommandLine.Command(name = "stddev", description = "Show stddev on prices for trade orders")
public class StandardDeviationCli extends ReusableOptions {

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        List<TradeItemSummary> tradeItemSummaryList = buildTradeItemSummaryList();

        AsciiTableBuilder asciiTableBuilder = new AsciiTableBuilder(tradeItemSummaryList);
        String table = asciiTableBuilder.build(item -> item.globalTradeOrder.buy.standardDeviation,
                item -> item.buy.standardDeviation,
                item -> item.buyOrderSummary,
                item -> item.highestStandardDeviation,
                item -> item.lowestStandardDeviation);
        int tableWidth = new Scanner(table).nextLine().length();
        System.out.println(StringUtils.center("[ Standard Deviation Buy Order ]", tableWidth));
        System.out.println(table);

        table = asciiTableBuilder.build(item -> item.globalTradeOrder.sell.standardDeviation,
                item -> item.sell.standardDeviation,
                item -> item.sellOrderSummary,
                item -> item.highestStandardDeviation,
                item -> item.lowestStandardDeviation);
        tableWidth = new Scanner(table).nextLine().length();
        System.out.println(StringUtils.center("[ Standard Deviation Sell Order ]", tableWidth));
        System.out.println(table);
    }
}
