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

    static class Coordinate {
        public int i, j;

        public Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    private void part1() {
        matrix = Utils.readFileCharMatrix("input/day12.txt");
        markMatrix();

        calculateArea();
        calculatePerimeter();

        Utils.outputMatrix(markedMatrix);

        // Compute the final answer
        int answer = area.keySet().stream()
                .mapToInt(region -> area.get(region) * perimeter.get(region))
                .sum();

        System.out.println("Answer = " + answer);
    }

    private void part2() {
        matrix = Utils.readFileCharMatrix("input/day12.txt");
        markMatrix();
        calculateArea();
        calculateSides();

        Utils.outputMatrix(markedMatrix);

        int answer = area.keySet().stream()
                .mapToInt(region -> area.get(region) * sides.get(region))
                .sum();

        System.out.println("sides = " + sides);
        System.out.println("Answer = " + answer);
    }

    private void calculateSides() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int regionId = markedMatrix[i][j];
                int cornersCount = 0;

                // ---OUTSIDE CORNERS---
                // top left corner
                if ((!isValidCell(i - 1, j) || markedMatrix[i - 1][j] != regionId) &&
                    (!isValidCell(i, j - 1) || markedMatrix[i][j - 1] != regionId)) {
                    logCorner(i, j, regionId, "top left");
                    cornersCount++;
                }

                // top right corner
                if ((!isValidCell(i - 1, j) || markedMatrix[i - 1][j] != regionId) &&
                    (!isValidCell(i, j + 1) || markedMatrix[i][j + 1] != regionId)) {
                    logCorner(i, j, regionId, "top right");
                    cornersCount++;
                }

                // bottom left corner
                if ((!isValidCell(i + 1, j) || markedMatrix[i + 1][j] != regionId) &&
                    (!isValidCell(i, j - 1) || markedMatrix[i][j - 1] != regionId)) {
                    logCorner(i, j, regionId, "bottom left");
                    cornersCount++;
                }

                // bottom right corner
                if ((!isValidCell(i + 1, j) || markedMatrix[i + 1][j] != regionId) &&
                    (!isValidCell(i, j + 1) || markedMatrix[i][j + 1] != regionId)) {
                    logCorner(i, j, regionId, "bottom right");
                    cornersCount++;
                }
                // ---END OUTSIDE CORNERS---

                // ---INSIDE CORNERS---
                // top left inside corner
                if ((i < markedMatrix.length - 1 && j < markedMatrix[0].length - 1) &&
                        markedMatrix[i + 1][j + 1] != regionId &&
                        markedMatrix[i + 1][j] == regionId &&
                        markedMatrix[i][j + 1] == regionId) {
                    logCorner(i, j, regionId, "top left inside");
                    cornersCount++;
                }

                // top right inside corner
                if ((i < markedMatrix.length - 1 && j > 0) &&
                        markedMatrix[i + 1][j - 1] != regionId &&
                        markedMatrix[i + 1][j] == regionId &&
                        markedMatrix[i][j - 1] == regionId) {
                    logCorner(i, j, regionId, "top right inside");
                    cornersCount++;
                }

                // bottom left inside corner
                if ((i > 0 && j < markedMatrix[0].length - 1) &&
                        markedMatrix[i - 1][j + 1] != regionId &&
                        markedMatrix[i - 1][j] == regionId &&
                        markedMatrix[i][j + 1] == regionId) {
                    logCorner(i, j, regionId, "bottom left inside");
                    cornersCount++;
                }

                // bottom right inside corner
                if ((i > 0 && j > 0) &&
                        markedMatrix[i - 1][j - 1] != regionId &&
                        markedMatrix[i - 1][j] == regionId &&
                        markedMatrix[i][j - 1] == regionId) {
                    logCorner(i, j, regionId, "bottom right inside");
                    cornersCount++;
                }

                // ---END INSIDE CORNERS---

                sides.merge(regionId, cornersCount, Integer::sum);
            }
        }
    }

    private void logCorner(int i, int j, int regionId, String condition) {
        System.out.println("regionId = " + regionId + ", i = " + i + ", j = " + j + ", " + condition);
    }

    private void markMatrix() {
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
            perimeter.put(i, 0);
        }
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

    private void calculateArea() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int regionId = markedMatrix[i][j];
                area.merge(regionId, 1, Integer::sum);
            }
        }
    }

    private void calculatePerimeter() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int regionId = markedMatrix[i][j];
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
