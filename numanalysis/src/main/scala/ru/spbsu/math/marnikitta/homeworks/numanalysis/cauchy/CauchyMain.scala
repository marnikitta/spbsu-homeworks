package ru.spbsu.math.marnikitta.homeworks.numanalysis.cauchy

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

  def adams(hist: Seq[(Double, Double)], xs: Seq[Double]): Seq[Double] = {
    var result = ListBuffer[Double]()
    var currentHist = hist
    for (x <- xs) {
      assert(currentHist.size == 5)
      val next = currentHist.last._2 + adamsNextDeplta(currentHist)
      currentHist = currentHist.tail ++ Seq((x, next))
      result += next
    }
    result
  }

  def adamsNextDeplta(tuples: Seq[(Double, Double)]): Double = {
    val q0 = h * f(tuples.head._1, tuples.head._2)
    val q1 = h * f(tuples(1)._1, tuples(1)._2)
    val q2 = h * f(tuples(2)._1, tuples(2)._2)
    val q3 = h * f(tuples(3)._1, tuples(3)._2)
    val q4 = h * f(tuples(4)._1, tuples(4)._2)

    1.0d / 720 * (1901 * q4 - 2774 * q3 + 2616 * q2 - 1274 * q1 + 251 * q0)
  }

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
    val adamsResult = adams(taylorSeq.zip(taylorSeq.map(taylorResult)), adamsSeq)
    println("Adams result:")
    println(adamsResult)
    println("Adams residuals:")
    println(adamsSeq.map(exactResult).zip(adamsResult).map { case (a, b) => Math.abs(a - b) })

    println()

    val rungeSeq = (0 to N).map(k => x0 + k * h)
    val rungeResult = runge(rungeSeq)
    println("Runge result:")
    println(rungeResult)
    println("Runge residuals:")
    println(rungeSeq.map(exactResult).zip(rungeResult).map { case (a, b) => Math.abs(a - b) })

    println()

    val eulerSeq = (0 to N).map(k => x0 + k * h)
    val eulerResult = euler(eulerSeq)
    println("Euler result:")
    println(eulerResult)
    println("Euler residuals:")
    println(eulerSeq.map(exactResult).zip(eulerResult).map { case (a, b) => Math.abs(a - b) })

    println()

    val betterEulerResult = betterEuler(eulerSeq)
    println("Better Euler result:")
    println(betterEulerResult)
    println("Better Euler residuals:")
    println(eulerSeq.map(exactResult).zip(betterEulerResult).map { case (a, b) => Math.abs(a - b) })

    println()

    //Совпадает с Эйлером :(
    val eulerCauchyResult = eulerCauchy(eulerSeq)
    println("Euler Cauchy result:")
    println(eulerCauchyResult)
    println("Euler Caucy residuals:")
    println(eulerSeq.map(exactResult).zip(eulerCauchyResult).map { case (a, b) => Math.abs(a - b) })

    println()

    println("Residual in last point residual:")
    val lastX = x0 + h * N
    val realLastY = exactResult(lastX)
    println("Adam residual:")
    println(Math.abs(realLastY - adamsResult.last))
    println("Runge residual:")
    println(Math.abs(realLastY - rungeResult.last))
    println("Euler residual:")
    println(Math.abs(realLastY - eulerResult.last))
    println("Better euler residual:")
    println(Math.abs(realLastY - betterEulerResult.last))
    println("Euler cauchy residual:")
    println(Math.abs(realLastY - eulerCauchyResult.last))
  }

  def euler(xWithX0: Seq[Double]): Seq[Double] = {
    val res: ListBuffer[Double] = ListBuffer()
    var xn: Double = x0
    var yn: Double = y0
    res += yn
    for (xn <- xWithX0) {
      yn = yn + h * f(xn, yn)
      res += yn
    }
    res
  }

  def eulerCauchy(xWithX0: Seq[Double]): Seq[Double] = {
    val res: ListBuffer[Double] = ListBuffer()
    var xn: Double = x0
    var yn: Double = y0
    res += yn
    for (xn <- xWithX0) {
      val k1 = f(xn, yn)
      val k2 = f(xn + h, yn + h * k1 * k1)
      yn = yn + h * (k1 + k2) / 2
      res += yn
    }
    res
  }

  def betterEuler(xWithX0: Seq[Double]): Seq[Double] = {
    val res: ListBuffer[Double] = ListBuffer()
    var xn: Double = x0
    var yn: Double = y0
    res += yn
    for (xn <- xWithX0) {
      yn = yn + h * f(xn + h / 2, yn + h / 2 * f(xn, yn))
      res += yn
    }
    res
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

  def runge(xWithX0: Seq[Double]): Seq[Double] = {
    val res: ListBuffer[Double] = ListBuffer()
    var xn: Double = x0
    var yn: Double = y0
    res += yn
    for (xn <- xWithX0) {
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
    val result = yn + 1d / 6 * (k1 + 2 * k2 + 2 * k3 + k4)
    result
  }

  def dividedDiff(points: Seq[(Double, Double)]): Seq[Double] = {
    val table: ListBuffer[Seq[((Double, Double), Double)]] = ListBuffer()
    table += points.map(p => ((p._1, p._1), p._2))

    if (points.size >= 2) {
      table += points.sliding(2).map((pair: Seq[(Double, Double)]) => {
        assert(pair.size == 2)
        val left = pair.head
        val right = pair.last
        ((left._1, right._1), (left._2 - right._2) / (left._1 - right._1))
      }).toIndexedSeq
    }

    while (table.last.size != 1) {
      table += table.last.sliding(2).map((pair: Seq[((Double, Double), Double)]) => {
        assert(pair.size == 2)
        val left = pair.head
        val right = pair.last
        ((left._1._1, right._1._2), (left._2 - right._2) / (left._1._1 - right._1._2))
      }).toSeq
    }
    table.map(line => line.head._2)
  }
}
