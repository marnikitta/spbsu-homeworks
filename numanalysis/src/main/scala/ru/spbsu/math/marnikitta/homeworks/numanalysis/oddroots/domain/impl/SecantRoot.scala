package ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.impl

import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.{RootOnSegment, RootResult}

/**
  * Created by marnikitta on 11.09.16.
  */
class SecantRoot(implicit precision: Double) extends RootOnSegment {

  override def root(f: (Double) => Double, df: Double => Double, segment: (Double, Double)): RootResult = {
    if (isGoodEnough(segment._1, segment._2)) {
      RootResult(
        solution = segment._1,
        precision = precision,
        residual = Math.abs(f(segment._1)),
        iterations = 1,
        lastSegment = segment)
    }
    else root(f, df, (improve(f, segment._1, segment._2), segment._1)).withIncreasedIteration
  }

  private def improve(f: Double => Double, guess: Double, prevGuess: Double): Double =
    guess - f(guess) * (guess - prevGuess) / (f(guess) - f(prevGuess))

  private def isGoodEnough(guess: Double, prevGuess: Double): Boolean = Math.abs(guess - prevGuess) < precision
}

