import com.github.sef24sp4.astarai.EntityPathFinder;
import com.github.sef24sp4.common.ai.IPathfindingProvider;

module AStarAI {
	requires CommonAI;
	requires Common;
	provides IPathfindingProvider with EntityPathFinder;
}
