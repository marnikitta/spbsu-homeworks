package org.tomat.spbsu.sakod;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MultiThread {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        new MultiThread().runMulti(n);
    }

    public class OddThread extends Thread {
        private Set<Long> set = new HashSet<>();
        private long[] powers;
        private int n;
        private int step = 1;
        private Waiting w;

        public OddThread(int n, int step, Waiting w) {
            this.n = n;
            this.powers = new long[n];
            this.step = step;
            this.w = w;
        }

        @Override
        public void run() {
            // Бежит либо по чет-чет + нечет -нечет, либо по нечет - чет, в зависимости от step
            for (int i = 1; i < n; i += step) {
                int start = step == 2 ? 2 : i;
                for (int j = start; j < n; j += 2) {
                    if (powers[i] == 0) {
                        powers[i] = (long) i * i * i;
                    }
                    if (powers[j] == 0) {
                        powers[j] = (long) j * j * j;
                    }

                    if (powers[i] + powers[j] < n) {
                        set.add(powers[i] + powers[j]);
                    }
                }
            }
            w.imFinish(this);
        }
    }

    long myStart;

    public void runMulti(int n) {
        myStart = System.nanoTime();
        Waiting waiting = new Waiting();

        OddThread th1 = new OddThread(n, 1, waiting);
        OddThread th2 = new OddThread(n, 2, waiting);

        th1.start();
        th2.start();
    }

    public class Waiting {
        private volatile int counter = 0;
        private int result = 0;

        public synchronized void imFinish(OddThread th) {
            result += th.set.size();

            counter++;

            if (counter == 2) {
                System.out.println("Multi: " + result + ". Time: " + (System.nanoTime() - myStart) / 1E9);
            }
        }
    }
}
