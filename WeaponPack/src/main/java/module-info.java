import com.github.sef24sp4.common.projectile.ProjectileSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.common.weapon.WeaponSPI;
import com.github.sef24sp4.weaponpack.machinegun.Bullet;
import com.github.sef24sp4.weaponpack.machinegun.BulletControlSystem;
import com.github.sef24sp4.weaponpack.machinegun.BulletPlugin;
import com.github.sef24sp4.weaponpack.machinegun.MachineGun;

module WeaponPack {
	requires Common;
	requires CommonProjectile;
	requires CommonWeapon;
	provides IGamePluginService with BulletPlugin;
	provides WeaponSPI with MachineGun;
	provides ProjectileSPI with BulletControlSystem;
	provides IEntityProcessingService with BulletControlSystem;
}
