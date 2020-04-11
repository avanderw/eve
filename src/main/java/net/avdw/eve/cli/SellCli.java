package net.avdw.eve.cli;

import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "sell", description = "Show best places to sell goods")
public class SellCli implements Runnable {
    @CommandLine.Parameters(description = "Goods to trade", arity = "1..*")
    private List<String> goodList;
    @CommandLine.Option(names = "--region")
    private List<String> regionList;

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        throw new UnsupportedOperationException();
    }
}
