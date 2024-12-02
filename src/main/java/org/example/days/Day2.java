package org.example.days;

import org.example.utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2 {
    public static void main(String[] args) {
        day2();
    }

    private static void day2() {
        List<String> input = FileUtils.readFile("input/day2.txt");
        int ans = 0;
        for (String s : input) {
            if (s.isBlank()) continue;

            int[] vals = Stream.of(s.split("\\s+")).mapToInt(Integer::parseInt).toArray();

            for (int j = -1; j < vals.length; j++) {
                boolean asc = false, desc = false, inRange = true;

                List<Integer> ints = transformArrWithExclusion(vals, j);
                for (int i = 1; i < ints.size(); i++) {
                    int diff = ints.get(i) - ints.get(i - 1);
                    if (diff > 0) {
                        asc = true;
                    } else if (diff < 0) {
                        desc = true;
                    }

                    if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                        inRange = false;
                    }
                }

                if (asc ^ desc && inRange) {
                    ans++;
                    break;
                }
            }

        }
        System.out.println("ans = " + ans);
    }

    private static List<Integer> transformArrWithExclusion(int[] vals, int removalIdx) {
        if (removalIdx < 0 || removalIdx >= vals.length) {
            return IntStream.of(vals)
                    .boxed()
                    .collect(Collectors.toList());
        }

        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i < vals.length; i++) {
            if (i != removalIdx) {
                arr.add(vals[i]);
            }
        }

        return arr;
    }

    private static void day1() {
        List<String> input = FileUtils.readFile("input/day2.txt");
        int ans = 0;
        for (String s : input) {
            if (s.isBlank()) continue;

            int[] vals = Stream.of(s.split("\\s+")).mapToInt(Integer::parseInt).toArray();

            boolean asc = false, desc = false, inRange = true;
            for (int i = 1; i < vals.length; i++) {
                int diff = vals[i] - vals[i - 1];
                if (diff > 0) {
                    asc = true;
                } else if (diff < 0) {
                    desc = true;
                }

                if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                    inRange = false;
                }
            }

            if (asc ^ desc && inRange) {
                ans++;
            }
        }
        System.out.println("ans = " + ans);
    }
}
