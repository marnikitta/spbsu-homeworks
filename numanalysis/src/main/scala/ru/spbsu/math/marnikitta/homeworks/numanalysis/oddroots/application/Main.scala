package ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.application

import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.impl.{BiSplitRoot, ModifiedNewtonRoot, NewtonRoot, SecantRoot}
import ru.spbsu.math.marnikitta.homeworks.numanalysis.oddroots.domain.{RootOnSegment, RootResult, SegmentSearcher}

/**
  * Created by marnikitta on 11.09.16.
  */
object Main {

  implicit val EPS = 1e-4
  val A = -1e3
  val B = 1e3
  val STEP = 1e-2

  val testCases = Seq(
    TestCase("0.25 * x - sin(x) = 0",
      x => 0.25 * x - math.sin(x),
      x => 0.25 - math.cos(x)
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

  def printRoot(root: RootResult): Unit = {
    println(root.toString)
  }
}
