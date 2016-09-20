package ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.application

import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.SegmentSearcher
import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.impl.NewtonRoot

/**
  * Created by marnikitta on 13/09/16.
  */
object BallMain {
  implicit val EPS = 1e-6
  val A = -1e3
  val B = 1e3
  val STEP = 1e-3

  def main(args: Array[String]): Unit = {
    val r = 10
    println("po = 0.9 r = 10\nx^3 - 3x^2 * 100 + 4 * 1000 * 0.90 = 0")
    println()
    val f: Double => Double = x => math.pow(x, 3) - 3 * math.pow(x, 2) * r * r + 4 * r * r * r * 0.9
    val df: Double => Double = x => 3 * math.pow(x, 2) - 3 * 2 * 100 * x

    val segs = new SegmentSearcher(A, B, STEP).segments(f)
    println("Segments with roots")
    println(segs)
    println()

    val root = segs.map(s => new NewtonRoot().root(f, df, s))
    println("Roots")
    root.foreach(println)
    println()

    println("Depth")
    root.filter(r => r.solution <= 10 && r.solution >= 0).foreach(r => println(r.solution))
  }
}
