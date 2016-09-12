package org.marnikitta.spbsu.numanalysis.oddroots.application

import org.marnikitta.spbsu.numanalysis.oddroots.domain.impl.{BiSplitRoot, ModifiedNewtonRoot, NewtonRoot, SecantRoot}
import org.marnikitta.spbsu.numanalysis.oddroots.domain.{RootOnSegment, RootSearchStatistics, SegmentSearcher}

/**
  * Created by marnikitta on 11.09.16.
  */
object Main {

  implicit val EPS = 1e-6
  val A = -1e3
  val B = 1e3
  val STEP = 1e-3

  val testCases = Seq(
    TestCase("x^3", x => math.pow(x, 3), x => 3 * x * x),
    TestCase("x^3 - 30x^2 + 2552",
      x => math.pow(x, 3) - 30 * math.pow(x, 2) + 2552,
      x => 3 * math.pow(x, 2) - 30 * 2 * x
    ),
    TestCase("x^4 - 16x^3 + 500x^2 - 8000x + 32000",
      x => math.pow(x, 4) - 16 * math.pow(x, 3) + 500 * math.pow(x, 2) - 8000 * x + 32000,
      x => 4 * math.pow(x, 3) - 16 * 3 * math.pow(x, 2) + 500 * 2 * x - 8000
    )
  )

  def main(args: Array[String]): Unit = {
    println("A = %f B = %f EPS = %f".format(A, B, EPS))
    println()

    testCases.foreach(c => run("Newton", new NewtonRoot, c))
    testCases.foreach(c => run("Modified Newton", new ModifiedNewtonRoot, c))
    testCases.foreach(c => run("Secant", new SecantRoot, c))
    testCases.foreach(c => run("Bisector", new BiSplitRoot, c))
  }

  def run(title: String, solver: RootOnSegment, testCase: TestCase): Unit = {
    println(title + " : " + testCase.title)

    val segments = new SegmentSearcher(A, B, STEP).segments(testCase.f)

    println("Segments containing roots: %s".format(segments))

    val roots = segments.toStream.map(s => solver.root(testCase.f, testCase.df, s)).toList
    roots.foreach(println)
    println()
  }

  def printRoot(root: RootSearchStatistics): Unit = {
    println(root.toString)
  }
}
