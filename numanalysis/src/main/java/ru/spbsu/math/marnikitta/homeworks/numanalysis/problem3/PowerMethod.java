package ru.spbsu.math.marnikitta.homeworks.numanalysis.problem3;

import org.jooq.lambda.tuple.Tuple2;
import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public final class PowerMethod {
  private final double eps;

  public PowerMethod(double eps) {
    this.eps = eps;
  }

  public Tuple2<Matrix, Double> maxEigenvalue(Matrix X) {
    Matrix yNew = Matrix.vectorOnes(X.width()).multiply(10);

    double lambdaOld;
    double lambdaNew = 1;

    int iter = 1;

    do {
      final Matrix yOld = yNew.infNormalize();
      iter += 1;
      lambdaOld = lambdaNew;

      yNew = X.dot(yOld);
      lambdaNew = yNew.get(1, 0) / yOld.get(1, 0);
    } while (Math.abs(lambdaNew - lambdaOld) > this.eps);

    System.out.println(">> PowerEigenvalue: iterations: " + iter);
    return new Tuple2<>(yNew, lambdaNew);
  }
}
