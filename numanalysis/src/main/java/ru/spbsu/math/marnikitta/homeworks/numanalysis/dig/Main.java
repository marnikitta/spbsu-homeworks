package ru.spbsu.math.marnikitta.homeworks.numanalysis.dig;

import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;

public final class Main {
  public static void main(final String... args) {
    final double alpha = 0.5;

    final DoubleUnaryOperator p = x -> -1;
    final DoubleUnaryOperator q = x -> -(x * x + alpha);
    final DoubleUnaryOperator r = x -> -2 * x;
    final DoubleUnaryOperator f = x -> 2 * (3 * x * x - alpha) / StrictMath.pow(x * x + alpha, 3);

    final DoubleUnaryOperator solution = x -> 1 / (x * x + alpha);

    final double a = 0;
    final double b = 1;

    final double alpha0 = 1;
    final double alpha1 = 1;
    final double A = 1 / alpha;

    final double beta0 = 1;
    final double beta1 = 0;
    final double B = 1.0d / (1 + alpha);

    final int n = 10;
    System.out.println("# Разностный метод для обыкновенного дифференциального уравнения второго порядка");
    System.out.println(String.format("## a = %f, b = %f, n = %d", a, b, n));
    System.out.println();

    System.out.println("### Схема первого порядка");

    final FirstEdgeSolver firstEdgeSolver = new FirstEdgeSolver(n);
    final double[] y = firstEdgeSolver.solve(p, q, r, f, a, b, alpha0, alpha1, A, beta0, beta1, B);

    final double[] realY = FirstEdgeSolver.granulate(solution, a, b, n);

    System.out.println("l2 норма вектора погрешности");
    System.out.println(new Matrix(y).subtract(new Matrix(realY)).vectorInfNorm());
    System.out.println();

    System.out.println("### Схема второго порядка");

    final SecondEdgeOrder secondEdgeOrder = new SecondEdgeOrder(n);
    final double[] y2 = secondEdgeOrder.solve(p, q, r, f, a, b, alpha0, alpha1, A, beta0, beta1, B);

    final double[] realY2 = SecondEdgeOrder.granulate(solution, a, b, n);

    System.out.println("l2 норма вектора погрешности");
    System.out.println(new Matrix(y2).subtract(new Matrix(realY2)).vectorInfNorm());
  }
}
