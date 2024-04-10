package com.github.sef24sp4.astarai;

import com.github.sef24sp4.common.ai.IPathfindingProvider;
import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.gamecontrol.IGameInput;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.interfaces.IVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Double.MAX_VALUE;

public class AStar implements IPathfindingProvider {
	private Node[][] nodes;

	private Node node;
	private Node startNode;
	private Node goalNode;
	private Node currentNode;

	private boolean goalReached = false;

	private List<Node> openList = new ArrayList<>(); //best for random access
	private List<Node> closedList = new ArrayList<>(); //do i even use this?
	private List<Node> pathList = new ArrayList<>(); //Is PriorityQueue good when removing specific element - LL is best for 1st element?

	private IGameSettings gameSettings;
	

	public AStar() {
	}

	private ArrayList<Node> openList = new ArrayList<>();
	private ArrayList<Node> closedList = new ArrayList<>();
	private ArrayList<Node> pathList = new ArrayList<>();

	//Explaination
	//startNode is where the entity using AI is.
	//goalNode is the position of the targetGoal
	//currentNode, is used when calculating next node to open.


	//Method for setNodes (startnode, goalNode, currentnode, og add currentnode til openList). //
	//set nodes for whole map?

	@Override
	public IVector nextCoordinateInPath(IEntity entity, IVector targetCoordinate) {
		//TODO: targetCoordinate = location of player = goalnode?
		int y = (int) targetCoordinate.getY(); //does it cause problems that it casts to int?
		int x = (int) targetCoordinate.getX();
	public IVector nextCoordinateInPath(IEntity entity, IVector targetCoordinate) {
		//set goalNode based on targetCoordinate
		setNodes();

	//make nodes for the map?
	//check if node i solid?
		int targetX = (int) Math.round(targetCoordinate.getX()); //round to nearest int (so 3.9 is 4 instead of 3)
		int targetY = (int) Math.round(targetCoordinate.getY());
		System.out.println(targetX);
		System.out.println(targetY);
		System.out.println(getNodes());

		setGoalNode(nodes[targetX][targetY]);

		//set startNode based on entity
		int startX = (int) Math.round(entity.getX());
		int startY = (int) Math.round(entity.getY());
		//setStartNode(new Node(startX,startY));

		this.search();

		Optional<Node> nextStep = this.getNextStep();
		if (nextStep.isPresent()) {
			Node nextNode = nextStep.get();
			return new Coordinates(nextNode.getX(), nextNode.getY());
		}
		return entity.getCoordinates();
	}

		if (pathFound) { //can be exchanged with just if search
			Node nextStep = getNextStep();
			double nextStepX = nextStep.getX();
			double nextStepY = nextStep.getY();

	private void setNodes() { //public?
		int width = Math.round(this.gameSettings.getDisplayWidth());
		int height = Math.round(this.gameSettings.getDisplayHeight());

	//Method for setNodes (startnode, goalNode, currentnode, og add currentnode til openList). //
		this.nodes = new Node[width][height];

		for (int x = 0; x < width - 1; x++) {
			for (int y = 0; y < height - 1; y++) {
				this.nodes[x][y] = new Node(x, y);
				//set some nodes to solid?
			}
		}
	}

	private Optional<Node> getNextStep() { //returns the next Node in the pathList
		if (!this.pathList.isEmpty()) {
			return Optional.of(this.pathList.remove(0));
		}
		return Optional.empty();
	}

	public Optional<Node> getNextStep() { //returns the next Node in the pathList
		if (!this.pathList.isEmpty()) {
			return Optional.of(pathList.remove(0));
		}
		return Optional.empty();
	}

	public Optional<Node> getNextStep() { //returns the next Node in the pathList
		if (!this.pathList.isEmpty()) {
			return Optional.of(pathList.remove(0));
		}
		return Optional.empty();
	}

	public Node getNextStep() { //returns the next Node in the pathList
		if  (!this.pathList.isEmpty()) {
			return pathList.remove(0);
		}
		return null;
	}

	//TODO: Calculate costs
	public void getCost(Node aNode) {
		//gCost (maybe it should be all parent nodes to startnode to get accurate cost)
		double gCostX = (double) aNode.getX() - (double) this.startNode.getX();
		double gCostY = (double) aNode.getY() - (double) this.startNode.getY();
		double gDistance = Math.sqrt(gCostX * gCostX + gCostY * gCostY);
		aNode.setGCost(gDistance);
		//hCost
		double hCostX = (double) aNode.getX() - (double) this.goalNode.getX();
		double hCostY = (double) aNode.getY() - (double) this.goalNode.getY();
		double hDistance = Math.sqrt(hCostX * hCostX + hCostY * hCostY);
		//fCost
		aNode.setFCost(gDistance + hDistance);
	}

	private void search() { //method

		this.currentNode = this.startNode; //at first current node is the same as startnode

		while (!this.goalReached) { // and step =?

			//TODO: need to specify currentNode?
			this.currentNode.setChecked(true);
			this.openList.remove(this.currentNode); //what if it is not in list?

			int bestNode = 0;
			int bestFCost = 999;
			this.checkNeighborNodes(this.currentNode); //neighbors added to openlist if possible.

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
			//step++? to avoid it calculates the whole way, when enemy is far away.
			//Could be based on the distance of Heuristics.

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
		Node endNode = this.goalNode;

		while (this.currentNode != this.startNode) {
			this.pathList.add(0, this.currentNode);
			this.currentNode = this.currentNode.getParent(); //parent to currentnode, is the next on path.

			// track the path with color?
		}
		return this.goalReached; //return if goal is reached or not.
	}

	private void checkNeighborNodes(Node theCurrentNode) {
		//open left neighbor
		int x = theCurrentNode.getX();
		int y = theCurrentNode.getY();

		if (x - 1 >= 0) { //is this correct?
			this.openNode(this.nodes[x - 1][y]);
		}
		//open right neighbor
		if (x + 1 < this.gameSettings.getDisplayWidth()) {
			this.openNode(this.nodes[x + 1][y]);
		}
		//open top neighbor
		if (y - 1 >= 0) {
			this.openNode(this.nodes[x][y - 1]);
		}
		//open bottom neighbor
		if (y + 1 < this.gameSettings.getDisplayHeight()) {
			this.openNode(this.nodes[x][y + 1]);
		}
		//topleft
		if (x - 1 >= 0 && y - 1 >= 0) {
			this.openNode(this.nodes[x - 1][y - 1]);
		}
		//topright
		if (x + 1 < this.gameSettings.getDisplayWidth() && y - 1 >= 0) {
			this.openNode(this.nodes[x + 1][y - 1]);
		}
		//bottomleft
		if (x - 1 >= 0 && y + 1 < this.gameSettings.getDisplayHeight()) {
			this.openNode(this.nodes[x - 1][y + 1]);
		}
		//bottomright
		if (x + 1 < this.gameSettings.getDisplayWidth() && y + 1 < this.gameSettings.getDisplayHeight()) {
			this.openNode(this.nodes[x + 1][y + 1]);
		}
	}

	//Method for open nodes if: not open, not checked and not solid : open it.
	private void openNode(Node aNode) {

		if (!aNode.isOpen() && !aNode.isChecked() && !aNode.isSolid()) {

			aNode.setOpen(true);

			aNode.setParent(this.currentNode); //set currentnode as parent

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




