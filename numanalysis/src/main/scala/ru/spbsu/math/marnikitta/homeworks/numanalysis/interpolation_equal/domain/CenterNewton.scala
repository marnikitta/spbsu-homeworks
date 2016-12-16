package ru.spbsu.math.marnikitta.homeworks.numanalysis.interpolation_equal.domain

/**
  * Created by marnikitta on 12/16/16.
  */
object CenterNewton {
  def apply(position: Int, table: Seq[Seq[Double]], degree: Int): Double => Double = {
    val y0s = takeFromTable(degree, position, table)
    val powers = dividedPowers(degree)
    assert(y0s.size == powers.size)
    y0s.zip(powers).map(p => (x: Double) => p._1 * p._2(x))
      .foldLeft((x: Double) => 0d)((cumm, add) => x => cumm(x) + add(x))
  }

  def takeFromTable(degree: Int, position: Int, table: Seq[Seq[Double]]): Seq[Double] =
    for (k <- 0 to degree) yield table(k)(position - k / 2)


  def dividedPowers(n: Int): Seq[Double => Double] = {
    (0 to n).map(n => (x: Double) => powerTaylor(n)(x) / factorial(n))
  }

  private def powerTaylor(n: Int): Double => Double = {
    (1 to n).foldLeft((x: Double) => 1d)((func, xi) => x => func(x) * (x - math.pow(-1, xi) * math.floor(xi / 2)))
  }

  private def factorial(n: Int): Long = n match {
    case x if x <= 1 => 1
    case x => x * factorial(x - 1)
  }
}
