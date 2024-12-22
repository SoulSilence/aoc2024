package org.example.days;

import org.example.utils.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day10 {
    public static void main(String[] args) {
        part2();
    }

    private static Set<List<Integer>> nines = new HashSet<>();

    private static void part1() {
        char[][] matrix = Utils.readFileCharMatrix("input/day10.txt");

        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '0') {
                    dfs(i, j, matrix);
                    ans += nines.size();
                    nines.clear();
                }
            }
        }
        System.out.println(ans);
    }

    private static void part2() {
        char[][] matrix = Utils.readFileCharMatrix("input/day10.txt");

        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '0') {
                    ans += dfs(i, j, matrix);
                    nines.clear();
                }
            }
        }
        System.out.println(ans);
    }


    private static int dfs(int i, int j, char[][] matrix) {
        if (matrix[i][j] == '9') {
            nines.add(List.of(i, j));
            return 1;
        }

        int ans = 0;

        if (inBounds(i - 1, j, matrix)) {
            if (matrix[i - 1][j] - matrix[i][j] == 1) {
                ans += dfs(i - 1, j, matrix);
            }
        }

        if (inBounds(i + 1, j, matrix)) {
            if (matrix[i + 1][j] - matrix[i][j] == 1) {
                ans += dfs(i + 1, j, matrix);
            }
        }
        if (inBounds(i, j - 1, matrix)) {
            if (matrix[i][j - 1] - matrix[i][j] == 1) {
                ans += dfs(i, j - 1, matrix);
            }
        }
        if (inBounds(i, j + 1, matrix)) {
            if (matrix[i][j + 1] - matrix[i][j] == 1) {
                ans += dfs(i, j + 1, matrix);
            }
        }
        return ans;
    }

    private static boolean inBounds(int i, int j, char[][] matrix) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[i].length;
    }

}
