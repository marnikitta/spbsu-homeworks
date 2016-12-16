package ru.spbsu.math.marnikitta.homeworks.numanalysis.interpolation_equal.application

import java.util.Scanner

import ru.spbsu.math.marnikitta.homeworks.numanalysis.interpolation.domain.LagrangeInterpolator
import ru.spbsu.math.marnikitta.homeworks.numanalysis.interpolation_equal.domain.{BeginningNewton, CenterNewton}

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
    val m = 4
    val degree = 7

    val f: (Double => Double) = x => math.sin(x) - x * x / 2

    val sc = new Scanner(System.in)

    println("Function: \n")
    val step = (b - a) / m
    val args = Stream.iterate(a, m + 1)(a => a + step).toIndexedSeq
    val points = args.zip(args.map(f))
    points.foreach(println)
    val table = diffs(points.map(_._2))

    val beginningNewton = BeginningNewton
    val centerNewton = CenterNewton
    val realNewton = LagrangeInterpolator

    while (true) {

      print("\nTarget point: ")
      val x = sc.nextDouble()

      if (x < a || x > b) {
        println("Out of bounds!")
      } else {
        print("Degree: ")
        val degree = sc.nextInt()

        if (degree >= points.size) {
          println("Degree is greater than available points")
        } else if (x <= a + step) {
          println("Newton for the beginning of the table")
          val func = beginningNewton(table, degree)

          print("Residual: ")
          println(math.abs(func((x - a) / step) - f(x)))

        } else if (x >= b - step) {
          println("Newton for the end of the table")
          val func = beginningNewton(table, degree)
          val funcTest = realNewton(points.take(degree + 1))
          println(func((x - a) / step))
          println(funcTest(x))
        } else {
          println("Euler for the middle of the table")
          val position = ((x - a) / step).toInt

          val func = centerNewton(position, table, degree)

          print("Residual: ")
          println(math.abs(func((x - (a + step * position)) / step) - f(x)))
        }
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
