package org.marnikitta.spbsu.numanalysis.oddroots.domain.impl

import org.marnikitta.spbsu.numanalysis.oddroots.domain.{RootOnSegment, RootSearchStatistics}

/**
  * Created by marnikitta on 11.09.16.
  */
class ModifiedNewtonRoot(implicit precision: Double) extends RootOnSegment {
  override def root(f: (Double) => Double, df: Double => Double, segment: (Double, Double)): RootSearchStatistics = {
    root(f, df(segment._2), segment)
  }

  private def root(f: (Double) => Double, df: Double, segment: (Double, Double)): RootSearchStatistics = {
    if (isGoodEnough(segment._1, segment._2)) {
      RootSearchStatistics(
        solution = segment._1,
        precision = precision,
        residual = Math.abs(f(segment._1)),
        iterations = 1,
        lastSegment = segment)
    }
    else root(f, df, (improve(f, segment._1, df), segment._1)).withIncreasedIteration
  }

  private def improve(f: Double => Double, guess: Double, df: Double): Double =
    guess - f(guess) / df

  private def isGoodEnough(guess: Double, prevGuess: Double): Boolean = Math.abs(guess - prevGuess) < precision
}
