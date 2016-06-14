package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class PackHunter {

	
	public static void update(GameController gc,Enemy targetEnemy,EnemyArea source, EnemyArea dest,Hero heroDest ){
		
		int houndCount;
		
		// If the Enemy with the PACK_HUNTER Ability was moved in a Hero Area update or create the PACK_HUNTER Modification
		if (dest==EnemyArea.HERO){
			// Get Number of Hounds in the same Area
			houndCount=0;
			for (Enemy enemy : heroDest.getEnemies()){
				if (enemy.getEnemyKeyword().contains(EnemyKeyword.HOUND) && enemy!=targetEnemy){
					houndCount++;
				}
			}
			// Try Update, if not successful, generate new entry
			targetEnemy.updateModification(ModSource.ENEMY, ModType.PACK_HUNTER, ModTarget.ATTACK, houndCount, targetEnemy.getEnemyID());
		}
		// If the Enemy with the PACK_HUNTER Ability was moved to the Quest Area remove the PACK_HUNTER Modification if possible
		if (dest==EnemyArea.QUEST){
			targetEnemy.removeModification(ModType.PACK_HUNTER, ModSource.ENEMY, targetEnemy.getEnemyID());
		}
	}
	
	
	public static void updateHound(GameController gc,Enemy targetEnemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource){
		
		int houndCount;
		
		// Update Pack Hunter if a HOUND moved or left a Hero Area with a Enemy with the PACK_HUNTER Ability
		if (targetEnemy.getEnemyKeyword().contains(EnemyKeyword.HOUND)){
			// if the HOUND Enemy was moved in a Hero Area
			if (dest==EnemyArea.HERO){
				houndCount=0;
				// Count the HOUND enemies
				for (Enemy enemy : heroDest.getEnemies()){
					if (enemy.getEnemyKeyword().contains(EnemyKeyword.HOUND)){
						houndCount++;
					}
				}
				// Check if there are Enemies with the PACK_HUNTER Ability in the same Area
				for (Enemy enemy : heroDest.getEnemies()){
					if (enemy.searchAbility(EnemyAbilityType.PACK_HUNTER)){
						// If a PACK_HUNTER Enemy is found an there are more than one HOUND enemy in the same Area, update the PACK_HUNTER Enemy
						// with the value houndCount-1 because he is a Hound himself, but does not count for his Ability
						if (houndCount>1){
							enemy.updateModification(ModSource.ENEMY, ModType.PACK_HUNTER, ModTarget.ATTACK, houndCount-1, enemy.getEnemyID());
						}
					}
				}
			}
			// if the HOUND Enemy was moved out of a Hero Area
			if (source==EnemyArea.HERO){
				houndCount=0;
				
				// Count the HOUND enemies
				for (Enemy enemy : heroSource.getEnemies()){
					if (enemy.getEnemyKeyword().contains(EnemyKeyword.HOUND)){
						houndCount++;
					}
				}
				// Check if there are Enemies with the PACK_HUNTER Ability in the same Area
				for (Enemy enemy : heroSource.getEnemies()){
					if (enemy.searchAbility(EnemyAbilityType.PACK_HUNTER)){
						// If a PACK_HUNTER Enemy is found an there are more than one HOUND enemy in the same Area, update the PACK_HUNTER Enemy
						// with the value houndCount-1 because he is a Hound himself, but does not count for his Ability
						if (houndCount>1){
							enemy.updateModification(ModSource.ENEMY, ModType.PACK_HUNTER, ModTarget.ATTACK, houndCount-1, enemy.getEnemyID());
						}
						// If not remove the Modification from the PACK_HUNTER Enemy if possible
						else{
							enemy.removeModification(ModType.PACK_HUNTER, ModSource.ENEMY, enemy.getEnemyID());
						}
					}
				}
			}			
		}
	}
	
}
