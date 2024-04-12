package com.github.sef24sp4.common.item;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.metadata.IGameMetadata;

public interface ItemSPI {
	/**
	 * Spawns a new item with specified metadata.
	 * <p>
	 * Note the new item does not have any start coordinates and should be provided after,
	 * or it will assume the standard values (0,0) provided by {@link com.github.sef24sp4.common.data.Coordinates}.
	 *
	 * @param metadata is used to identify what kind of item it is.
	 * @return An item object of type {@link ICollidableEntity}.
	 * @see ICollidableEntity
	 */
	public ICollidableEntity spawnItem(IGameMetadata metadata);

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
	 * Consumes item.
	 */
	public void useItem();
}
