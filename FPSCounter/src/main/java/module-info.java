import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.fpscounter.FPSCounter;

module FPSCounter {
	requires Common;
	provides IGamePluginService with FPSCounter;
	provides IEntityProcessingService with FPSCounter;
}
