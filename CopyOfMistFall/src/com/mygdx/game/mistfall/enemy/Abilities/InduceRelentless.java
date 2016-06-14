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
import com.mygdx.game.mistfall.model.modifications.Modification;

public class InduceRelentless {
	
	
	public static void update(GameController gc,Enemy targetEnemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource, EnemyAbilityType enemyAbilityType, EnemyKeyword enemyKeyword){

		boolean modificationFound;
		
		// If a enemy with the PURSUIT Ability enters a Hero Area give the first BEAST without the RELENTLESS Modification/Ability in the same Area the RELENTLESS Modification
		if (dest==EnemyArea.HERO){
			// Go through all Enemies in the same Area
			for(Enemy enemy1 : heroDest.getEnemies()){
				// If there is a BEAST that does not possess the RELENTLESS Ability nor Modification, give it the RELENTLESS Modification and end the loop
				if (enemy1.getEnemyKeyword().contains(enemyKeyword) &&
					enemy1.searchAbility(EnemyAbilityType.RELENTLESS)==false &&
					enemy1.searchModification(ModType.RELENTLESS)==false){
					
					enemy1.updateModification(ModSource.ENEMY, ModType.RELENTLESS, ModTarget.GENERAL, 0, targetEnemy.getEnemyID());
					break;
				}
			}	
		}
		// If a enemy with the PURSUIT Ability leaves a Hero Area, remove the RELENTLESS Modification from Enemies in the source Area if possible
		if (source==EnemyArea.HERO){
			// Go through all Enemies in the same Area and remove the specified RELENTLESS Modification if able
			for (Enemy enemy1 : heroSource.getEnemies()){
				if (enemy1.getEnemyKeyword().contains(enemyKeyword) &&
					enemy1.searchAbility(EnemyAbilityType.RELENTLESS)==false &&
					enemy1.searchModification(ModType.RELENTLESS)==true){
						
					enemy1.removeModification(ModType.RELENTLESS, ModSource.ENEMY, targetEnemy.getEnemyID());
				}
			}
			// Check if there is another ENEMY with the PURSUIT Ability in the source Area
			for (Enemy enemy1 : heroSource.getEnemies()){
				if (enemy1.searchAbility(enemyAbilityType)){
					// Check if there is Already an Enemy Modified by PURSUIT of THIS Enemy in the Area
					modificationFound=false;
					for (Enemy enemy2 : heroSource.getEnemies()){
						if (enemy2.getEnemyKeyword().contains(enemyKeyword) &&
							enemy2.searchAbility(EnemyAbilityType.RELENTLESS)==false &&
							enemy2.searchModification(ModType.RELENTLESS)==true){
							
							for (Modification mod : enemy2.getModifications()){
								if (mod.getSourceID()==enemy1.getEnemyID()){
									modificationFound=true;
									break;
								}
							}
							if (modificationFound==true){
								break;
							}
						}
					}
					// If no Modification was found try to apply the RELENTLESS Modification to 1 BEAST Enemy
					if (modificationFound==false){
						for (Enemy enemy2 : heroSource.getEnemies()){
							if (enemy2.getEnemyKeyword().contains(enemyKeyword) &&
								enemy2.searchAbility(EnemyAbilityType.RELENTLESS)==false &&
								enemy2.searchModification(ModType.RELENTLESS)==false){
								enemy2.updateModification(ModSource.ENEMY, ModType.RELENTLESS, ModTarget.GENERAL, 0, enemy1.getEnemyID());
								break;
							}
						}	
					}
				}
			}
		}
	}
	
	public static void updateEnemyKeyword(GameController gc,Enemy targetEnemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource, EnemyAbilityType enemyAbilityType, EnemyKeyword enemyKeyword){
		
		boolean updatePossible;
		
		// If a "enemyKeyword" Enemy without the RELENTLESS Ability moves in or out of a Hero Area with a "enemyAbilityType" Enemy update the RELENTLESS Modification
		if (targetEnemy.getEnemyKeyword().contains(enemyKeyword) && targetEnemy.searchAbility(EnemyAbilityType.RELENTLESS)==false){
			// If the Beast Enemy moved out of a Hero Area
			if (source==EnemyArea.HERO){
				// If the "enemyKeyword" Enemy does not has the RELENTLESS Ability, but has the RELENTLESS Modification
				if(targetEnemy.searchModification(ModType.RELENTLESS)){
					// Remove the RELENTLESS Modification
					targetEnemy.removeModification(ModType.RELENTLESS, ModSource.ENEMY, -1);
				}
				
				// Check if there is an Enemy with the "enemyAbilityType" Ability in the Source Hero Area
				for (Enemy enemy : heroSource.getEnemies()){
					updatePossible=true;
					if (enemy.searchAbility(enemyAbilityType)==true){
						// Check if there is already a "enemyKeyword" Enemy in the Area modified by THIS Enemy with "enemyAbilityType"
						for (Enemy enemy2 : heroSource.getEnemies()){
							if (enemy2.getEnemyKeyword().contains(enemyKeyword) &&
								enemy2.searchAbility(EnemyAbilityType.RELENTLESS)==false &&
								enemy2.searchModification(ModType.RELENTLESS)==true){
								for (Modification mod : enemy2.getModifications()){
									if (mod.getModType()==ModType.RELENTLESS && mod.getSourceID()==enemy.getEnemyID()){
										updatePossible=false;
										break;
									}	
								}
								if (updatePossible==false){
									break;
								}
							}
						}
						// If there is an Enemy with the "enemyAbilityType" Ability that did not already used it, try to give a "enemyKeyword" Enemy the RELENTLESS MOD
						if (updatePossible==true){
							for (Enemy enemy3 : heroSource.getEnemies()){
								if (enemy3.getEnemyKeyword().contains(enemyKeyword) &&
									enemy3.searchAbility(EnemyAbilityType.RELENTLESS)==false &&
									enemy3.searchModification(ModType.RELENTLESS)==false){
									enemy3.updateModification(ModSource.ENEMY, ModType.RELENTLESS, ModTarget.GENERAL, 0, enemy.getEnemyID());
									break;
								}
							}
							break;
						}
					}
				}
			}
			
			// If the "enemyKeyword" Enemy moved in a Hero Area with a "enemyAbilityType" Enemy
			if (dest==EnemyArea.HERO){
				// Search for all Enemies with the "enemyAbilityType" Ability
				System.out.println(heroDest.toString());
				for (Enemy enemy1 : heroDest.getEnemies()){
					updatePossible=true;
					
					if (enemy1.searchAbility(enemyAbilityType)==true){
						// Check if there is already a "enemyKeyword" Enemy in the Area modified by THIS Enemy with "enemyAbilityType"
						for (Enemy enemy2 : heroDest.getEnemies()){
							if (enemy2.getEnemyKeyword().contains(enemyKeyword) &&
								enemy2.searchAbility(EnemyAbilityType.RELENTLESS)==false &&
								enemy2.searchModification(ModType.RELENTLESS)==true){
								for (Modification mod : enemy2.getModifications()){
									if (mod.getModType()==ModType.RELENTLESS && mod.getSourceID()==enemy1.getEnemyID()){
										updatePossible=false;
										break;
									}	
								}
								if (updatePossible==false){
									break;
								}
							}
						}
						// If there is an Enemy with the "enemyAbilityType" Ability that did not already used it, give the "enemyKeyword" the Relentless Modification
						if (updatePossible==true){
							targetEnemy.updateModification(ModSource.ENEMY, ModType.RELENTLESS, ModTarget.GENERAL, 0, enemy1.getEnemyID());
							break;
						}
					}
				}
			}
		}
	}
}
