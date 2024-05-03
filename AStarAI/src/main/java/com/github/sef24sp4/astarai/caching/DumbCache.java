package com.github.sef24sp4.astarai.caching;

import com.github.sef24sp4.astarai.AStar;
import com.github.sef24sp4.astarai.Node;
import com.github.sef24sp4.common.ai.map.MapNode;
import com.github.sef24sp4.common.ai.map.Map;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.IVector;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DumbCache implements IPathCaching {

	private final ICollidableEntity entity;
	private AStar aStarSession;
	private Map map;
	private MapNode mapNode;
	private Node entityNode;
	private Node targetNode;
	List<Node> pathList = new LinkedList<>();

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
	public IVector getNextCoordinates(ICollidableEntity entity, final IVector targetCoordinate) {

		if (this.isCachedPathValid(targetCoordinate)) { //if cache is valid, return (optional Node) or null
			return this.getNextNode().map(Node::getCoordinates).orElse(null);
		}

		this.map.getNodeContaining(this.entity.getCoordinates()).ifPresent(node -> {
			this.entityNode = new Node(this.entity.getCoordinates(), node);
		});
		this.map.getNodeContaining(entity.getCoordinates()).ifPresent(node -> {
			this.targetNode = new Node(targetCoordinate, node);
		});

		//if no cache : calc new route and take next node from pathlist.
		this.aStarSession = new AStar(this.entityNode, targetNode);

		pathList.clear();
		this.pathList = aStarSession.calculatePath(entity);

		return this.getNextNode().map(Node::getCoordinates).orElse(null);
	}

	public boolean isCachedPathValid(IVector targetCoordinate) {
		IVector lastNodeCoordinate = this.pathList.getFirst().getCoordinates(); //first element is the goalCoordinate
		double distance = targetCoordinate.getVectorTo(lastNodeCoordinate).getNorm();

		long maxPathTimeAge = 4000; //4 seconds in milliseconds
		long currenttime = System.currentTimeMillis();

		//if distance is less than 20, and time is less than 4 seconds
		if (distance < 20 || (currenttime - this.lastTimeCalled) < maxPathTimeAge) {
			return true;
		}
		pathList.clear(); //if player move, empty the list.
		return false;
	}

	private Optional<Node> getNextNode() { //returns the next Node in the pathList
		if (!this.pathList.isEmpty()) {
			return Optional.of(this.pathList.remove(0));
		}
		return Optional.empty();
	}

}
