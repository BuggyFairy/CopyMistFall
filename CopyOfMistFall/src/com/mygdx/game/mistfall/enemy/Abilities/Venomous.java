package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Venomous {
	
	// Venomous: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, place 1 Poison Token on that player's Hero Charter"
	
	public static void addModification(GameController gc,Enemy enemy, EnemyArea dest,Hero heroDest){
		
		if (dest==EnemyArea.HERO){
			int enemyPos=gc.getHeroes().get(heroDest.getHeroID()).getHeroEnemies().getEnemyPos(enemy);
			gc.getHeroes().get(heroDest.getHeroID()).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.VENOMOUS, ModTarget.GENERAL, 0, enemy.getEnemyID(), enemy.getName());
		}
	}
	
	
	public static void applyEffect(){
		
	}

}
