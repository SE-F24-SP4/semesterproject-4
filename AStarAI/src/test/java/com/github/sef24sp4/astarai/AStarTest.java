package com.github.sef24sp4.astarai;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.gamecontrol.IGameInput;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.interfaces.IVector;
import com.github.sef24sp4.common.services.IGamePluginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
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

		when(gameSettings.getDisplayWidth()).thenAnswer(invocation -> {
			return mapWidth;
		});

		when(gameSettings.getDisplayHeight()).thenAnswer(invocation -> {
			return mapHeight;
		});

		System.out.println(gameSettings.getDisplayHeight());
		System.out.println(gameSettings.getDisplayWidth());

		AStar aStar = new AStar();
		aStar.setNodes(new Node[mapWidth][mapHeight]);

/*
		for (int i = 0; i < mapWidth - 1; i++) {
			for (int j = 0; j < mapHeight - 1; j++) {
				aStar.setNodes(new Node[i][j]);

				if (i == 3 && j >= 3 && j <= 7) {
					nodegrid[i][j].setSolid(true);

				}
			}
		}
		for (int j = 0; j < mapHeight - 1; j++) {
			for (int i = 0; i < mapWidth - 1; i++) {
				if (nodegrid[i][j].isSolid()) {
					System.out.print("# "); // Print solid node
				} else {
					System.out.print(". "); // Print non-solid node
				}
			}
			System.out.println(); // Move to the next line after printing each row
		}

 */

		CommonEntity enemy = new CommonEntity();
		enemy.setX(1);
		enemy.setY(1);


		IVector goalNodeCoordinates = new Coordinates(8, 8);

		System.out.println("Path from start to goal");


		aStar.nextCoordinateInPath(enemy, goalNodeCoordinates);
	}

}




