package ru.spbsu.math.marnikitta.homeworks.numanalysis.problem2;

import ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.Matrix;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public final class SimpleIteration {
  private final double eps;

  public SimpleIteration(double eps) {
    this.eps = eps;
  }

  public Matrix solve(Matrix H, Matrix g) {

    Matrix xOld = Matrix.vectorOnes(H.width());
    Matrix xNew = H.dot(xOld).add(g);

    int iter = 1;

    while (Math.abs(xNew.subtract(xOld).vectorInfNorm()) > this.eps) {
      xOld = xNew;
      xNew = H.dot(xOld).add(g);
      iter += 1;
    }

    final double hNorm = H.matrixInfNorm();

    final double aPriory = StrictMath.pow(hNorm, iter) * Matrix.vectorOnes(H.width()).vectorInfNorm()
            + StrictMath.pow(hNorm, iter) / (1 - hNorm) * g.vectorInfNorm();
    System.out.println(">> SimpleIteration: a priori: " + aPriory);

    System.out.println(">> SimpleIteration: a posteriori: " + hNorm / (1 - hNorm) * xNew.subtract(xOld).vectorInfNorm());

    System.out.println(">> SimpleIteration: iterations: " + iter);
    return xNew;
  }
}
