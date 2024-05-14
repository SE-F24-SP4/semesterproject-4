package com.github.sef24sp4.collisionsystem.map.util;

import com.github.sef24sp4.common.vector.BasicVector;
import com.github.sef24sp4.common.vector.IVector;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MathToolsTest {

	/**
	 * Argument source for {@link #isPointBetween(IVector, IVector, IVector)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> isPointBetween() {
		return Stream.of(
				Arguments.of(new BasicVector(2, 5), new BasicVector(3, 3.5), new BasicVector(4, 2)),
				Arguments.of(new BasicVector(2, 5), new BasicVector(3.8, 2.3), new BasicVector(4, 2)),
				Arguments.of(new BasicVector(2, 5), new BasicVector(3.7, 2.45), new BasicVector(4, 2)),
				Arguments.of(new BasicVector(2, 5), new BasicVector(2, 5), new BasicVector(4, 2)),
				Arguments.of(new BasicVector(2, 5), new BasicVector(3.97936, 2.03096), new BasicVector(4, 2)),
				Arguments.of(new BasicVector(2, 5), new BasicVector(4, 2), new BasicVector(4, 2))
		);
	}

	/**
	 * Argument source for {@link #pointIsNotBetween(IVector, IVector, IVector)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> pointIsNotBetween() {
		return Stream.of(
				Arguments.of(new BasicVector(2, 5), new BasicVector(3.98, 2.032), new BasicVector(4, 2)),
				Arguments.of(new BasicVector(2, 5), new BasicVector(3.97934, 2.031), new BasicVector(4, 2)),
				Arguments.of(new BasicVector(2, 5), new BasicVector(4.04, 1.94), new BasicVector(4, 2)),
				Arguments.of(new BasicVector(2, 5), new BasicVector(4.008, 1.988), new BasicVector(4, 2))
		);
	}

	/**
	 * Argument source for {@link #isAlmostEqualTo(double, double, double)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> isAlmostEqualTo() {
		return Stream.of(
				Arguments.of(-5, -4.5, 1),
				Arguments.of(-5, -4.5, 0.5),
				Arguments.of(-4, -4.5, 0.5),
				Arguments.of(4, 4.5, 0.5),
				Arguments.of(4.5, 4, 0.5),
				Arguments.of(4.2, 4.2, 0),
				Arguments.of(4.2, 4.1, 0.2)
		);
	}

	/**
	 * Argument source for {@link #isNotAlmostEqualTo(double, double, double)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> isNotAlmostEqualTo() {
		return Stream.of(
				Arguments.of(-5, -3.5, 1),
				Arguments.of(-5.1, -4.5, 0.5),
				Arguments.of(-4, -4.500000000000001, 0.5),
				Arguments.of(4, 4.6, 0.5),
				Arguments.of(4.5, 4, 0.49),
				Arguments.of(4.2, 4.2001, 0),
				Arguments.of(4.21, 4, 0.2)
		);
	}

	@ParameterizedTest
	@MethodSource
	void isPointBetween(final IVector a, final IVector point, final IVector b) {
		assertTrue(MathTools.isPointBetween(a, point, b));
	}

	@ParameterizedTest
	@MethodSource
	void pointIsNotBetween(final IVector a, final IVector point, final IVector b) {
		assertFalse(MathTools.isPointBetween(a, point, b));
	}

	@ParameterizedTest
	@MethodSource
	void isAlmostEqualTo(final double value, final double expected, final double tolerance) {
		assertTrue(MathTools.isAlmostEqualTo(value, expected, tolerance));
	}

	@ParameterizedTest
	@MethodSource
	void isNotAlmostEqualTo(final double value, final double expected, final double tolerance) {
		assertFalse(MathTools.isAlmostEqualTo(value, expected, tolerance));
	}
}