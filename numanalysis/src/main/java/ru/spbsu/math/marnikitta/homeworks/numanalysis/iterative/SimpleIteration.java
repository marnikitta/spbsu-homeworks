package ru.spbsu.math.marnikitta.homeworks.numanalysis.iterative;

import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public final class SimpleIteration {
  private final double eps;

  public SimpleIteration(final double eps) {
    this.eps = eps;
  }

  public Matrix solve(final Matrix H, final Matrix g) {

    Matrix xOld = Matrix.vectorOnes(H.width());
    Matrix xNew = H.dot(xOld).add(g);

    int iter = 1;

    while (Math.abs(xNew.subtract(xOld).vectorInfNorm()) > this.eps) {
      xOld = xNew;
      xNew = H.dot(xOld).add(g);
      iter += 1;
    }

    System.out.println(">> SimpleIteration: iterations: " + iter);
    return xNew;
  }
}
