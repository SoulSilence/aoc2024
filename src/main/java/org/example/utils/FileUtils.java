package org.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FileUtils {
    public static List<String> readFile(String fileName) {
        try {
            String filePath = Objects.requireNonNull(FileUtils.class.getClassLoader().getResource(fileName)).getPath();
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
}