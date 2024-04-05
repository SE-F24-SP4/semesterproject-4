import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.polygonenemy.PolygonEnemyPlugin;
import com.github.sef24sp4.common.enemy.EnemySPI;
import com.github.sef24sp4.polygonenemy.PolygonEnemyControlSystem;

module PolygonEnemy {
	requires Common;
	requires CommonEnemy;
	provides IGamePluginService with PolygonEnemyPlugin;
	provides EnemySPI with PolygonEnemyControlSystem;
}
