package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.collisionsystem.CollidableEntityContainer;
import com.github.sef24sp4.common.ai.map.MapNode;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IGameSettings;
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
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BucketMapTest {
	private static final int WIDTH = 805;
	private static final int HEIGHT = 605;
	private static final int SIZE = 10;

	@Mock
	private IGameSettings gameSettings;

	private BucketMap bucketMap;

	/**
	 * Argument source for {@link #getNodeContaining(IVector)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> getNodeContaining() {
		return Stream.of(
				Arguments.of(new BasicVector(0, 0)),
				Arguments.of(new BasicVector(5, 5)),
				Arguments.of(new BasicVector(10, 10)),
				Arguments.of(new BasicVector(50, 50)),
				Arguments.of(new BasicVector(100, 50)),
				Arguments.of(new BasicVector(50, 100)),
				Arguments.of(new BasicVector(WIDTH, HEIGHT))
		);
	}

	/**
	 * Argument source for {@link #getNodeContainingReturnEmpty(IVector)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> getNodeContainingReturnEmpty() {
		return Stream.of(
				Arguments.of(new BasicVector(-1, -1)),
				Arguments.of(new BasicVector(1, -1)),
				Arguments.of(new BasicVector(-1, 1)),
				Arguments.of(new BasicVector(-1, 501)),
				Arguments.of(new BasicVector(WIDTH + SIZE + 1, -1)),
				Arguments.of(new BasicVector(WIDTH + SIZE + 1, 1)),
				Arguments.of(new BasicVector(1, HEIGHT + SIZE + 1)),
				Arguments.of(new BasicVector(WIDTH + SIZE + 1, HEIGHT + SIZE + 1))
		);
	}

	/**
	 * Argument source for {@link #getNeighboursTo(IVector, int, int, int)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> getNeighboursTo() {
		final int halfWidth = WIDTH / 2;
		final int halfHeight = HEIGHT / 2;
		final int totalColumns = WIDTH / SIZE;
		final int totalRows = HEIGHT / SIZE;
		return Stream.of(
				Arguments.of(new BasicVector(0, 0), 0, 0, 3),
				Arguments.of(new BasicVector(WIDTH, halfHeight), totalColumns, 30, 5),
				Arguments.of(new BasicVector(0, halfHeight), 0, 30, 5),
				Arguments.of(new BasicVector(halfWidth, halfHeight), 40, 30, 8),
				Arguments.of(new BasicVector(halfWidth, 0), 40, 0, 5),
				Arguments.of(new BasicVector(halfWidth, HEIGHT), 40, totalRows, 5),
				Arguments.of(new BasicVector(WIDTH, HEIGHT), totalColumns, totalRows, 3)
		);
	}

	/**
	 * A Helper method to get a {@link Stream} of {@link CollidableEntityContainer} with
	 * {@link Mockito mocked} {@link ICollidableEntity entities}.
	 *
	 * @return The stream of test {@link CollidableEntityContainer entities}.
	 * @see #addEntity()
	 * @see #getAllEntities()
	 */
	private static Stream<CollidableEntityContainer> getStreamOfTestEntities() {
		return Stream.of(
				entry(new BasicVector(0, 0), 13.0),
				entry(new BasicVector(0, 1), 20.0),
				entry(new BasicVector(1, 0), 15.0),
				entry(new BasicVector(1, 1), 50.0),
				entry(new BasicVector(100, 1), 5.1),
				entry(new BasicVector(1, HEIGHT), 100.0),
				entry(new BasicVector(15, 14), 0.0),
				entry(new BasicVector(14, 140), 1.0),
				entry(new BasicVector(140, 14), 100.0),
				entry(new BasicVector(14, 14), 5.0),
				entry(new BasicVector(230.7, 23.5), 5.0),
				entry(new BasicVector(29, 260), 11.5),
				entry(new BasicVector(300, 40), 70.0),
				entry(new BasicVector(34, 36), 25.0),
				entry(new BasicVector(500, 14), 6.0),
				entry(new BasicVector(WIDTH, 2), 15.0),
				entry(new BasicVector(WIDTH, HEIGHT), 25.0)
		).map(EntityTestTools::getEntityContainerWithMockedEntities);
	}

	/**
	 * Argument source for {@link #addEntity(CollidableEntityContainer)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> addEntity() {
		return getStreamOfTestEntities().map(Arguments::of);
	}

	/**
	 * Argument source for {@link #doNotAddEntity(CollidableEntityContainer)}.
	 *
	 * @return The test arguments.
	 * @see ParameterizedTest
	 * @see MethodSource
	 */
	public static Stream<Arguments> doNotAddEntity() {
		return Stream.of(
				entry(new BasicVector(-1, -1), 13.0),
				entry(new BasicVector(-1, 1), 20.0),
				entry(new BasicVector(1, -1), 15.0),
				entry(new BasicVector(-40, 1), 50.0),
				entry(new BasicVector(1, HEIGHT + 20), 100.0),
				entry(new BasicVector(WIDTH + 20, 2), 15.0),
				entry(new BasicVector(WIDTH + 20, HEIGHT + 20), 25.0)
		).map(EntityTestTools::getEntityContainerWithMockedEntities).map(Arguments::of);
	}

	@BeforeEach
	void setUp() {
		Mockito.when(this.gameSettings.getDisplayWidth()).thenReturn(WIDTH);
		Mockito.when(this.gameSettings.getDisplayHeight()).thenReturn(HEIGHT);
		this.bucketMap = BucketMap.generate(this.gameSettings, SIZE);
	}

	@Test
	void getColumnSize() {
		assertEquals(Math.ceilDiv(WIDTH, SIZE), this.bucketMap.getColumnSize());
	}

	@Test
	void getRowSize() {
		assertEquals(Math.ceilDiv(HEIGHT, SIZE), this.bucketMap.getRowSize());
	}

	@ParameterizedTest
	@MethodSource
	void getNodeContaining(final IVector testVector) {
		final Optional<MapNode> result = this.bucketMap.getNodeContaining(testVector);
		assertTrue(result.isPresent());
		assertTrue(result.get().containsCoordinates(testVector));
	}

	@ParameterizedTest
	@MethodSource
	void getNodeContainingReturnEmpty(final IVector testVector) {
		final Optional<MapNode> result = this.bucketMap.getNodeContaining(testVector);
		assertTrue(result.isEmpty());
	}


	@ParameterizedTest
	@MethodSource
	void getNeighboursTo(final IVector testVector, final int expectedColumn, final int expectedRow, final int expectedNeighbours) {
		final MapNode mapNode = this.bucketMap.getNodeContaining(testVector).orElseThrow();

		if (mapNode instanceof final INode node) {
			assertEquals(expectedColumn, node.getColumn());
			assertEquals(expectedRow, node.getRow());
		} else fail("Not of type INode");

		final Collection<MapNode> neighbours = mapNode.getNeighboringNodes();
		assertEquals(expectedNeighbours, neighbours.size());
	}

	@ParameterizedTest
	@MethodSource
	void addEntity(final CollidableEntityContainer entity) {
		assertFalse(this.bucketMap.containsEntity(entity));
		assertTrue(this.bucketMap.addEntity(entity));
		assertTrue(this.bucketMap.containsEntity(entity));
	}

	@ParameterizedTest
	@MethodSource
	void doNotAddEntity(final CollidableEntityContainer entity) {
		assertFalse(this.bucketMap.containsEntity(entity));
		assertFalse(this.bucketMap.addEntity(entity));
		assertFalse(this.bucketMap.containsEntity(entity));
	}

	@Test
	void getCollidingEntitiesFor() {
		// TODO:
	}

	@Test
	void getAllEntities() {
		final Collection<CollidableEntityContainer> entities = getStreamOfTestEntities().toList();

		entities.forEach(entity -> assertTrue(this.bucketMap.addEntity(entity), String.format("Failed to add: %s", entity)));

		assertTrue(this.bucketMap.getAllEntities().containsAll(entities));

		this.bucketMap.clearEntities();
		assertTrue(this.bucketMap.getAllEntities().isEmpty());
	}
}