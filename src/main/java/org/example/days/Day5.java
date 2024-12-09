package org.example.days;

import org.example.utils.Utils;

import java.util.*;

public class Day5 {
    public static void main(String[] args) throws InterruptedException {
        part2();
    }

    private static void part2() throws InterruptedException {
        List<String> input = Utils.readFile("input/day5.txt");

        // key is the smallest value, and set contains numbers that must be after that
        HashMap<String, Set<String>> orderingMap = new HashMap<>();

        for (String s : input) {
            if (!s.contains("|")) {
                break;
            }
            String[] split = s.split("\\|");
            orderingMap.putIfAbsent(split[1], new HashSet<>());
            orderingMap.get(split[1]).add(split[0]);
        }

        long ans = 0;
        for (String s : input) {
            if (!s.contains(",")) {
                continue;
            }

            boolean isValid = true;
            String[] split = s.split(",");
            for (int i = 0; i < split.length; i++) {
                Set<String> setMustBeBefore = orderingMap.getOrDefault(split[i], Collections.emptySet());
                for (int j = i + 1; j < split.length; j++) {
                    if (setMustBeBefore.contains(split[j])) {
                        isValid = false;
                        break;
                    }
                }
                if (!isValid) {
                    break;
                }
            }

            if (isValid) {
                continue;
            }

            boolean needSort = true;
            while (needSort) {
                needSort = false;
//                System.out.println("split = " + Arrays.toString(split));
//                Thread.sleep(100);
                for (int i = 0; i < split.length; i++) {
                    Set<String> setMustBeBefore = orderingMap.getOrDefault(split[i], Collections.emptySet());
                    for (int j = i + 1; j < split.length; j++) {
                        if (setMustBeBefore.contains(split[j])) {
                            needSort = true;
                            String oldI = split[i];
                            split[i] = split[j];
                            split[j] = oldI;
                            break;
                        }
                    }
                    if (needSort) {
                        break;
                    }
                }
            }

            ans += Integer.parseInt(split[split.length/2]);
        }
        System.out.println("ans = " + ans);
    }

    private static void part1() {
        List<String> input = Utils.readFile("input/day5.txt");

        // key is the smallest value, and set contains numbers that must be after that
        HashMap<String, Set<String>> orderingMap = new HashMap<>();

        for (String s : input) {
            if (!s.contains("|")) {
                break;
            }
            /*
            The first section specifies the page ordering rules, one per line.
            The first rule, 47|53, means that if an update includes both page number 47 and page number 53,
            then page number 47 must be printed at some point before page number 53.
            53 : [47]
             */
            String[] split = s.split("\\|");
            orderingMap.putIfAbsent(split[1], new HashSet<>());
            orderingMap.get(split[1]).add(split[0]);
        }

        long ans = 0;
        for (String s : input) {
            if (!s.contains(",")) {
                continue;
            }

            boolean isValid = true;
            String[] split = s.split(",");
            for (int i = 0; i < split.length; i++) {
                Set<String> setMustBeBefore = orderingMap.getOrDefault(split[i], Collections.emptySet());
                for (int j = i + 1; j < split.length; j++) {
                     if (setMustBeBefore.contains(split[j])) {
                         isValid = false;
                         break;
                     }
                }
                if (!isValid) {
                    break;
                }
            }

            if (isValid) {
                ans += Integer.parseInt(split[split.length/2]);
            }
        }
        System.out.println("ans = " + ans);
    }
}
