package net.avdw.eve.marketer.domain;

import java.util.List;

public class ForQuery {
    public boolean bid;
    public List<Long> types;
    public List<Long> regions;
    public List<Long> systems;
    public Integer hours;
    public Integer minq;

    @Override
    public String toString() {
        return "ForQuery{" +
                "bid=" + bid +
                ", types=" + types +
                ", regions=" + regions +
                ", systems=" + systems +
                ", hours=" + hours +
                ", minq=" + minq +
                '}';
    }
}
