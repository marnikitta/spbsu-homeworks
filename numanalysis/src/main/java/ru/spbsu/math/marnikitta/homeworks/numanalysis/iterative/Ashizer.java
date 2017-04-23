package ru.spbsu.math.marnikitta.homeworks.numanalysis.iterative;

import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

public final class Ashizer {

  public Matrix ashizeH(final Matrix A, final Matrix b) {
    final double[][] h = new double[A.height()][A.width()];

    for (int i = 0; i < A.height(); ++i) {
      for (int j = 0; j < A.width(); ++j) {
        if (i == j) {
          h[i][j] = 0;
        } else {
          h[i][j] = -A.get(i, j) / A.get(i, i);
        }
      }
    }

    return new Matrix(h);
  }

  public Matrix ashizeB(final Matrix A, final Matrix b) {
    final double[][] g = new double[b.height()][1];

    for (int i = 0; i < b.height(); ++i) {
      g[i][0] = b.get(i, 0) / A.get(i, i);
    }

    return new Matrix(g);
  }
}
