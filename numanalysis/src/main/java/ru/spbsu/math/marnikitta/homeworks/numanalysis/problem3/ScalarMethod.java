package ru.spbsu.math.marnikitta.homeworks.numanalysis.problem3;

import org.jooq.lambda.tuple.Tuple2;
import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

@SuppressWarnings("ALL")
public final class ScalarMethod {
  private final double eps;

  public ScalarMethod(final double eps) {
    this.eps = eps;
  }

  public Tuple2<Matrix, Double> maxEigenvalue(final Matrix X) {
    Matrix yNew = Matrix.vectorOnes(X.width()).multiply(1);

    double lambdaOld;
    double lambdaNew = 100;

    int iter = 1;

    do {
      final Matrix yOld = yNew.infNormalize();
      iter += 1;
      lambdaOld = lambdaNew;

      yNew = X.dot(yOld);

      lambdaNew = yNew.transposed().dot(yOld).get(0, 0)  / yOld.transposed().dot(yOld).get(0, 0);
    } while (Math.abs(lambdaNew - lambdaOld) > this.eps);

    System.out.println(">> ScalarEigenvalue: iterations: " + iter);
    return new Tuple2<>(yNew, lambdaNew);
  }
}
