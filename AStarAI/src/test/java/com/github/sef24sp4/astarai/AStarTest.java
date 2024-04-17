package com.github.sef24sp4.astarai;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.gamecontrol.IGameInput;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.interfaces.IVector;
import com.github.sef24sp4.core.game.GameSettings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class AStarTest {

	private IGameSettings gameSettings = mock(IGameSettings.class);
	//private GameSettings gs = mock(GameSettings.class);

	@Test
	void aStarNavigateTest() {

		int mapWidth = 10;
		int mapHeight = 10;

		//when(gameSettings.setDisplayHeight(anyInt())).thenReturn());

		//doAnswer(when(gameSettings).setDisplayHeight(anyInt()));

		//this.gameSettings.setDisplayWidth(mapWidth);
		this.gameSettings.setDisplayHeight(mapHeight);
		System.out.println(gameSettings.getDisplayHeight());

		//assertTrue(this.gameSettings.getDisplayWidth() == 10);

		Node[][] nodes = new Node[10][10];


		for (int i = 0; i < mapWidth-1; i++) {
			for (int j = 0; j < mapHeight-1; j++) {
				nodes[i][j] = new Node(i,j);

				if (i == 3 && j >= 3 && j <= 7) {
					nodes[i][j].setSolid(true);
				}
			}


			//AStar aStar = new AStar();
			CommonEntity enemy = new CommonEntity();
			enemy.setX(1);
			enemy.setY(1);

			IVector iVector = new Coordinates(8, 8);


			//aStar.nextCoordinateInPath(enemy, iVector);
		}

	}


}

