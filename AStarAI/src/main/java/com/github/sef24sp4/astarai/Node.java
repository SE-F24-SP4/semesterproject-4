package com.github.sef24sp4.astarai;


public class Node {
	private Node parent;
	private int x;
	private int y;
	private double gCost;
	private double hCost;
	private double fCost;
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

	public double getGCost() {
		return this.gCost;
	}

	public double getHCost() {
		return this.hCost;
	}

	public double getFCost() {
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

	public void setGCost(double cost) {
		this.gCost = cost;
	}

	public void setHCost(double cost) {
		this.hCost = cost;
	}

	public void setFCost(double cost) {
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
