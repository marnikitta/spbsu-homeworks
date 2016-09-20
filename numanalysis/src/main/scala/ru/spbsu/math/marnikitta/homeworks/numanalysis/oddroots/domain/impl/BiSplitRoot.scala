package ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.impl

import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.{RootOnSegment, RootResult}

/**
  * Created by marnikitta on 10.09.16.
  */
class BiSplitRoot(implicit precision: Double) extends RootOnSegment {

  override def root(f: (Double) => Double, df: (Double) => Double, segment: (Double, Double)): RootResult = {
    require(segment._1 <= segment._2)
    require(f(segment._1) * f(segment._2) <= 0)

    if (isGoodEnough(segment)) {
      RootResult(
        solution = segment._1,
        precision = precision,
        residual = Math.abs(f(segment._1)),
        iterations = 1,
        lastSegment = segment)
    }
    else root(f, df, nextSegment(f, segment)).withIncreasedIteration
  }

  private def nextSegment(f: Double => Double, segment: (Double, Double)): (Double, Double) = {
    val middle = (segment._2 + segment._1) / 2

    if (f(middle) * f(segment._1) < 0) (segment._1, middle)
    else (middle, segment._2)
  }

  private def isGoodEnough(segment: (Double, Double)): Boolean = Math.abs(segment._2 - segment._1) < precision
}
