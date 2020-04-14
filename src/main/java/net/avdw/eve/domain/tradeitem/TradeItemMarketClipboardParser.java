package net.avdw.eve.domain.tradeitem;

import com.google.inject.Inject;
import org.tinylog.Logger;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TradeItemMarketClipboardParser {


    private TradeItemLookup tradeItemLookup;

    @Inject
    public TradeItemMarketClipboardParser(final TradeItemLookup tradeItemLookup) {
        this.tradeItemLookup = tradeItemLookup;
    }

    public List<TradeItem> parse() {
        List<TradeItem> tradeItemList = new ArrayList<>();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            Scanner scanner = new Scanner(clipboard.getData(DataFlavor.stringFlavor).toString());
            while (scanner.hasNextLine()) {
                Scanner lineScanner = new Scanner(scanner.nextLine());
                lineScanner.useDelimiter("\\t");
                String searchTerm = lineScanner.next();
                tradeItemList.add(tradeItemLookup.lookup(searchTerm));
            }
        } catch (UnsupportedFlavorException | IOException e) {
            Logger.error(e.getMessage());
            Logger.debug(e);
        }
        Logger.debug(tradeItemList);
        return tradeItemList;
    }
}
