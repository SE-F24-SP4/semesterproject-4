import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.wavemanager.WaveControlSystem;

module WaveManager {
	uses com.github.sef24sp4.common.enemy.EnemySPI;
	requires Common;
	requires CommonEnemy;
	provides IEntityProcessingService with WaveControlSystem;
	provides IGamePluginService with WaveControlSystem;
}
