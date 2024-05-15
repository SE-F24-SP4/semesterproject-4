package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.collisionsystem.CollidableEntityContainer;
import com.github.sef24sp4.collisionsystem.map.util.MathTools;
import com.github.sef24sp4.common.vector.BasicVector;
import com.github.sef24sp4.common.vector.IVector;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static com.github.sef24sp4.collisionsystem.map.EntityTestTools.getEntityContainerWithMockedEntities;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
public class SafeCoordinatesTest {
	public static final double DEFAULT_TOLERANCE = MathTools.DEFAULT_TOLERANCE;

	/**
	 * Argument source for {@link #calculateSafeCoordinatesFor(CollidableEntityContainer, CollidableEntityContainer, IVector)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> calculateSafeCoordinatesFor() {
		return Stream.of(
				Arguments.of(getEntityContainerWithMockedEntities(44, 16, 5), getEntityContainerWithMockedEntities(38, 25, 5),
						new BasicVector(43.41013748054263, 16.58986251947377)),
				Arguments.of(getEntityContainerWithMockedEntities(44, 16, 5), getEntityContainerWithMockedEntities(41, 21, 5), new BasicVector(47, 13)),
				Arguments.of(getEntityContainerWithMockedEntities(44, 16, 5), getEntityContainerWithMockedEntities(49, 21, 5), new BasicVector(49, 11)),
				Arguments.of(getEntityContainerWithMockedEntities(44, 16, 5), getEntityContainerWithMockedEntities(35, 23, 5), new BasicVector(43, 17)),
				Arguments.of(getEntityContainerWithMockedEntities(44, 16, 5), getEntityContainerWithMockedEntities(28, 18, 5), new BasicVector(36, 24)),
				Arguments.of(getEntityContainerWithMockedEntities(44, 16, 5), getEntityContainerWithMockedEntities(28, 22, 5), new BasicVector(38, 22))
		);
	}

	/**
	 * Argument source for {@link #calculateSafeCoordinatesForEmptyResult(CollidableEntityContainer, CollidableEntityContainer)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> calculateSafeCoordinatesForEmptyResult() {
		return Stream.of(
				Arguments.of(getEntityContainerWithMockedEntities(44, 16, 5), getEntityContainerWithMockedEntities(42.2, 32, 5)),
				Arguments.of(getEntityContainerWithMockedEntities(44, 16, 5), getEntityContainerWithMockedEntities(32.8, 13, 5))
		);
	}

	@ParameterizedTest
	@MethodSource
	void calculateSafeCoordinatesFor(final CollidableEntityContainer mainEntity, final CollidableEntityContainer otherEntity, final IVector expectedTarget) {
		final IVector vectorToCenter = mainEntity.getCoordinates().getVectorTo(new BasicVector(35, 25));

		final Optional<IVector> result = Bucket.calculateSafeCoordinatesFor(mainEntity, mainEntity.getCoordinates(), otherEntity, vectorToCenter);
		assertTrue(result.isPresent());
		final double distanceFromTarget = expectedTarget.getVectorTo(result.get()).getNorm();
		assertTrue(distanceFromTarget <= DEFAULT_TOLERANCE, String.format("Wanted: %s but got %s, which is %f away", expectedTarget, result.get(), distanceFromTarget));
	}

	@ParameterizedTest
	@MethodSource
	void calculateSafeCoordinatesForEmptyResult(final CollidableEntityContainer mainEntity, final CollidableEntityContainer otherEntity) {
		final IVector vectorToCenter = mainEntity.getCoordinates().getVectorTo(new BasicVector(35, 25));

		final Optional<IVector> result = Bucket.calculateSafeCoordinatesFor(mainEntity, mainEntity.getCoordinates(), otherEntity, vectorToCenter);
		assertTrue(result.isEmpty());
	}
}
