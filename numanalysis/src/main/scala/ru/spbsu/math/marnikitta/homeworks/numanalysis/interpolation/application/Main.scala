package ru.spbsu.math.marnikitta.homeworks.numanalysis.interpolation.application

import com.quantifind.charts.Highcharts._
import ru.spbsu.math.marnikitta.homeworks.numanalysis.interpolation.domain.{LagrangeInterpolator, NewtonInterpolator}

/**
  * Created by marnikitta on 26.09.16.
  */
object Main {

  def main(args: Array[String]): Unit = {
    println()
    test()
  }

  def test(): Unit = {
    val a = -1d
    val b = 1d
    val m = 8
    val target = 0

    val f: (Double => Double) = x => 1 / (1 + 25 * x * x)

    val step = (b - a) / m
    val args = Stream.iterate(a, m + 1)(a => a + step).toIndexedSeq
    val points = args.zip(args.map(f))

    val lagrange = LagrangeInterpolator.apply(points)
    val newton = NewtonInterpolator.apply(points)

    plot(a to b by step / 1000, f)
    hold()
    plot(a to b by step / 1000, newton)
    hold()

    val m1 = 10
    val step1 = (b - a) / m1
    val args1 = Stream.iterate(a, m1 + 1)(a => a + step1).toIndexedSeq
    val points1 = args1.zip(args1.map(f))

    val newton1 = NewtonInterpolator.apply(points1)
    plot(a to b by step1 / 1000, newton1)
  }

  def plot(points: Seq[Double], f: Double => Double): Unit = {
    line(points, points.map(f))
  }
}

