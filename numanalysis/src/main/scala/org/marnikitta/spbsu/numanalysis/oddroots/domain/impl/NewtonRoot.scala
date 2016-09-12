package org.marnikitta.spbsu.numanalysis.oddroots.domain.impl

import org.marnikitta.spbsu.numanalysis.oddroots.domain.{Logging, RootOnSegment, RootSearchStatistics}

/**
  * Created by marnikitta on 07.09.16.
  */
class NewtonRoot(implicit precision: Double) extends RootOnSegment with Logging {

  override def root(f: (Double) => Double, df: Double => Double, segment: (Double, Double)): RootSearchStatistics = {
    if (isGoodEnough(segment._1, segment._2)) {
      RootSearchStatistics(
        solution = segment._1,
        precision = precision,
        residual = Math.abs(f(segment._1)),
        iterations = 1,
        lastSegment = segment)
    }
    else root(f, df, (improve(f, df, segment._1), segment._1)).withIncreasedIteration
  }

  private def improve(f: Double => Double, df: Double => Double, guess: Double): Double = guess - f(guess) / df(guess)

  private def isGoodEnough(guess: Double, prevGuess: Double): Boolean = Math.abs(guess - prevGuess) < precision
}
