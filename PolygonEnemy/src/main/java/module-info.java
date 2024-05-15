import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.enemy.EnemySPI;
import com.github.sef24sp4.polygonenemy.PolygonEnemyControlSystem;

module PolygonEnemy {
	uses com.github.sef24sp4.common.ai.IPathfindingProvider;
	requires Common;
	requires CommonEnemy;
	requires CommonAI;
	provides EnemySPI with PolygonEnemyControlSystem;
	provides IEntityProcessingService with PolygonEnemyControlSystem;
}
