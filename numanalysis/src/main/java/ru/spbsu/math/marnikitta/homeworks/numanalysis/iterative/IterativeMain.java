package ru.spbsu.math.marnikitta.homeworks.numanalysis.iterative;

import ru.spbsu.math.marnikitta.homeworks.numanalysis.gauss.GaussSolver;
import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public final class IterativeMain {

  public static void main(final String... args) {
    new IterativeMain().run();
  }

  public void run() {
    final Matrix A = new Matrix(new double[][]{
            {10, 0.5, 0.2},
            {0.5, 12, 0.4},
            {0.2, 0.4, 11}});

    final Matrix b = new Matrix(new double[][]{{3}, {1}, {2}});

    final GaussSolver solver = new GaussSolver(1.0e-6);

    System.out.format("## Матрица A:\n%s\n\n", A.niceToString());

    System.out.format("## Вектор B:\n%s\n\n", b.niceToString());

    final Matrix columnGauss = solver.solveColumnwiseGauss(A, b);
    System.out.format("# Решение системы методом Гаусса с поколоночным выбором наибольшего элемента:\n" +
            "## Решение:\n" +
            "%s\n\n" +
            "## l^inf норма невязки:\n" +
            "%s\n\n", columnGauss.niceToString(), A.dot(columnGauss).subtract(b).vectorInfNorm());

    final Ashizer ashizer = new Ashizer();

    final Matrix H = ashizer.ashizeH(A, b);
    final Matrix g = ashizer.ashizeB(A, b);
    System.out.printf("# Приведение системы к виду x = Hx + g\n" +
            "## Матрица H:\n" +
            "%s\n\n" +
            "## Вектор g:\n" +
            "%s\n\n", H.niceToString(), g.niceToString());

    System.out.printf("## l^inf норма матрицы H:\n" +
            "%s\n\n", H.matrixInfNorm());

    final SimpleIteration simpleIteration = new SimpleIteration(1.0e-10);
    final Matrix simpleIterationSolution = simpleIteration.solve(H, g);
    System.out.printf("# Решение методом простой итерации:\n" +
            "%s\n\n" +
            "## l^inf норма невязки:\n" +
            "%s\n\n", simpleIterationSolution.niceToString(), A.dot(simpleIterationSolution).subtract(b).vectorInfNorm());

    final SeidIterations seidIterations = new SeidIterations(1.0e-10);
    final Matrix seidSolution = seidIterations.solve(H, g);
    System.out.printf("# Решение методом Зейделя:\n" +
            "%s\n\n" +
            "## l^inf норма невязки:\n" +
            "%s\n\n", seidSolution.niceToString(), A.dot(seidSolution).subtract(b).vectorInfNorm());

    final UpperRelaxation upperRelaxation = new UpperRelaxation(1.0e-10, 0.9);
    final Matrix upperResult = upperRelaxation.solve(H, g);
    System.out.printf("# Решение методом Верхней релаксации:\n" +
            "%s\n\n" +
            "## l^inf норма невязки:\n" +
            "%s\n\n", upperResult.niceToString(), A.dot(upperResult).subtract(b).vectorInfNorm());
  }
}
