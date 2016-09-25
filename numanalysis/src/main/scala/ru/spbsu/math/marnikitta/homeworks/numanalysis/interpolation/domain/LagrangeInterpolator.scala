package ru.spbsu.math.marnikitta.homeworks.numanalysis.interpolation.domain

/**
  * Created by marnikitta on 25.09.16.
  */
object LagrangeInterpolator extends Interpolator {
  override def interpolate(points: Seq[(Double, Double)]): Function[Double, Double] = {
    val indexedSeq = points.unzip._1.toIndexedSeq

    points.unzip._2.toStream.zipWithIndex.map(tup => lk(indexedSeq, tup._2) andThen (_ * tup._1))
      .reduceLeft((add1, add2) => x => add1(x) + add2(x))
  }

  private def lk(points: IndexedSeq[Double], k: Int): Function[Double, Double] = {
    assert(points.size > k)
    val xk = points(k)

    points.toStream.zipWithIndex.filter(tup => tup._2 != k).map(_._1)
      .foldLeft((x: Double) => 1d)((res, next) => x => res(x) * (x - next) / (xk - next))
  }
}
