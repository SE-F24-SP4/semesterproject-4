package com.github.sef24sp4.astarai;
import org.junit.jupiter.api.Test;

public class AStarTest {


	@Test
	void aStarNavigateTest() {

	}
	@Test
	void trackPathTest() {


		final Node startNode = null;
		final Node goalNode = null;
		final Node middleNode = null;

		AStar aStar = new AStar(startNode,goalNode);

		middleNode.setParent(goalNode);
		goalNode.setParent(middleNode);

		

	}



}




