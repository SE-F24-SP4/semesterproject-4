import com.github.sef24sp4.common.item.ItemSPI;
import com.github.sef24sp4.common.projectile.ProjectileSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.weaponpack.machinegun.MachineGunBulletControlSystem;
import com.github.sef24sp4.weaponpack.BulletPlugin;
import com.github.sef24sp4.weaponpack.machinegun.MachineGunItemProvider;
import com.github.sef24sp4.weaponpack.shotgun.ShotGunBulletControlSystem;
import com.github.sef24sp4.weaponpack.shotgun.ShotGunItemProvider;

module WeaponPack {
	requires Common;
	requires CommonProjectile;
	requires CommonWeapon;
	requires CommonItem;
	provides IGamePluginService with BulletPlugin;
	provides ItemSPI with ShotGunItemProvider, MachineGunItemProvider;
	provides ProjectileSPI with MachineGunBulletControlSystem, ShotGunBulletControlSystem;
	provides IEntityProcessingService with MachineGunBulletControlSystem, ShotGunBulletControlSystem;
}
