package org.example.days;

import org.example.utils.FileUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    public static void main(String[] args) {
        part2();
    }

    private static void part2() {
        String input = FileUtils.readFile("input/day3.txt").getFirst();
        long ans = 0;
        boolean doMul = true;
        Matcher matcher = Pattern
                .compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)")
                .matcher(input);

        while (matcher.find()) {
            String group = matcher.group();
            if ("do()".equals(group)) {
                doMul = true;
            } else if ("don't()".equals(group)) {
                doMul = false;
            } else {
                if (doMul) {
                    ans += Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2));
                }
            }
        }
        System.out.println("ans = " + ans);
    }

    private static void part1() {
        String s = FileUtils.readFile("input/day3.txt").getFirst();
        long ans = 0;
        Matcher matcher = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)").matcher(s);
        while (matcher.find()) {
            ans += Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2));
        }
        System.out.println("ans = " + ans);
    }
}
