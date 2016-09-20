package ru.spbsu.math.marnikitta.homeworks.sakod;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Напишите простую консольную программу которая _печатает_ рисунки и умеет украшать их декораторами.
 * <p>
 * В ней должен быть:
 * <p>
 * - интерфейс Shape, у которого
 * - метод print()
 * - какой-то метод, который возвращает размер фигуры (потребуется для декоратора)
 * <p>
 * - класс Square, который реализует интерфейс Shape и печатает на  консоль квадрат n на n из звездочек
 * Число n задается в конструкторе Square
 * <p>
 * - класс Triang, который реализует интерфейс Shape и печатает на  консоль прямоугольный треугольник n на n из звездочек
 * Число n задается в конструкторе Square
 * <p>
 * -  еще декоратор "подчеркиватель" который делает из фигуры такую же, но в конце проведена еще горизонтальная линия, скажем из минусов.
 * <p>
 * - главная программа должна создавать массив  Shape
 * И в цикле выполнять команды:
 * <p>
 * S n
 * - добавить новый квадрат размера n
 * T n
 * - добавить новый треугольник размера n
 * U k
 * - добавить подчеркнутую копию ранее заданной фигуры номер k
 * P - напечатать все фигуры
 * X - завершить работу
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
