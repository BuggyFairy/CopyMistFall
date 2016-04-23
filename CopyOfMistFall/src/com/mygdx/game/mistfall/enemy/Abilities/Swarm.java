package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Swarm {

	public static void updateMoved(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource){
		
		int enemyPos;
		int heroIdDest;
		int value;

		// IF an Enemy with the SWARM Ability enters a Hero Area, add the SWARM Modification if the Value is >0
		if(dest==EnemyArea.HERO){
			heroIdDest=heroDest.getHeroID();
			enemyPos=gc.getHeroes().get(heroIdDest).getHeroEnemies().getEnemyPos(enemy);
			value=gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).getLife().getValueCurrent();
			if (value>0){
				gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.SWARM, ModTarget.ATTACK, value, enemy.getEnemyID());
			}
		}
		// If a Enemy with the SWARM Ability moves from a Hero Area in the Quest Area, remove the SWARM Modification if possible
		if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
			enemyPos=gc.getQuestArea().getEnemyPos(enemy);
			gc.getQuestArea().getQuestAreaEnemies().get(enemyPos).removeModification(ModType.SWARM, ModSource.ENEMY, enemy.getEnemyID());
		}
	}
	
	public static void updateTakenDamge(GameController gc,Enemy enemy,Hero hero){
		
		int enemyPos;
		int heroIDsource;
		int value;
		
		heroIDsource=hero.getHeroID();
		enemyPos=gc.getHeroes().get(heroIDsource).getHeroEnemies().getEnemyPos(enemy);
		value=gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(enemyPos).getLife().getValueCurrent();
		gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.SWARM, ModTarget.ATTACK, value, enemy.getEnemyID());
	}
	
}
