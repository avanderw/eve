package net.avdw.eve.marketer;

import com.google.gson.Gson;
import net.avdw.eve.domain.TradeItem;
import net.avdw.eve.marketer.domain.Type;
import org.tinylog.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;

public class MarketerClient implements MarketerApi {
    @Override
    public List<TradeItem> request(MarketerRequest marketerRequest) {
        try {
            URL url = new URL("https://api.evemarketer.com/ec/marketstat/json?typeid=16240,592&regionlimit=10000002&usesystem=30000142");
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "java/8");
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            Logger.debug(String.format("%s %s", con.getResponseCode(), con.getResponseMessage()));
            Logger.debug(String.format("X-Ratelimit-Reset: %s", con.getHeaderField("X-Ratelimit-Reset")));
            Logger.debug(String.format("X-Ratelimit-Limit: %s", con.getHeaderField("X-Ratelimit-Limit")));
            Logger.debug(String.format("X-Ratelimit-Remaining: %s", con.getHeaderField("X-Ratelimit-Remaining")));

            Reader streamReader = new InputStreamReader(con.getResponseCode() > 299 ? con.getErrorStream() : con.getInputStream());
            try (BufferedReader in = new BufferedReader(streamReader)) {
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                Logger.debug(String.format("Response: %s", content));
                return MarketerMapper.INSTANCE.toTradeItemList(new Gson().fromJson(content.toString(), Type[].class));
            } finally {
                con.disconnect();
            }
        } catch (IOException e) {
            Logger.error(e.getMessage());
            Logger.debug(e);
        }

        return null;
    }
}
