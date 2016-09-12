package org.marnikitta.spbsu.numanalysis.oddroots.domain

/**
  * Created by marnikitta on 07.09.16.
  */
trait RootOnSegment {
  /**
    * @param f       target function
    * @param dt      first derivative
    * @param segment segment with root
    * @return result
    */
  def root(f: Double => Double, dt: Double => Double, segment: (Double, Double)): RootResult
}
