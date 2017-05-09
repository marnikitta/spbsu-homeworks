package ru.spbsu.math.marnikitta.homeworks.numanalysis.dig;

import java.util.function.DoubleUnaryOperator;

public final class FirstEdgeSolver {
  private final int n;
  private final ThreeDigSolver solver = new ThreeDigSolver(true);

  public FirstEdgeSolver(final int n) {
    this.n = n;
  }

  public double[] solve(final DoubleUnaryOperator p, final DoubleUnaryOperator q, final DoubleUnaryOperator r,
                        final DoubleUnaryOperator f, final double a, final double b, final double alpha0,
                        final double alpha1, final double a1, final double beta0, final double beta1, final double b1) {
    final double[] pp = FirstEdgeSolver.granulate(p, a, b, this.n);
    final double[] qq = FirstEdgeSolver.granulate(q, a, b, this.n);
    final double[] rr = FirstEdgeSolver.granulate(r, a, b, this.n);
    final double[] ff = FirstEdgeSolver.granulate(f, a, b, this.n);

    final double[] AA = new double[this.n];
    final double[] BB = new double[this.n];
    final double[] CC = new double[this.n];
    final double[] GG = new double[this.n];

    final double h = (b - a) / (this.n - 1);

    for (int i = 1; i < this.n - 1; ++i) {
      AA[i] = -pp[i] / (h * h) - qq[i] / (2 * h);
      BB[i] = 2 * pp[i] / (h * h) + rr[i];
      CC[i] = -pp[i] / (h * h) + qq[i] / (2 * h);
      GG[i] = ff[i];
    }

    BB[0] = alpha0 + alpha1 / h;
    CC[0] = -alpha1 / h;
    GG[0] = a1;

    AA[this.n - 1] = -beta1 / h;
    BB[this.n - 1] = beta0 + beta1 / h;
    GG[this.n - 1] = b1;

    return this.solver.solve(AA, BB, CC, GG);
  }

  public static double[] net(final double a, final double b, final int n) {
    final double step = (b - a) / (n - 1);
    final double[] result = new double[n];

    double x = a;
    for (int i = 0; i < n; ++i, x += step) {
      result[i] = x;
    }
    return result;

  }

  public static double[] granulate(final DoubleUnaryOperator f, final double a, final double b, final int n) {
    final double step = (b - a) / (n - 1);
    final double[] result = new double[n];

    double x = a;
    for (int i = 0; i < n; ++i, x += step) {
      result[i] = f.applyAsDouble(x);
    }
    return result;
  }
}
