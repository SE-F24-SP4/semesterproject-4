module CommonItem {
	uses com.github.sef24sp4.common.item.ItemSPI;
	requires Common;
	requires CommonWeapon;
	exports com.github.sef24sp4.common.item;
	exports com.github.sef24sp4.common.item.itemtypes;
	exports com.github.sef24sp4.common.item.loottable;
}
