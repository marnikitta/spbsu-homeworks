package org.marnikitta.spbsu.numanalysis.oddroots.domain

import scala.collection.mutable

/**
  * Created by marnikitta on 11.09.16.
  */
class SegmentSearcher(a: Double, b: Double, step: Double) {

  /**
    * @param f target function which roots we want to find
    * @return collection of segments with roots
    */
  def segments(f: Double => Double): mutable.Iterable[(Double, Double)] = {
    val result = mutable.Set[(Double, Double)]()
    var currentSegment = (a, a + step)

    while (currentSegment._1 < b) {
      if (f(currentSegment._1) * f(currentSegment._2) <= 0) result.add(currentSegment)
      currentSegment = (currentSegment._2, currentSegment._2 + step)
    }

    result
  }
}

