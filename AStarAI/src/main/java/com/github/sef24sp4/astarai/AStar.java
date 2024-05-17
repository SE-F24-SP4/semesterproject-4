package com.github.sef24sp4.astarai;

import com.github.sef24sp4.common.ai.map.NotAdjacentNodeException;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.IVector;

import java.util.*;

public class AStar {
	private final Node startNode; //startNode is where the entity using AI starts.
	private final Node goalNode; //goalNode is the position of the target Goal.
	private Node currentNode; //is used to iterate though path.
	private final Collection<Node> openList = new HashSet<>();
	private final List<Node> pathList = new LinkedList<>();


	public AStar(final Node startNode, final Node goalNode) {
		this.startNode = startNode;
		this.goalNode = goalNode;
	}

	public List<Node> calculatePath(ICollidableEntity entity) {
		return this.search(entity);
	}

	private void setFCostForNode(Node aNode) {
		//gCost
		double distanceSoFar = aNode.getGCost();
		//hCost
		double heuristics = aNode.getMapNode().calculateHeuristicsFor(this.goalNode.getMapNode());
		//fCost
		aNode.setFCost(distanceSoFar + heuristics);
	}

	/**
	 * Using A* algorithm, this method finds the optimal path to a targetNode.
	 * @param entity is the entity that needs to be moved.
	 * @return a list of Nodes, which is the path of Nodes.
	 * Each Node holds a MapNode and a Coordinate.
	 */
	private List<Node> search(ICollidableEntity entity) {
		this.currentNode = this.startNode; //at first current node is the same as startnode

		this.currentNode.setGCost(0);

		while (!(this.currentNode == this.goalNode)) { //while goal not reached

			this.openList.remove(this.currentNode);
			this.currentNode.setChecked(true);

			//get neighbors and open nodes.
			for (Node eachNode : this.currentNode.getNeighboringNodesFromMapNode()) {
				if (eachNode.getCoordinates() == this.goalNode.getCoordinates()) {
					this.goalNode.setParent(this.currentNode);
					this.currentNode = this.goalNode;
					this.trackPath();
					return this.pathList;
				}
				this.openNode(entity, eachNode);
			}

			if (this.openList.isEmpty()) {
				break;
			}
			Optional<Node> minNode = this.openList.stream()
					.min(Comparator.comparingDouble(Node::getFCost)
							.thenComparing(Node::getGCost));

			if (minNode.isPresent()) {
				this.currentNode = minNode.get();
			}
		}
		this.trackPath(); //when whileloop is over : track Path and add to pathList
		return this.pathList;
	}

	private void trackPath() {
		while (!(this.currentNode == this.startNode)) { //while current node != startnode : backtrack path
			this.pathList.add(0, this.currentNode);
			this.currentNode = this.currentNode.getParent(); //parent to currentnode, is the next on path.
		}
	}

	//Method for open node if not already in the openList
	private void openNode(ICollidableEntity entity, Node aNode) {
		if (this.openList.contains(aNode)) return;
		if (aNode.isChecked()) return;

		//Check how far Entity can go into the aNode.
		try {
			Optional<IVector> aNodeSafeCoordinates = aNode.getMapNode().getSafeCoordinatesForEntity(entity, this.currentNode.getCoordinates());

			if (aNodeSafeCoordinates.isPresent()) {

				double distanceFromParentNode = aNodeSafeCoordinates.get().getVectorTo(this.currentNode.getCoordinates()).getNorm();

				Node newNode = new Node(aNodeSafeCoordinates.get(), aNode.getMapNode());

				newNode.setParent(this.currentNode); //set currentnode as parent
				newNode.setGCost(this.currentNode.getGCost() + distanceFromParentNode);
				this.setFCostForNode(newNode); //set cost when node is opened

				this.openList.add(newNode);
			}
		} catch (NotAdjacentNodeException e) {
			throw new RuntimeException(e);
		}
	}

}




