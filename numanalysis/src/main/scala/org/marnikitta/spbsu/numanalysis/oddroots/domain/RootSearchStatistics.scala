package org.marnikitta.spbsu.numanalysis.oddroots.domain

/**
  * Created by marnikitta on 11.09.16.
  */
case class RootSearchStatistics(solution: Double,
                                precision: Double,
                                residual: Double,
                                iterations: Int,
                                lastSegment: (Double, Double)) {
  def withIncreasedIteration: RootSearchStatistics =
    RootSearchStatistics(solution, precision, residual, iterations + 1, lastSegment)

  override def toString: String =
    "\nSolution is %f with precision %f \nResidual is %s. \nFinished in %d iterations"
      .format(solution, precision, residual, iterations)
}
