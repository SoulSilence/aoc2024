package org.example.days;

import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class Day1 {
    public static void main(String[] args) {
        part2();
    }

    private static void part2() {
        List<String> input = Utils.readFile("input/day1.txt");
        List<Integer> arr1 = new ArrayList<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (String s : input) {
            if (s.isBlank()) continue;

            int[] vals = Stream.of(s.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int a = vals[0], b = vals[1];
            arr1.add(a);
            map2.compute(b, (k, v) -> {
                if (v == null) {
                    return 1;
                }
                return v + 1;
            });
        }

        long result = 0;
        for (Integer integer : arr1) {
            result += (long) integer * map2.getOrDefault(integer, 0);
        }

        System.out.println("result = " + result);
    }

    private static void part1() {
        List<String> input = Utils.readFile("input/day1.txt");
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        for (String s : input) {
            if (s.isBlank()) continue;

            int[] vals = Stream.of(s.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int a = vals[0], b = vals[1];
            arr1.add(a);
            arr2.add(b);
        }

        arr1.sort(Integer::compareTo);
        arr2.sort(Integer::compareTo);

        long result = 0;

        for (int i = 0; i < arr1.size(); i++) {
            result += Math.abs(arr1.get(i) - arr2.get(i));
        }

        System.out.println("result = " + result);
    }
}
