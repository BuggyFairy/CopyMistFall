package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Skulduggery {
	
	// Skulduggery: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, remove 1 Objectiv Token from the active Encounter
	// 				card and place it on this card. Return all Objective Tokens to the Encounter card when this Enemy is discarded"
	
	public static void addModification(GameController gc,Enemy enemy, EnemyArea dest,Hero heroDest){
		
		if (dest==EnemyArea.HERO){
			int enemyPos=gc.getHeroes().get(heroDest.getHeroID()).getHeroEnemies().getEnemyPos(enemy);
			gc.getHeroes().get(heroDest.getHeroID()).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.SKULDUGGERY, ModTarget.GENERAL, 0, enemy.getEnemyID());
		}
	}
	
	
	public static void applyEffect(){
		
	}

}
