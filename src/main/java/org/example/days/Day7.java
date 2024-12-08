package org.example.days;

import org.example.utils.FileUtils;

import java.util.Arrays;
import java.util.List;

public class Day7 {
    public static void main(String[] args) {
        solve(false); // Part 1
        solve(true);  // Part 2
    }

    private static void solve(boolean allowConcat) {
        List<String> input = FileUtils.readFile("input/day7.txt");
        long total = input.stream()
                .mapToLong(line -> processLine(line, allowConcat))
                .sum();
        System.out.println("Total = " + total);
    }

    private static long processLine(String line, boolean allowConcat) {
        String[] split = line.split(":");
        long expectedAns = Long.parseLong(split[0]);

        List<Long> numbers = Arrays.stream(split[1].split(" "))
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
                .toList();

        return recursiveCalculation(numbers.get(0), expectedAns, 1, numbers, allowConcat) ? expectedAns : 0;
    }

    private static boolean recursiveCalculation(long current, long target, int idx, List<Long> numbers, boolean allowConcat) {
        if (idx >= numbers.size()) {
            return current == target;
        }

        long next = numbers.get(idx);
        boolean result = recursiveCalculation(current + next, target, idx + 1, numbers, allowConcat)
                || recursiveCalculation(current * next, target, idx + 1, numbers, allowConcat);

        if (allowConcat) {
            long concatenated = Long.parseLong(current + String.valueOf(next));
            result = result || recursiveCalculation(concatenated, target, idx + 1, numbers, allowConcat);
        }

        return result;
    }
}
