package org.example.days;

import org.example.utils.FileUtils;

import java.nio.file.Files;

public class Day1 {
    public static void main(String[] args) {
        for (String s : FileUtils.readFile("input/day1.txt")) {
            System.out.println("s = " + s);
        }
    }
}
