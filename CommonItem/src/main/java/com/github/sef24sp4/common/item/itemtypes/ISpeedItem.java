package com.github.sef24sp4.common.item.itemtypes;

public interface ISpeedItem {

	/**
	 * Retrieves the speed boost amount provided by this item.
	 * <p>
	 * This method is typically invoked when another entity interacts with this item and needs to determine the speed increase it should receive.
	 * The returned value represents the additional speed units that the interacting entity's current speed should be increased by.
	 *
	 * @return The additional speed units provided by this item.
	 */
	public double getSpeedAmount();

	/**
 	* Retrieves the duration for which this item's speed boost effect lasts.
	* <p>
	* This method is typically invoked after an entity has interacted with this item, to determine for how long the speed boost effect should be applied.
	* The returned value represents the duration in milliseconds.
	*
	* @return The duration of the speed boost effect in milliseconds.
	*/
	public long getUseDuration();
}
