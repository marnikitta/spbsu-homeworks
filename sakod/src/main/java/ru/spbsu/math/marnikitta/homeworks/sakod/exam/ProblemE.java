package ru.spbsu.math.marnikitta.homeworks.sakod.exam;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProblemE {
    public static void main(String[] args) throws IOException {
        new ProblemE().run();
    }

    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int m = sc.nextInt();
        System.out.println(f(n, k, m));
        sc.close();
    }

    Map<Tuple, Double> set= new HashMap<>();
    public double f(int i, int j, int k) {
        if (i == 0 || j == 0 || k == 0) {
            return 1;
        }
        Tuple t = new Tuple(i, j, k);
        if (!set.containsKey(t)) {
            double result = Math.sin(f(i - 1, j, k) + f(i, j - 1, k) + f(i, j, k - 1));
            set.put(t, result);
        }
        return set.get(t);
    }

    public static class Tuple {
        private int a, b, c;

        public Tuple(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public boolean equals(Object o) {
            if (o instanceof Tuple) {
                Tuple t = (Tuple) o;
                return a == t.a && b == t.b && c == t.c;
            }
            return false;
        }
        public int hashCode() {
            return ((a * 31 + b) * 31) + c;
        }
    }
}
