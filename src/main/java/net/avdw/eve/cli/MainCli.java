package net.avdw.eve.cli;

import net.avdw.eve.cli.main.*;
import picocli.CommandLine;

@CommandLine.Command(name = "eve", description = "The eve tool to assist with trading", version = "1.0-SNAPSHOT", mixinStandardHelpOptions = true,
        subcommands = {
                BuyCli.class,
                FindCli.class,
                RouteCli.class,
                SellCli.class,
                TradeCli.class
        })
public class MainCli implements Runnable {

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        CommandLine.usage(MainCli.class, System.out);
    }
}
