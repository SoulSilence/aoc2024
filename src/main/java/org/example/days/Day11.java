package org.example.days;

import org.example.utils.Utils;

import java.util.*;
import java.util.regex.Pattern;

public class Day11 {
    public static void main(String[] args) {
        part2();
    }

    private static Map<String, Long> memo = new HashMap<>();

    private static void part2() {
        List<Long> stones = Arrays.stream(Utils.readFile("input/day11.txt").getFirst()
                        .split(" "))
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
                .toList();
        long ans = 0;
        for (Long stone : stones) {
            ans += blink(stone, 0, 75);
        }
        System.out.println("ans = " + ans);
        System.out.println("memo.size() = " + memo.size());
    }

    private static long blink(Long stone, int depth, int maxDepth) {
        if (depth == maxDepth) {
            return 1;
        }
        if (memo.containsKey(stone + "-" + depth)) {
            return memo.get(stone + "-" + depth);
        }

        if (stone == 0) {
            return blink(1L, depth + 1, maxDepth);
        } else if (String.valueOf(stone).length() % 2 == 0) {
            String s = String.valueOf(stone);

            long result = blink(Long.parseLong(s.substring(0, s.length() / 2)), depth + 1, maxDepth) +
                    blink(Long.parseLong(s.substring(s.length() / 2)), depth + 1, maxDepth);
            memo.put(stone + "-" + depth, result);
            return result;
        } else {
            return blink(stone * 2024, depth + 1, maxDepth);
        }
    }

    private static void part1() {
        List<Long> stones = Arrays.stream(Utils.readFile("input/day11.txt").getFirst()
                        .split(" "))
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
                .toList();

        for (int i = 0; i < 25; i++) {
            System.out.println("i = " + i);
            List<Long> newStones = new ArrayList<>();
            for (long stone : stones) {
                if (stone == 0) {
                    newStones.add(1L);
                } else if (String.valueOf(stone).length() % 2 == 0) {
                    String s = String.valueOf(stone);

                    newStones.add(Long.parseLong(
                            s.substring(0, s.length() / 2)
                    ));
                    newStones.add(Long.parseLong(
                            s.substring(s.length() / 2)
                    ));
                } else {
                    newStones.add(stone * 2024);
                }
            }
            stones = newStones;
        }
        System.out.println("stones.size() = " + stones.size());
    }
}
