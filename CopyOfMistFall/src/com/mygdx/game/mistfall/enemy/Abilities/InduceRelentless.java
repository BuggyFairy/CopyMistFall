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

public class InduceRelentless {
	
	
	public static void update(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource, EnemyAbilityType enemyAbilityType, EnemyKeyword enemyKeyword){
		
		int enemyID;
		String enemyName;
		int heroIdDest;
		int heroIDsource;
		boolean modificationFound;
		
		// If a enemy with the PURSUIT Ability enters a Hero Area give the first BEAST without the RELENTLESS Modification/Ability in the same Area the RELENTLESS Modification
		if (dest==EnemyArea.HERO){
			heroIdDest=heroDest.getHeroID();
			// Go through all Enemies in the same Area
			for (int i=0;i<gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().size();i++){
				// If there is a BEAST that does not possess the RELENTLESS Ability nor Modification, give it the RELENTLESS Modification and end the loop
				if (gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).getEnemyKeyword().contains(enemyKeyword) &&
					gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).searchAbility(EnemyAbilityType.RELENTLESS)==false &&
					gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).searchModification(ModType.RELENTLESS)==false){
					
					gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).updateModification(ModSource.ENEMY, ModType.RELENTLESS, ModTarget.GENERAL, 0, enemy.getEnemyID(), enemy.getName());
					break;
				}
			}	
		}
		// If a enemy with the PURSUIT Ability leaves a Hero Area, remove the RELENTLESS Modification from Enemies in the source Area if possible
		if (source==EnemyArea.HERO){
			heroIDsource=heroSource.getHeroID();
			// Go through all Enemies in the same Area and remove the specified RELENTLESS Modification if able
			for (int i=0;i<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();i++){
				if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).getEnemyKeyword().contains(enemyKeyword) &&
					gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).searchAbility(EnemyAbilityType.RELENTLESS)==false &&
					gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).searchModification(ModType.RELENTLESS)==true){
						
					gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).removeModification(ModType.RELENTLESS, ModSource.ENEMY, enemy.getEnemyID());
				}
			}
			// Check if there is another ENEMY with the PURSUIT Ability in the source Area
			for (int i=0;i<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();i++){
				if (enemy.searchAbility(enemyAbilityType)){
					// Check if there is Already an Enemy Modified by PURSUIT of THIS Enemy in the Area
					modificationFound=false;
					for (int j=0;j<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();j++){
						if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).getEnemyKeyword().contains(enemyKeyword) &&
								gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).searchAbility(EnemyAbilityType.RELENTLESS)==false &&
								gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).searchModification(ModType.RELENTLESS)==true){
							enemyID=gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).getEnemyID();
							
							for (int k=0;k<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).getModifications().size();k++){
								if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).getModifications().get(k).getSourceID()==enemyID){
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
						for (int k=0;k<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();k++){
							if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(k).getEnemyKeyword().contains(enemyKeyword) &&
								gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(k).searchAbility(EnemyAbilityType.RELENTLESS)==false &&
								gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(k).searchModification(ModType.RELENTLESS)==false){
								enemyID=gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(k).getEnemyID();
								enemyName=gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(k).getName();
								gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(k).updateModification(ModSource.ENEMY, ModType.RELENTLESS, ModTarget.GENERAL, 0, enemyID, enemyName);
								break;
							}
						}	
					}
				}
			}
		}
	}
	
	public static void updateEnemyKeyword(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource, EnemyAbilityType enemyAbilityType, EnemyKeyword enemyKeyword){
		
		int enemyPos;
		int enemyID;
		String enemyName;
		int heroIdDest;
		int heroIDsource;
		boolean updatePossible;
		
		// If a "enemyKeyword" Enemy without the RELENTLESS Ability moves in or out of a Hero Area with a "enemyAbilityType" Enemy update the RELENTLESS Modification
		if (enemy.getEnemyKeyword().contains(enemyKeyword) && enemy.searchAbility(EnemyAbilityType.RELENTLESS)==false){
			// If the Beast Enemy moved out of a Hero Area
			if (source==EnemyArea.HERO){
				// If the "enemyKeyword" Enemy does not has the RELENTLESS Ability, but has the RELENTLESS Modification
				heroIDsource=heroSource.getHeroID();
				heroIdDest=heroDest.getHeroID();
				enemyPos=gc.getHeroes().get(heroIdDest).getHeroEnemies().getEnemyPos(enemy);
				if(enemy.searchModification(ModType.RELENTLESS)){
					// Remove the RELENTLESS Modification
					gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).removeModification(ModType.RELENTLESS, ModSource.ENEMY, -1);
				}
				
				// Check if there is an Enemy with the "enemyAbilityType" Ability in the Source Hero Area
				updatePossible=true;
				for (int i=0;i<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();i++){
					if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).searchAbility(enemyAbilityType)){
						// Check if there is already a "enemyKeyword" Enemy in the Area modified by THIS Enemy with "enemyAbilityType"
						enemyID=gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).getEnemyID();
						for (int j=0;j<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();j++){
							if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).getEnemyKeyword().contains(enemyKeyword) &&
								gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).searchAbility(EnemyAbilityType.RELENTLESS)==false &&
								gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).searchModification(ModType.RELENTLESS)==true){
								for (int k=0;k<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).getModifications().size();k++){
									if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).getModifications().get(k).getSourceID()==enemyID){
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
							for (int j=0;j<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();j++){
								if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).getEnemyKeyword().contains(enemyKeyword) &&
									gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).searchAbility(EnemyAbilityType.RELENTLESS)==false &&
									gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).searchModification(ModType.RELENTLESS)==false){
									enemyID=gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).getEnemyID();
									enemyName=gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).getName();
									gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(j).updateModification(ModSource.ENEMY, ModType.RELENTLESS, ModTarget.GENERAL, 0, enemyID, enemyName);
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
				heroIdDest=heroDest.getHeroID();
				enemyPos=gc.getHeroes().get(heroIdDest).getHeroEnemies().getEnemyPos(enemy);
				// Search for all Enemies with the "enemyAbilityType" Ability
				updatePossible=true;
				for (int i=0;i<gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().size();i++){
					if (gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).searchAbility(enemyAbilityType)){
						// Check if there is already a "enemyKeyword" Enemy in the Area modified by THIS Enemy with "enemyAbilityType"
						enemyID=gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).getEnemyID();
						for (int j=0;j<gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().size();j++){
							if (gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(j).getEnemyKeyword().contains(enemyKeyword) &&
								gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(j).searchAbility(EnemyAbilityType.RELENTLESS)==false &&
								gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(j).searchModification(ModType.RELENTLESS)==true){
								for (int k=0;k<gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(j).getModifications().size();k++){
									if (gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(j).getModifications().get(k).getSourceID()==enemyID){
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
							enemyID=gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).getEnemyID();
							enemyName=gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).getName();
							gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.RELENTLESS, ModTarget.GENERAL, 0, enemyID, enemyName);
							break;
						}
					}
				}
			}
		}
	}
}
