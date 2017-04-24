package ru.spbsu.math.marnikitta.homeworks.numanalysis.eigenvalues;

import org.jooq.lambda.tuple.Tuple2;
import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public final class EigenMain {
  public static void main(final String... args) {
    final Matrix A = new Matrix(new double[][]{
            {-1.482129, -0.039163, 1.082538},
            {-0.039163, 1.139581, 0.016167},
            {1.082538, 0.016167, -0.482714}
    });

    System.out.printf("# Матрица A:\n%s\n\n", A.niceToString());

    final PowerMethod powerMethod = new PowerMethod(1.0e-7);
    final Tuple2<Matrix, Double> powerResult = powerMethod.maxEigenvalue(A);

    System.out.printf("# Степенной метод:\n" +
            "## Максимальное по модулю собственное число:\n" +
            "%s\n" +
            "## Соответствующий собственный вектор:\n" +
            "%s\n\n", powerResult.v2(), powerResult.v1().niceToString());

    final ScalarMethod scalarMethod = new ScalarMethod(1.0e-7);
    final Tuple2<Matrix, Double> scalarResult = scalarMethod.maxEigenvalue(A);

    System.out.printf("# Метод скалярных произведений:\n" +
            "## Максимальное по модулю собственное число:\n" +
            "%s\n" +
            "## Соответствующий собственный вектор:\n" +
            "%s\n\n", scalarResult.v2(), scalarResult.v1().niceToString());


    final double max = powerResult.v2();

    final Matrix B = A.subtract(Matrix.ones(A.width()).multiply(max));
    System.out.printf("# Матрица B = A - lambda * E:\n%s\n\n", B.niceToString());

    final Tuple2<Matrix, Double> bPowerResult = powerMethod.maxEigenvalue(B);

    System.out.printf("# Степенной метод:\n" +
            "## Противоположная граница спектра:\n" +
            "%s\n\n", powerResult.v2() + bPowerResult.v2());


    System.out.printf("# Третье собственное число:\n%s\n\n", A.trace() - powerResult.v2() - (powerResult.v2() + bPowerResult.v2()));


    final Jacobi jacobi = new Jacobi(1.0e-7);

    final Tuple2<Matrix, Matrix> jacobiResult = jacobi.eigen(A);

    System.out.printf("# Метод вращений Якоби:\n" +
            "## Матрица Ak:\n" +
            "%s\n" +
            "## Матрица Vk:\n\n" +
            "%s\n\n", jacobiResult.v1().niceToString(), jacobiResult.v2().niceToString());
  }
}
