package com.github.sef24sp4.astarai.caching;

import com.github.sef24sp4.astarai.AStar;
import com.github.sef24sp4.astarai.Node;
import com.github.sef24sp4.common.ai.map.Map;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.IVector;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DumbCache implements IPathCaching {

	private final ICollidableEntity entity;
	private AStar aStarSession;
	private final Map map;
	private Node entityNode;
	private Node targetNode;
	private List<Node> pathList = new LinkedList<>();
	private static final double MAX_DISTANCE_NUM = 100;
	private final long maxPathTimeAge = 4000; //4 seconds in milliseconds
	private long pathCreationTime;

	public DumbCache(final ICollidableEntity entity, final Map map) {
		this.entity = entity;
		this.map = map;
	}

	@Override
	public void flush() {
		this.aStarSession = null;
		this.pathList.clear();
	}

	@Override
	public IVector getNextCoordinates(final IVector targetCoordinate) {
		if (!this.isCachedPathValid(targetCoordinate)) {
			//make node for entity, which has the mapNode and coordinate
			this.map.getNodeContaining(this.entity.getCoordinates()).ifPresent(entityMapNode -> {
				this.entityNode = new Node(this.entity.getCoordinates(), entityMapNode);
			});
			this.map.getNodeContaining(targetCoordinate).ifPresent(targetMapNode -> {
				this.targetNode = new Node(targetCoordinate, targetMapNode);
			});

			//if no cache : calc new route and take next node from pathlist.
			//if no nodes are null make AStar instance
			if (!(this.entityNode == null) && !(this.targetNode == null)) {
				this.aStarSession = new AStar(this.entityNode, this.targetNode);

				this.pathList.clear();
				this.pathList = this.aStarSession.calculatePath(this.entity);
				this.pathCreationTime = System.currentTimeMillis(); //get the time for when last times calculatePath called
			}
			if (getNextNodeCoordinate().isPresent()) {
				return getNextNodeCoordinate().get();
			}
			return this.entity.getCoordinates();
		}
		//if cache is valid, return (optional Node) or current position

		if (getNextNodeCoordinate().isPresent()) {
			return getNextNodeCoordinate().get();
		}
		return this.entity.getCoordinates();

	}

	public boolean isCachedPathValid(IVector targetCoordinate) {
		if (pathList.isEmpty()) {
			return false;
		}
		long currentTime = System.currentTimeMillis();
		if (((currentTime - this.pathCreationTime) > this.maxPathTimeAge)) {
			this.flush();
			return false;
		}
		IVector lastNodeCoordinate = this.pathList.getLast().getMapNode().getCenterCoordinates(); //last element is the goalCoordinate
		System.out.println(lastNodeCoordinate);
		double distance = targetCoordinate.getVectorTo(lastNodeCoordinate).getNorm();
		if (distance > MAX_DISTANCE_NUM) {
			this.flush();
			return false;
		}
		return true;
	}

	private Optional<IVector> getNextNodeCoordinate() { //returns the next Node in the pathList
		if (!this.pathList.isEmpty()) {
			return Optional.of(this.pathList.remove(0).getMapNode().getCenterCoordinates());
		}
		return Optional.empty();
	}

}
