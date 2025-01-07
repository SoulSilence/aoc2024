package org.example.days;

import org.example.utils.Utils;

import java.util.*;

public class Day12 {
    public static void main(String[] args) {
        new Day12().part2();
    }

    private char[][] matrix;
    private int[][] markedMatrix;
    private final Map<Integer, Integer> area = new HashMap<>();
    private final Map<Integer, Integer> perimeter = new HashMap<>();
    private final Map<Integer, Integer> sides = new HashMap<>();
    private int regionCounter = 1;

    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    private static final double[][] DIAGONALS = {
            {0.5, -0.5}, {0.5, 0.5}, {-0.5, 0.5}, {-0.5, 0.5}
    };

    // 823953 too low
    private void part2() {
        matrix = Utils.readFileCharMatrix("input/day12_test.txt");
        markedMatrix = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (markedMatrix[i][j] == 0) {
                    markRegion(i, j, regionCounter++);
                }
            }
        }

        for (int i = 1; i < regionCounter; i++) {
            area.put(i, 0);
            sides.put(i, 0);
        }

        calculateAreaAndSides();

        Utils.outputMatrix(markedMatrix);

        int answer = area.keySet().stream()
                .mapToInt(region -> area.get(region) * sides.get(region))
                .sum();

        System.out.println("sides = " + sides);
        System.out.println("Answer = " + answer);
    }

    private void calculateAreaAndSides() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int regionId = markedMatrix[i][j];
                if (regionId == 0) continue; // Skip unmarked cells

                area.merge(regionId, 1, Integer::sum);

                // Check all four edges of the current cell
                for (int[] direction : DIRECTIONS) {
                    int ni = i + direction[0];
                    int nj = j + direction[1];



                }
            }
        }
    }


    private void part1() {
        matrix = Utils.readFileCharMatrix("input/day12.txt");
        markedMatrix = new int[matrix.length][matrix[0].length];

        // Mark all regions
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (markedMatrix[i][j] == 0) {
                    markRegion(i, j, regionCounter++);
                }
            }
        }

        // Initialize area and perimeter maps
        for (int i = 1; i < regionCounter; i++) {
            area.put(i, 0);
            perimeter.put(i, 0);
        }

        calculateAreaAndPerimeter();

        Utils.outputMatrix(markedMatrix);

        // Compute the final answer
        int answer = area.keySet().stream()
                .mapToInt(region -> area.get(region) * perimeter.get(region))
                .sum();

        System.out.println("Answer = " + answer);
    }

    private void markRegion(int i, int j, int regionId) {
        if (!isValidCell(i, j) || markedMatrix[i][j] != 0) {
            return;
        }

        markedMatrix[i][j] = regionId;

        for (int[] dir : DIRECTIONS) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            if (isValidCell(ni, nj) && markedMatrix[ni][nj] == 0 && matrix[ni][nj] == matrix[i][j]) {
                markRegion(ni, nj, regionId);
            }
        }
    }

    private void calculateAreaAndPerimeter() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int regionId = markedMatrix[i][j];
                area.merge(regionId, 1, Integer::sum);

                int localPerimeter = calculateCellPerimeter(i, j);
                perimeter.merge(regionId, localPerimeter, Integer::sum);
            }
        }
    }

    private int calculateCellPerimeter(int i, int j) {
        int perimeter = 0;
        for (int[] dir : DIRECTIONS) {
            int ni = i + dir[0], nj = j + dir[1];
            if (!isValidCell(ni, nj) || matrix[ni][nj] != matrix[i][j]) {
                perimeter++;
            }
        }
        return perimeter;
    }

    private boolean isValidCell(int i, int j) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;
    }

}
