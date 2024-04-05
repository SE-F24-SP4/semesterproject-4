package com.github.sef24sp4.astarai;


public class Node {
	private Node parent;
	private int x;
	private int y;
	private int gCost;
	private int hCost;
	private int fCost;
	private boolean solid;
	private boolean open;
	private boolean checked;


	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getGCost() {
		return this.gCost;
	}

	public int getHCost() {
		return this.hCost;
	}

	public int getFCost() {
		return this.fCost;
	}

	public boolean isSolid() {
		return this.solid;
	}

	public boolean isOpen() {
		return this.open;
	}

	public boolean isChecked() {
		return this.checked;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setGCost(int cost) {
		this.gCost = cost;
	}

	public void setHCost(int cost) {
		this.hCost = cost;
	}

	public void setFCost(int cost) {
		this.fCost = cost;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Node getParent() {
		return this.parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
}
