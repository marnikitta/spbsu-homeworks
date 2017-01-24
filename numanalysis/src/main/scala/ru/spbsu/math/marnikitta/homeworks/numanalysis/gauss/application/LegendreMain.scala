package ru.spbsu.math.marnikitta.homeworks.numanalysis.gauss.application

import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.SegmentSearcher
import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.impl.BiSplitRoot

/**
  * Created by marnikitta on 12/18/16.
  */
object LegendreMain {
  def main(args: Array[String]): Unit = {

    val f: Double => Double = x => math.sqrt((x + 1) / 2) * math.sin((x + 1) / 2) / 2
    val integral = 0.364222
    //    val f: Double => Double = x => math.pow(x, 5) - math.pow(x, 4) + 21 * math.pow(x, 3) - 2 * x * x + x + 2
    //    val integral = 34d/15

    val k = 1d / 2
    val b = 1d / 2

    val poly = polyLeg(3)
    val dPoly = dPolyLeg(3)
    val roots = findRoots(poly, dPoly, -1, 1)
    val coefs = roots.map(t => 2 / (1 - t * t) / math.pow(dPoly(t), 2))
    println("Roots: %s".format(roots))
    println("Coefs: %s".format(coefs))

    val quadrat = roots.map(f).zip(coefs).map(p => p._1 * p._2).foldLeft(0d)((seed, add) => seed + add)

    //    val quadrat = a1 * f(roots.head) + a2 * f(roots(1)) + a3 * f(roots(2))
    println(quadrat)
    println("Residual = %.20f" format math.abs(quadrat - integral))
  }

  def dPolyLeg(n: Int): Double => Double = x => n.toDouble / (1 - x * x) * (polyLeg(n - 1)(x) - x * polyLeg(n)(x))

  def polyLeg(n: Int): Double => Double = n match {
    case 0 => x => 1d
    case 1 => x => x
    case m if m > 1 => x => (2d * m - 1) / m * x * polyLeg(m - 1)(x) - (m - 1d) / m * polyLeg(m - 2)(x)
  }

  def findRoots(f: Double => Double, df: Double => Double, l: Double, r: Double): Seq[Double] = {
    implicit val presision = 1e-7
    val rootSearcher = new BiSplitRoot()
    val segments = new SegmentSearcher(l, r, 1e-4).segments(f)
    segments.map(rootSearcher.root(f, df, _)).map(_.solution).toSeq
  }
}
