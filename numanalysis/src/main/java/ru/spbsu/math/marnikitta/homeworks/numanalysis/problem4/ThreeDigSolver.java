package ru.spbsu.math.marnikitta.homeworks.numanalysis.problem4;

import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

import java.util.Arrays;

public final class ThreeDigSolver {
  private final boolean debug;

  public ThreeDigSolver(boolean debug) {
    this.debug = debug;
  }

  public double[] solve(double[] a, double[] b, double[] c, double[] d) {
    final int n = d.length;

    if (a[0] != 0 || c[n - 1] != 0) {
      throw new IllegalArgumentException();
    }

    if (this.debug) {
      System.out.println("### Метод разностной прогонки\n");
    }

    final double[] m = new double[n];
    m[0] = c[0] / -b[0];
    final double[] k = new double[n];
    k[0] = d[0] / b[0];
    if (this.debug) {
      System.out.println(String.format("m[0] = %.20f, k[0] = %.20f", m[0], k[0]));
    }

    for (int i = 1; i < n; ++i) {
      m[i] = c[i] / (-b[i] - a[i] * m[i - 1]);
      k[i] = (a[i] * k[i - 1] - d[i]) / (-b[i] - a[i] * m[i - 1]);
      if (this.debug) {
        System.out.println(String.format("s[%d] = %.20f, t[%d] = %.20f", i, m[i], i, k[i]));
      }
    }

    final double[] x = new double[n];
    x[n - 1] = k[n - 1];
    for (int i = n - 2; i >= 0; --i) {
      x[i] = m[i] * x[i + 1] + k[i];
    }

    if (this.debug) {
      System.out.println();
      System.out.println("Трехдиагоднальная матрица:");
      System.out.println(Matrix.threeDig(a, b, c).niceToString());
      System.out.println();
      System.out.println("Вектор свободных членов:");
      System.out.println(Arrays.toString(d));
      System.out.println();
      System.out.println(String.format("Полученный вектор неизвестных:\n%s", Arrays.toString(x)));
      System.out.println();
      System.out.println(String.format("Норма вектора невязки:\n%.15f", Matrix.threeDig(a, b, c).dot(new Matrix(x)).subtract(new Matrix(d)).vectorInfNorm()));
    }

    return x;
  }
}
