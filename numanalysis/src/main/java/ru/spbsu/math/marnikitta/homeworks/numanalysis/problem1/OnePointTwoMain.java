package ru.spbsu.math.marnikitta.homeworks.numanalysis.problem1;

import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

@SuppressWarnings({"UseOfSystemOutOrSystemErr", "StandardVariableNames"})
public final class OnePointTwoMain {

  public static void main(String... args) {
    new OnePointTwoMain().run();
  }

  public void run() {
    final Matrix A = new Matrix(new double[][]{
            {6.5176E-06, -8.0648E-03, 4.23528},
            {5.9176E-03, -0.80648, 1.46528},
            {0.87176, 0.79352, 0.91528}});

    final Matrix b = new Matrix(new double[][]{{3.61628}, {1.52097}, {1.81150}});

    final GaussSolver solver = new GaussSolver(1.0e-6);

    System.out.println("## Матрица A:");
    System.out.println(A.niceToString());
    System.out.println();

    System.out.println("## Вектор B:");
    System.out.println(b.niceToString());
    System.out.println();

    System.out.println("# Решение системы обычным методом Гаусса:");
    System.out.println();
    final Matrix gaussSolution = solver.solveSimpleGauss(A, b);
    System.out.println();
    System.out.println("## Решение:");
    System.out.println(gaussSolution.niceToString());
    System.out.println();
    System.out.println("## l1 норма невзки:");
    System.out.println(A.dot(gaussSolution).subtract(b).vectorInfNorm());
    System.out.println();


    System.out.println("# Решение системы методом Гаусса с поколоночным выбором наибольшего элемента:");
    System.out.println();
    final Matrix columnGauss = solver.solveColumnwiseGauss(A, b);
    System.out.println();
    System.out.println("## Решение:");
    System.out.println(columnGauss.niceToString());
    System.out.println();
    System.out.println("## l1 норма невзки:");
    System.out.println(A.dot(columnGauss).subtract(b).vectorInfNorm());
    System.out.println();

    System.out.println("# Определитель матрицы A:");
    System.out.println();
    System.out.println(A.det());
    System.out.println();

    System.out.println("# Обратная матрица методом Гаусса с выбором наибольшего по модулю элемента в строке:");
    System.out.println();
    final Matrix inv = solver.inverseColumnwiseGauss(A);
    System.out.println();
    System.out.println(inv.niceToString());
    System.out.println();

    System.out.println("# Решение системы с использованием обратной матрицы:");
    System.out.println();
    final Matrix invSolution = inv.dot(b);
    System.out.println("## Решение:");
    System.out.println(invSolution.niceToString());
    System.out.println();
    System.out.println("## l1 норма невзки:");
    System.out.println(A.dot(invSolution).subtract(b).vectorInfNorm());
    System.out.println();

    System.out.println("# Число обусловленности:");
    System.out.println();
    System.out.println(inv.matrixInfNorm() * A.matrixInfNorm());

  }
}
