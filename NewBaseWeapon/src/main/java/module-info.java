import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.common.weapon.WeaponSPI;
import com.github.sef24sp4.newbaseweapon.BulletPlugin;
import com.github.sef24sp4.newbaseweapon.shotgun.ShotGun;
import com.github.sef24sp4.newbaseweapon.shotgun.ShotGunBulletControlSystem;

module NewBaseWeapon {
	requires Common;
	requires CommonProjectile;
	requires CommonWeapon;
	requires CommonItem;
	provides IGamePluginService with BulletPlugin;
	provides IEntityProcessingService with ShotGunBulletControlSystem;
	provides WeaponSPI with ShotGun;
}
