package com.github.sef24sp4.astarai;

import com.github.sef24sp4.common.ai.IPathfindingProvider;
import com.github.sef24sp4.common.ai.map.Map;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.ai.map.MapNode;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.vector.IVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Double.MAX_VALUE;

public class AStar implements IPathfindingProvider {
	private Node[][] nodes;
	private Node startNode;
	private Node goalNode;
	private Node currentNode;

	private boolean goalReached = false;

	private List<Node> openList = new ArrayList<>(); //best for random access
	private List<Node> closedList = new ArrayList<>(); //do i even use this?
	private List<Node> pathList = new ArrayList<>(); //Is PriorityQueue good when removing specific element - LL is best for 1st element?

	private MapNode mapNode;
	private Map map;

	private IGameSettings gameSettings;

	public AStar() {
	}

	//Explaination
	//startNode is where the entity using AI is.
	//goalNode is the position of the targetGoal
	//currentNode, is used when calculating next node to open.


	//Method for setNodes (startnode, goalNode, currentnode, og add currentnode til openList). //
	//set nodes for whole map?

	public Coordinates nextCoordinateInPath(IEntity entity, IVector targetCoordinate) {

		int targetX = (int) Math.round(targetCoordinate.getX());
		int targetY = (int) Math.round(targetCoordinate.getY());
		System.out.println(targetX);
		System.out.println(targetY);

		this.goalNode = new Node(map.getNodeContaining(targetCoordinate).get());

		this.startNode = new Node(map.getNodeContaining(entity.getCoordinates()).get());

		this.search();

		Optional<Node> nextStep = this.getNextStep();
		if (nextStep.isPresent()) {
			Node nextNode = nextStep.get();
			return new Coordinates(nextNode.getX(), nextNode.getY());
		}

		return entity.getCoordinates();
	}

	private Optional<Node> getNextStep() { //returns the next Node in the pathList
		if (!this.pathList.isEmpty()) {
			return Optional.of(this.pathList.remove(0));
		}
		return Optional.empty();
	}

	public void getCost(Node aNode) {
		//gCost
		double distanceSoFar = aNode.getGCost();
		//hCost
		double heuristics = aNode.getMapNode().calculateHeuristicsFor(goalNode.getMapNode());
		//fCost
		aNode.setFCost(distanceSoFar + heuristics);
	}

	private void search() { //method

		this.currentNode = this.startNode; //at first current node is the same as startnode
		this.currentNode.setGCost(0);
		while (!this.goalReached) { // and step =?

			this.currentNode.setChecked(true);
			this.openList.remove(this.currentNode);

			this.currentNode.getNeighboringNodes();
			for (Node nodes: currentNode.getNeighboringNodes()) {
				nodes.setGCost(currentNode.getGCost() + 1);
			}

			int bestNode = 0; //best node in index
			double bestFCost = MAX_VALUE; //so far best fCost close to infinity.

			//find best node with loop though openList, comparing fCost.
			for (int i = 0; i < this.openList.size(); i++) {

				this.getCost(this.openList.get(i)); //calculate its cost.
				if (this.openList.get(i).getFCost() < bestFCost) {
					bestNode = i;
					bestFCost = this.openList.get(i).getFCost();
				} else if (this.openList.get(i).getFCost() == bestFCost) {
					if (this.openList.get(i).getGCost() < this.openList.get(bestNode).getGCost()) {
						bestNode = i;
					}
				}
			}

			if (this.openList.isEmpty()) {
				break;
			}

			this.currentNode = this.openList.get(bestNode); //next curentNode is the one with lowest fCost.

			if (this.currentNode.equals(this.goalNode)) {
				this.goalReached = true;
				this.trackPath();
				return;
			}
		}
	}

	private void trackPath() { //This method can be used to draw and track the path from goalNode to startNode.

		while (this.currentNode != this.startNode) {
			this.pathList.add(0, this.currentNode);
			this.currentNode = this.currentNode.getParent(); //parent to currentnode, is the next on path.
		}
	}



	//Method for open nodes if: not open, not checked and not solid : open it.
	private void openNode(Node aNode) {

		if (!aNode.isOpen() && !aNode.isChecked() && !aNode.isSolid()) {

			aNode.setOpen(true);

			aNode.setParent(this.currentNode); //set currentnode as parent
			//if a node is direct nighbor gcost = current.node + 1
			aNode.setGCost(this.currentNode.getGCost() + 1);
			//if a node is diagonal neighbor gcost = current.node + root(2)
			aNode.setGCost(this.currentNode.getGCost() + Math.sqrt(2));
			this.openList.add(aNode);

		}
	}

	//getters and setters


	public Node[][] getNodes() {
		return this.nodes;
	}

	public Node getStartNode() {
		return this.startNode;
	}

	public Node getGoalNode() {
		return this.goalNode;
	}

	public Node getCurrentNode() {
		return this.currentNode;
	}

	public boolean isGoalReached() {
		return this.goalReached;
	}

	public List<Node> getOpenList() {
		return this.openList;
	}

	public List<Node> getClosedList() {
		return this.closedList;
	}

	public List<Node> getPathList() {
		return this.pathList;
	}

	public void setNodes(Node[][] nodes) {
		this.nodes = nodes;
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}

	public void setGoalNode(Node goalNode) {
		this.goalNode = goalNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public void setGoalReached(boolean goalReached) {
		this.goalReached = goalReached;
	}

	public void setOpenList(ArrayList<Node> openList) {
		this.openList = openList;
	}

	public void setClosedList(ArrayList<Node> closedList) {
		this.closedList = closedList;
	}

	public void setPathList(ArrayList<Node> pathList) {
		this.pathList = pathList;
	}

}




