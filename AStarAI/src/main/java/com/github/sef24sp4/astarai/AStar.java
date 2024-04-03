package com.github.sef24sp4.astarai;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar {
	Node[][] node;
	Node startNode, goalNode, currentNode;
	public boolean goalReached = false;

	ArrayList<Node> openList = new ArrayList<>();
	ArrayList<Node> closedList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	//Boolean [][] closedList = new Boolean[row][col];


	//startNode is where the enemy is.
	//goalNode is the position of the player
	//currentNode, is used when calculating next node to open.

	//make nodes for the map?
	//check if node i solid?


	//method for getting neighbors

	//method for getting distance (hCost) from current node to goalNode Enemy -> player.


	//method (getNeighbors) for seaching while goalNotReached(loop), Open all neighbors next to currentnode.

	public void getCost(Node node) {
		//gCost

		//hCost

		//fCost
	}

//	public void search() {
//		while (goalReached == false) {
//			for (int i = 0; i < openList.size(); i++) {
//
//			}
//		}
//	}

	public boolean getNeighbors() {
		while (goalReached == false) {
			int x = currentNode.x;
			int y = currentNode.y;

			currentNode.checked = true;
			openList.remove(currentNode);

			//open left neighbor
			if (x - 1 >= 0) { //is this correct?
				openNode(node[x - 1][y]);
			}

			//open right neihgbor

			//open top neighbor

			//open button neighbor

			//topleft
			//topright
			//buttonleft
			//buttonright

			int bestNode = 0;
			int bestFCost = 999;

			//find best node with loop though openlist, comparing fCost.
			for (int i = 0; i < openList.size(); i++) {
				if (openList.get(i).fCost < bestFCost) {
					bestNode = i;
					bestFCost = openList.get(i).fCost;
				}
				//if f cost i equal, compare gcost.
			}

			//If there is no elements in openlist, end loop.
			if (openList.isEmpty()) {
				break;
			}
			currentNode = openList.get(bestNode);

			if (currentNode.equals(goalNode)) {
				goalReached = true;
				//track the path?
			}

		}
		return false;
	}

	private void openNode(Node node) {
		if (node.open == false && node.checked == false && node.solid == false) {

			node.open = true;
			//set currentnode as parent?

			openList.add(node);

		}
	}
}

/*
HVORDAN ER VORES MAPSTRUKTUR, IFT AT SE HVOR NODES ER?
- HVORDAN UDREGNES AFSTANDEN AF G,F,H COST.


 */
