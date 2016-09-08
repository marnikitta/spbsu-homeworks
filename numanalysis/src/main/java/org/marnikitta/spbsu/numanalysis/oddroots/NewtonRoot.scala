package org.marnikitta.spbsu.numanalysis.oddroots

/**
  * Created by marnikitta on 07.09.16.
  */
class NewtonRoot(eps: Double) extends RootOnSegment {
  override def root(f: (Double) => Double, df: Double => Double, segment: (Double, Double)): Double = {
    if (isGoodEnough(segment._1, segment._2)) segment._1
    else root(f, df, (improve(f, df, segment._1), segment._2))
  }

  private def improve(f: Double => Double, df: Double => Double, guess: Double): Double = guess - f(guess) / df(guess)

  private def isGoodEnough(guess: Double, prevGuess: Double): Boolean = Math.abs(guess - prevGuess) < eps
}
