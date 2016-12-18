package ru.spbsu.math.marnikitta.homeworks.numanalysis.equalinterpolation.domain

/**
  * Created by marnikitta on 12/16/16.
  */
object EndingNewton {
  def apply(table: Seq[Seq[Double]], power: Int): Double => Double = {
    val y0s = table.map(_.last).take(power + 1)
    val powers = dividedPowers(power)
    assert(y0s.size == powers.size)
    y0s.zip(powers).map(p => (x: Double) => p._1 * p._2(x))
      .foldLeft((x: Double) => 0d)((cumm, add) => x => cumm(x) + add(x))
  }

  def dividedPowers(n: Int): Seq[Double => Double] = {
    (0 to n).map(n => (x: Double) => powerTaylor(n)(x) / factorial(n))
  }

  private def powerTaylor(n: Int): Double => Double = {
    (1 to n).foldLeft((x: Double) => 1d)((func, xi) => x => func(x) * (x + xi - 1))
  }

  private def factorial(n: Int): Long = n match {
    case x if x <= 1 => 1
    case x => x * factorial(x - 1)
  }
}
