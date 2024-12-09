package org.example.days;

import org.example.utils.Utils;

import java.util.Arrays;
import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        part2();
    }

    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1},
    };

    private static final int[][] DIAGONALS = {
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1},
    };

    private static final String[] TARGET = {"X", "M", "A", "S"};
    private static int ans1 = 0;
    private static int ans2 = 0;

    private static void part1() {
        List<List<String>> input = Utils.readFile("input/day4.txt").stream().map(
                s -> Arrays.stream(s.split("")).toList()
        ).toList();

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.getFirst().size(); j++) {
                for (int[] direction : DIRECTIONS) {
                    dfs(input, i, j, direction, 0);
                }
            }
        }

        System.out.println(ans1);
    }

    // 2025 too high
    private static void part2() {
        List<List<String>> input = Utils.readFile("input/day4.txt").stream().map(
                s -> Arrays.stream(s.split("")).toList()
        ).toList();

        for (int i = 1; i < input.size() - 1; i++) {
            for (int j = 1; j < input.getFirst().size() - 1; j++) {
                if (input.get(i).get(j).equals("A")) {
                    int countM = 0;
                    int countS = 0;

                    for (int[] diagonal : DIAGONALS) {
                        int newI = diagonal[0] + i;
                        int newJ = diagonal[1] + j;
                        if (input.get(newI).get(newJ).equals("M")) {
                            countM++;
                        }
                        if (input.get(newI).get(newJ).equals("S")) {
                            countS++;
                        }
                    }
                    if (countM == countS && countM == 2 && !input.get(i - 1).get(j - 1).equals(input.get(i + 1).get(j + 1))) {
                        ans2++;
                    }
                }
            }
        }

        System.out.println("ans2 = " + ans2);
    }

    private static void dfs(List<List<String>> input, int i, int j, int[] direction, int depth) {
        if (depth == TARGET.length - 1) {
            if (TARGET[depth].equals(input.get(i).get(j))) {
                ans1++;
            }
            return;
        }

        if (TARGET[depth].equals(input.get(i).get(j))) {
            int newI = i + direction[0];
            int newJ = j + direction[1];

            if (isSafeDirection(input, newI, newJ)) {
                dfs(input, newI, newJ, direction, depth + 1);
            }
        }
    }

    private static boolean isSafeDirection(List<List<String>> input, int newI, int newJ) {
        return (newI >= 0 && newI < input.size() && (newJ >= 0 && newJ < input.getFirst().size()));
    }

}
