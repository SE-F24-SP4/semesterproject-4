package com.github.sef24sp4.common.item;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.metadata.IGameMetadata;

public interface ItemSPI {
	/**
	 * Spawns a new item with specified metadata.
	 * <p>
	 * Note the new item does not have any start coordinates and should be provided after,
	 * or it will assume the standard values (0,0) provided by {@link com.github.sef24sp4.common.data.Coordinates}.
	 *
	 * @param coordinates define where on the map the item should spawn.
	 * @return An item object of type {@link ICollidableEntity}.
	 * @see ICollidableEntity
	 * @see CommonItem
	 */
	public void spawnItem(Coordinates coordinates, IEntityManager entityManager);

	/**
	 * Despawns/removes the item from the map.
	 * Can be used if an item isn't picked up after a duration of time.
	 */
	public void despawnItem();

	/**
	 * Collects the item and adds it to the entities inventory.
	 *
	 * @param entity is the entity that is collecting the item.
	 */
	public void collectItem(ICollidableEntity entity);

	/**
	 * Uses item on an entity.
	 *
	 * @param targetEntity The entity that the item is used on.
	 */
	public void useItem(IEntity targetEntity);
}
