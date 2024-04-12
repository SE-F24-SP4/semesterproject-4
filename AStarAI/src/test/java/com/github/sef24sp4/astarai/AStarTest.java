package com.github.sef24sp4.astarai;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.interfaces.IVector;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Entity;

public class AStarTest {
	IGameSettings gameSettings;


	@Test
	void AStarNavigateTest() {
		int mapWidth = 10;
		int mapHeight = 10;

		gameSettings.setDisplayWidth(mapWidth);
		gameSettings.setDisplayHeight(mapHeight);
		
		Node[][] nodes = new Node[gameSettings.getDisplayWidth()][gameSettings.getDisplayHeight()];

		for (int i = 0; i < mapWidth; i++) {
			for (int j = 0; j < mapHeight; j++) {
				nodes[i][j] = new Node(i,j);

				if(i==3 && j>=3 && j <=7){
					nodes[i][j].setSolid(true);
				}
			}

			AStar aStar = new AStar();
			CommonEntity enemy = new CommonEntity();
			enemy.setX(1);
			enemy.setY(1);

			IVector iVector = new Coordinates(8,8);

			aStar.nextCoordinateInPath(enemy,iVector);

		}


	}
}
