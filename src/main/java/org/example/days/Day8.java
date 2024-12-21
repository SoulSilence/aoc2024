package org.example.days;

import org.example.utils.Utils;

import java.util.*;

public class Day8 {
    public static void main(String[] args) {
        part2();
    }

    static char[][] matrix;
    static Set<List<Integer>> ans = new HashSet<>();

    // 901 too low
    private static void part2() {
        matrix = Utils.readFileCharMatrix("input/day8.txt");

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

        for (List<List<Integer>> lists : antennas.values()) {
            for (int i = 0; i < lists.size(); i++) {
                for (int j = i + 1; j < lists.size(); j++) {
                    List<Integer> first = lists.get(i);
                    List<Integer> second = lists.get(j);
                    List<Integer> diff = List.of(second.get(0) - first.get(0), second.get(1) - first.get(1));

                    int diffI = diff.get(0);
                    int diffJ = diff.get(1);

                    ans.add(new ArrayList<>(List.of(first.get(0), first.get(1))));
                    ans.add(new ArrayList<>(List.of(second.get(0), second.get(1))));
                    registerAntinode(first.get(0) - diffI, first.get(1) - diffJ, -diffI, -diffJ);
                    registerAntinode(second.get(0) + diffI, second.get(1) + diffJ, diffI, diffJ);
                }
            }
        }

        Utils.outputMatrix(matrix);
        System.out.println("ans = " + ans.size());
    }

    private static void registerAntinode(int i, int j, int diffI, int diffJ) {
        while (diffI % 2 == 0 && diffJ % 2 == 0) {
            diffI /= 2;
            diffJ /= 2;
        }

        while (inBounds(i, j, matrix)) {
            ans.add(List.of(i, j));
            if (matrix[i][j] == '.') {
                matrix[i][j] = '#';
            }
            i += diffI;
            j += diffJ;
        }
    }

    private static void part1() {
        matrix = Utils.readFileCharMatrix("input/day8.txt");

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

        for (List<List<Integer>> lists : antennas.values()) {
            for (int i = 0; i < lists.size(); i++) {
                for (int j = i + 1; j < lists.size(); j++) {
                    List<Integer> first = lists.get(i);
                    List<Integer> second = lists.get(j);
                    List<Integer> diff = List.of(second.get(0) - first.get(0), second.get(1) - first.get(1));

                    List<Integer> firstAnti = List.of(first.get(0) - diff.get(0), first.get(1) - diff.get(1));
                    List<Integer> secondAnti = List.of(second.get(0) + diff.get(0), second.get(1) + diff.get(1));

                    if (inBounds(firstAnti.get(0), firstAnti.get(1), matrix)) {
                        ans.add(List.of(firstAnti.get(0), firstAnti.get(1)));
                        matrix[firstAnti.get(0)][firstAnti.get(1)] = '#';
                    }
                    if (inBounds(secondAnti.get(0), secondAnti.get(1), matrix)) {
                        ans.add(List.of(secondAnti.get(0), secondAnti.get(1)));
                        matrix[secondAnti.get(0)][secondAnti.get(1)] = '#';
                    }
                }
            }
        }

        Utils.outputMatrix(matrix);
        System.out.println("ans = " + ans.size());
    }

    private static boolean inBounds(int i, int j, char[][] matrix) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;
    }
}
