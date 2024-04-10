package com.github.sef24sp4.astarai;

import com.github.sef24sp4.common.ai.IPathfindingProvider;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IVector;

import java.util.ArrayList;

public class AStar implements IPathfindingProvider {
	private Node[][] node;
	private Node startNode;
	private Node goalNode;
	private Node currentNode;

	private boolean goalReached = false;

	private ArrayList<Node> openList = new ArrayList<>();
	private ArrayList<Node> closedList = new ArrayList<>();
	private ArrayList<Node> pathList = new ArrayList<>();

	//Explaination
	//startNode is where the entity using AI is.
	//goalNode is the position of the targetGoal
	//currentNode, is used when calculating next node to open.


	@Override
	public IVector nextCoordinateInPath(IEntity entity, IVector targetCoordinate) {
		//TODO: targetCoordinate = location of player = goalnode?
		int y = (int) targetCoordinate.getY(); //does it cause problems that it casts to int?
		int x = (int) targetCoordinate.getX();

		this.goalNode = node[x][y]; //does this work?
		//TODO: Make this.startNode = entity.x,y

		double iVectorX = getNextStep().getX();
		double iVectorY = getNextStep().getY();





		//Method for setNodes (startnode, goalNode, currentnode, og add currentnode til openList). //

		//set nodes for whole map?


		//TODO: Where is the enemy, and how do i call the search method?

		return null;
	}
	//returns the next Node in the pathList
	public Node getNextStep() {
		if  (!this.pathList.isEmpty()) {
			return pathList.remove(0);
		}
		return null;
	}

	//TODO: Calculate costs
	public void getCost(Node aNode) {
		//gCost

		//hCost

		//fCost
	}


	public boolean search() { //could i add parameter nodes?
		while (!this.goalReached) { // and step =?

			int x = this.currentNode.getX();
			int y = this.currentNode.getY();

			//TODO: need to specify currentNode?
			this.currentNode.setChecked(true);

			this.openList.remove(this.currentNode);

			//open left neighbor
			if (x - 1 >= 0) { //is this correct?
				this.openNode(this.node[x - 1][y]);
			}
			//Expand to see other
			//TODO: Check if neighbor nodes are on the map.
/*
			//open right neighbor
			if (x + 1 < gridSizeX) {
				this.openNode(this.node[x + 1][y]);
			}
			//open top neighbor
			if (y - 1 >= 0) {
				this.openNode(this.node[x][y - 1]);
			}
			//open bottom neighbor
			if (y + 1 < gridSizeY) {
				this.openNode(this.node[x][y + 1]);
			}
			//topleft
			if (x - 1 >= 0 && y - 1 >= 0) {
				this.openNode(this.node[x - 1][y - 1]);
			}
			//topright
			if (x + 1 < gridSizeX && y - 1 >= 0) {
				this.openNode(this.node[x + 1][y - 1]);
			}
				//bottomleft
			if (x - 1 >= 0 && y + 1 < gridSizeY) {
				this.openNode(this.node[x - 1][y + 1]);
			}
			//bottomright
			if (x + 1 < gridSizeX && y + 1 < gridSizeY) {
				this.openNode(this.node[x + 1][y + 1]);
			}
 */

			int bestNode = 0;
			int bestFCost = 999;

			//find best node with loop though openlist, comparing fCost.
			for (int i = 0; i < this.openList.size(); i++) {
				if (this.openList.get(i).getFCost() < bestFCost) {
					bestNode = i;
					bestFCost = this.openList.get(i).getFCost();
				}
				//TODO: if f cost is equal, compare gcost.
			}
			//If there is no elements in openlist, end loop.
			if (this.openList.isEmpty()) {
				break;
			}

			this.currentNode = this.openList.get(bestNode);

			if (this.currentNode.equals(this.goalNode)) {
				this.goalReached = true;

				//when goal reached : track the path by adding all parent node from goalNode to startNode.
				trackPath();

			}
			//step++? to avoid it calculates the whole way, when enemy is far away.
			//Could be based on the distance of Heuristics.

		}
		return this.goalReached; //return if goal is reached or not.
	}


	public void trackPath() { //This method can be used to draw and track the path from goalNode to startNode.
		Node currentNode = this.goalNode;

		while (currentNode != this.startNode) {
			this.pathList.add(0, currentNode); //does it always add to index 0?
			currentNode = currentNode.getParent(); //parent to currentnode, is the next on path.

			//TODO: track the path with color?
		}
	}

	//Method for open nodes if: not open, not checked and not solid : open it.
	private void openNode(Node aNode) {

		if (!aNode.isOpen() && !aNode.isChecked() && !aNode.isSolid()) {

			aNode.setOpen(true);

			aNode.setParent(currentNode); //set currentnode as parent

			this.openList.add(aNode);

		}
	}

	//getters and setters
	public Node[][] getNode() {
		return this.node;
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

	public ArrayList<Node> getOpenList() {
		return this.openList;
	}

	public ArrayList<Node> getClosedList() {
		return this.closedList;
	}

	public ArrayList<Node> getPathList() {
		return this.pathList;
	}

	public void setNode(Node[][] node) {
		this.node = node;
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




