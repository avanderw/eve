package net.avdw.eve.cli;

import picocli.CommandLine;

@CommandLine.Command(name = "eve", description = "The eve tool to assist with trading", version = "1.0-SNAPSHOT", mixinStandardHelpOptions = true,
        subcommands = {
                DetailCli.class,
                MinPriceCli.class,
                MedianCli.class,
                MaxPriceCli.class,
                ListCli.class
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
