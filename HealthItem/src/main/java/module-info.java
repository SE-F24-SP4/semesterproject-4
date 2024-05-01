import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.healthitem.HealthItemPlugin;

module HealthItem {
	exports com.github.sef24sp4.healthitem;
	requires Common;
	requires CommonItem;
	provides IGamePluginService with HealthItemPlugin;
}
