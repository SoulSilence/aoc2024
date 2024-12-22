package org.example.days;

import org.example.utils.Utils;

import java.util.LinkedList;
import java.util.List;

public class Day9 {
    public static void main(String[] args) {
        part2();
    }

    static class Block {
        int id;
        int pos;
        int size;

        @Override
        public String toString() {
            return "Block{" +
                    "id=" + id +
                    ", pos=" + pos +
                    ", size=" + size +
                    '}';
        }

        public Block(int pos, int size) {
            this.pos = pos;
            this.size = size;
        }
    }

    // 8632330985597 too high
    static void part2() {
        int[] input = Utils.readFile("input/day9.txt").getFirst().chars()
                .map(x -> x - '0')
                .toArray();

        int realIdx = 0;

        List<Block> files = new LinkedList<>();
        List<Block> spaces = new LinkedList<>();

        for (int i = 0; i < input.length; i++) {
            Block block = new Block(realIdx, input[i]);
            if (i % 2 == 0) {
                block.id = i / 2;
                files.add(block);
            } else {
                spaces.add(block);
            }
            realIdx += block.size;
        }

        for (int i = files.size() - 1; i >= 0; i--) {
            Block file = files.get(i);
            for (Block space : spaces) {
                if (space.pos > file.pos) {
                    break;
                }
                if (space.size >= file.size) {
                    file.pos = space.pos;
                    space.pos = space.pos + file.size;
                    space.size -= file.size;
                    break;
                }
            }
        }

        long ans = 0;

        for (Block file : files) {
            for (int i = 0; i < file.size; i++) {
                ans += (long) (file.pos + i) * file.id;
            }
        }

        System.out.println("ans = " + ans);
    }

    static void part1() {
        int[] input = Utils.readFile("input/day9.txt").getFirst().chars()
                .map(x -> x - '0')
                .toArray();

        int rId = input.length / 2;
        int lId = 0;

        System.out.println("rId = " + rId);

        long ans = 0;
        int finalIdx = 0;

        for (int i = 0, r = input.length - 2 + (input.length % 2); i < input.length; i++) {
            int blockSize = input[i];

            if (i % 2 == 0) {
                for (int j = 0; j < blockSize; j++) {
                    System.out.print(lId);
                    ans += (long) finalIdx * lId;
                    finalIdx++;
                }
                lId++;
            } else {
                for (int j = 0; j < blockSize; j++) {
                    if (lId >= rId) {
                        break;
                    }

                    if (input[r] != 0) {
                        System.out.print(rId);
                        ans += (long) finalIdx * rId;
                        finalIdx++;
                        input[r] -= 1;
                    } else {
                        r -= 2;
                        rId--;
                        j -= 1;
                    }
                }
            }
        }

        System.out.println();
        System.out.println("ans = " + ans);
    }
}
