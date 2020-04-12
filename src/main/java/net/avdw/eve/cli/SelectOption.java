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
            System.out.println("===== Multiple options found =====");
            item = null;
            for (int i = 0; i < optionList.size(); i++) {
                System.out.println(String.format("[%3s] %s", i + 1, optionList.get(i)));
            }
            System.out.print("      Choose one: ");
            boolean goodSelection = false;
            Scanner scanner = new Scanner(System.in);
            while (!goodSelection) {
                String option = scanner.next();
                try {
                    item = optionList.get(Integer.parseInt(option) - 1);
                    goodSelection = true;
                } catch (RuntimeException ex) {
                    System.out.print("      Try again: ");
                }
            }
        }
        return item;
    }
}
