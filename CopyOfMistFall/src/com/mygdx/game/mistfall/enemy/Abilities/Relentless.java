package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Relentless {

	public static void update(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource){
		
		int enemyPos;
		int heroIdDest;

		
		// If a Enemy with the RELENTLESS Ability enters a Hero Area add the RELENTLESS Modification
		if (dest==EnemyArea.HERO){
			heroIdDest=heroDest.getHeroID();
			enemyPos=gc.getHeroes().get(heroIdDest).getHeroEnemies().getEnemyPos(enemy);
			gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.RELENTLESS, ModTarget.GENERAL, 0, enemy.getEnemyID());
		}
		// If a Enemy with the RELENTLESS Modification moves from a Hero Area in the Quest Area, remove the RELENTLESS Modification if possible
		if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
			enemyPos=gc.getQuestArea().getEnemyPos(enemy);
			gc.getQuestArea().getQuestAreaEnemies().get(enemyPos).removeModification(ModType.RELENTLESS, ModSource.ENEMY, enemy.getEnemyID());
		}
	}
	
}
