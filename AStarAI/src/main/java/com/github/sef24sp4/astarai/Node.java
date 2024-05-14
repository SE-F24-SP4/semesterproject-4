package com.github.sef24sp4.astarai;


import com.github.sef24sp4.common.ai.map.MapNode;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.vector.IVector;

import java.util.Collection;

public class Node {
	private Node parent;
	private double gCost;
	private double fCost;
	private final IVector coordinates;

	private final MapNode mapNode;
	private Node goalNode;

	public Node(IVector coordinates, MapNode mapNode) {
		this.coordinates = coordinates;
		this.mapNode = mapNode;
	}

	public Collection<Node> getNeighboringNodes() {
		return this.mapNode.getNeighboringNodes().stream().map(mapNode1 -> new Node(this.coordinates, mapNode1)).toList();

	}

	public IVector getCoordinates() {
		return coordinates;
	}

	public MapNode getMapNode() {
		return this.mapNode;
	}

	public boolean hasSameMapNode(Node node) {
		return this.getMapNode().equals(node.getMapNode());
	}

	public double getGCost() {
		return this.gCost;
	}

	public double getFCost() {
		return this.fCost;
	}

	public void setGCost(double cost) {
		this.gCost = cost;
	}

	public void setFCost(double cost) {
		this.fCost = cost;
	}

	public Node getParent() {
		return this.parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
}
