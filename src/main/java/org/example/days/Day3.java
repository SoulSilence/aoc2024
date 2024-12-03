package org.example.days;

import org.example.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static final String DO = "do()";
    public static final String DON_T = "don't()";
    private static final Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)");

    public static void main(String[] args) {
        part2();
    }

    private static void part2() {
        String input = FileUtils.readFile("input/day3.txt").getFirst();

        long ans = 0;
        boolean doMul = true;

        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String group = matcher.group();
            if (DO.equals(group)) {
                doMul = true;
            } else if (DON_T.equals(group)) {
                doMul = false;
            } else {
                if (!doMul) {
                    continue;
                }

                ans += Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2));
            }
        }

        System.out.println("ans = " + ans);
    }


    private static void part1() {
        List<String> input = FileUtils.readFile("input/day3.txt");
        long ans = 0;
        for (String s : input) {
            Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
            Matcher matcher = pattern.matcher(s);

            while (matcher.find()) {
                ans += Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2));
            }
        }
        System.out.println("ans = " + ans);
    }
}
