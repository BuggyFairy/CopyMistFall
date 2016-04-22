package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Thievery {
	
	// Thievery: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, attach 1 GEAR card from their <Hero Area> to this card.
	//			  Return all Attachments to owners' HAND when this Enemy is discarded"
	
	public static void addModification(GameController gc,Enemy enemy, EnemyArea dest,Hero heroDest){
		
		if (dest==EnemyArea.HERO){
			int enemyPos=gc.getHeroes().get(heroDest.getHeroID()).getHeroEnemies().getEnemyPos(enemy);
			gc.getHeroes().get(heroDest.getHeroID()).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.THIEVERY, ModTarget.GENERAL, 0, enemy.getEnemyID(), enemy.getName());
		}
	}
	
	
	public static void applyEffect(){
		
	}

}
