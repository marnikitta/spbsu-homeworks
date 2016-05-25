package org.tomat.spbsu.sakod;

import java.util.*;

/***
 * ������� ����� �������� � �������� ������ ������ � ������������ ������ � ������������ ����� �� ������ (��� ����� ���� �����)
 * ����� �� �����, ����� ������� ����� ������ ����� ���� ������������, ��� ����� � Dictionary ��� HashSet,
 * � ��� ���� ������������� ������������ �������� ��������� �� �������.
 * ��������, ������� � ������� (1,1) � �������� (2,4) ������ ���� ����� �������� � ������� (1,1) � �������� (0,-1).
 * ���������� ����������� ��� ����� ������.
 * <p>
 * �������� ���������, ������� ������ ������ � ���������� ���������, ���� ����� ������ ������� �� ����������,
 * � ������� ���������� ��������� ���������. ��� ����, ���������� ����������� Dictionary ��� HashSet, ��� ����� � ��� ��������.
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
         * ������� ���������� ������ �����, ���������� �� ������ � ���������� �� ���������� �� x-� �� ����� �� ������
         * <p>
         * ���������� �� x-� � ������ �� ������ ����� ��������� �� ����� ���� ��������� ��������
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
