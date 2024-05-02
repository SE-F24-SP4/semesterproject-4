import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.itempack.ItemPackPlugin;

module ItemPack {
	exports com.github.sef24sp4.itempack;
	requires CommonItem;
	requires Common;
	provides IGamePluginService with ItemPackPlugin;
}
