package net.avdw.eve.cli;

import java.util.List;
import java.util.Scanner;

public class SelectOption<T> {
    T select(List<T> optionList) {
        T item;
        if (optionList == null || optionList.isEmpty()) {
            throw new UnsupportedOperationException("Option list is empty");
        } else if (optionList.size() == 1) {
            item = optionList.get(0);
        } else {
            item = null;
            optionList.forEach(System.out::println);
            System.out.print("Choose one: ");
            boolean goodSelection = false;
            Scanner scanner = new Scanner(System.in);
            while (!goodSelection) {
                String option = scanner.next();
                try {
                    item = optionList.get(Integer.parseInt(option));
                    goodSelection = true;
                } catch (RuntimeException ex) {
                    System.out.print("Choose again: ");
                }
            }
        }
        return item;
    }
}
