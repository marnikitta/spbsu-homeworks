package ru.spbsu.math.marnikitta.homeworks.sakod.exam;

import java.io.*;
import java.util.Arrays;

public class ProblemA {
    public static void main(String[] args) throws IOException {
        new ProblemA().run();
    }

    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        boolean result = Arrays.stream(str.split("\\s")).allMatch(s -> {
            if (!s.chars().anyMatch(c -> c == 'a')) {
                return s.chars().anyMatch(c -> c == 'b' || c == 'c');
            }
            return true;
        });

        System.out.println(result);
    }
}
