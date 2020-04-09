package net.avdw.eve.cli.main;

import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "route", description = "Best trade route for good type")
public class RouteCli  implements Runnable {
    @CommandLine.Parameters(description = "Goods to trade", arity = "1..*")
    private List<String> goodList;
    @CommandLine.Option(names = "--region")
    private List<String> regionList;
    @CommandLine.Option(names = "--system")
    private List<String> systemList;

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        throw new UnsupportedOperationException();
    }
}
