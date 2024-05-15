import com.github.sef24sp4.collisionsystem.BucketMapProvider;
import com.github.sef24sp4.common.ai.map.MapProvider;
import com.github.sef24sp4.common.collisionsystem.CollisionSystemFactory;

module CollisionSystem {
	requires Common;
	requires CommonAI;
	requires CommonCollisionSystem;
	provides CollisionSystemFactory with com.github.sef24sp4.collisionsystem.CollisionSystemFactory;
	provides MapProvider with BucketMapProvider;
}
