package org.example.days;

import org.example.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    public static final String DO = "do()";
    public static final String DON_T = "don't()";

    public static void main(String[] args) {
        part2();
    }

    private static void part2() {
        String input = FileUtils.readFile("input/day3.txt").getFirst();
        boolean doMul = true;
        long ans = 0;

        Pattern pattern = Pattern.compile("^mul\\(\\d{1,3},\\d{1,3}\\)");
        Pattern numerPattern = Pattern.compile("\\d{1,3}");

        for (int i = 0; i < input.length(); i++) {
            if (DO.equals(input.substring(i, Math.min(i + DO.length(), input.length())))) {
                doMul = true;
                continue;
            }
            if (DON_T.equals(input.substring(i, Math.min(i + DON_T.length(), input.length())))) {
                doMul = false;
                System.out.println("do false");
                continue;
            }

            if (!doMul) {
                continue;
            }

            Matcher matcher = pattern.matcher(input.substring(i));
            if (matcher.find()) {
                String mulExp = matcher.group();
                Matcher matcher1 = numerPattern.matcher(mulExp);
                List<Long> numbers = new ArrayList<>();
                while (matcher1.find()) {
                    numbers.add(Long.parseLong(matcher1.group()));
                }
                ans += numbers.get(0) * numbers.get(1);
                i += mulExp.length() - 1;
            }
        }
        System.out.println("ans = " + ans);
    }

    private static void part1() {
        List<String> input = FileUtils.readFile("input/day3.txt");
        long ans = 0;
        for (String s : input) {
            Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
            Pattern numerPattern = Pattern.compile("\\d{1,3}");
            Matcher matcher = pattern.matcher(s);

            while (matcher.find()) {
                String mulExp = matcher.group();
                Matcher matcher1 = numerPattern.matcher(mulExp);
                List<Long> numbers = new ArrayList<>();
                while (matcher1.find()) {
                    numbers.add(Long.parseLong(matcher1.group()));
                }
                ans += numbers.get(0) * numbers.get(1);
            }
        }
        System.out.println("ans = " + ans);
    }
}
