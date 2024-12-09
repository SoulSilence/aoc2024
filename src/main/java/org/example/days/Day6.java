package org.example.days;

import org.example.utils.Utils;

import java.util.*;

import static java.util.Arrays.asList;

public class Day6 {
    public static void main(String[] args) throws Exception {
        part2();
    }

    private static final List<Character> GUARD_SYMBOLS = asList('^', '>', 'v', '<');
    private static final List<List<Integer>> DIRECTIONS = asList(
            asList(-1, 0), asList(0, 1), asList(1, 0), asList(0, -1)
    );

    private static void part2() throws Exception {
        char[][] matrix = Utils.readFileCharMatrix("input/day6.txt");

        long ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '.') {
                    char[][] modifiedMatrix = Utils.readFileCharMatrix("input/day6.txt");
                    modifiedMatrix[i][j] = '#';
                    try {
                        part1(modifiedMatrix, false);
                    } catch (RuntimeException e) {
                        if (e.getMessage().equalsIgnoreCase("loop")) {
                            ans++;
                        }
                    }
                }
            }
        }

        System.out.println("ans = " + ans);
    }

    private static void part1(char[][] matrix, boolean outputAnswer) throws InterruptedException {
        int guardI = 0, guardJ = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (GUARD_SYMBOLS.contains(matrix[i][j])) {
                    guardI = i;
                    guardJ = j;
                }
            }
        }

        Set<List<Integer>> ans = new HashSet<>();
        List<List<Integer>> allVisited = new ArrayList<>();
        do {
//            outputMatrix(matrix);
            ans.add(Arrays.asList(guardI, guardJ));
            allVisited.add(Arrays.asList(guardI, guardJ));

            if (allVisited.size() > ans.size() * 3) {
                throw new RuntimeException("loop");
            }

            int guardSymbolIdx = GUARD_SYMBOLS.indexOf(matrix[guardI][guardJ]);
            List<Integer> direction = DIRECTIONS.get(guardSymbolIdx);
            int newI = guardI + direction.get(0);
            int newJ = guardJ + direction.get(1);

            if (areIandJSafe(newI, newJ, matrix)) {
                if (matrix[newI][newJ] == '#') {
                    matrix[guardI][guardJ] = GUARD_SYMBOLS.get((guardSymbolIdx + 1) & GUARD_SYMBOLS.size() - 1);
                } else {
                    matrix[guardI][guardJ] = 'X';
                    guardI = newI;
                    guardJ = newJ;
                    if (areIandJSafe(guardI, guardJ, matrix)) {
                        matrix[guardI][guardJ] = GUARD_SYMBOLS.get(guardSymbolIdx);
                    }
                }
            } else {
                break;
            }
        } while (areIandJSafe(guardI, guardJ, matrix));

        if (outputAnswer) {
            System.out.println("ans.size() = " + ans.size());
        }
    }

    private static boolean areIandJSafe(int guardI, int guardJ, char[][] matrix) {
        return guardI >= 0 && guardI < matrix.length && guardJ >= 0 && guardJ < matrix[0].length;
    }
}
