package org.example.days;

import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 {
    public static void main(String[] args) {
        new Day13().part1();
    }

    public void part1() {
        List<String> input = Utils.readFile("input/day13.txt");
        long ans = 0;

        for (int i = 0; i < input.size(); i += 4) {
            List<Long> ALine = parseLine(input.get(i));
            List<Long> BLine = parseLine(input.get(i+1));
            List<Long> Prize = parseLine(input.get(i+2));

            ans += playGame(ALine, BLine, Prize);
        }

        System.out.println("ans = " + ans);
    }

    public void part2() {
        List<String> input = Utils.readFile("input/day13.txt");
        long ans = 0;

        for (int i = 0; i < input.size(); i += 4) {
            System.out.println("i = " + i);
            List<Long> ALine = parseLine(input.get(i));
            List<Long> BLine = parseLine(input.get(i+1));
            List<Long> Prize = parseLine(input.get(i+2));

            Prize.set(0, Prize.get(0) + 10000000000000L);
            Prize.set(1, Prize.get(1) + 10000000000000L);

            ans += playGame(ALine, BLine, Prize);
            System.out.println("ans = " + ans);
        }

        System.out.println("ans = " + ans);
    }

    private long playGame(List<Long> ALine, List<Long> BLine, List<Long> Prize) {
        long AX = ALine.get(0);
        long AY = ALine.get(1);
        long BX = BLine.get(0);
        long BY = BLine.get(1);
        long PX = Prize.get(0);
        long PY = Prize.get(1);

        long result = Long.MAX_VALUE;
        for (long i = 0; i < 101; i++) {
            for (long j = 0; j < 101; j++) {
                if (i * AX + j * BX == PX && i * AY + j * BY == PY) {
                    result = Math.min(result, 3 * i + j);
                }
            }
        }

        if (result == Long.MAX_VALUE) {
            return 0;
        } else {
            return result;
        }
    }

    private List<Long> parseLine(String line) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(line);
        List<Long> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(Long.parseLong(matcher.group()));
        }
        return list;
    }
}
