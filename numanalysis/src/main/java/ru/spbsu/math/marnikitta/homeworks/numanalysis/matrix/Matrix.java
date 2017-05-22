package ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Matrix {
  private final double[][] matrix;

  private final int width;
  private final int height;

  public Matrix(double[][] matrix) {
    this.height = matrix.length;
    this.width = matrix[0].length;

    for (int i = 0; i < this.height; ++i) {
      if (matrix[i].length != this.width) {
        throw new IllegalArgumentException("All rows must be the same length");
      }
    }

    this.matrix = new double[this.height][this.width];
    for (int i = 0; i < this.height; ++i) {
      System.arraycopy(matrix[i], 0, this.matrix[i], 0, this.width);
    }
  }

  public Matrix(double[] vector) {
    this.height = vector.length;
    this.width = 1;

    this.matrix = new double[this.height][this.width];

    for (int i = 0; i < this.height; ++i) {
      this.matrix[i][0] = vector[i];
    }
  }

  public static Matrix threeDig(double[] a, double[] b, double[] c) {
    final int n = a.length;
    final double[][] result = new double[n][n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        if (i == j) {
          result[i][j] = b[i];
        } else if (i == j + 1) {
          result[i][j] = a[i];
        } else if (i == j - 1) {
          result[i][j] = c[i];
        }
      }
    }
    return new Matrix(result);
  }

  public static Matrix ones(int n) {
    final double[][] result = new double[n][n];
    for (int i = 0; i < n; ++i) {
      result[i][i] = 1;
    }
    return new Matrix(result);
  }

  public static Matrix vectorOnes(int n) {
    final double[][] result = new double[n][1];
    for (int i = 0; i < n; ++i) {
      result[i][0] = 1;
    }
    return new Matrix(result);
  }

  public double trace() {
    double result = 0;
    for (int i = 0; i < Math.min(this.width, this.height); ++i) {
      result += this.matrix[i][i];
    }
    return result;
  }

  public double vectorInfNorm() {
    double result = -1;
    for (int i = 0; i < this.height(); ++i) {
      for (int j = 0; j < this.width(); ++j) {
        result = Math.max(Math.abs(this.get(i, j)), result);
      }
    }
    return result;
  }

  public double matrixInfNorm() {
    double result = -1;
    for (int i = 0; i < this.height(); ++i) {
      double rowSum = 0;
      for (int j = 0; j < this.width(); ++j) {
        rowSum += Math.abs(this.get(i, j));
      }
      result = Math.max(result, rowSum);
    }
    return result;
  }

  public double matrixOneNorm() {
    double result = -1;
    for (int j = 0; j < this.width(); ++j) {
      double colSum = 0;
      for (int i = 0; i < this.height(); ++i) {
        colSum += Math.abs(this.get(i, j));
      }
      result = Math.max(result, colSum);
    }
    return result;
  }

  public Matrix upperDiag() {
    final double[][] upper = new double[this.height][this.width];
    for (int i = 0; i < this.height; ++i) {
      for (int j = 0; j < this.width; ++j) {
        if (j >= i) {
          upper[i][j] = this.matrix[i][j];
        }
      }
    }
    return new Matrix(upper);
  }

  public Matrix transposed() {
    final double[][] result = new double[this.width][this.height];
    for (int i = 0; i < this.height; ++i) {
      for (int j = 0; j < this.width; ++j) {
        result[j][i] = this.matrix[i][j];
      }
    }
    return new Matrix(result);
  }

  public Matrix multiply(double arg) {
    final double[][] result = new double[this.height][this.width];
    for (int i = 0; i < this.height; ++i) {
      for (int j = 0; j < this.width; ++j) {
        result[i][j] = this.matrix[i][j] * arg;
      }
    }
    return new Matrix(result);
  }

  public Matrix divide(double arg) {
    return this.multiply(1.0 / arg);
  }

  public Matrix infNormalize() {
    return this.divide(this.vectorInfNorm());
  }

  public Matrix dot(Matrix that) {
    if (this.width() != that.height()) {
      throw new IllegalArgumentException(
              String.format("Width of the first matrix should equal height of second, %d != %d ",
                      this.width(), that.height())
      );
    }

    final double[][] result = new double[this.height()][that.width()];

    for (int i = 0; i < this.height(); ++i) {
      for (int j = 0; j < that.width(); ++j) {
        for (int t = 0; t < this.width(); ++t) {
          result[i][j] += this.get(i, t) * that.get(t, j);
        }
      }
    }
    return new Matrix(result);
  }

  public Matrix add(Matrix that) {
    if (this.height() != that.height() || this.width() != that.width()) {
      throw new IllegalArgumentException("");
    }

    final double[][] result = new double[this.height()][this.width()];

    for (int i = 0; i < this.height(); ++i) {
      for (int j = 0; j < this.width(); ++j) {
        result[i][j] = this.get(i, j) + that.get(i, j);
      }
    }
    return new Matrix(result);
  }

  public Matrix negate() {
    final double[][] result = new double[this.height()][this.width()];

    for (int i = 0; i < this.height(); ++i) {
      for (int j = 0; j < this.width(); ++j) {
        result[i][j] = -this.get(i, j);
      }
    }
    return new Matrix(result);
  }

  public Matrix subtract(Matrix that) {
    return this.add(that.negate());
  }

  public int width() {
    return this.width;
  }

  public int height() {
    return this.height;
  }


  public double[] column(int j) {
    final double[] result = new double[this.height()];
    for (int i = 0; i < this.height(); ++i) {
      result[i] = this.matrix[i][j];
    }
    return result;
  }

  public Matrix diag() {
    final double[] result = new double[this.height()];
    for (int i = 0; i < this.height(); ++i) {
      result[i] = this.matrix[i][i];
    }
    return new Matrix(result);

  }

  public List<Matrix> columns() {
    final List<Matrix> result = new ArrayList<>();
    for (int j = 0; j < this.width; ++j) {
      result.add(new Matrix(this.column(j)));
    }
    return result;
  }

  public Matrix concat(Matrix that) {
    if (this.height() != that.height()) {
      throw new IllegalArgumentException("Heights should be equal");
    }
    final double[][] result = new double[this.height()][this.width() + that.width()];
    for (int i = 0; i < this.height(); ++i) {
      for (int j = 0; j < this.width() + that.width(); ++j) {
        if (j < this.width()) {
          result[i][j] = this.get(i, j);
        } else {
          result[i][j] = that.get(i, j - this.width());
        }
      }
    }
    return new Matrix(result);
  }

  public double get(int i, int j) {
    return this.matrix[i][j];
  }

  public double[][] array() {
    final double[][] result = new double[this.height()][this.width()];
    for (int i = 0; i < this.height(); ++i) {
      for (int j = 0; j < this.width(); ++j) {
        result[i][j] = this.get(i, j);
      }
    }
    return result;
  }

  public double det() {
    if (this.width() != this.height()) {
      throw new IllegalArgumentException("");
    }

    return Matrix.det(this.matrix, this.width());
  }

  private static double det(double[][] A, int N) {
    if (N == 1) {
      return A[0][0];
    } else if (N == 2) {
      return A[0][0] * A[1][1] - A[1][0] * A[0][1];
    } else {
      double det = 0;
      for (int j1 = 0; j1 < N; j1++) {
        final double[][] m = new double[N - 1][];
        for (int k = 0; k < N - 1; k++) {
          m[k] = new double[N - 1];
        }
        for (int i = 1; i < N; i++) {
          int j2 = 0;
          for (int j = 0; j < N; j++) {
            if (j == j1)
              continue;
            m[i - 1][j2] = A[i][j];
            j2++;
          }
        }
        det += StrictMath.pow(-1.0, 1.0 + j1 + 1.0) * A[0][j1] * Matrix.det(m, N - 1);
      }
      return det;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    final Matrix matrix1 = (Matrix) o;

    if (this.width != matrix1.width) return false;
    if (this.height != matrix1.height) return false;
    return Arrays.deepEquals(this.matrix, matrix1.matrix);
  }

  @Override
  public int hashCode() {
    int result = Arrays.deepHashCode(this.matrix);
    result = 31 * result + this.width;
    result = 31 * result + this.height;
    return result;
  }

  public static String niceToString(double[][] array) {
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < array.length; ++i) {
      for (int j = 0; j < array[i].length; ++j) {
        sb.append(String.format("%10.5f", array[i][j]));
        if (j != array[i].length - 1) {
          sb.append(' ');
        }
      }
      if (i != array.length - 1) {
        sb.append('\n');
      }
    }
    return sb.toString();

  }


  public String niceToString() {
    return Matrix.niceToString(this.matrix);
  }
}

