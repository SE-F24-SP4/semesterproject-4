package com.github.sef24sp4.collisionsystem.map;

import com.github.sef24sp4.collisionsystem.CollidableEntityContainer;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.BasicVector;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.vector.IVector;
import org.mockito.Mockito;

import static java.util.Map.Entry;

/**
 * A Helper class which contains logic to create
 * test {@link com.github.sef24sp4.common.entities.ICollidableEntity entities}
 * and test {@link CollidableEntityContainer entityContainers}.
 */
public class EntityTestTools {
	/**
	 * Helper method for easily creating {@link CollidableEntityContainer} with {@link Mockito#mock(Object[]) mocked} {@link ICollidableEntity entities}.
	 *
	 * @param coordinates The coordinates the mock should return.
	 * @param radius      The radius the mock should return.
	 * @return The newly created container with mocked entity.
	 */
	static CollidableEntityContainer getEntityContainerWithMockedEntities(final IVector coordinates, final double radius) {
		final ICollidableEntity entity = Mockito.mock();
		Mockito.when(entity.getCoordinates()).thenReturn(Coordinates.valuesOf(coordinates));
		Mockito.when(entity.getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(0, radius)});
		return new CollidableEntityContainer(entity);
	}

	static CollidableEntityContainer getEntityContainerWithMockedEntities(final Entry<? extends IVector, Double> entry) {
		return getEntityContainerWithMockedEntities(entry.getKey(), entry.getValue());
	}

	static CollidableEntityContainer getEntityContainerWithMockedEntities(final double x, final double y, final double radius) {
		return getEntityContainerWithMockedEntities(new BasicVector(x, y), radius);
	}
}
