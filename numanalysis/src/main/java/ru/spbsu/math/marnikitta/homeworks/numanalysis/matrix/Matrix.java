package ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix;

import java.util.Arrays;
import java.util.List;

/**
 * Created by marnikitta on 01.10.16.
 */
public class Matrix {
  private final double[][] matrix;

  private final int width;
  private final int height;

  public Matrix(final double[][] matrix) {
    this.height = matrix.length;
    this.width = matrix[0].length;

    for (int i = 0; i < height; ++i) {
      if (matrix[i].length != width) {
        throw new IllegalArgumentException("All rows must be the same length");
      }
    }

    this.matrix = new double[height][width];
    for (int i = 0; i < height; ++i) {
      System.arraycopy(matrix[i], 0, this.matrix[i], 0, width);
    }
  }

  public static Matrix fromList(final List<double[]> matrix) {
    final double[][] array = new double[matrix.size()][matrix.get(0).length];

    for (int i = 0; i < matrix.size(); ++i) {
      array[i] = matrix.get(i);
    }
    return new Matrix(array);
  }

  public static Matrix vector(final double[] b) {
    final double[][] a = new double[b.length][1];
    for (int i = 0; i < b.length; ++i) {
      a[i][0] = b[i];
    }
    return new Matrix(a);
  }

  public static Matrix ones(final int n) {
    final double[][] result = new double[n][n];
    for (int i = 0; i < n; ++i) {
      result[i][i] = 1;
    }
    return new Matrix(result);
  }

  public double vectorNorm(final int p) {
    double result = 0;
    for (int i = 0; i < height(); ++i) {
      for (int j = 0; j < width(); ++j) {
        result += Math.pow(Math.abs(get(i, j)), p);
      }
    }
    return Math.pow(result, 1d / p);
  }

  public double vectorInfNorm() {
    double result = -1;
    for (int i = 0; i < height(); ++i) {
      for (int j = 0; j < width(); ++j) {
        result = Math.max(Math.abs(result), get(i, j));
      }
    }
    return result;
  }

  public double matrixInfNorm() {
    double result = -1;
    for (int i = 0; i < height(); ++i) {
      double rowSum = 0;
      for (int j = 0; j < width(); ++j) {
        rowSum += Math.abs(get(i, j));
      }
      result = Math.max(result, rowSum);
    }
    return result;
  }

  public double matrixOneNorm() {
    double result = -1;
    for (int j = 0; j < width(); ++j) {
      double colSum = 0;
      for (int i = 0; i < height(); ++i) {
        colSum += Math.abs(get(i, j));
      }
      result = Math.max(result, colSum);
    }
    return result;
  }

  public Matrix transposed() {
    final double[][] result = new double[width][height];
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        result[j][i] = matrix[i][j];
      }
    }
    return new Matrix(result);
  }

  public Matrix multiply(final double arg) {
    final double[][] result = new double[height][width];
    for (int i = 0; i < height; ++i) {
      System.arraycopy(matrix[i], 0, result[i], 0, width);
    }
    return new Matrix(result);
  }

  public Matrix dot(final Matrix that) {
    if (this.width() != that.height()) {
      throw new IllegalArgumentException(
              String.format("Width of the first matrix should equal height of second, %d != %d ",
                      this.width(), that.height())
      );
    }

    final double[][] result = new double[this.height()][that.width];

    for (int i = 0; i < this.height(); ++i) {
      for (int j = 0; j < that.width(); ++j) {
        for (int t = 0; t < this.width(); ++t) {
          result[i][j] += this.get(i, t) * that.get(t, j);
        }
      }
    }
    return new Matrix(result);
  }

  public Matrix add(final Matrix that) {
    if (this.height() != that.height() || this.width() != that.width()) {
      throw new IllegalArgumentException();
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
    final double[][] result = new double[height()][width()];

    for (int i = 0; i < height(); ++i) {
      for (int j = 0; j < width(); ++j) {
        result[i][j] = -get(i, j);
      }
    }
    return new Matrix(result);
  }

  public Matrix subtract(final Matrix that) {
    return add(that.negate());
  }

  public int width() {
    return this.width;
  }

  public int height() {
    return this.height;
  }

  public Matrix inverse() {
    if (width() != height()) {
      throw new IllegalArgumentException("Matrix should be square");
    }
    final int length = width();
    final double[][] a = array();
    final double[][] result = Matrix.ones(length).array();

    for (int i = length - 1; i > 0; --i) {
      if (a[i - 1][0] < a[i][0]) {
        for (int j = 0; j < length; ++j) {
          final double temp1 = a[i][j];
          a[i][j] = a[i - 1][j];
          a[i - 1][j] = temp1;

          final double temp2 = result[i][j];
          result[i][j] = result[i - 1][j];
          result[i - 1][j] = temp2;
        }
      }
    }

    for (int i = 0; i < length; ++i) {
      for (int j = 0; j < length; ++j) {
        if (j != i) {
          final double coef = a[j][i] / a[i][i];
          for (int k = 0; k < length; k++) {
            a[j][k] -= a[i][k] * coef;
            result[j][k] -= result[i][k] * coef;
          }
        }
      }
    }

    for (int i = 0; i < length; ++i) {
      final double coef = a[i][i];
      for (int j = 0; j < length; ++j) {
        result[i][j] = result[i][j] / coef;
      }
    }
    return new Matrix(result);
  }

  public double[] row(final int i) {
    final double[] result = new double[width()];
    System.arraycopy(matrix[i], 0, result, 0, width());
    return result;
  }

  public double[] column(final int j) {
    final double[] result = new double[height()];
    for (int i = 0; i < height(); ++i) {
      result[i] = matrix[i][j];
    }
    return result;
  }

  public static Matrix concat(final Matrix a, final Matrix b) {
    if (a.height() != b.height()) {
      throw new IllegalArgumentException("Heights should be equal");
    }
    final double[][] result = new double[a.height()][a.width() + b.width()];
    for (int i = 0; i < a.height(); ++i) {
      for (int j = 0; j < a.width() + b.width(); ++j) {
        if (j < a.width()) {
          result[i][j] = a.get(i, j);
        } else {
          result[i][j] = b.get(i, j - a.width());
        }
      }
    }
    return new Matrix(result);
  }

  public double get(final int i, final int j) {
    return matrix[i][j];
  }

  public double[][] array() {
    final double[][] result = new double[height()][width()];
    for (int i = 0; i < height(); ++i) {
      for (int j = 0; j < width(); ++j) {
        result[i][j] = get(i, j);
      }
    }
    return result;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Matrix matrix1 = (Matrix) o;

    if (width != matrix1.width) return false;
    if (height != matrix1.height) return false;
    return Arrays.deepEquals(matrix, matrix1.matrix);

  }

  @Override
  public int hashCode() {
    int result = Arrays.deepHashCode(matrix);
    result = 31 * result + width;
    result = 31 * result + height;
    return result;
  }

  @Override
  public String toString() {
    return "Matrix{" + "matrix=" + (matrix == null ? "null" : Arrays.deepToString(matrix)) +
            '}';
  }

  public String niceToString() {
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < height(); ++i) {
      for (int j = 0; j < width(); ++j) {
        sb.append(get(i, j));
        if (j != width() - 1) {
          sb.append(" ");
        }
      }
      if (i != height() - 1) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }
}

