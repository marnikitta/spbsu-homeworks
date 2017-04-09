package ru.spbsu.math.marnikitta.homeworks.numanalysis.gauss;

import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

public final class Solver {
  private final static double EPS = 1e-6;

  private Solver() {
  }

  public static Matrix inverseSimpleGauss(final Matrix matrix) {
    if (matrix.width() != matrix.height()) {
      throw new IllegalArgumentException("Matrix should be square");
    }
    final double[][] a = matrix.concat(Matrix.ones(matrix.width())).array();
    simpleGauss(a);

    final double[][] result = new double[matrix.height()][matrix.width()];
    for (int i = 0; i < matrix.height(); ++i) {
      for (int j = 0; j < matrix.width(); ++j) {
        result[i][j] = a[i][matrix.width() + j];
      }
    }

    return new Matrix(result);
  }

  private static void simpleGauss(final double[][] a) {
    final int width = a.length;
    final int sumWidth = a[0].length;
    final int height = a.length;

    for (int k = 0; k < height; ++k) {
      final double mainElement = a[k][k];
      if (Math.abs(mainElement) < EPS) {
        System.out.println("Main element is lower then Eps");
      }

      for (int j = k + 1; j < sumWidth; ++j) {
        a[k][j] /= mainElement;
      }


      for (int i = k + 1; i < height; ++i) {
        final double tmp = a[i][k];
        for (int j = k + 1; j < sumWidth; ++j) {
          a[i][j] -= a[k][j] * tmp;
        }
      }
    }


    System.out.println("Gauss forward run result:");
    System.out.println(Matrix.niceToString(a));
    System.out.flush();

    for (int k = height - 1; k >= 0; --k) {
      for (int i = k - 1; i >= 0; --i) {
        final double tmp = a[i][k];
        for (int j = width; j < sumWidth; ++j) {
          a[i][j] -= a[k][j] * tmp;
        }
      }
    }

    System.out.println("Gauss backward run result:");
    System.out.println(Matrix.niceToString(a));
    System.out.flush();
  }


  public static Matrix inverseColumnwiseGauss(final Matrix matrix) {
    if (matrix.width() != matrix.height()) {
      throw new IllegalArgumentException("Matrix should be square");
    }
    final double[][] a = matrix.concat(Matrix.ones(matrix.width())).array();

    final int width = a.length;
    final int sumWidth = a[0].length;
    final int height = a.length;

    final int[] columnSwaps = new int[a.length];
    for (int i = 0; i < a.length; ++i) {
      columnSwaps[i] = i;
    }

    for (int k = 0; k < height; ++k) {
      int maxCol = k;
      for (int col = k; col < width; ++col) {
        if (Math.abs(a[k][columnSwaps[col]]) > Math.abs(a[k][columnSwaps[maxCol]])) {
          maxCol = col;
        }
      }

      {
        final int tmp = columnSwaps[k];
        columnSwaps[k] = columnSwaps[maxCol];
        columnSwaps[maxCol] = tmp;
      }

      final double mainElement = a[k][columnSwaps[k]];
      if (Math.abs(mainElement) < EPS) {
        System.out.println("Main element is lower then Eps");
      }

      for (int j = k + 1; j < width; ++j) {
        a[k][columnSwaps[j]] /= mainElement;
      }
      for (int j = width; j < sumWidth; ++j) {
        a[k][columnSwaps[j - width] + width] /= mainElement;
      }


      for (int i = k + 1; i < height; ++i) {
        final double coef = a[i][columnSwaps[k]];
        for (int j = k + 1; j < width; ++j) {
          a[i][columnSwaps[j]] -= a[k][columnSwaps[j]] * coef;
        }
        for (int j = width; j < sumWidth; ++j) {
          a[i][columnSwaps[j - width] + width] -= a[k][columnSwaps[j - width] + width] * coef;
        }
      }
    }


    System.out.println("Gauss forward run result:");
    System.out.println(Matrix.niceToString(a));
    System.out.flush();

    for (int k = height - 1; k >= 0; --k) {
      for (int i = k - 1; i >= 0; --i) {
        final double tmp = a[i][columnSwaps[k]];
        for (int j = width; j < sumWidth; ++j) {
          a[i][columnSwaps[j - width] + width] -= a[k][columnSwaps[j - width] + width] * tmp;
        }
      }
    }

    System.out.println("Gauss backward run result:");
    System.out.println(Matrix.niceToString(a));
    System.out.flush();

    final double[][] result = new double[matrix.height()][matrix.width()];
    for (int i = 0; i < matrix.height(); ++i) {
      System.arraycopy(a[i], width, result[columnSwaps[i]], 0, matrix.width());
    }
    return new Matrix(result);
  }

  public static Matrix solveColumnwiseGauss(final Matrix A, final Matrix b) {
    if (A.width() != A.height()) {
      throw new IllegalArgumentException("Matrix should be square");
    }
    if (b.width() != 1) {
      throw new IllegalArgumentException();
    }
    final double[][] a = A.array();
    final double[] leftSide = b.column(0);

    final int width = a.length;
    final int height = a.length;

    final int[] columnSwaps = new int[a.length];
    for (int i = 0; i < a.length; ++i) {
      columnSwaps[i] = i;
    }

    for (int k = 0; k < height; ++k) {
      int maxCol = k;
      for (int col = k; col < width; ++col) {
        if (Math.abs(a[k][columnSwaps[col]]) > Math.abs(a[k][columnSwaps[maxCol]])) {
          maxCol = col;
        }
      }

      {
        final int tmp = columnSwaps[k];
        columnSwaps[k] = columnSwaps[maxCol];
        columnSwaps[maxCol] = tmp;
      }

      final double mainElement = a[k][columnSwaps[k]];
      if (Math.abs(mainElement) < EPS) {
        System.out.println("Main element is lower then Eps");
      }

      for (int j = k + 1; j < width; ++j) {
        a[k][columnSwaps[j]] /= mainElement;
      }
      leftSide[k] /= mainElement;


      for (int i = k + 1; i < height; ++i) {
        final double coef = a[i][columnSwaps[k]];
        for (int j = k + 1; j < width; ++j) {
          a[i][columnSwaps[j]] -= a[k][columnSwaps[j]] * coef;
        }
        leftSide[i] -= leftSide[k] * coef;
      }
    }


    System.out.println("Gauss forward run result:");
    System.out.println(Matrix.niceToString(a));
    System.out.flush();

    for (int k = height - 1; k >= 0; --k) {
      for (int i = k - 1; i >= 0; --i) {
        leftSide[i] -= leftSide[k] * a[i][columnSwaps[k]];
      }
    }

    System.out.println("Gauss backward run result:");
    System.out.println(Matrix.niceToString(a));
    System.out.flush();

    final double[][] result = new double[A.height()][1];
    for (int i = 0; i < A.height(); ++i) {
      result[columnSwaps[i]][0] = leftSide[i];
    }
    return new Matrix(result);
  }

  public static Matrix solveSimpleGauss(final Matrix A, final Matrix b) {
    if (A.width() != A.height()) {
      throw new IllegalArgumentException("Matrix should be square");
    }
    if (b.width() != 1) {
      throw new IllegalArgumentException();
    }
    final double[][] a = A.concat(b).array();
    simpleGauss(a);

    final double[][] result = new double[A.height()][1];
    for (int i = 0; i < A.height(); ++i) {
      result[i][0] = a[i][A.width()];
    }

    return new Matrix(result);
  }

}
