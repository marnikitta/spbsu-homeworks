package ru.spbsu.math.marnikitta.homeworks.numanalysis.problem3;

import org.jooq.lambda.tuple.Tuple2;
import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

@SuppressWarnings({"OverlyComplexBooleanExpression", "UseOfSystemOutOrSystemErr"})
public final class Jacobi {
  private final double eps;

  public Jacobi(double eps) {
    this.eps = eps;
  }

  public Tuple2<Matrix, Matrix> eigen(Matrix A) {
    Matrix Ak = A;
    Matrix X = Matrix.ones(A.width());

    double maxUpper = -1;

    int ik = -1;
    int jk = -1;
    for (int i = 0; i < Ak.height(); ++i) {
      for (int j = i + 1; j < Ak.width(); ++j) {
        if (Math.abs(Ak.get(i, j)) > maxUpper) {
          maxUpper = Math.abs(Ak.get(i, j));
          ik = i;
          jk = j;
        }
      }
    }


    int iter = 0;
    while (maxUpper > this.eps) {
      final Matrix Vk = Jacobi.Vk(Ak, ik, jk);
      Ak = Vk.transposed().dot(Ak).dot(Vk);
      X = X.dot(Vk);
      iter += 1;

      maxUpper = -1;
      ik = -1;
      jk = -1;
      for (int i = 0; i < Ak.height(); ++i) {
        for (int j = i + 1; j < Ak.width(); ++j) {
          if (Math.abs(Ak.get(i, j)) > maxUpper) {
            maxUpper = Math.abs(Ak.get(i, j));
            ik = i;
            jk = j;
          }
        }
      }
    }

    System.out.println(">> Jacobi iterations: " + iter);

    return new Tuple2<>(Ak, X);
  }

  private static Matrix Vk(Matrix a, int ik, int jk) {
    final double d = Math.sqrt(StrictMath.pow(a.get(ik, ik) - a.get(jk, jk), 2) + 4 * a.get(ik, jk) * a.get(ik, jk));
    final double c = Math.sqrt(0.5 * (1 + Math.abs(a.get(ik, ik) - a.get(jk, jk)) / d));
    final double s = Math.signum(a.get(ik, jk) * (a.get(ik, ik) - a.get(jk, jk)))
            * Math.sqrt(0.5 * (1 - Math.abs(a.get(ik, ik) - a.get(jk, jk)) / d));


    final double[][] V = new double[a.height()][a.width()];

    for (int i = 0; i < V.length; ++i) {
      V[i][i] = 1;
    }

    V[ik][jk] = -s;
    V[jk][ik] = s;
    V[ik][ik] = c;
    V[jk][jk] = c;

    return new Matrix(V);
  }
}
