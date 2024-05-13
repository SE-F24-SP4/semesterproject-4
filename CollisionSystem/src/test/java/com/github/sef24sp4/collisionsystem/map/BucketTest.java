package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.collisionsystem.CollidableEntityContainer;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.BasicVector;
import com.github.sef24sp4.common.vector.IVector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.stream.Stream;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BucketTest {
	private static final int SIZE = 10;
	private static final int COLUMN = 1;
	private static final int ROW = 1;

	@Mock
	private IGridMap map;

	private Bucket bucket;

	/**
	 * Argument source for {@link #addOverlappingEntity(CollidableEntityContainer)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> addOverlappingEntity() {
		return getStreamOfOverlappingEntities().map(Arguments::of);
	}

	/**
	 * Argument source for {@link #addNonOverlappingEntityReturnFalse(IVector, double)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> addNonOverlappingEntityReturnFalse() {
		return Stream.of(
				Arguments.of(new BasicVector(23, 24), 5),
				Arguments.of(new BasicVector(25, 15), 4),
				Arguments.of(new BasicVector(30, 40), 0),
				Arguments.of(new BasicVector(30, 40), 20),
				Arguments.of(new BasicVector(9, 9), 0)
		);
	}

	/**
	 * Argument source for {@link #isNodeAdjacent(int, int)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> isNodeAdjacent() {
		return Stream.of(
				Arguments.of(COLUMN + 1, ROW + 1),
				Arguments.of(COLUMN + 1, ROW),
				Arguments.of(COLUMN - 1, ROW - 1),
				Arguments.of(COLUMN - 1, ROW),
				Arguments.of(COLUMN, ROW + 1),
				Arguments.of(COLUMN, ROW - 1)
		);
	}

	/**
	 * Argument source for {@link #nodeIsNotAdjacent(int, int)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> nodeIsNotAdjacent() {
		return Stream.of(
				Arguments.of(COLUMN + 2, ROW),
				Arguments.of(COLUMN + 3, ROW - 1),
				Arguments.of(COLUMN - 2, ROW + 3),
				Arguments.of(COLUMN - 2, ROW),
				Arguments.of(COLUMN, ROW + 3),
				Arguments.of(COLUMN, ROW - 2)
		);
	}

	/**
	 * A Helper method to get a {@link Stream} of {@link CollidableEntityContainer} with
	 * {@link Mockito mocked} {@link ICollidableEntity entities} which overlaps with the test {@link Bucket}.
	 *
	 * @return The stream of test {@link CollidableEntityContainer entities}.
	 * @see #addOverlappingEntity()
	 * @see #getAllEntities()
	 */
	private static Stream<CollidableEntityContainer> getStreamOfOverlappingEntities() {
		return Stream.of(
				entry(new BasicVector(2, 2), 13.0),
				entry(new BasicVector(2, 2), 15.0),
				entry(new BasicVector(1, 1), 100.0),
				entry(new BasicVector(1, 1), 15.0),
				entry(new BasicVector(1, 1), 20.0),
				entry(new BasicVector(1, 1), 25.0),
				entry(new BasicVector(1, 1), 50.0),
				entry(new BasicVector(6, 26), 11.0),
				entry(new BasicVector(16, 16), 11.0),
				entry(new BasicVector(14, 14), 0.0),
				entry(new BasicVector(14, 14), 100.0),
				entry(new BasicVector(14, 14), 5.0),
				entry(new BasicVector(23.5, 23.5), 5.0),
				entry(new BasicVector(30, 40), 70.0),
				entry(new BasicVector(34, 36), 25.0),
				entry(new BasicVector(29, 26), 11.5),
				entry(new BasicVector(5, 14), 6.0)
		).map(EntityTestTools::getEntityContainerWithMockedEntities);
	}

	@BeforeEach
	void setUp() {
		Mockito.when(this.map.getNodeSize()).thenReturn(SIZE);
		this.bucket = new Bucket(COLUMN, ROW, this.map);

	}

	@Test
	void getStartCoordinates() {
		assertEquals(new BasicVector(SIZE * COLUMN, SIZE * ROW), this.bucket.getStartCoordinates());
	}

	@Test
	void getEndCoordinates() {
		assertEquals(new BasicVector(SIZE * (COLUMN + 1), SIZE * (ROW + 1)), this.bucket.getEndCoordinates());
	}

	@Test
	void getSize() {
		assertEquals(SIZE, this.bucket.getSize());
	}

	@Test
	void getCenterCoordinates() {
		assertEquals(new BasicVector(SIZE * COLUMN + SIZE / 2.0, SIZE * ROW + SIZE / 2.0), this.bucket.getCenterCoordinates());
	}

	@Test
	void containsCoordinates() {
		assertTrue(this.bucket.containsCoordinates(new BasicVector(SIZE, SIZE)));
		assertFalse(this.bucket.containsCoordinates(new BasicVector(-1, -1)));
		assertFalse(this.bucket.containsCoordinates(new BasicVector(Integer.MAX_VALUE, Integer.MAX_VALUE)));
	}

	@ParameterizedTest
	@MethodSource
	void addOverlappingEntity(final CollidableEntityContainer entity) {
		assertFalse(this.bucket.containsEntity(entity));

		assertTrue(this.bucket.addEntity(entity));
		assertTrue(this.bucket.containsEntity(entity));
	}

	@ParameterizedTest
	@MethodSource
	void addNonOverlappingEntityReturnFalse(final IVector coordinates, final double radius) {
		final CollidableEntityContainer entity = EntityTestTools.getEntityContainerWithMockedEntities(coordinates, radius);

		assertFalse(this.bucket.addEntity(entity));
		assertFalse(this.bucket.containsEntity(entity));
	}

	@Test
	void getSafeCoordinatesForEntity() {
		//TODO:
	}

	@Test
	void calculateHeuristicsFor() {
		final Bucket otherBucket = new Bucket(1, 3, this.map);
		assertEquals(20, this.bucket.calculateHeuristicsFor(otherBucket));
	}

	@Test
	void getRow() {
		assertEquals(ROW, this.bucket.getRow());
	}

	@Test
	void getColumn() {
		assertEquals(COLUMN, this.bucket.getColumn());
	}

	@ParameterizedTest
	@MethodSource
	void isNodeAdjacent(final int column, final int row) {
		final Bucket otherBucket = new Bucket(column, row, this.map);
		assertTrue(this.bucket.isNodeAdjacent(otherBucket));
	}

	@ParameterizedTest
	@MethodSource
	void nodeIsNotAdjacent(final int column, final int row) {
		final Bucket otherBucket = new Bucket(column, row, this.map);
		assertFalse(this.bucket.isNodeAdjacent(otherBucket));
	}

	@Test
	void getCollidingEntitiesFor() {
		final Collection<CollidableEntityContainer> nonCollidingEntities = Stream.of(
				entry(new BasicVector(2, 2), 13.0),
				entry(new BasicVector(2, 2), 15.0),
				entry(new BasicVector(8, 22), 6.0),
				entry(new BasicVector(11, 12), 3.0),
				entry(new BasicVector(5, 14), 8.0)
		).map(e -> EntityTestTools.getEntityContainerWithMockedEntities(e.getKey(), e.getValue())).toList();

		final Collection<CollidableEntityContainer> collidingEntities = Stream.of(
				entry(new BasicVector(13, 16), 2.0),
				entry(new BasicVector(16, 18), 2.0),
				entry(new BasicVector(6, 26), 11.0),
				entry(new BasicVector(12, 28), 11.0),
				entry(new BasicVector(18, 18), 11.0),
				entry(new BasicVector(29, 26), 12.0),
				entry(new BasicVector(21, 19), 3.0),
				entry(new BasicVector(19.5, 10.5), 3.0),
				entry(new BasicVector(11.5, 11.5), 3.0),
				entry(new BasicVector(11, 12), 4.0),
				entry(new BasicVector(5, 14), 9.0)
		).map(e -> EntityTestTools.getEntityContainerWithMockedEntities(e.getKey(), e.getValue())).toList();

		nonCollidingEntities.forEach(e -> assertTrue(this.bucket.addEntity(e), String.format("Failed to add: %s", e)));
		collidingEntities.forEach(e -> assertTrue(this.bucket.addEntity(e), String.format("Failed to add: %s", e)));

		final CollidableEntityContainer otherEntity = EntityTestTools.getEntityContainerWithMockedEntities(new BasicVector(18, 16), 5);


		final Collection<CollidableEntityContainer> collidedEntities = this.bucket.getCollidingEntitiesFor(otherEntity);

		assertEquals(collidingEntities.size(), collidedEntities.size());

		assertTrue(collidedEntities.containsAll(collidingEntities));

		assertFalse(collidedEntities.stream().anyMatch(nonCollidingEntities::contains));
	}

	@Test
	void getAllEntities() {
		final Collection<CollidableEntityContainer> entities = getStreamOfOverlappingEntities().toList();

		entities.forEach(entity -> assertTrue(this.bucket.addEntity(entity), String.format("Failed to add: %s", entity)));

		assertTrue(this.bucket.getAllEntities().containsAll(entities));

		this.bucket.clearEntities();
		assertTrue(this.bucket.getAllEntities().isEmpty());
	}
}
