package com.github.sef24sp4.astarai;

import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.vector.IVector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AStarTest {

	private IGameSettings gameSettings;


	@BeforeEach
	public void setup() {
		this.gameSettings = mock(IGameSettings.class);
	}


	@Test
	void aStarNavigateTest() {

		int mapWidth = 10;
		int mapHeight = 10;

		AStar aStar = new AStar();
		aStar.setNodes(new Node[mapWidth][mapHeight]);

		CommonEntity enemy = new CommonEntity();
		enemy.setX(1);
		enemy.setY(1);

		IVector goalNodeCoordinates = new Coordinates(8, 8);

		System.out.println("Path from start to goal");


		//aStar.nextCoordinateInPath(enemy, goalNodeCoordinates);
	}

}




