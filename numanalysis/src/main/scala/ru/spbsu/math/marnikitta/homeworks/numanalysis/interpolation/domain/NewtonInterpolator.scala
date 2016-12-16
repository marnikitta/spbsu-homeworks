package ru.spbsu.math.marnikitta.homeworks.numanalysis.interpolation.domain

import scala.collection.mutable.ListBuffer

/**
  * Created by marnikitta on 12/15/16.
  */
object NewtonInterpolator extends Interpolator {
  override def apply(points: Seq[(Double, Double)]): Function[Double, Double] = {
    val diffs = dividedDiff(points)
    val m = multies(points.map(_._1))
    assert(m.head(122) == 1)
    assert(diffs.size == m.size)
    diffs.zip(m).map(p => (x: Double) => p._1 * p._2(x))
      .foldLeft((x: Double) => 0d)((res, f) => x => res(x) + f(x))
  }

  def multies(list: Seq[Double]): Seq[Double => Double] = {
    val buff = ListBuffer[Double => Double]()
    for (i <- list.indices) {
      buff += multi(list.take(i))
    }
    buff
  }

  def multi(list: Seq[Double]): Double => Double =
    list.foldLeft((x: Double) => 1d)((func, xi) => x => func(x) * (x - xi))


  def dividedDiff(points: Seq[(Double, Double)]): Seq[Double] = {
    val table: ListBuffer[Seq[((Double, Double), Double)]] = ListBuffer()
    table += points.map(p => ((p._1, p._1), p._2))

    if (points.size >= 2) {
      table += points.sliding(2).map((pair: Seq[(Double, Double)]) => {
        assert(pair.size == 2)
        val left = pair.head
        val right = pair.last
        ((left._1, right._1), (left._2 - right._2) / (left._1 - right._1))
      }).toIndexedSeq
    }

    while (table.last.size != 1) {
      table += table.last.sliding(2).map((pair: Seq[((Double, Double), Double)]) => {
        assert(pair.size == 2)
        val left = pair.head
        val right = pair.last
        ((left._1._1, right._1._2), (left._2 - right._2) / (left._1._1 - right._1._2))
      }).toSeq
    }
    table.map(line => line.head._2)
  }
}
