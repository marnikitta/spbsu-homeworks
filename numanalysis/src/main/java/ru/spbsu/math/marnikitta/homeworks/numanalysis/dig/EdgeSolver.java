package ru.spbsu.math.marnikitta.homeworks.numanalysis.dig;

import java.util.function.DoubleUnaryOperator;

public final class EdgeSolver {
  private final int n;
  private final boolean secondOrder;
  private final ThreeDigSolver solver = new ThreeDigSolver(true);

  public EdgeSolver(final int n, final boolean secondOrder) {
    this.n = n;
    this.secondOrder = secondOrder;
  }

  public double[] solve(final DoubleUnaryOperator p, final DoubleUnaryOperator q, final DoubleUnaryOperator r,
                        final DoubleUnaryOperator f, final double a, final double b, final double alpha0,
                        final double alpha1, final double a1, final double beta0, final double beta1, final double b1) {
    final double[] pp = EdgeSolver.granulate(p, a, b, this.n);
    final double[] qq = EdgeSolver.granulate(q, a, b, this.n);
    final double[] rr = EdgeSolver.granulate(r, a, b, this.n);
    final double[] ff = EdgeSolver.granulate(f, a, b, this.n);

    final double[] AA = new double[this.n];
    final double[] BB = new double[this.n];
    final double[] CC = new double[this.n];
    final double[] GG = new double[this.n];

    final double h = (b - a) / (this.n - 1);

    for (int i = 1; i < this.n - 1; ++i) {
      AA[i] = pp[i] - qq[i] * h / 2;
      BB[i] = -2 * pp[i] + rr[i] * h * h;
      CC[i] = pp[i] + qq[i] * h / 2;
      GG[i] = ff[i] * h * h;
    }

    if (!this.secondOrder) {
      BB[0] = alpha0 * h - alpha1;
      CC[0] = alpha1;
      GG[0] = a1 * h;

      AA[this.n - 1] = -beta1;
      BB[this.n - 1] = beta0 * h + beta1;
      GG[this.n - 1] = b1 * h;
    } else {
      BB[0] = 2 * alpha0 * h + alpha1 * (AA[1] / CC[1] - 3);
      CC[0] = alpha1 * (BB[1] / CC[1] + 4);
      GG[0] = 2 * a1 * h + alpha1 * GG[1] / CC[1];

      AA[this.n - 1] = -beta1 * (BB[this.n - 2] / AA[this.n - 2] + 4);
      BB[this.n - 1] = 2 * beta0 * h + beta1 * (3 - CC[this.n - 2] / AA[this.n - 2]);
      GG[this.n - 1] = 2 * b1 * h - beta1 * GG[this.n - 2] / AA[this.n - 2];
    }

    return this.solver.solve(AA, BB, CC, GG);
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
