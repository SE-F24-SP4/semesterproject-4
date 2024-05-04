import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.item.CommonItem;
import com.github.sef24sp4.common.item.ItemSPI;
import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.itempack.ItemPackPlugin;
import com.github.sef24sp4.itempack.SpeedItemProvider;

module ItemPack {
	exports com.github.sef24sp4.itempack;
	requires CommonItem;
	requires Common;
	provides IGamePluginService with ItemPackPlugin;
	provides ItemSPI with SpeedItemProvider;
}
