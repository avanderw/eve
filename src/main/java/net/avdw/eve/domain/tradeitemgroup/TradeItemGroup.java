package net.avdw.eve.domain.tradeitemgroup;

public class TradeItemGroup {
    public Long id;
    public String name;

    @Override
    public String toString() {
        return String.format("(%s) %s", id, name);

    }
}
