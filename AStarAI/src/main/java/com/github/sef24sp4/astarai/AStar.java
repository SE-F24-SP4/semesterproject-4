package com.github.sef24sp4.astarai;

import com.github.sef24sp4.common.ai.IPathfindingProvider;
import com.github.sef24sp4.common.ai.map.Map;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.ai.map.MapNode;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.vector.IVector;

import java.util.*;

public class AStar {
	private final Node startNode;
	private final Node goalNode;
	private Node currentNode;
	private List<Node> openList = new ArrayList<>();
	private List<Node> pathList = new LinkedList<>();


	public AStar(final Node startNode, final Node goalNode) {
		this.startNode = startNode;
		this.goalNode = goalNode;
	}

	public List<Node> calculatePath(ICollidableEntity entity) {
		return this.search(entity);
	}

	//Explaination
	//startNode is where the entity using AI is.
	//goalNode is the position of the targetGoal
	//currentNode, is used when calculating next node to open.
	@Override
	public Coordinates nextCoordinateInPath(ICollidableEntity entity, IVector targetCoordinate) {

	private void setFCostForNode(Node aNode) {
		//gCost
		double distanceSoFar = aNode.getGCost();
		//hCost
		double heuristics = aNode.getMapNode().calculateHeuristicsFor(this.goalNode.getMapNode());
		//fCost
		aNode.setFCost(distanceSoFar + heuristics);
	}

	private List<Node> search(ICollidableEntity entity) {

		this.currentNode = this.startNode; //at first current node is the same as startnode
		this.currentNode.setGCost(0);
		while (!this.currentNode.hasSameMapNode(this.goalNode)) { //while goal == not reached

			//TODO: need to specify currentNode?
			this.currentNode.setChecked(true);
			this.openList.remove(this.currentNode);

			//get neighbors and open nodes.
			for (Node eachNode : this.currentNode.getNeighboringNodes()) {
				this.openNode(entity, eachNode);
			}

			if (this.openList.isEmpty()) {
				break;
			}

			int bestNode = 0; //best node in index

			Optional<Node> minNode = this.openList.stream()
					.min(Comparator.comparingDouble(Node::getFCost)
							.thenComparing(Node::getGCost));

			if (minNode.isPresent()) {
				bestNode = this.openList.indexOf(minNode.get());
			}

			this.currentNode = this.openList.get(bestNode); //next curentNode is the one with lowest fCost.

			if (this.currentNode.hasSameMapNode(this.goalNode)) { //if currentNode equals goalNode, goal is reached
				this.trackPath();
			}
		}
		return this.pathList;
	}

	private void trackPath() { //This method can be used to draw and track the path from goalNode to startNode.

		while (this.currentNode != this.startNode) {
			this.pathList.add(0, this.currentNode);
			this.currentNode = this.currentNode.getParent(); //parent to currentnode, is the next on path.
		}
		return this.goalReached; //return if goal is reached or not.
	}

	//Method for open nodes if: not open, not checked and not solid : open it.
	private void openNode(ICollidableEntity entity, Node aNode) {
		if (this.openList.contains(aNode)) return;

		//Check how far Entity can go into the aNode.
		Optional<IVector> aNodeSafeCoordinates = aNode.getMapNode().getSafeCoordinatesForEntity(entity, this.currentNode.getCoordinates());

		if (aNodeSafeCoordinates.isPresent()) {

			double distanceFromParentNode = aNodeSafeCoordinates.get().getVectorTo(this.currentNode.getCoordinates()).getNorm();

			Node newNode = new Node(aNodeSafeCoordinates.get(), aNode.getMapNode());

			newNode.setParent(this.currentNode); //set currentnode as parent
			newNode.setGCost(this.currentNode.getGCost() + distanceFromParentNode);
			this.setFCostForNode(newNode); //set cost when node is opened

			this.openList.add(newNode);
		}
		else {

			aNode.setGCost(this.currentNode.getGCost() + 1);

		}

		//if empty = solid node.
		//if not empty. Coodinate to openlist.

		//c1.getvectorto(c2).getnorm) = distance

		//if a node is direct nighbor gcost = current.node + 1
		//if a node is diagonal neighbor gcost = current.node + root(2)
		aNode.setGCost(this.currentNode.getGCost() + Math.sqrt(2));

		this.openList.add(aNode);

	}

}




