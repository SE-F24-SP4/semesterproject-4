package com.github.sef24sp4.collisionsystem;

import com.github.sef24sp4.common.collisionsystem.CollisionSystemSPI;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.vector.BasicVector;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.vector.IVector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CollisionSystemTest {
	private CollisionSystemSPI collisionSystem;

	@Mock
	private IGameSettings gameSettings;

	@Mock
	private IEntityManager entityManager;

	@Mock
	private ICollidableEntity entity1;
	@Mock
	private ICollidableEntity entity2;
	@Mock
	private ICollidableEntity entity3;
	@Mock
	private ICollidableEntity entity4;

	@BeforeEach
	void setUp() {

		Mockito.lenient().when(this.gameSettings.getDisplayHeight()).thenReturn(400);
		Mockito.lenient().when(this.gameSettings.getDisplayWidth()).thenReturn(600);


		this.collisionSystem = (new CollisionSystemFactory()).create(this.gameSettings);


		Mockito.lenient().when(this.entity1.getCoordinates()).thenReturn(new Coordinates(3, 3));
		Mockito.when(this.entity1.getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(5, 5)});
		this.collisionSystem.addEntity(this.entity1);


		Mockito.lenient().when(this.entity2.getCoordinates()).thenReturn(new Coordinates(410, 310));
		Mockito.when(this.entity2.getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(5, 5)});
		this.collisionSystem.addEntity(this.entity2);

		Mockito.lenient().when(this.entity3.getCoordinates()).thenReturn(new Coordinates(400, 300));
		Mockito.when(this.entity3.getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(20, 30)});
		this.collisionSystem.addEntity(this.entity3);

		Mockito.lenient().when(this.entity4.getCoordinates()).thenReturn(new Coordinates(280, 200));
		Mockito.when(this.entity4.getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(100, 100)});
		this.collisionSystem.addEntity(this.entity4);
	}

	@Test
	void addEntity() {
		final ICollidableEntity anotherEntity = Mockito.mock(ICollidableEntity.class);
		Mockito.when(anotherEntity.getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(5, 5)});
		assertTrue(this.collisionSystem.addEntity(anotherEntity));
		assertFalse(this.collisionSystem.addEntity(anotherEntity));

		assertFalse(this.collisionSystem.addEntity(this.entity1));
		assertFalse(this.collisionSystem.addEntity(this.entity2));
		assertFalse(this.collisionSystem.addEntity(this.entity3));
	}

	@Test
	void removeEntity() {
		assertTrue(this.collisionSystem.removeEntity(this.entity1));
		assertTrue(this.collisionSystem.removeEntity(this.entity2));
		assertTrue(this.collisionSystem.removeEntity(this.entity3));

		assertFalse(this.collisionSystem.removeEntity(this.entity1));
		assertFalse(this.collisionSystem.removeEntity(this.entity2));
		assertFalse(this.collisionSystem.removeEntity(this.entity3));

		final ICollidableEntity anotherEntity = Mockito.mock(ICollidableEntity.class);
		Mockito.when(anotherEntity.getPolygonCoordinates()).thenReturn(new IVector[]{new BasicVector(5, 5)});
		assertFalse(this.collisionSystem.removeEntity(anotherEntity));
	}

	@Test
	void processCollisions() {
		this.collisionSystem.processCollisions(this.entityManager);
		Mockito.verify(this.entity1, Mockito.atLeastOnce()).getCoordinates();
		Mockito.verify(this.entity2, Mockito.atLeastOnce()).getCoordinates();
		Mockito.verify(this.entity3, Mockito.atLeastOnce()).getCoordinates();
		Mockito.verify(this.entity4, Mockito.atLeastOnce()).getCoordinates();


		Mockito.verify(this.entity1, Mockito.never()).collide(Mockito.any(), Mockito.any());


		Mockito.verify(this.entity2).collide(this.entityManager, this.entity3);
		Mockito.verify(this.entity3).collide(this.entityManager, this.entity2);
		Mockito.verify(this.entity3).collide(this.entityManager, this.entity4);
		Mockito.verify(this.entity4).collide(this.entityManager, this.entity3);

		Mockito.verifyNoMoreInteractions(this.entity1, this.entity2, this.entity3, this.entity4);
	}

	@Test
	void verifyNoCollisionOfRemoved() {
		assertTrue(this.collisionSystem.removeEntity(this.entity1));
		assertTrue(this.collisionSystem.removeEntity(this.entity2));
		assertTrue(this.collisionSystem.removeEntity(this.entity3));

		this.collisionSystem.processCollisions(this.entityManager);

		Mockito.verify(this.entity1, Mockito.never()).collide(Mockito.any(), Mockito.any());
		Mockito.verify(this.entity2, Mockito.never()).collide(Mockito.any(), Mockito.any());
		Mockito.verify(this.entity3, Mockito.never()).collide(Mockito.any(), Mockito.any());
	}
}