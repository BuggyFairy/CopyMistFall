package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.hero.Hero;

public class Scavenger {

	// Scavenger: "<Hero Area> After this Enemy enters a <Hero Area> the Player Buries 2 cards from their discard pile if able."
	public static void activateScavenger(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource){
		if (dest==EnemyArea.HERO){
			// TODO: Bury 2 cards
		}
	}
}
