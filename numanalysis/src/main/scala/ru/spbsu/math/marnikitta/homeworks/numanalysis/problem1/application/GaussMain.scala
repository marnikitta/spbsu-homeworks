package ru.spbsu.math.marnikitta.homeworks.numanalysis.problem1.application

import breeze.linalg._
import breeze.numerics._
import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.SegmentSearcher
import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.impl.BiSplitRoot

/**
  * Created by marnikitta on 12/18/16.
  */
object GaussMain {
  def main(args: Array[String]): Unit = {
    val w: Double => Double = math.sqrt
    val l = 0
    val r = 1

    val mu0 = 2d / 3
    val mu1 = 2d / 5
    val mu2 = 2d / 7
    val mu3 = 2d / 9
    val mu4 = 2d / 11
    val mu5 = 2d / 13

    val f: Double => Double = math.sin
    val integral = 0.364222
    //    val f: Double => Double = x => math.pow(x, 5) - math.pow(x, 4) + 21 * math.pow(x, 3) - 2 * x * x + x + 2
    //    val integral = 29032d / 5005

    val polyMatrix = DenseMatrix((mu2, mu1, mu0), (mu3, mu2, mu1), (mu4, mu3, mu2))
    val polyB = DenseVector(-mu3, -mu4, -mu5)

    val polyCoefs = polyMatrix \ polyB

    val a = polyCoefs.apply(0)
    val b = polyCoefs.apply(1)
    val c = polyCoefs.apply(2)
    println("a = %f, b = %f, c = %f".format(a, b, c))

    val ortoPolynom: Double => Double = x => (x * x * x) + a * (x * x) + b * x + c
    val dOrtoPolynom: Double => Double = x => (x * x) / 3 + a * x / 2 + b

    val roots = findRoots(ortoPolynom, dOrtoPolynom, l, r)
    println("Roots: %s".format(roots))

    val aMatrix = DenseMatrix(
      (1d, 1d, 1d),
      (roots.head, roots(1), roots(2)),
      (roots.head * roots.head, roots(1) * roots(1), roots(2) * roots(2)))

    val aVector = DenseVector(mu0, mu1, mu2)

    val coefs = aMatrix \ aVector
    val a1 = coefs(0)
    val a2 = coefs(1)
    val a3 = coefs(2)
    println("a1 = %f, a2 = %f, a3 = %f".format(a1, a2, a3))

    val quadrat = a1 * f(roots.head) + a2 * f(roots(1)) + a3 * f(roots(2))
    println(quadrat)
    println("Residual = %.20f" format math.abs(quadrat - integral))
  }

  def findRoots(f: Double => Double, df: Double => Double, l: Double, r: Double): Seq[Double] = {
    implicit val presision = 1e-7
    val rootSearcher = new BiSplitRoot()
    val segments = new SegmentSearcher(l, r, 1e-4).segments(f)
    segments.map(rootSearcher.root(f, df, _)).map(_.solution).toSeq
  }
}
