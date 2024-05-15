package com.github.sef24sp4.astarai;

import com.github.sef24sp4.common.ai.map.Map;
import com.github.sef24sp4.common.ai.map.MapNode;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.vector.IVector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AStarTest {

	private Node startNode;
	private Node goalNode;
	private Map map;
	private MapNode mapNode;
	private AStar aStar;


	@Test
	void calculatePathTest() {
		this.map = mock(Map.class); //mock the map
		this.mapNode = mock(MapNode.class);
		ICollidableEntity entity = mock(ICollidableEntity.class);

		//create 4x4 grid and set up connections
		MapNode[][] grid = new MapNode[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				grid[i][j] = mock(MapNode.class);
				when(this.map.getNodeContaining(any(IVector.class))).thenReturn(Optional.of(grid[i][j]));
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				List<MapNode> neighbors = new ArrayList<>();
				if (i > 0) neighbors.add(grid[i - 1][j]); // Up
				if (i < 3) neighbors.add(grid[i + 1][j]); // Down
				if (j > 0) neighbors.add(grid[i][j - 1]); // Left
				if (j < 3) neighbors.add(grid[i][j + 1]); // Right

				// Add diagonal neighbors
				if (i > 0 && j > 0) neighbors.add(grid[i - 1][j - 1]); // Up-Left
				if (i > 0 && j < 3) neighbors.add(grid[i - 1][j + 1]); // Up-Right
				if (i < 3 && j > 0) neighbors.add(grid[i + 1][j - 1]); // Down-Left
				if (i < 3 && j < 3) neighbors.add(grid[i + 1][j + 1]); // Down-Right

				when(grid[i][j].getNeighboringNodes()).thenReturn(neighbors);
			}
		}
		IVector startCoordinate = new Coordinates(0, 0);
		IVector goalCoordinate = new Coordinates(3, 3);
		this.startNode = new Node(startCoordinate, grid[0][0]);
		this.goalNode = new Node(goalCoordinate, grid[3][3]);
		Node currentNode = this.startNode;

		//need help to mock the getSafeCoordinatesForEntity
		/*
		when(mapNode.getSafeCoordinatesForEntity(eq(entity),eq(this.currentNode.getCoordinates()))).
				thenReturn(Optional.ofNullable(new Node(node.getCoordinates(), this.currentNode.getMapNode()).getCoordinates()));
		 */
		/*
		when(currentNode.getMapNode().getSafeCoordinatesForEntity(entity,currentNode.getMapNode().getCenterCoordinates())).
				thenReturn(Optional.ofNullable())

		 */


		this.aStar = new AStar(this.startNode, this.goalNode);
		List<Node> path = this.aStar.calculatePath(entity);

		List<IVector> expectedPath = Arrays.asList(
				new Coordinates(0, 0),
				new Coordinates(1, 1),
				new Coordinates(2, 2),
				new Coordinates(3, 3)
		);
		/*
		for (int i = 0; i < expectedPath.size(); i++) {
			assertEquals(expectedPath.get(i),path.get(i).getCoordinates());
		}

		 */
	}


}







