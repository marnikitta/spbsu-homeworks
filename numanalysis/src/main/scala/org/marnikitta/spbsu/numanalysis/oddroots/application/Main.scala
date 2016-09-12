package org.marnikitta.spbsu.numanalysis.oddroots.application

import org.marnikitta.spbsu.numanalysis.oddroots.domain.impl.NewtonRoot
import org.marnikitta.spbsu.numanalysis.oddroots.domain.{RootOnSegment, RootSearchStatistics, SegmentSearcher}

/**
  * Created by marnikitta on 11.09.16.
  */
object Main {
  implicit val EPS = 1e-6
  val A = -1e3
  val B = 1e3

  val STEP = 1e-1

  def main(args: Array[String]): Unit = {
    run(new NewtonRoot, TestCase("x^3", x => math.pow(x, 3), x => 3 * x * x))
  }

  def run(solver: RootOnSegment, testCase: TestCase): Unit = {
    println("Case: " + testCase.title)
    println("A = %f B = %f EPS = %f".format(A, B, EPS))

    val segments = new SegmentSearcher(A, B, STEP).segments(testCase.f)

    println("Segments containing roots:")
    println(segments)

    val roots = segments.toStream.map(s => solver.root(testCase.f, testCase.df, s)).toList
    roots.foreach(println)
  }

  def printRoot(root: RootSearchStatistics): Unit = {
    println(root.toString)
  }
}
