package ru.spbsu.math.marnikitta.homeworks.sakod.dynam;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Dist {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        new Dist().run();
    }

    public void run() throws IOException {
        //InputStream str = System.in;
        InputStream str = new FileInputStream("in");
        Scanner sc = new Scanner(str);
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Edge>[] g = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; ++i) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int w = sc.nextInt();
            g[from].add(new Edge(to, w));
        }

        int[] dist = new int[n];

        Arrays.fill(dist, INF);

        dist[0] = 0;

        for (int i = 0; i < n; ++i) {
            for(Edge e : g[i]) {
                if (dist[i] !=  INF) {
                    dist[e.getTo()] = Math.min(dist[e.getTo()], dist[i] + e.getWeight());
                }
            }

        }

        System.out.println(dist[n - 1]);

        sc.close();
    }

    public static class Edge {
        private int to;
        private int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }
    }
}
