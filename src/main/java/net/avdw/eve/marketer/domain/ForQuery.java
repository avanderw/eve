package net.avdw.eve.marketer.domain;

import java.util.List;

public class ForQuery {
    public boolean bid;
    public List<Integer> types;
    public List<Integer> regions;
    public List<Integer> systems;
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
