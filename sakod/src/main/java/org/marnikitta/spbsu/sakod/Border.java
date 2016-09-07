package org.marnikitta.spbsu.sakod;

import java.util.HashSet;
import java.util.Scanner;

public class Border {
    public static void main(String[] args) {
        new Border().run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        HashSet<Edge> set = new HashSet<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; ++i) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int x3 = sc.nextInt();
            int y3 = sc.nextInt();
            Edge e1 = new Edge(x1, y1, x2, y2);
            if (set.contains(e1)) {
                set.remove(e1);
            } else {
                set.add(e1);
            }
            Edge e2 = new Edge(x1, y1, x3, y3);
            if (set.contains(e2)) {
                set.remove(e2);
            } else {
                set.add(e2);
            }
            Edge e3 = new Edge(x2, y2, x3, y3);
            if (set.contains(e3)) {
                set.remove(e3);
            } else {
                set.add(e3);
            }
        }
        sc.close();
        System.out.println(set.size());
    }

    public static class Edge {
        private int x1, y1, x2, y2;

        public Edge(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;
            return y1 == edge.y1 && y2 == edge.y2 && x1 == edge.x1 && x2 == edge.x2 ||
                    y2 == edge.y1 && y1 == edge.y2 && x2 == edge.x1 && x1 == edge.x2;
        }

        @Override
        public int hashCode() {
            int result = x1 ^ x2;
            result = 31 * result + (y1 ^ y2);
            return result;
        }

    }
}
