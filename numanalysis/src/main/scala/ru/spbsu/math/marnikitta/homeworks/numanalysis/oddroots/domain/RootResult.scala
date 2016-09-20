package ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain

/**
  * Created by marnikitta on 11.09.16.
  */
case class RootResult(solution: Double,
                      precision: Double,
                      residual: Double,
                      iterations: Int,
                      lastSegment: (Double, Double)) {
  def withIncreasedIteration: RootResult =
    RootResult(solution, precision, residual, iterations + 1, lastSegment)

  override def toString: String =
    "Solution=%f, precision=%f, residual=%f, iterations=%d, lastSegment=%s"
      .format(solution, precision, residual, iterations, lastSegment)
}
