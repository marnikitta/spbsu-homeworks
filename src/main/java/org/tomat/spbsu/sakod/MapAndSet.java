package org.tomat.spbsu.sakod;

import java.util.*;

/***
 * Опишите класс «квадрат» с четырьмя целыми полями – координатами центра и координатами одной из вершин (она может быть любой)
 * Пусть мы хотим, чтобы обьекты этого класса можно было использовать, как ключи в Dictionary или HashSet,
 * и при этом геометрически смовпадающие квадраты считались бы равными.
 * Например, квалрат с центром (1,1) и вершиной (2,4) должен быть равен квадрату с центром (1,1) и вершиной (0,-1).
 * Определите необходимые для этого методы.
 * <p>
 * Напишите программу, которая вводит данные о нескольких квадратах, пока какой нибудь квалрат не повторится,
 * и выводит количество введенных квадратов. При этом, пожалуйста используйте Dictionary или HashSet, где ключи – это квадраты.
 */

public class MapAndSet {
    public static void main(String[] args) {
        new MapAndSet().runSet();
        new MapAndSet().runMap();
    }

    public void runSet() {
        Scanner sc = new Scanner(System.in);
        Set<Square> set = new HashSet<>();
        while (true) {
            int xCenter = sc.nextInt();
            int yCenter = sc.nextInt();
            int xCorner = sc.nextInt();
            int yCorner = sc.nextInt();

            Square sq = new Square(xCenter, yCenter, xCorner, yCorner);

            if (set.contains(sq)) {
                break;
            } else {
                set.add(sq);
            }
        }
        System.out.println("Duplicate");
        System.out.println(set.size());
    }

    public void runMap() {
        Scanner sc = new Scanner(System.in);
        Map<String, Square> map = new HashMap<>();
        while (true) {
            int xCenter = sc.nextInt();
            int yCenter = sc.nextInt();
            int xCorner = sc.nextInt();
            int yCorner = sc.nextInt();

            Square sq = new Square(xCenter, yCenter, xCorner, yCorner);

            if (map.containsKey(sq.getKey())) {
                break;
            } else {
                map.put(sq.getKey(), sq);
            }
        }
        System.out.println("Duplicate");
        System.out.println(map.size());
    }

    public static class Square {
        private int xCenter;
        private int yCenter;
        private int xCorner;
        private int yCorner;

        public Square(int xCenter, int yCenter, int xCorner, int yCorner) {
            int dX = xCorner - xCenter;
            int dY = yCorner - yCenter;

            int xTrueCorner = dX * dY >= 0 ? xCenter - Math.abs(dX) : xCenter - Math.abs(dY);
            int yTrueCorner = dX * dY < 0 ? xCenter - Math.abs(dY) : xCenter - Math.abs(dX);

            this.xCenter = xCenter;
            this.yCenter = yCenter;
            this.xCorner = xTrueCorner;
            this.yCorner = yTrueCorner;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Square square = (Square) o;

            if (xCenter != square.xCenter) return false;
            if (yCenter != square.yCenter) return false;
            if (xCorner != square.xCorner) return false;
            return yCorner == square.yCorner;
        }

        @Override
        public int hashCode() {
            int result = xCenter;
            result = 31 * result + yCenter;
            result = 31 * result + xCorner;
            result = 31 * result + yCorner;
            return result;
        }

        /***
         * Квадрат однозначно задает центр, расстояние до центра и наименьшее из расстояний по x-у до одной из вершин
         * <p>
         * Расстояние по x-у о центра до вершин может принимать не более двух различных значений
         */
        public String getKey() {
            StringBuilder sb = new StringBuilder();
            sb.append(xCenter).append(',').append(yCenter)
                    .append('#')
                    .append(xCorner).append(',').append(yCorner);
            return sb.toString();
        }
    }
}
