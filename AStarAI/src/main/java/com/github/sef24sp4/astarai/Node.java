package com.github.sef24sp4.astarai;

import java.util.PriorityQueue;

public class Node {
	public int x, y;

	public int gCost, hCost, fCost;

	public boolean solid;
	public boolean open;
	public boolean checked;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
