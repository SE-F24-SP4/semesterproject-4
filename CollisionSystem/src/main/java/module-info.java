import com.github.sef24sp4.collisionsystem.BucketMapProvider;
import com.github.sef24sp4.common.ai.map.MapProvider;
import com.github.sef24sp4.common.collisionsystem.CollisionSystemProvider;

module CollisionSystem {
	requires Common;
	requires CommonAI;
	requires CommonCollisionSystem;
	provides CollisionSystemProvider with com.github.sef24sp4.collisionsystem.CollisionSystemProvider;
	provides MapProvider with BucketMapProvider;
}
