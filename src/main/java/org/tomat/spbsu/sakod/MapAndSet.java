package org.tomat.spbsu.sakod;

import java.util.*;

/***
 * ������� ����� �������� � �������� ������ ������ � ������������ ������ � ������������ ����� �� ������ (��� ����� ���� �����)
 * ����� �� �����, ����� ������� ����� ������ ����� ���� ������������, ��� ����� � Dictionary ��� HashSet,
 * � ��� ���� ������������� ������������ �������� ��������� �� �������.
 * ��������, ������� � ������� (1,1) � �������� (2,4) ������ ���� ����� �������� � ������� (1,1) � �������� (0,-1).
 * ���������� ����������� ��� ����� ������.

 �������� ���������, ������� ������ ������ � ���������� ���������, ���� ����� ������ ������� �� ����������,
 � ������� ���������� ��������� ���������. ��� ����, ���������� ����������� Dictionary ��� HashSet, ��� ����� � ��� ��������.
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
            this.xCenter = xCenter;
            this.yCenter = yCenter;
            this.xCorner = xCorner;
            this.yCorner = yCorner;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Square square = (Square) o;

            if (xCenter != square.xCenter) return false;

            int dist = (xCorner - xCenter) * (xCorner - xCenter)
                    + (yCorner - yCenter) * (yCorner - yCenter);
            int distO = (square.xCorner - square.xCenter) * (square.xCorner - square.xCenter)
                    + (square.yCorner - square.yCenter) * (square.yCorner - square.yCenter);
            if (dist != distO) return false;

            int d = Math.abs(square.xCenter - square.xCorner);
            int d1 = Math.abs(square.xCenter - square.xCorner);
            int d2 = Math.abs(square.xCenter - square.yCorner);

            return d == d1 || d == d2;

        }

        /***
         * � �������� ���� ���������� ����� � ������� ���������� �� ������
         */
        @Override
        public int hashCode() {
            int result = xCenter;
            result = 31 * result + yCenter;
            int dist = (xCorner - xCenter) * (xCorner - xCenter)
                    + (yCorner - yCenter) * (yCorner - yCenter);
            result = 31 * result + dist;
            return result;
        }

        /***
         * ������� ���������� ������ �����, ���������� �� ������ � ���������� �� ���������� �� x-� �� ����� �� ������
         *
         * ���������� �� x-� � ������ �� ������ ����� ��������� �� ����� ���� ��������� ��������
         */
        public String getKey() {
            StringBuilder sb = new StringBuilder();
            sb.append(xCenter).append(',').append(yCenter)
                    .append('#')
                    .append((xCorner - xCenter) * (xCorner - xCenter)
                            + (yCorner - yCenter) * (yCorner - yCenter))
                    .append('#')
                    .append(Math.min(Math.abs(xCenter - xCorner), Math.abs(xCenter - yCorner)));
            return sb.toString();
        }
    }
}
