package com.github.sef24sp4.common.item;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;


public interface ItemSPI {
	/**
	 * Spawns a new item with specified metadata.
	 * <p>
	 * Note the new item does not have any start coordinates and should be provided after,
	 * or it will assume the standard values (0,0) provided by {@link com.github.sef24sp4.common.data.Coordinates}.
	 *
	 * @param coordinates define where on the map the item should spawn.
	 * @param entityManager the games entity manager.
	 * @see CommonItem
	 */
	public void spawnItem(Coordinates coordinates, IEntityManager entityManager);

	/**
	 * Despawns/removes the item from the map.
	 * Can be used if an item isn't picked up after a duration of time.
	 * @param entityManager the games entity manager.
	 */
	public void despawnItem(IEntityManager entityManager);

	/**
	 * Collects the item and adds it to the entities inventory.
	 *
	 * @param entity is the entity that is collecting the item.
	 * @param entityManager the games entity manager.
	 */
	public void collectItem(ICollidableEntity entity, IEntityManager entityManager);

	/**
	 * Uses item on an entity.
	 *
	 * @param targetEntity The entity that the item is used on.
	 * @param entityManager the games entity manager.
	 */
	public void useItem(IEntity targetEntity, IEntityManager entityManager);
}
