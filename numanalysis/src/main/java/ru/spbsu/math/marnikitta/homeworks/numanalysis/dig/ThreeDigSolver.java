package ru.spbsu.math.marnikitta.homeworks.numanalysis.dig;

import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

import java.util.Arrays;

public final class ThreeDigSolver {
  private final boolean debug;

  public ThreeDigSolver(final boolean debug) {
    this.debug = debug;
  }

  public double[] solve(final double[] a, final double[] b, final double[] c, final double[] d) {
    final int n = d.length;

    if (a[0] != 0 || c[n - 1] != 0) {
      throw new IllegalArgumentException();
    }

    final double[] s = new double[n];
    final double[] t = new double[n];

    if (this.debug) {
      System.out.println("### Метод разностной прогонки\n");
    }

    s[0] = c[0] / -b[0];
    t[0] = d[0] / b[0];
    if (this.debug) {
      System.out.println(String.format("s[0] = %.20f, t[0] = %.20f", s[0], t[0]));
    }

    for (int i = 1; i < n; ++i) {
      s[i] = c[i] / (-b[i] - a[i] * s[i - 1]);
      t[i] = (a[i] * t[i - 1] - d[i]) / (-b[i] - a[i] * s[i - 1]);
      if (this.debug) {
        System.out.println(String.format("s[%d] = %.20f, t[%d] = %.20f", i, s[0], i, t[0]));
      }
    }

    final double[] x = new double[n];
    x[n - 1] = t[n - 1];
    for (int i = n - 2; i >= 0; --i) {
      x[i] = s[i] * x[i + 1] + t[i];
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
