package ru.spbsu.math.marnikitta.homeworks.numanalysis.cauchy

import ru.spbsu.math.marnikitta.homeworks.numanalysis.interpolation.domain.NewtonInterpolator

import scala.collection.mutable.ListBuffer

object CauchyMain {
  val x0 = 0
  val y0 = 1
  val h = 0.1
  val f: (Double, Double) => Double = {
    case (x, y) => -2 * y + y * y
  }
  val N = 10
  val exactResult: Double => Double = x => 2 / (Math.exp(2 * x) + 1)

  def main(args: Array[String]): Unit = {
    val seq = (-2 to N).map(k => x0 + k * h)
    println("Points:")
    println(seq)

    println("Exact result:")
    println(seq.map(exactResult))

    println()

    val taylorSeq = (-2 to 2).map(k => x0 + k * h)
    println("Taylor result:")
    println(taylorSeq.map(taylorResult))
    println("Taylor residuals:")
    println(taylorSeq.map(exactResult).zip(taylorSeq.map(taylorResult)).map { case (a, b) => Math.abs(a - b) })

    println()

    val adamsSeq = (3 to N).map(k => x0 + k * h)
    val adamsResult = NewtonInterpolator(seq.zip(seq.map(taylorResult)))
    println("Adams result:")
    println(adamsSeq.map(adamsResult))
    println("Adams residuals:")
    println(adamsSeq.map(exactResult).zip(adamsSeq.map(adamsResult)).map { case (a, b) => Math.abs(a - b) })

    println()

    val rungeSeq = (1 to N).map(k => x0 + k * h)
    val rungeResult = runge(rungeSeq)
    println("Runge result:")
    println(rungeResult)
    println("Runge residuals:")
    println(rungeSeq.map(exactResult).zip(rungeResult).map { case (a, b) => Math.abs(a - b) })
  }

  lazy val taylorResult: Double => Double = {
    val y = 1
    val dy = -2 * y + y * y
    val ddy = (2 * y - 2) * dy
    val dddy = (2 * y - 2) * ddy + 2 * dy * dy
    val ddddy = (2 * y - 2) * dddy + 6 * dy * ddy
    val dddddy = (2 * y - 1) * ddddy + 3 * ddy * ddy + 4 * dddy * dy

    x =>
      (y
        + dy * (x - x0)
        + ddy * Math.pow(x - x0, 2) / 2
        + dddy * Math.pow(x - x0, 3) / 6
        + ddddy * Math.pow(x - x0, 4) / 24
        + ddddy * Math.pow(x - x0, 5) / 120)
  }

  def runge(xWithoutX0: Seq[Double]): Seq[Double] = {
    val res: ListBuffer[Double] = ListBuffer()
    var xn: Double = x0
    var yn: Double = y0
    for (xn <-  xWithoutX0) {
      yn = rungeStep(xn, yn)
      res += yn
    }
    res
  }

  def rungeStep(xn: Double, yn: Double): Double = {
    val k1 = h * f(xn, yn)
    val k2 = h * f(xn + h / 2, yn + k1 / 2)
    val k3 = h * f(xn + h / 2, yn + k2 / 2)
    val k4 = h * f(xn + h, yn + k3)
    yn + 1 / 6 * (k1 + 2 * k2 + 2 * k3 + k4)
  }
}
