package com.mygdx.game.mistfall.controller;

import com.mygdx.game.mistfall.enemy.Enemy;

public class Test2 {
	public static void main(String[] args) {
		GameController gc = new GameController();
		gc.setEnemyController(new EnemyController());
		gc.setGameSetupController(new GameSetupController(gc));
		Enemy enemyGreen1=gc.getGameSetupController().getGreenEnemies().get(0);
		Enemy enemyBlue1=gc.getGameSetupController().getBlueEnemies().get(0);
		Enemy enemyRed1=gc.getGameSetupController().getRedEnemies().get(0);
		System.out.println(enemyGreen1.toString());
		System.out.println(enemyBlue1.toString());
		System.out.println(enemyRed1.toString());
	}
}
