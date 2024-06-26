import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.player.PlayerControl;
import com.github.sef24sp4.player.PlayerPlugin;

module Player {
	uses com.github.sef24sp4.common.weapon.WeaponSPI;
	requires Common;
	requires CommonWeapon;
	requires CommonProjectile;
	requires CommonItem;
	provides IGamePluginService with PlayerPlugin;
	provides IEntityProcessingService with PlayerControl;
}
