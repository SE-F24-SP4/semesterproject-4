import com.github.sef24sp4.common.collisionsystem.CollisionSystemFactory;

module CollisionSystem {
	requires Common;
	requires CommonAI;
	requires CommonCollisionSystem;
	provides CollisionSystemFactory with com.github.sef24sp4.collisionsystem.CollisionSystemFactory;
}
