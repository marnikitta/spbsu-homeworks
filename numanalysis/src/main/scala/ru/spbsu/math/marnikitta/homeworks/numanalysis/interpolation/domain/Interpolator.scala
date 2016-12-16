package ru.spbsu.math.marnikitta.homeworks.numanalysis.interpolation.domain

/**
  * Created by marnikitta on 25.09.16.
  */
trait Interpolator {
  def apply(points: Seq[(Double, Double)]): Function[Double, Double]
}
