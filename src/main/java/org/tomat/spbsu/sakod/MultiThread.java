package org.tomat.spbsu.sakod;

import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class MultiThread {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        new MultiThread().runNaive(n);

        new MultiThread().runMulti(n);
    }

    public void runNaive(int n) {
        long start = System.nanoTime();
        int[] cubes = new int[n];

        for (int i = 0; i < n; ++i) {
            cubes[i] = i * i * i;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                if (cubes[i] + cubes[j] < n) {
                    set.add(cubes[i] + cubes[j]);
                }
            }
        }
        System.out.println("Naive: " + set.size() + ". Time: " + (System.nanoTime() - start) / 1E9);
    }

    private ConcurrentHashMap<Integer, Integer> result = new ConcurrentHashMap<>();

    public class MyThread extends Thread {
        private final int start;
        private final int n;
        private Waiting waiting;

        public MyThread(int start, int n, Waiting w) {
            this.start = start;
            this.n = n;
            this.waiting = w;
        }

        @Override
        public void run() {
            for (int i = start; i < n; ++i) {
                for (int j = i; j < n; ++j) {
                    int c = i * i * i + j * j * j;
                    if (c < n) {
                        result.putIfAbsent(c, c);
                    }
                    Thread.yield();
                }
            }
            waiting.imFinish();
        }
    }

    long myStart;

    public void runMulti(int n) {
        myStart = System.nanoTime();
        Waiting waiting = new Waiting();
        MyThread half = new MyThread(1, n, waiting);
        MyThread second = new MyThread((n >> 1) - 1, n, waiting);

        half.start();
        second.start();
    }

    public class Waiting {
        private volatile int counter = 0;

        public void imFinish() {
            counter++;
            if (counter == 2) {
                System.out.println("Multi: " + result.size() + ". Time: " + (System.nanoTime() - myStart) / 1E9);
            }
        }
    }
}
