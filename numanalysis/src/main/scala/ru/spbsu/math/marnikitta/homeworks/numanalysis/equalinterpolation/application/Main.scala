package ru.spbsu.math.marnikitta.homeworks.numanalysis.equalinterpolation.application

import java.util.Scanner

import ru.spbsu.math.marnikitta.homeworks.numanalysis.equalinterpolation.domain.{BeginningNewton, CenterNewton, EndingNewton}
import ru.spbsu.math.marnikitta.homeworks.numanalysis.interpolation.domain.LagrangeInterpolator

import scala.collection.mutable.ListBuffer

/**
  * Created by marnikitta on 26.09.16.
  */
object Main {

  def main(args: Array[String]): Unit = {
    test()
  }

  def test(): Unit = {
    val a = 0d
    val b = 1d
    val m = 10

    val f: (Double => Double) = x => math.sin(x) + x * x / 2

    val sc = new Scanner(System.in)

    println("Function:")
    val step = (b - a) / m
    val args = Stream.iterate(a, m + 1)(a => a + step).toIndexedSeq
    val points = args.zip(args.map(f))
    points.foreach(println)
    val table = diffs(points.map(_._2))

    val beginningNewton = BeginningNewton
    val endingNewton = EndingNewton
    val centerNewton = CenterNewton
    val realNewton = LagrangeInterpolator

    var degree = -1
    while (degree < 0 || degree > m) {
      println("Degree should be in [%d, %d]".format(1, m))
      print("Degree: ")
      degree = sc.nextInt()
    }


    val centerLeft = a + ((degree + 1) / 2) * step
    val centerRight = b - ((degree + 1) / 2) * step
    println("Target point should be in [%f, %f], [%f, %f] or [%f, %f]"
      .format(a, a + step, centerLeft, centerRight, b - step, b))


    while (true) {
      print("Target point: ")
      val x = sc.nextDouble()

      if (x <= a + step && x >= a) {
        println("Newton for the beginning of the table")
        val func = beginningNewton(table, degree)
        println("Residual: %.20f".format(math.abs(func((x - a) / step) - f(x))))
      } else if (x >= b - step && x <= b) {
        println("Newton for the end of the table")
        val func = endingNewton(table, degree)
        println("Residual: %.20f".format(math.abs(func((x - b) / step) - f(x))))
      } else if (x >= centerLeft && x <= centerRight) {
        println("Euler for the middle of the table")
        val position = ((x - a) / step).toInt
        val func = centerNewton(position, table, degree)
        println("Residual: %.20f".format(math.abs(func((x - (a + step * position)) / step) - f(x))))
      } else {
        println("Point is out of bounds!")
      }
    }
  }

  def diffs(points: Seq[Double]): Seq[Seq[Double]] = {
    val table: ListBuffer[Seq[Double]] = ListBuffer()
    table += points

    while (table.last.size != 1) {
      table += table.last.sliding(2).map((pair: Seq[Double]) => {
        assert(pair.size == 2)
        pair.last - pair.head
      }).toSeq
    }
    table
  }

}
