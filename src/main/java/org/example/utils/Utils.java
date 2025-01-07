package org.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Utils {
    public static List<String> readFile(String fileName) {
        try {
            String filePath = Objects.requireNonNull(Utils.class.getClassLoader().getResource(fileName)).getPath();
            try (Stream<String> s = Files.lines(Paths.get(filePath))) {
                return s.toList();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static char[][] readFileCharMatrix(String fileName) {
        return readFile(fileName).stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    public static void outputMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void outputMatrix(int[][] matrix) {
        for (int[] chars : matrix) {
            for (int aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
        System.out.println();
    }
}