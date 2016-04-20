package org.tomat.spbsu.sakod.exam;

import java.io.*;

public class ProblemD {
    public static void main(String[] args) throws IOException {
        new ProblemD().run();
    }

    public void run() throws IOException {
    }

    public static interface Figure {
        public double getArea();

        public double getPerim();
    }

    public static class Square implements Figure {

        private double side;

        public Square(double side) {
            this.side = side;
        }

        @Override
        public double getArea() {
            return side * side;
        }

        @Override
        public double getPerim() {
            return 4 * side;
        }

    }

    public static class PentagonHoleDecorator implements Figure {

        private double side;
        private Figure figure;

        public PentagonHoleDecorator(Figure figure, double side) {
            this.figure = figure;
            this.side = side;
        }

        @Override
        public double getArea() {
            double myArea = Math.sqrt(25 + 10 * Math.sqrt(5)) / 4 * side * side;
            return figure.getArea() - myArea;
        }

        @Override
        public double getPerim() {
            return figure.getPerim() + 5 * side;
        }
    }
}
