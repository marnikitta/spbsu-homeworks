package org.tomat.spbsu.sakod;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * �������� ������� ���������� ��������� ������� _��������_ ������� � ����� �������� �� ������������.
 * <p>
 * � ��� ������ ����:
 * <p>
 * - ��������� Shape, � ��������
 * - ����� print()
 * - �����-�� �����, ������� ���������� ������ ������ (����������� ��� ����������)
 * <p>
 * - ����� Square, ������� ��������� ��������� Shape � �������� ��  ������� ������� n �� n �� ���������
 * ����� n �������� � ������������ Square
 * <p>
 * - ����� Triang, ������� ��������� ��������� Shape � �������� ��  ������� ������������� ����������� n �� n �� ���������
 * ����� n �������� � ������������ Square
 * <p>
 * -  ��� ��������� "��������������" ������� ������ �� ������ ����� ��, �� � ����� ��������� ��� �������������� �����, ������ �� �������.
 * <p>
 * - ������� ��������� ������ ��������� ������  Shape
 * � � ����� ��������� �������:
 * <p>
 * S n
 * - �������� ����� ������� ������� n
 * T n
 * - �������� ����� ����������� ������� n
 * U k
 * - �������� ������������ ����� ����� �������� ������ ����� k
 * P - ���������� ��� ������
 * X - ��������� ������
 */

public class Decorator {
    public static void main(String[] args) {
        new Decorator().run();
    }

    public void run() {
        PrintWriter pw = new PrintWriter(System.out);
        Scanner sc = new Scanner(System.in);
        List<Shape> list = new ArrayList<>();
        while (true) {
            String command = sc.nextLine();
            if (command.charAt(0) == 'S') {
                list.add(new Square(Integer.parseInt(command.split(" ")[1])));
            } else if (command.charAt(0) == 'T') {
                list.add(new Triang(Integer.parseInt(command.split(" ")[1])));
            } else if (command.charAt(0) == 'U') {
                list.add(new UnderLiner(list.get(Integer.parseInt(command.split("\\s")[1]))));
            } else if (command.charAt(0) == 'P') {
                list.forEach(sh -> sh.print(pw));
                pw.flush();
            } else if (command.charAt(0) == 'X') {
                break;
            }
        }
    }

    public static interface Shape {
        int getWidth();

        int getHeight();

        void print(PrintWriter pw);
    }

    public static class Square implements Shape {
        private int n;

        public Square(int n) {
            this.n = n;
        }

        @Override
        public int getWidth() {
            return n;
        }

        @Override
        public int getHeight() {
            return n;
        }

        @Override
        public void print(PrintWriter pw) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i == 0 || i == n - 1) {
                        sb.append('*');
                    } else if (j == 0 || j == n - 1) {
                        sb.append('*');
                    } else {
                        sb.append(' ');
                    }
                }
                sb.append('\n');
            }
            pw.print(sb.toString());
        }
    }

    public static class Triang implements Shape {
        private int n;

        public Triang(int n) {
            this.n = n;
        }

        @Override
        public int getWidth() {
            return n;
        }

        @Override
        public int getHeight() {
            return n;
        }

        @Override
        public void print(PrintWriter pw) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (i == n - 1) {
                        sb.append('*');
                    } else if (j == 0) {
                        sb.append('*');
                    } else if (i == j) {
                        sb.append('*');
                    } else {
                        sb.append(' ');
                    }
                }
                sb.append('\n');
            }

            pw.print(sb.toString());
        }
    }

    public static class UnderLiner implements Shape {

        private Shape sh;

        public UnderLiner(Shape sh) {
            this.sh = sh;
        }

        @Override
        public int getWidth() {
            return sh.getWidth();
        }

        @Override
        public int getHeight() {
            return sh.getHeight() + 1;
        }

        @Override
        public void print(PrintWriter pw) {
            sh.print(pw);
            for (int i = 0; i < getWidth(); ++i) {
                pw.print('-');
            }
            pw.println();
        }
    }
}
