package com.github.sef24sp4.astarai.caching;

import com.github.sef24sp4.astarai.AStar;
import com.github.sef24sp4.astarai.Node;
import com.github.sef24sp4.common.ai.map.Map;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.IVector;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.spi.AbstractResourceBundleProvider;

public class DumbCache implements IPathCaching {

	private final ICollidableEntity entity;
	private AStar aStarSession;
	private Map map;
	private Node entityNode;
	private Node targetNode;
	private List<Node> pathList = new LinkedList<>();
	private static final double maxDistanceNumer = 100;
	private final long maxPathTimeAge = 4000;//4 seconds in milliseconds

	private long lastTimeCalled;

	public DumbCache(final ICollidableEntity entity) {
		this.entity = entity;
	}

	@Override
	public boolean flush() {
		this.aStarSession = null;
		return false;
	}

	@Override
	public IVector getNextCoordinates(ICollidableEntity collidableEntity, final IVector targetCoordinate) {

		if (!this.isCachedPathValid(targetCoordinate)) {
			this.map.getNodeContaining(this.entity.getCoordinates()).ifPresent(node -> {
				this.entityNode = new Node(this.entity.getCoordinates(), node);
			});
			this.map.getNodeContaining(collidableEntity.getCoordinates()).ifPresent(node -> {
				this.targetNode = new Node(targetCoordinate, node);
			});

			//if nodes are null throw exception.
			if (entityNode == null || targetNode == null) {
				throw new IllegalArgumentException();
			}
			//if no cache : calc new route and take next node from pathlist.
			this.aStarSession = new AStar(this.entityNode, this.targetNode);

			this.pathList.clear();
			this.pathList = this.aStarSession.calculatePath(collidableEntity);
			this.lastTimeCalled = System.currentTimeMillis(); //get the time for when last times calculatePath called

			return this.getNextNode().map(Node::getCoordinates).orElse(collidableEntity.getCoordinates());
		}

		//if cache is valid, return (optional Node) or current position
		return this.getNextNode().map(Node::getCoordinates).orElse(collidableEntity.getCoordinates());
	}

	public boolean isCachedPathValid(IVector targetCoordinate) {
		IVector lastNodeCoordinate = this.pathList.getFirst().getCoordinates(); //first element is the goalCoordinate
		double distance = targetCoordinate.getVectorTo(lastNodeCoordinate).getNorm();

		long currenttime = System.currentTimeMillis();

		//if distance is less than 100, or less than 4 seconds ago
		if (distance < maxDistanceNumer || (currenttime - this.lastTimeCalled) < this.maxPathTimeAge) {
			return true;
		}
		this.pathList.clear(); //if player move, empty the list.
		return false;
	}

	private Optional<Node> getNextNode() { //returns the next Node in the pathList
		if (!this.pathList.isEmpty()) {
			return Optional.of(this.pathList.remove(0));
		}
		return Optional.empty();
	}

}
