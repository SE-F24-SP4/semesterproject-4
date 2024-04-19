package com.github.sef24sp4.common.data;

import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.metadata.IGameMetadata;
import com.github.sef24sp4.common.metadata.MetadataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class EntityManagerTest {

	private EntityManager entityManager;

	@BeforeEach
	void setUp() {
		this.entityManager = new EntityManager();
	}

	@Test
	void addEntity() {
		final IEntity entity = new TestEntityA();
		assertTrue(this.entityManager.addEntity(entity));
		assertFalse(this.entityManager.addEntity(entity));

		assertEquals(1, this.entityManager.getAllEntities().size());

		assertTrue(this.entityManager.getAllEntities().contains(entity));
	}

	@Test
	void removeEntity() {
		final IEntity entity1 = new TestEntityA();
		final IEntity entity2 = new TestEntityA();

		assertTrue(this.entityManager.addEntity(entity1));
		assertTrue(this.entityManager.addEntity(entity2));
		assertEquals(2, this.entityManager.getAllEntities().size());
		assertTrue(this.entityManager.getAllEntities().contains(entity1));

		assertTrue(this.entityManager.removeEntity(entity1));
		assertEquals(1, this.entityManager.getAllEntities().size());
		assertFalse(this.entityManager.getAllEntities().contains(entity1));

		assertFalse(this.entityManager.removeEntity(entity1));

		assertTrue(this.entityManager.getAllEntities().contains(entity2));
	}

	@Test
	void getAllEntities() {
		final IEntity entity = new TestEntityA();
		assertTrue(this.entityManager.addEntity(entity));

		final Collection<IEntity> collection = this.entityManager.getAllEntities();

		assertTrue(collection.contains(entity));

		assertThrows(UnsupportedOperationException.class, () -> {
			collection.add(new TestEntityA());
		});
		assertThrows(UnsupportedOperationException.class, () -> {
			collection.remove(entity);
		});
	}

	@Test
	void getEntitiesByClass() {
		final IEntity entityA = new TestEntityA();
		final IEntity entityB = new TestEntityB();

		assertTrue(this.entityManager.addEntity(entityA));
		assertTrue(this.entityManager.addEntity(entityB));

		final Collection<CommonEntity> collection = this.entityManager.getEntitiesByClass(CommonEntity.class);

		assertEquals(2, collection.size());
		assertTrue(collection.contains(entityA));
		assertTrue(collection.contains(entityB));

		final Collection<TestEntityA> collectionA = this.entityManager.getEntitiesByClass(TestEntityA.class);
		final Collection<TestEntityB> collectionB = this.entityManager.getEntitiesByClass(TestEntityB.class);

		assertEquals(1, collectionA.size());
		assertEquals(1, collectionB.size());

		assertTrue(collectionA.contains(entityA));
		assertTrue(collectionB.contains(entityB));

		assertThrows(UnsupportedOperationException.class, () -> {
			collectionA.remove(entityA);
		});

		assertThrows(UnsupportedOperationException.class, () -> {
			collectionB.remove(entityB);
		});
	}

	@Test
	void removeEntitiesByClass() {
		final IEntity entityA = new TestEntityA();
		final IEntity entityB = new TestEntityB();

		assertTrue(this.entityManager.addEntity(entityA));
		assertTrue(this.entityManager.addEntity(entityB));

		final Collection<TestEntityA> removedA = this.entityManager.removeEntitiesByClass(TestEntityA.class);

		assertTrue(removedA.contains(entityA));
		assertFalse(removedA.contains(entityB));

		assertFalse(this.entityManager.getAllEntities().contains(entityA));
		assertTrue(this.entityManager.getAllEntities().contains(entityB));
	}

	@Test
	void getEntitiesByGameElementType() {
		final IEntity entityA = new TestEntityA();
		final IEntity entityB = new TestEntityB();

		assertTrue(this.entityManager.addEntity(entityA));
		assertTrue(this.entityManager.addEntity(entityB));

		final Collection<IEntity> filtered = this.entityManager.getEntitiesByGameElementType(GameElementType.OTHER);

		assertTrue(filtered.contains(entityA));
		assertFalse(filtered.contains(entityB));
	}

	private static final class TestEntityA extends CommonEntity {
		@Override
		public IGameMetadata getMetadata() {
			return new MetadataBuilder(GameElementType.OTHER).getMetadata();
		}
	}

	private static final class TestEntityB extends CommonEntity {
		@Override
		public IGameMetadata getMetadata() {
			return new MetadataBuilder(GameElementType.ENEMY).getMetadata();
		}
	}
}