import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemSPI;
import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.healthitem.HealthItemPlugin;
import com.github.sef24sp4.healthitem.HealthItemProvider;

module HealthItem {
	exports com.github.sef24sp4.healthitem;
	requires Common;
	requires CommonItem;
	provides IGamePluginService with HealthItemPlugin;
	provides ItemSPI with HealthItemProvider;
}
