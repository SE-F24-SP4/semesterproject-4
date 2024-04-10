import com.github.sef24sp4.baseweapon.BaseProjectilePlugin;
import com.github.sef24sp4.baseweapon.BaseWeapon;
import com.github.sef24sp4.baseweapon.ProjectileControlSystem;
import com.github.sef24sp4.common.projectile.ProjectileSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.common.weapon.WeaponSPI;

module BaseWeapon {
	requires Common;
	requires CommonProjectile;
	requires CommonWeapon;
	provides IGamePluginService with BaseProjectilePlugin;
	provides WeaponSPI with BaseWeapon;
	provides ProjectileSPI with ProjectileControlSystem;
	provides IEntityProcessingService with ProjectileControlSystem;
}
