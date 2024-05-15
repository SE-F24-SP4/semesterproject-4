package com.github.sef24sp4.common.collisionsystem;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;

import java.util.ServiceLoader;

/**
 * This is a factory for creating {@link CollisionSystemSPI} instances.
 * It is provided as {@link ServiceLoader} pattern does not support constructor arguments.
 * Thus, this helper class is provided, which uses a factory pattern circumvent this limitation.
 */
@FunctionalInterface
public interface CollisionSystemProvider {
	/**
	 * Loads the first found provider of {@link CollisionSystemProvider} using {@link ServiceLoader} if any.
	 * Or a {@link CollisionSystemProvider.Default default} dummy implementation if no suitable provider is found.
	 *
	 * @return An implementation of {@link CollisionSystemProvider} if found.
	 * Otherwise, {@link CollisionSystemProvider.Default}.
	 */
	public static CollisionSystemProvider load() {
		return ServiceLoader.load(CollisionSystemProvider.class).findFirst().orElse(new CollisionSystemProvider.Default());
	}


	/**
	 * Creates a {@link CollisionSystemSPI} using the provided {@link IGameSettings}.
	 *
	 * @param gameSettings The game global {@link IGameSettings}.
	 * @return A newly created {@link CollisionSystemSPI}.
	 */
	public CollisionSystemSPI create(final IGameSettings gameSettings);


	/**
	 * Provides a fallback dummy collision system which does nothing.
	 * It is provided to make collision system an optional implementation.
	 * This implementation is essentially a no-op.
	 */
	public static final class Default implements CollisionSystemProvider {
		@Override
		public CollisionSystemSPI create(final IGameSettings gameSettings) {
			return new CollisionSystemSPI() {
				@Override
				public boolean addEntity(final ICollidableEntity entity) {
					return true;
				}

				@Override
				public boolean removeEntity(final ICollidableEntity entity) {
					return true;
				}

				@Override
				public boolean processCollisions(final IEntityManager entityManager) {
					return true;
				}
			};
		}
	}
}
