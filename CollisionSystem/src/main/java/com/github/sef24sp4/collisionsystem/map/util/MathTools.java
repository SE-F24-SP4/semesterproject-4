package com.github.sef24sp4.collisionsystem.map.util;

import com.github.sef24sp4.common.vector.IVector;

/**
 * A collection of helper tools for complex calculations.
 */
public final class MathTools {
	public static final double DEFAULT_TOLERANCE = 0.000_000_000_2;

	/**
	 * Check if a {@link IVector point} is between two other points drawn on a straight line.
	 *
	 * @param a         The start {@link IVector point}.
	 * @param point     The {@link IVector point} to check for.
	 * @param b         The end {@link IVector point}.
	 * @param tolerance The allowed tolerance when doing the check. See {@link #isAlmostEqualTo(double, double, double)}.
	 * @return {@code true} if {@link IVector point} is between {@link IVector a} and {@link IVector b}.
	 * {@code false} otherwise.
	 * @see <a href="https://stackoverflow.com/a/17693146">StackOverflow: Check is a point (x,y) is between two points drawn on a straight line</a>
	 */
	public static boolean isPointBetween(final IVector a, final IVector point, final IVector b, final double tolerance) {
		final double distanceBetweenAB = a.getVectorTo(b).getNorm();
		final double sumDistance = a.getVectorTo(point).getNorm() + point.getVectorTo(b).getNorm();
		return isAlmostEqualTo(sumDistance, distanceBetweenAB, tolerance);
	}

	public static boolean isPointBetween(final IVector a, final IVector point, final IVector b) {
		return isPointBetween(a, point, b, DEFAULT_TOLERANCE);
	}

	/**
	 * Check if a {@code value} is almost equal to an {@code expected} value given a desired {@code tolerance}.
	 *
	 * @param value     The value to test for.
	 * @param expected  The expected value.
	 * @param tolerance The allowed tolerance. Must be a positive number.
	 * @return {@code true} if value is within the {@code tolerance} margins of the {@code expected} value.
	 * {@code false} otherwise.
	 * @throws IllegalArgumentException If {@code tolerance} is negative.
	 */
	public static boolean isAlmostEqualTo(final double value, final double expected, final double tolerance) {
		if (tolerance < 0) throw new IllegalArgumentException("tolerance cannot be negative");
		return Math.abs(expected - value) <= tolerance;
	}
}
