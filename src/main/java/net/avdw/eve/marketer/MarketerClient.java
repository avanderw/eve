package net.avdw.eve.marketer;

import com.google.gson.Gson;
import com.google.inject.Inject;
import net.avdw.eve.cli.SelectOption;
import net.avdw.eve.domain.DomainMapper;
import net.avdw.eve.domain.TradeItemSummary;
import net.avdw.eve.domain.TradeItemSummaryBuilder;
import net.avdw.eve.domain.region.Region;
import net.avdw.eve.domain.region.repository.RegionByIdSpecification;
import net.avdw.eve.domain.tradeitem.TradeItem;
import net.avdw.eve.marketer.domain.Type;
import net.avdw.repository.Repository;
import org.tinylog.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MarketerClient implements MarketerApi {
    private Repository<Region> regionRepository;
    private TradeItemSummaryBuilder tradeItemSummaryBuilder;

    @Inject
    MarketerClient(final Repository<Region> regionRepository, final TradeItemSummaryBuilder tradeItemSummaryBuilder) {
        this.regionRepository = regionRepository;
        this.tradeItemSummaryBuilder = tradeItemSummaryBuilder;
    }

    @Override
    public List<TradeItem> request(MarketerRequest marketerRequest) {
        try {
            if (marketerRequest.tradeItemIdList == null || marketerRequest.tradeItemIdList.isEmpty()) {
                throw new UnsupportedOperationException();
            }

            String urlString = "https://api.evemarketer.com/ec/marketstat/json?typeid=" + marketerRequest.tradeItemIdList.stream().map(String::valueOf).collect(Collectors.joining(","));
            if (marketerRequest.region != null) {
                urlString += String.format("&regionlimit=%s", marketerRequest.region.id);
            }
            if (marketerRequest.solarSystem != null) {
                urlString += String.format("&usesystem=%s", marketerRequest.solarSystem.id);
            }
            Logger.debug("Connecting to {}", urlString);
            URL url = new URL(urlString);
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

    @Override
    public List<TradeItemSummary> request(List<MarketerRequest> marketerRequestList, List<TradeItem> tradeItemRequestList) {
        List<TradeItem> tradeItemList = new ArrayList<>();
        marketerRequestList.forEach(marketerRequest -> {
            List<TradeItem> tradeItemResponseList = request(marketerRequest);
            tradeItemResponseList.forEach(tradeItemResponse -> {
                TradeItem tradeItemRequest = tradeItemRequestList.stream()
                        .filter(item -> item.id.equals(tradeItemResponse.id))
                        .findAny()
                        .orElseThrow(UnsupportedOperationException::new);
                DomainMapper.INSTANCE.toTradeItem(tradeItemRequest, tradeItemResponse, marketerRequest);
                tradeItemList.add(tradeItemResponse);
            });
        });

        Map<Long, List<TradeItem>> groupBySystemId = tradeItemList.stream()
                .collect(Collectors.groupingBy(tradeItem -> tradeItem.solarSystem.id != null ? tradeItem.solarSystem.id : -1));
        groupBySystemId.forEach((solarSystemId, list) -> {
            if (solarSystemId != -1) {
                List<Region> regionList = regionRepository.query(new RegionByIdSpecification(list.get(0).region.id));
                Region region = new SelectOption<Region>().select(regionList);
                list.forEach(tradeItem -> tradeItem.region.name = region.name);
            }
        });

        tradeItemList.forEach(tradeItem -> {
            if (tradeItem.solarSystem.id != null && tradeItem.solarSystem.id != -1) {
                tradeItem.locationName = tradeItem.solarSystem.name;
            } else if (tradeItem.region.id != null && tradeItem.region.id != -1) {
                tradeItem.locationName = tradeItem.region.name;
            } else {
                tradeItem.locationName = "Global";
            }
        });

        return tradeItemSummaryBuilder.build(tradeItemList);
    }
}
