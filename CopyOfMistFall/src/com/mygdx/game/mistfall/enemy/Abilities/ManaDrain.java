package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class ManaDrain {
	
	// Mana Drain: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, that player discards 1 ARCANE or SPELL card, if able"
	
	public static void addModification(GameController gc,Enemy enemy, EnemyArea dest,Hero heroDest){
		
		if (dest==EnemyArea.HERO){
			int enemyPos=gc.getHeroes().get(heroDest.getHeroID()).getHeroEnemies().getEnemyPos(enemy);
			gc.getHeroes().get(heroDest.getHeroID()).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.MANA_DRAIN, ModTarget.GENERAL, 0, enemy.getEnemyID());
		}
	}
	
	
	public static void applyEffect(){
		
	}

}
