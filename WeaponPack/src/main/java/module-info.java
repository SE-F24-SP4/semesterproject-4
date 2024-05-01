import com.github.sef24sp4.common.projectile.ProjectileSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.common.weapon.WeaponSPI;
import com.github.sef24sp4.weaponpack.machinegun.MachineGunBulletControlSystem;
import com.github.sef24sp4.weaponpack.machinegun.MachineGun;
import com.github.sef24sp4.weaponpack.BulletPlugin;

module WeaponPack {
	requires Common;
	requires CommonProjectile;
	requires CommonWeapon;
	provides IGamePluginService with BulletPlugin;
	provides WeaponSPI with MachineGun;
	provides ProjectileSPI with MachineGunBulletControlSystem;
	provides IEntityProcessingService with MachineGunBulletControlSystem;
}
