package net.avdw.eve.cli;

import org.tinylog.Logger;
import picocli.CommandLine;

@CommandLine.Command(name = "eve", description = "Some fancy description", version = "1.0-SNAPSHOT", mixinStandardHelpOptions = true,
        subcommands = {})
public class MainCli implements Runnable {

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        Logger.debug("MainCli.java entry point. Start coding here");
    }
}
