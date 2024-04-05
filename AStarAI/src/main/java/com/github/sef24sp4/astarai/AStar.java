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
	//startNode is where the enemy is.
	//goalNode is the position of the player
	//currentNode, is used when calculating next node to open.

	//make nodes for the map?
	//check if node i solid?

	//method for getting neighbors

	//method for getting distance (hCost) from current node to goalNode Enemy -> player.

	//method (getNeighbors) for seaching while goalNotReached(loop), Open all neighbors next to currentnode.

	//Method for setNodes (startnode, goalNode, currentnode, og add currentnode til openList). //


	@Override
	public IVector calculateNextMove(IEntity entity, IVector targetCoordinate) {
		return null;
	}

	//TODO: Calculate costs
	public void getCost(Node aNode) {
		//gCost

		//hCost

		//fCost
	}

	//Method to expand neigbors, and open the one with best f-score.
	public boolean search() {
		while (!this.goalReached) { // and step =?
			int x = this.currentNode.getX();
			int y = this.currentNode.getY();

			this.currentNode.setChecked(true);

			this.openList.remove(this.currentNode);

			//open left neighbor
			if (x - 1 >= 0) { //is this correct?
				this.openNode(this.node[x - 1][y]);
			}
			//Expand to see other
			//TODO: Make the right check if neighbor nodes are on the map.
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
				//track the path?
				trackPath(); //
			}
			//step++? to avoid it calculates the whole way, when enemy is far away.
			//Could be based on the distance of Heuristics.

		}
		return this.goalReached; //return if goal is reached or not.
	}


	public void trackPath() { //This method can be used to draw the path.
		Node currentNode = this.goalNode;

		while (currentNode != this.startNode) {
			this.pathList.add(0, currentNode);

			//TODO: set parent for currentnode?

		}
	}

	//Method for open nodes if: not open, not checked and not solid : open it.
	private void openNode(Node aNode) {

		if (!aNode.isOpen() && !aNode.isChecked() && !aNode.isSolid()) {

			aNode.setOpen(true);
			//set currentnode as parent
			aNode.setParent(currentNode);

			this.openList.add(aNode);

		}
	}

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




