package ru.spbsu.math.marnikitta.homeworks.numanalysis.iterative;

import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

import java.util.Arrays;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public final class UpperRelaxation {
  private final double eps;
  private final double q;

  public UpperRelaxation(final double eps, final double q) {
    this.eps = eps;
    this.q = q;
  }

  public Matrix solve(final Matrix H, final Matrix g) {
    double[] xOld = new double[H.width()];
    double[] xNew = new double[H.width()];

    for (int i = 0; i < xNew.length; ++i) {
      xNew[i] = 1;
    }

    int iter = 0;

    do {
      final double[] tmp = xOld;
      xOld = xNew;
      xNew = tmp;

      for (int i = 0; i < xNew.length; ++i) {
        double tempI = 0;

        for (int j = 0; j < i; ++j) {
          tempI += H.get(i, j) * xNew[j];
        }

        for (int j = i; j < xNew.length; ++j) {
          tempI += H.get(i, j) * xOld[j];
        }

        xNew[i] =  xOld[i] + this.q * (tempI - xOld[i] + g.get(i, 0));
      }
      iter += 1;
    } while (UpperRelaxation.vectorInf(xNew, xOld) > this.eps);

    System.out.println(">> UpperRelaxation: iterations: " + iter);

    return new Matrix(xNew);
  }

  private static double vectorInf(final double[] x1, final double[] x2) {
    double result = -1;
    for (int i = 0; i < x1.length; ++i) {
      result = Math.max(Math.abs(x1[i] - x2[i]), result);
    }
    return result;
  }
}
