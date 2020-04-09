package net.avdw.eve.marketer.domain;

public class Type {
    public TypeStat buy;
    public TypeStat sell;

    @Override
    public String toString() {
        return "Type{" +
                "buy=" + buy +
                ", sell=" + sell +
                '}';
    }
}
