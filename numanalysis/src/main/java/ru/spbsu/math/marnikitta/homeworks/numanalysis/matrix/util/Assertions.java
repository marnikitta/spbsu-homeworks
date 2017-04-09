package ru.spbsu.math.marnikitta.homeworks.numanalysis.matrix.util;

import java.util.List;

/**
 * Created by marnikitta on 09.10.16.
 */
public class Assertions {
  public static void assertSameLength(final List<?> a, final List<?> b) {
    if (a.size() != b.size()) {
      throw new IllegalArgumentException("Lists should have same size");
    }
  }

  public static void assertSameLength(final double[] a, final double[] b) {
    if (a.length != b.length) {
      throw new IllegalArgumentException("Arrays should have same length");
    }
  }

  public static void assertNotEmpty(final double[] a) {
    if (a.length == 0) {
      throw new IllegalArgumentException("Array shouldn't be empty");
    }
  }
}
