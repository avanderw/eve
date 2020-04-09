package net.avdw.eve.cli;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.avdw.eve.FullResponseBuilder;
import net.avdw.eve.marketer.MarketerApi;
import net.avdw.eve.marketer.MarketerClient;
import net.avdw.eve.marketer.MarketerRequest;
import org.tinylog.Logger;
import picocli.CommandLine;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

@CommandLine.Command(name = "eve", description = "Some fancy description", version = "1.0-SNAPSHOT", mixinStandardHelpOptions = true,
        subcommands = {})
public class MainCli implements Runnable {

    /**
     * Entry point for picocli.
     */
    @Override
    public void run() {
        Logger.debug("MainCli.java entry point. Start coding here");

        try {
            URL url = new URL("https://api.evemarketer.com/ec/marketstat/json?typeid=16240");
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "java/8");
            con.setRequestMethod("GET");

            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            MarketerRequest marketerRequest = new MarketerRequest();
            marketerRequest.typeIdList = Arrays.asList(16240, 592);
            marketerRequest.regionId = 10000002; // The Forge
            marketerRequest.systemId = 30000142; // Jita
            MarketerApi marketerApi = new MarketerClient();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Logger.debug(gson.toJson(marketerApi.request(marketerRequest)));
            con.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
