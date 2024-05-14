package com.github.sef24sp4.collisionsystem;

import com.github.sef24sp4.common.collisionsystem.CollisionSystemSPI;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;

import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

public class CollisionSystem implements CollisionSystemSPI {
	private final Collection<CollidableEntityContainer> collidableEntities = new HashSet<>();
	private final Stack<CollidableEntityContainer> toBeAdded = new Stack<>();
	private final Stack<CollidableEntityContainer> toBeRemoved = new Stack<>();
	private final IGameSettings gameSettings;

	CollisionSystem(final IGameSettings gameSettings) {
		this.gameSettings = gameSettings;
		gameSettings.getDisplayWidth();
	}

	@Override
	public boolean addEntity(final ICollidableEntity entity) {
		final CollidableEntityContainer container = new CollidableEntityContainer(entity);
		if (this.collidableEntities.contains(container)) return false;
		if (this.toBeAdded.contains(container)) return false;
		this.toBeAdded.push(container);
		return true;
	}

	@Override
	public boolean removeEntity(final ICollidableEntity entity) {
		final CollidableEntityContainer container = new CollidableEntityContainer(entity);

		if (this.toBeRemoved.contains(container)) return false;

		if (this.collidableEntities.contains(container) || this.toBeAdded.contains(container)) {
			this.toBeRemoved.push(container);
			return true;
		}
		return false;
	}

	@Override
	public boolean processCollisions(final IEntityManager entityManager) {
		/* TODO:
		 * Add all to a grid and while adding do collision detection.
		 *
		 */
		while (!this.toBeAdded.isEmpty()) {
			final CollidableEntityContainer c = this.toBeAdded.pop();
			if (this.toBeRemoved.contains(c)) continue;
			this.collidableEntities.add(c);
		}
		for (final CollidableEntityContainer entity1 : this.collidableEntities) {
			for (final CollidableEntityContainer entity2 : this.collidableEntities) {
				if (entity1.doesCollideWith(entity2)) {
					entity1.getEntity().collide(entityManager, entity2.getEntity());
				}
			}
		}
		while (!this.toBeRemoved.isEmpty()) {
			this.collidableEntities.remove(this.toBeRemoved.pop());
		}
		return true;
	}
}
