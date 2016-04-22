package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class BloodFury {

	public static void updateMoved(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource){
		
		int enemyPos;
		int heroIdDest;
		int value;

		// IF an Enemy with the BLOOD_FURY Ability enters a Hero Area, add the BLOOD_FURY Modification if the Value is >0
		if(dest==EnemyArea.HERO){
			heroIdDest=heroDest.getHeroID();
			enemyPos=gc.getHeroes().get(heroIdDest).getHeroEnemies().getEnemyPos(enemy);
			value=gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).getLife().getWounds();
			if (value>0){
				gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.BLOOD_FURY, ModTarget.ATTACK, value, enemy.getEnemyID(), enemy.getName());
			}
		}
		// If a Enemy with the BLOOD_FURY Ability moves from a Hero Area in the Quest Area, remove the BLOOD_FURY Modification if possible
		if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
			enemyPos=gc.getQuestArea().getEnemyPos(enemy);
			gc.getQuestArea().getQuestAreaEnemies().get(enemyPos).removeModification(ModType.BLOOD_FURY, ModSource.ENEMY, enemy.getEnemyID());
		}
	}
	
	public static void updateTakenDamage(GameController gc,Enemy enemy,Hero hero){
		
		int enemyPos;
		int heroIDsource;
		int value;
		
		heroIDsource=hero.getHeroID();
		enemyPos=gc.getHeroes().get(heroIDsource).getHeroEnemies().getEnemyPos(enemy);
		value=gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(enemyPos).getLife().getWounds();
		gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.BLOOD_FURY, ModTarget.ATTACK, value, enemy.getEnemyID(), enemy.getName());
	}
	
}
