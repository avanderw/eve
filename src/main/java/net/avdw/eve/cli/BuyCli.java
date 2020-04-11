package net.avdw.eve.cli;

import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "buy", description = "Show best places to buy goods")
public class BuyCli implements Runnable {
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
