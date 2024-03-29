import com.github.sef24sp4.common.enemy.EnemySPI;
import com.github.sef24sp4.polygonenemy.PolygonEnemyControlSystem;

module PolygonEnemy {
	requires Common;
	requires CommonEnemy;
	provides EnemySPI with PolygonEnemyControlSystem;
}
