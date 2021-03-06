package ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain

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
    "Solution=%.10f, precision=%.10f, residual=%.10f, iterations=%d, lastSegment=%s"
      .format(solution, precision, residual, iterations, lastSegment)
}
