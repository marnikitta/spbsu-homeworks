package ru.spbsu.math.marnikitta.homeworks.numanalysis.numberdiff

/**
  * Created by marnikitta on 25.10.16.
  */
object Main {
  val f: Double => Double = x => Math.log(x)
  val df: Double => Double = x => 1 / x
  val df2: Double => Double = x => - 1 / (x * x)

  val a = 10d
  val b = 10.01d
  val steps = 10

  def main(args: Array[String]): Unit = {
    val step = (b - a) / steps
    val args = Stream.iterate(a, steps + 1)(a => a + step).toIndexedSeq.sliding(3).toIndexedSeq

    val first = args.head
    val body = args.reverse.tail.reverse
    val last = args.last

    println("All points:")

    println((first.head,
      f(first.head),
      leftDerivative(f, first(0), first(1), first(2), step),
      Math.abs(df(first(0)) - leftDerivative(f, first(0), first(1), first(2), step)),
      leftSecondDerivative(f, first(0), first(1), first(2), step),
      Math.abs(df2(first(0)) - leftSecondDerivative(f, first(0), first(1), first(2), step))
      ))

    body.foreach(line =>
      println((line(1),
        f(line(1)),
        derevative(f, line(0), line(1), line(2), step),
        Math.abs(df(line(0)) - derevative(f, line(0), line(1), line(2), step)),
        secondDerevative(f, line(0), line(1), line(2), step),
        Math.abs(df2(line(0)) - secondDerevative(f, line(0), line(1), line(2), step))
        ))
    )

    println((last.last,
      f(last.last),
      rightDerivative(f, last(0), last(1), last(2), step),
      Math.abs(df(last(0)) - rightDerivative(f, last(0), last(1), last(2), step)),
      rightSecondDerivative(f, last(0), last(1), last(2), step),
      Math.abs(df2(first(0)) - rightSecondDerivative(f, last(0), last(1), last(2), step))
      ))
  }

  def leftSecondDerivative(f: Double => Double, a: Double, x2: Double, x3: Double, step: Double): Double =
    (f(a) - 2 * f(x2) + f(x3)) / (step * step)

  def rightSecondDerivative(f: Double => Double, x1: Double, x2: Double, a: Double, step: Double): Double =
    (f(a) - 2 * f(x2) + f(x1)) / (step * step)

  def secondDerevative(f: Double => Double, x1: Double, a: Double, x2: Double, step: Double): Double =
    (f(x1) - 2 * f(a) + f(x2)) / (step * step)

  def leftDerivative(f: Double => Double, a: Double, x2: Double, x3: Double, step: Double): Double =
    (-3 * f(a) + 4 * f(x2) - f(x3)) / (2 * step)

  def rightDerivative(f: Double => Double, x1: Double, x2: Double, a: Double, step: Double): Double =
    (-3 * f(a) + 4 * f(x2) - f(x1)) / (-2 * step)

  def derevative(f: Double => Double, x1: Double, a: Double, x2: Double, step: Double): Double =
    (f(x2) - f(x1)) / (2 * step)
}
