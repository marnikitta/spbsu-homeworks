package org.tomat.spbsu.sakod.exam;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProblemB {
    public static void main(String[] args) throws IOException {
        new ProblemB().run();
    }

    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        Map<String, String> books = new HashMap<>();
        Map<String, String> artists = new HashMap<>();

        while (flag) {
            String line = br.readLine();

            if(line.equals("*")) {
                return;
            }
            String[] entity = line.split("\\s");

            if (artists.containsKey(entity[2])) {
                System.out.println(entity[0] + " " + artists.get(entity[2]));
            } else if (books.containsKey(entity[1])) {
                System.out.println(entity[0] + " " + books.get(entity[1]));
            }
            books.put(entity[1], entity[0]);
            artists.put(entity[2], entity[0]);
        }
    }
}
