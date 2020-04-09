package net.avdw.eve.marketer;

import java.util.List;

public class IterableNonIterableUtil {
    @FirstElement
    public <T> T first(List<T> in) {
        if (in != null && !in.isEmpty()) {
            return in.get(0);
        } else {
            return null;
        }
    }
}
