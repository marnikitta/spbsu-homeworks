package ru.spbsu.math.marnikitta.homeworks.numanalysis.gauss;

import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

public class OnePointTwoMain {
  public static void main(final String... args) {
    final Matrix A = new Matrix(new double[][]{
            {6.5176E-06, -8.0648E-03, 4.23528},
            {5.9176E-03, -0.80648, 1.46528},
            {0.87176, 0.79352, 0.91528}});
    final Matrix b = new Matrix(new double[][]{{3.61628}, {1.52097}, {1.81150}});

    System.out.println("Матрица A:");
    System.out.println(A.niceToString());
    System.out.println();

    System.out.println("Вектор B:");
    System.out.println(b.niceToString());
    System.out.println();

    System.out.println("Решение системы обычным методом Гаусса:");
    System.out.println();
    final Matrix gaussSolution = Solver.solveSimpleGauss(A, b);
    System.out.println();
    System.out.println(gaussSolution.niceToString());
    System.out.println();

    System.out.println("Решение системы методом Гаусса с поколоночным выбором наибольшего элемента:");
    System.out.println();
    final Matrix columnGauss = Solver.solveColumnwiseGauss(A, b);
    System.out.println();
    System.out.println(columnGauss.niceToString());
    System.out.println();

    System.out.println("Определитель матрицы A:");
    System.out.println();
    System.out.println(A.det());
    System.out.println();

    System.out.println("Обратная матрица методом Гаусса с поколоночным выбором наименьшего элемента:");
    System.out.println();
    final Matrix inv = Solver.inverseColumnwiseGauss(A);
    System.out.println();
    System.out.println(inv.niceToString());
    System.out.println();
    System.out.flush();

    System.out.println("Решение системы с испольщованием обратной матрицы:");
    System.out.println();
    System.out.println(inv.dot(b).niceToString());
    System.out.println();

    System.out.println("Число обусловленности:");
    System.out.println();
    System.out.println(inv.matrixInfNorm() * A.matrixInfNorm());
  }
}
