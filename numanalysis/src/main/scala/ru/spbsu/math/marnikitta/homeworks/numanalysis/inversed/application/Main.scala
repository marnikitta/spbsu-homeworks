package ru.spbsu.math.marnikitta.homeworks.numanalysis.inversed.application

import ru.spbsu.math.marnikitta.homeworks.numanalysis.interpolation.domain.LagrangeInterpolator
import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.SegmentSearcher
import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.impl.BiSplitRoot

/**
  * Created by marnikitta on 24.10.16.
  */
object Main {
  val f: Double => Double = x => Math.sin(x) + x * x
  val target = 1.8d

  val a = 0d
  val b = 1d
  val steps = 10

  val power = 5
  val eps = 1e-1

  implicit val presision = 1e-8


  def main(args: Array[String]): Unit = {
    val step = (b - a) / steps
    val args = Stream.iterate(a, steps + 1)(a => a + step).toIndexedSeq
    val points = args.zip(args.map(f))

    println("All points:")
    points.foreach(p => println(p))

    println("\nTask 1\n")
    taskOne(points)

    println("\nTask 2\n")
    taskTwo(points)
  }

  def taskTwo(points: Seq[(Double, Double)]): Unit = {
    val chosen = points.sortBy(a => Math.abs(a._2 - target)).take(power + 1)

    val qn = LagrangeInterpolator.interpolate(chosen)
    val rootSearcher = new BiSplitRoot()

    val segments = new SegmentSearcher(chosen.minBy(_._1)._1, chosen.maxBy(_._1)._1, eps).segments(x => qn(x) - target)
    val roots = segments.map(s => rootSearcher.root(x => qn(x) - target, (x: Double) => x, s)).map(_.solution)

    println("f^-1(target) = " + roots)
    println("Residuals: " + roots.map(r => Math.abs(target - f(r))))
  }

  def taskOne(points: Seq[(Double, Double)]): Unit = {
//    println("Nearest points:")
    val chosen = points.sortBy(a => Math.abs(a._2 - target)).take(power + 1)
//    println(chosen)

    val qn = LagrangeInterpolator.interpolate(chosen.map(_.swap))
    println("f^-1(target) = " + qn(target))
    println("Residual = " + Math.abs(target - f(qn(target))))
  }
}
