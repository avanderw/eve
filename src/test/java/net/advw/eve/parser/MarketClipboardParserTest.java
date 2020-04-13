package net.advw.eve.parser;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.avdw.eve.MainModule;
import net.avdw.eve.tradeitem.TradeItemMarketClipboardParser;
import org.junit.jupiter.api.Test;

class MarketClipboardParserTest {

    @Test
    void parse() {
        Injector injector = Guice.createInjector(new MainModule());
        injector.getInstance(TradeItemMarketClipboardParser.class).parse();
    }
}