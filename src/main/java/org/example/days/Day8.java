package org.example.days;

import org.example.utils.Utils;

import java.util.*;

public class Day8 {
    public static void main(String[] args) {
        part1();
    }

    // 209 is too low
    private static void part1() {
        char[][] matrix = Utils.readFileCharMatrix("input/day8_test.txt");

        Utils.outputMatrix(matrix);
        Map<Character, List<List<Integer>>> antennas = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != '.') {
                    antennas.computeIfAbsent(matrix[i][j], _ -> new ArrayList<>()).add(
                            List.of(i, j)
                    );
                }
            }
        }

        Set<List<Integer>> ans = new HashSet<>();

        for (List<List<Integer>> lists : antennas.values()) {
            for (int i = 0; i < lists.size(); i++) {
                for (int j = 0; j < lists.size(); j++) {
                    if (i == j) {
                        continue;
                    }

                    List<Integer> first = lists.get(i);
                    List<Integer> second = lists.get(j);
                    List<Integer> diff = List.of(first.get(0) - second.get(0), first.get(1) - second.get(1));

                    List<Integer> firstAnti = List.of(first.get(0) - diff.get(0), first.get(1) - diff.get(1));
                    List<Integer> secondAnti = List.of(second.get(0) - diff.get(0), second.get(1) - diff.get(1));

                    if (!outOfBounds(firstAnti.get(0), firstAnti.get(1), matrix)) {
                        ans.add(List.of(firstAnti.get(0), firstAnti.get(1)));
                    }
                    if (!outOfBounds(secondAnti.get(0), secondAnti.get(1), matrix)) {
                        ans.add(List.of(secondAnti.get(0), secondAnti.get(1)));
                    }
                }
            }
        }

        Utils.outputMatrix(matrix);
        System.out.println("ans = " + ans.size());
    }

    private static boolean outOfBounds(int i, int j, char[][] matrix) {
        return i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length;
    }
}
