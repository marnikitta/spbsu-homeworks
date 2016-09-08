package org.marnikitta.spbsu.numanalysis.oddroots

/**
  * Created by marnikitta on 07.09.16.
  */
trait RootOnSegment {
  def root(f: Double => Double, dt: Double => Double, segment: (Double, Double)): Double
}
