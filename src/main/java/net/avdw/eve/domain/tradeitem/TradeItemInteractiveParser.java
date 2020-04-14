package net.avdw.eve.domain.tradeitem;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TradeItemInteractiveParser {
    private TradeItemLookup tradeItemLookup;

    @Inject
    public TradeItemInteractiveParser(final TradeItemLookup tradeItemLookup) {
        this.tradeItemLookup = tradeItemLookup;
    }

    public List<TradeItem> parse() {
        System.out.println("Enter/paste trade item list:");
        List<TradeItem> tradeItemList = new ArrayList<>();
        Scanner inputScanner = new Scanner(System.in);
        String line = inputScanner.nextLine();
        while (!line.isEmpty()) {
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter("\\t");
            tradeItemList.add(tradeItemLookup.lookup(lineScanner.next()));
            line = inputScanner.nextLine();
        }
        return tradeItemList;
    }
}
