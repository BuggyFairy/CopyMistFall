package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.enums.EnemyKeyword;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class PackHunter {

	
	public static void update(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource){
		
		int enemyPos;
		int heroIdDest;
		int houndCount;

		
		// If the Enemy with the PACK_HUNTER Ability was moved in a Hero Area update or create the PACK_HUNTER Modification
		if (dest==EnemyArea.HERO){
			heroIdDest=heroDest.getHeroID();
			enemyPos=gc.getHeroes().get(heroIdDest).getHeroEnemies().getEnemyPos(enemy);
			// Get Number of Hounds in the same Area
			houndCount=0;
			for (int i=0;i<gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().size();i++){
				if (gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).getEnemyKeyword().contains(EnemyKeyword.HOUND) &&
					i!=enemyPos){
					houndCount++;
				}
			}
			// Try Update, if not successful, generate new entry
			gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.PACK_HUNTER, ModTarget.ATTACK, houndCount, enemy.getEnemyID(), enemy.getName());
		}
		// If the Enemy with the PACK_HUNTER Ability was moved to the Quest Area remove the PACK_HUNTER Modification
		if (dest==EnemyArea.QUEST){
			enemyPos=gc.getQuestArea().getEnemyPos(enemy);
			gc.getQuestArea().getQuestAreaEnemies().get(enemyPos).removeModification(ModType.PACK_HUNTER, ModSource.ENEMY, enemy.getEnemyID());
		}
	}
	
	
	public static void updateHound(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource){
		
		int enemyID;
		String enemyName;
		int heroIdDest;
		int heroIDsource;
		int houndCount;
		
		// Update Pack Hunter if a HOUND moved or left a Hero Area with a Enemy with the PACK_HUNTER Ability
		if (enemy.getEnemyKeyword().contains(EnemyKeyword.HOUND)){
			// if the HOUND Enemy was moved in a Hero Area
			if (dest==EnemyArea.HERO){
				houndCount=0;
				heroIdDest=heroDest.getHeroID();
				// Count the HOUND enemies
				for (int i=0;i<gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().size();i++){
					houndCount++;
				}
				// Check if there are Enemies with the PACK_HUNTER Ability in the same Area
				for (int i=0;i<gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().size();i++){
					if (gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).searchAbility(EnemyAbilityType.PACK_HUNTER)){
						// If a PACK_HUNTER Enemy is found an there are more than one HOUND enemy in the same Area, update the PACK_HUNTER Enemy
						// with the value houndCount-1 because he is a Hound himself, but does not count for his Ability
						enemyID=gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).getEnemyID();
						enemyName=gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).getName();
						if (houndCount>1){
							gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).updateModification(ModSource.ENEMY, ModType.PACK_HUNTER, ModTarget.ATTACK, houndCount-1, enemyID, enemyName);
						}
					}
				}
			}
			// if the HOUND Enemy was moved out of a Hero Area
			if (source==EnemyArea.HERO){
				houndCount=0;
				heroIDsource=heroSource.getHeroID();
				// Count the HOUND enemies
				for (int i=0;i<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();i++){
					houndCount++;
				}
				// Check if there are Enemies with the PACK_HUNTER Ability in the same Area
				for (int i=0;i<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();i++){
					if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).searchAbility(EnemyAbilityType.PACK_HUNTER)){
						enemyID=gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).getEnemyID();
						enemyName=gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).getName();
						// If a PACK_HUNTER Enemy is found an there are more than one HOUND enemy in the same Area, update the PACK_HUNTER Enemy
						// with the value houndCount-1 because he is a Hound himself, but does not count for his Ability
						if (houndCount>1){
							gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).updateModification(ModSource.ENEMY, ModType.PACK_HUNTER, ModTarget.ATTACK, houndCount-1, enemyID,enemyName);
						}
						// If not remove the Modification from the PACK_HUNTER Enemy if possible
						else{
							gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).removeModification(ModType.PACK_HUNTER, ModSource.ENEMY, enemyID);
						}
					}
				}
			}			
		}
	}
	
}
