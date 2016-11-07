package ru.spbsu.math.marnikitta.homeworks.numanalysis.quadrature

/**
  * Created by marnikitta on 07.11.16.
  */
object Main {
  val a = 5d
  val b = 10d
  val m = 10
  val w: Double => Double = x => 1
  val f: Double => Double = x => Math.log(x) - Math.sin(x)
  val target = 8.85592765323028

  def main(args: Array[String]): Unit = {
    println("A = %f, B = %f, m = %d, target = %f\n".format(a, b, m, target))
    val step = (b - a) / m
    val points = Stream.iterate(a, m + 1)(x => x + step).toIndexedSeq

    val rectIntegral = points.toStream.drop(1).map(_ - step / 2).map(f).sum * step
    println("Rectangle integral = %f, residual = %f\n".format(rectIntegral, Math.abs(target - rectIntegral)))

    val trapezeIntegral = step * (points.toStream.drop(1).dropRight(1).map(f).sum + (f(points.head) + f(points.last)) / 2)
    println("Trapeze integral = %f, residual = %f\n".format(trapezeIntegral, Math.abs(target - trapezeIntegral)))

    val parabolaIntegral = (b - a) / (3 * m) * (points.toStream.zipWithIndex.drop(1).dropRight(1)
      .map(t => (if (t._2 % 2 == 0) 2 else 4) * f(t._1)).sum + f(points.head) + f(points.last))
    println("Parabola integral = %f, residual = %f\n".format(parabolaIntegral, Math.abs(target - parabolaIntegral)))
  }
}
