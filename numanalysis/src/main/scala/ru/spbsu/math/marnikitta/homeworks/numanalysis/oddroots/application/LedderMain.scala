package ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.application

import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.SegmentSearcher
import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.impl.NewtonRoot

/**
  * Created by marnikitta on 13/09/16.
  */
object LedderMain {
  implicit val EPS = 1e-6
  val A = -1e3
  val B = 1e3
  val STEP = 1e-3

  def main(args: Array[String]) {
    println("x^4 - 16x^3 + 500x^2 - 8000x + 32000 = 0")
    val f: Double => Double = x => math.pow(x, 4) - 16 * math.pow(x, 3) + 500 * math.pow(x, 2) - 8000 * x + 32000
    val df: Double => Double = x => 4 * math.pow(x, 3) - 16 * 3 * math.pow(x, 2) + 500 * 2 * x - 8000

    val segs = new SegmentSearcher(A, B, STEP).segments(f)
    println("Segments with roots")
    println(segs)
    println()

    val root = segs.map(s => new NewtonRoot().root(f, df, s))
    println("Roots")
    root.foreach(println)
    println()

    println("Distance between walls")
    root.filter(r => r.solution >= 8 && r.solution < 20).foreach(r => println(math.sqrt(400 - math.pow(r.solution, 2))))
  }
}
