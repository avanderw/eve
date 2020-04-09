package net.avdw.eve.cli.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.avdw.eve.marketer.MarketerApi;
import net.avdw.eve.marketer.MarketerClient;
import net.avdw.eve.marketer.MarketerRequest;
import org.tinylog.Logger;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.List;

@CommandLine.Command(name = "trade", description = "Show price statistics for goods")
public class TradeCli implements Runnable {
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
        MarketerRequest marketerRequest = new MarketerRequest();
        marketerRequest.goodIdList = Arrays.asList(16240, 592);
        marketerRequest.regionId = 10000002; // The Forge
        marketerRequest.systemId = 30000142; // Jita
        MarketerApi marketerApi = new MarketerClient();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Logger.trace(gson.toJson(marketerApi.request(marketerRequest)));
    }
}
