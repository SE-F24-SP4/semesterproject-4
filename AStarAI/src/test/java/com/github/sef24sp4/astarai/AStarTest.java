package com.github.sef24sp4.astarai;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.interfaces.IVector;
import org.junit.jupiter.api.Test;

public class AStarTest {
	private IGameSettings gameSettings;

	@Test
	void aStarNavigateTest() {
		int mapWidth = 10;
		int mapHeight = 10;

		this.gameSettings.setDisplayWidth(mapWidth);
		this.gameSettings.setDisplayHeight(mapHeight);

		Node[][] nodes = new Node[this.gameSettings.getDisplayWidth()][this.gameSettings.getDisplayHeight()];

		for (int i = 0; i < mapWidth; i++) {
			for (int j = 0; j < mapHeight; j++) {
				nodes[i][j] = new Node(i, j);

				if (i == 3 && j >= 3 && j <= 7) {
					nodes[i][j].setSolid(true);
				}
			}

			AStar aStar = new AStar();
			CommonEntity enemy = new CommonEntity();
			enemy.setX(1);
			enemy.setY(1);

			IVector iVector = new Coordinates(8, 8);

			aStar.nextCoordinateInPath(enemy, iVector);
		}
	}
}
