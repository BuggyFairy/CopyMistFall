package com.mygdx.game.mistfall.controller;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyOperation;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.enums.EnemyKeyword;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class EnemyController {
		


//	public static void updateHeroAreaEnemies(GameController gc, int heroID){
//		
//		// CHECK ENRAGE
//		
//		//
//		int enemyPos=0;
//		int abilityPos=0;
//		
//		boolean beastRide=false;
//		boolean pursuit=false;
//		boolean darkPresence=false;
//		boolean found=false;
//		int value;
//		Enemy currentEnemy;
//		
//		// Go through all enemies
//		while(enemyPos<=gc.getHeroes().get(heroID).getHeroEnemies().getCards().size())
//		{
//			// Set Current Enemy
//			currentEnemy=gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos);
//			// Go through all abilities of the current enemy
//			while(abilityPos<=gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getAbilities().size()){
//				switch (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getAbilities().get(abilityPos).getType()){
//				
//					// Blood Fury: "This Enemy deals +1 physical damage for each Wound on this card"
//					case BLOOD_FURY:
//						value=gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getLife().getWounds();
//						// Try Update, if not successful, generate new entry
//						if (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getAttack().updateModValue(ModSource.ENEMY, ModType.BLOOD_FURY, value,currentEnemy.getName())==false){
//							gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getAttack().addMod(ModSource.ENEMY,ModType.BLOOD_FURY,value,"",currentEnemy.getName());
//						}
//					break;
//					
//					// Swarm: "This Enemy adds +1 physical damage for each lifepoint"
//					case SWARM:
//						value=gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getLife().getValueCurrent();
//						// Try Update, if not successful, generate new entry
//						if (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getAttack().updateModValue(ModSource.ENEMY, ModType.SWARM, value,currentEnemy.getName())==false){
//							gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getAttack().addMod(ModSource.ENEMY,ModType.SWARM,value,"",currentEnemy.getName());
//						}
//					break;
//					
//					// Pack Hunter: "This Enemy deals +1 physical damage for each other HOUND in the same Hero Area"
//					case PACK_HUNTER:
//						int houndCount=0;
//						// Get Number of Enemies with the Keyword HOUND in the same Area
//						for (Enemy e : gc.getHeroes().get(heroID).getHeroEnemies().getCards()){
//							if (e.getEnemyKeyword().contains(EnemyKeyword.HOUND)){
//								houndCount++;
//							}
//						}
//						// Try Update, if not successful, generate new entry
//						if (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getAttack().updateModValue(ModSource.ENEMY, ModType.PACK_HUNTER, houndCount,currentEnemy.getName())==false){
//							gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getAttack().addMod(ModSource.ENEMY,ModType.PACK_HUNTER,houndCount,"",currentEnemy.getName());
//						}
//					break;
//					
//					case BEAST_RIDE:
//						beastRide=true;
//						// Check all Enemies in the Hero Area
//						for (int i=0;i<gc.getHeroes().get(heroID).getHeroEnemies().getCards().size();i++){
//							// if the current Enemy is a WILDLANDER and does not possess the ability RELENTLESS
//							if (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).searchAbility(EnemyAbilityType.RELENTLESS)==false &&
//								gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getEnemyKeyword().contains(EnemyKeyword.WILDLANDER)){
//								// Induce the RELENTLESS ABILITY
//								gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getAbilities().add(new EnemyAbility(EnemyAbilityType.RELENTLESS,EnemyAbilityArea.HERO,"","",true));
//							}
//						}
//					break;
//					
//					case PURSUIT:
//						pursuit=true;
//						
//						// Check all Enemies in the Hero Area
//						for (int i=0;i<gc.getHeroes().get(heroID).getHeroEnemies().getCards().size();i++){
//							// if the current Enemy is a BEAST and does not possess the ability RELENTLESS
//							if (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).searchAbility(EnemyAbilityType.RELENTLESS)==false &&
//								gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getEnemyKeyword().contains(EnemyKeyword.BEAST)){
//								// Induce the RELENTLESS ABILITY
//								gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getAbilities().add(new EnemyAbility(EnemyAbilityType.RELENTLESS,EnemyAbilityArea.HERO,"","",true));
//							}
//						}
//					break;
//					
//					case DARK_PRESENCE:
//						darkPresence=true;
//						found=false;
//						// Check if the DARK_PRESENCE Modification is already applied to the Hero
//						for (int i=0;i<gc.getHeroes().get(heroID).getModifications().size();i++){
//							if (gc.getHeroes().get(heroID).getModifications().get(i).getModType()==ModType.DARK_PRESENCE){
//								found=true;
//								break;
//							}
//						}
//						// If The Modification is not found, create it and update draw Count
//						if (found==false){
//							gc.getHeroes().get(heroID).getModifications().add(new Modification(ModSource.ENEMY,ModType.DARK_PRESENCE,1,"",currentEnemy.getName()));
//							gc.getHeroes().get(heroID).setDrawLimit(gc.getHeroes().get(heroID).getDrawLimit()-1);
//						}
//					break;
//					
//					default:
//					break;
//				}
//				abilityPos++;		
//			}
//			enemyPos++;
//		}
//		
//		// Remove induced RELENTLESS Ability if there is no Enemy with the BEAST_RIDE or PURSUIT Ability
//		for (int i=0;i<gc.getHeroes().get(heroID).getHeroEnemies().getCards().size();i++){
//			for (int j=0;j<gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getAbilities().size();j++){
//				// if the current enemy possess the RELENTLESS ability AND if the ability is induced
//				if (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getAbilities().get(j).getType()==EnemyAbilityType.RELENTLESS &&
//					gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getAbilities().get(j).getInduced()==true){
//					// If there is no Enemy with BEAST_RIDE and the current Enemy is a WILDLANDER
//					if (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getEnemyKeyword().contains(EnemyKeyword.WILDLANDER) &&
//						beastRide==false){
//						// Remove Relentless Ability
//						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getAbilities().remove(EnemyAbilityType.RELENTLESS);
//					}
//					// If there is no Enemy with PURSUIT and the current Enemy is a BEAST
//					if (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getEnemyKeyword().contains(EnemyKeyword.BEAST) &&
//						pursuit==false){
//						// Remove Relentless Ability
//						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getAbilities().remove(EnemyAbilityType.RELENTLESS);
//					}
//					
//				}
//			}
//		}
//		
//		// Remove DARK_PRESENCE from Hero if there is no Enemy with the DARK_PRESENCE Ability
//		//TODO
//		
//	}
	
	
	
	
	public void updateEnemiesDraw(GameController gc,Enemy enemy){
		switchArea(gc,enemy,EnemyArea.DECK,EnemyArea.QUEST,null,null,EnemyOperation.DRAW);
	}
	public void updateEnemiesPursuit(GameController gc,Enemy enemy,Hero heroDest){
		switchArea(gc,enemy,EnemyArea.QUEST,EnemyArea.HERO,heroDest,null,EnemyOperation.PURSUIT);
	}
	public void updateEnemiesDisperse(GameController gc,Enemy enemy,Hero heroSource){
		switchArea(gc,enemy,EnemyArea.HERO,EnemyArea.DISCARD,null,heroSource,EnemyOperation.DISPERSE);
	}
	public void updateEnemiesDefeated(GameController gc,Enemy enemy,Hero heroSource){
		switchArea(gc,enemy,EnemyArea.HERO,EnemyArea.DISCARD,null,heroSource,EnemyOperation.DEFEATED);
	}
	public void updateEnemiesHeroHero(GameController gc,Enemy enemy,Hero heroSource, Hero heroDest){
		switchArea(gc,enemy,EnemyArea.HERO,EnemyArea.HERO,heroSource,heroDest,EnemyOperation.MOVE);
	}
	public void updateEnemiesHeroQuest(GameController gc,Enemy enemy,Hero heroSource){
		switchArea(gc,enemy,EnemyArea.HERO,EnemyArea.QUEST,null,heroSource,EnemyOperation.MOVE);
	}
	public void updateEnemiesQuestHero(GameController gc,Enemy enemy,Hero heroDest){
		switchArea(gc,enemy,EnemyArea.QUEST,EnemyArea.HERO,heroDest,null,EnemyOperation.MOVE);
	}
	
	
	public void updateEnemiesRecievingWounds(GameController gc,Enemy enemy,Hero hero){
	
		int heroID=hero.getHeroID();
		int enemyPos=gc.getHeroes().get(heroID).getHeroEnemies().getEnemyPos(enemy);
		int value;
		for (int i=0;i<enemy.getAbilities().size();i++){
			switch (enemy.getAbilities().get(i).getType()){
				// Blood Fury: "<Hero Area> This Enemy deals +1 Physical Damage for each Wound on this card."
				case BLOOD_FURY:
					value=gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getLife().getWounds();
					gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.BLOOD_FURY, ModTarget.ATTACK, value, enemy.getEnemyID(), enemy.getName());
				break;
				// Swarm: "<Hero Area> This Enemy adds +1 Physical Damage for each 1 Lifepoint"
				case SWARM:
					value=gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getLife().getValueCurrent();
					gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.SWARM, ModTarget.ATTACK, value, enemy.getEnemyID(), enemy.getName());
				break;
				default:
				break;
			}
		}
		
	}
	
	
	private void switchArea(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource, EnemyOperation enemyOperation){
		int enemyPos;
		int enemyID;
		String enemyName;
		
		int heroIdDest;
		int heroIDsource;
		int houndCount;
		
		for (int abilityCount=0;abilityCount<enemy.getAbilities().size();abilityCount++){
			switch (enemy.getAbilities().get(abilityCount).getType()){
				// Pack Hunter: "<Hero Area> This Enemy deals +1 physical damage for each other HOUND in the same Hero Area"
				case PACK_HUNTER:
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
				break;
			
				case REANIMATE:
				break;
				
				case SCAVENGER:
				break;
				
				case VENGEFUL_SHRIEK:
				break;
					
				case CURSE_OF_WEAKNESS:
				break;
					
				case AMBUSH:
				break;
					
				case FLAILING:
				break;
				// Slow: "<Hero Area> This Enemy does not attack on the same round it enters a Hero Area"
				case SLOW:
					// If a Enemy with the SLOW Ability enters a Hero Area add the SLOW Modification
					if (dest==EnemyArea.HERO){
						heroIdDest=heroDest.getHeroID();
						enemyPos=gc.getHeroes().get(heroIdDest).getHeroEnemies().getEnemyPos(enemy);
						gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.SLOW, ModTarget.GENERAL, 0, enemy.getEnemyID(), enemy.getName());
					}
					// If a Enemy with the SLOW Ability moves from a Hero Area in the Quest Area, remove the SLOW Modification if possible
					if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
						enemyPos=gc.getQuestArea().getEnemyPos(enemy);
						gc.getQuestArea().getQuestAreaEnemies().get(enemyPos).removeModification(ModType.SLOW, ModSource.ENEMY, enemy.getEnemyID());
					}
				break;
				// Relentless: "<Hero Area> Do not Discard this Enemy when Dispersing Enemies"
				case RELENTLESS:
					// If a Enemy with the RELENTLESS Ability enters a Hero Area add the RELENTLESS Modification
					if (dest==EnemyArea.HERO){
						heroIdDest=heroDest.getHeroID();
						enemyPos=gc.getHeroes().get(heroIdDest).getHeroEnemies().getEnemyPos(enemy);
						gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.RELENTLESS, ModTarget.GENERAL, 0, enemy.getEnemyID(), enemy.getName());
					}
					// If a Enemy with the RELENTLESS Ability moves from a Hero Area in the Quest Area, remove the RELENTLESS Modification if possible
					if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
						enemyPos=gc.getQuestArea().getEnemyPos(enemy);
						gc.getQuestArea().getQuestAreaEnemies().get(enemyPos).removeModification(ModType.RELENTLESS, ModSource.ENEMY, enemy.getEnemyID());
					}
				break;
				//TODO: A Enemy can have multiple instances of the RELENTLESS Modification from different Enemies with the PURSUIT or BEAST_RIDE Ability
				// Pursuit: "<Hero Area> Any 1 other BEAST (that does not have the RELENTLESS Ability) in the same Hero Area receives the RELENTLESS Ability
				case PURSUIT:
					// If a enemy with the PURSUIT Ability enters a Hero Area give all BEAST's in the same Area the RELENTLESS Ability
					if (dest==EnemyArea.HERO){
						heroIdDest=heroDest.getHeroID();
						// Go through all Enemies in the same Area
						for (int i=0;i<gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().size();i++){
							// If they are a BEAST and do not have the RELENTLESS Ability, give them the RELENTLESS Modification
							if (gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).getEnemyKeyword().contains(EnemyKeyword.BEAST) &&
								gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).searchAbility(EnemyAbilityType.RELENTLESS)==false){
								
								gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).updateModification(ModSource.ENEMY, ModType.RELENTLESS, ModTarget.GENERAL, 0, enemy.getEnemyID(), enemy.getName());
							}
						}	
					}
					// If a enemy with the PURSUIT Ability leaves a Hero Area, remove the RELENTLESS Modification from Enemies in the source Area
					if (source==EnemyArea.HERO){
						heroIDsource=heroSource.getHeroID();
						// Go through all Enemies in the same Area
						for (int i=0;i<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();i++){
							if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).getEnemyKeyword().contains(EnemyKeyword.BEAST) &&
								gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).searchAbility(EnemyAbilityType.RELENTLESS)==false){
									
								gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).removeModification(ModType.RELENTLESS, ModSource.ENEMY, enemy.getEnemyID());
							}
						}
					}
				break;
				
				case BEAST_RIDE:
				break;
				
				case DARK_PRESENCE:
				break;
				
				// Skirmisher: "<Quest Area> <Hero Area> Requires an extra 1 Range to target. Ignore this Ability if all Enemies in the same Area are Ranged."
				case SKIRMISHER:
					
					// If a Skirmisher Enemy enters a Hero Area check for other range enemies
					if (dest==EnemyArea.HERO){
						heroIdDest=heroDest.getHeroID();
						enemyPos=gc.getHeroes().get(heroIdDest).getHeroEnemies().getEnemyPos(enemy);
						boolean onlyRanged=true;
						// Check the Hero Area for Enemies with the RANGED Keyword
						for (Enemy e : gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards()){
							if (e.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
								onlyRanged=false;
								break;
							}
						}
						// If there are only RANGED Enemies, remove the SKIRMISHER Modification if possible
						if (onlyRanged==true){
							gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemy.getEnemyID());
						}
						// If there is at least 1 Enemy without the RANGED Keyword, add the SKIRMISHER Modification if possible
						else{
							gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.SKIRMISHER, ModTarget.RANGE, 1, enemy.getEnemyID(), enemy.getName());
						}
					}
					// If a Skirmisher Enemy enters a Quest Area check for other range enemies
					if (dest==EnemyArea.QUEST){
						enemyPos=gc.getQuestArea().getEnemyPos(enemy);
						boolean onlyRanged=true;
						// Check the Quest Area for Enemies with the RANGED Keyword
						for (Enemy e : gc.getQuestArea().getQuestAreaEnemies()){
							if (e.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
								onlyRanged=false;
								break;
							}
						}
						// If there are only RANGED Enemies, remove the SKIRMISHER Modification if possible
						if (onlyRanged==true){
							gc.getQuestArea().getQuestAreaEnemies().get(enemyPos).removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemy.getEnemyID());
						}
						// If there is at least 1 Enemy without the RANGED Keyword, add the SKIRMISHER Modification if possible
						else{
							gc.getQuestArea().getQuestAreaEnemies().get(enemyPos).updateModification(ModSource.ENEMY, ModType.SKIRMISHER, ModTarget.RANGE, 1, enemy.getEnemyID(), enemy.getName());
						}
					}
					
				break;
				
				default:
				break;
			}
		}
		
		
		
		// If a Enemy without the RANGED Ability moves in the Hero Area and there is a Skirmisher
		if(enemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false && dest==EnemyArea.HERO){
			heroIdDest=heroDest.getHeroID();
			// Search for Enemies with the Skirmisher Ability and remove the Skirmisher Modification if possible
			for (int i=0;i<gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().size();i++){
				if (gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).searchAbility(EnemyAbilityType.SKIRMISHER)){
					enemyID=gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).getEnemyID();
					gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemyID);
				}
			}
		}
		// If a Enemy without the RANGED Ability moves in the Quest Area and there is a Skirmisher
		if(enemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false && dest==EnemyArea.HERO){
			// Search for Enemies with the Skirmisher Ability and remove the Skirmisher Modification if possible
			for (int i=0;i<gc.getQuestArea().getQuestAreaEnemies().size();i++){
				if (gc.getQuestArea().getQuestAreaEnemies().get(i).searchAbility(EnemyAbilityType.SKIRMISHER)){
					enemyID=gc.getQuestArea().getQuestAreaEnemies().get(i).getEnemyID();
					gc.getQuestArea().getQuestAreaEnemies().get(i).removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemyID);
				}
			}
		}
		// If a Enemy without the RANGED Ability moves out of a Hero Area and there is a Skirmisher
		if(enemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false && source==EnemyArea.HERO){
			heroIDsource=heroSource.getHeroID();
			boolean onlyRanged=true;
			// Check if there is still a Enemy without the RANGED Ability left
			for (int i=0;i<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();i++){
				if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
					onlyRanged=false;
					break;
				}
			}	
			// If there are only RANGED Enemies left Search for Enemies with the Skirmisher Ability and remove the Skirmisher Modification if possible
			if (onlyRanged==true){
				for (int i=0;i<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();i++){
					if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).searchAbility(EnemyAbilityType.SKIRMISHER)){
						enemyID=gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).getEnemyID();
						gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemyID);
					}
				} 
			}
			
		}
		// If a Enemy without the RANGED Ability moves out of the Quest Area and there is a Skirmisher
		if(enemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false && source==EnemyArea.QUEST){
			boolean onlyRanged=true;
			// Check if there is still a Enemy without the RANGED Ability left
			for (int i=0;i<gc.getQuestArea().getQuestAreaEnemies().size();i++){
				if (gc.getQuestArea().getQuestAreaEnemies().get(i).getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
					onlyRanged=false;
					break;
				}
			}	
			// If there are only RANGED Enemies left Search for Enemies with the Skirmisher Ability and remove the Skirmisher Modification if possible
			if (onlyRanged==true){
				for (int i=0;i<gc.getQuestArea().getQuestAreaEnemies().size();i++){
					if (gc.getQuestArea().getQuestAreaEnemies().get(i).searchAbility(EnemyAbilityType.SKIRMISHER)){
						enemyID=gc.getQuestArea().getQuestAreaEnemies().get(i).getEnemyID();
						gc.getQuestArea().getQuestAreaEnemies().get(i).removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemyID);
					}
				} 
			}
			
		}
		
		
		
		
		
		// If a BEAST Enemy without the RELENTLESS Ability moves in or out of a Hero Area with a PURSUIT Enemy update the RELENTLESS Modification
		if (enemy.getEnemyKeyword().contains(EnemyKeyword.BEAST) && enemy.searchAbility(EnemyAbilityType.RELENTLESS)==false){
			// If the BEAST Enemy moved in a Hero Area with a PURSUIT Enemy
			if (dest==EnemyArea.HERO){
				heroIdDest=heroDest.getHeroID();
				enemyPos=gc.getHeroes().get(heroIdDest).getHeroEnemies().getEnemyPos(enemy);
				// Search for Enemy with PURSUIT Ability
				for (int i=0;i<gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().size();i++){
					if (gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).searchAbility(EnemyAbilityType.PURSUIT)){
						// If a Enemy with PURSUIT was found in the same Area, give the BEAST Enemy the RELENTLESS Modification
						gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.RELENTLESS, ModTarget.GENERAL, 0, enemy.getEnemyID(), enemy.getName());
						break;
					}
				}
			}
			// If the Beast Enemy moved out of a Hero Area
			if (source==EnemyArea.HERO){
				// If the Beast Enemy does not has the RELENTLESS Ability, but has the RELENTLESS Modification
				heroIDsource=heroSource.getHeroID();
				if(enemy.searchAbility(EnemyAbilityType.RELENTLESS)==false && enemy.searchModification(ModType.RELENTLESS)){
					// Remove the RELENTLESS Modification
					enemyPos=gc.getHeroes().get(heroIDsource).getHeroEnemies().getEnemyPos(enemy);
					int i=0;
					while(i<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(enemyPos).getModifications().size()){
						if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(enemyPos).getModifications().get(i).getModType()==ModType.RELENTLESS){
							gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(enemyPos).getModifications().remove(i);
						}
						else{
							i++;
						}
					}
				}
			}
		}
		
		
		
		
		
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
		
		
		
		
		
		// Remove all Modifications if a Enemy is discarded or defeated
		if (dest==EnemyArea.DISCARD){
			enemyPos=gc.getGameSetupController().getEnemyPositionDiscard(enemy);
			if (enemyPos!=-1){
				switch (enemy.getEnemyType()){
					case BLUE:
						// Clear Modification List
						gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getModifications().clear();
						// Reset Life, Attack and Resistance Values to the Base Values
						gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getAttack().setValueMod(gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getAttack().getValueBase());
						gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getLife().setValueMod(gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getLife().getValueBase());
						gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getLife().setValueCurrent(gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getLife().getValueBase());
						gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getResistances().setMagicalResMod(gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getResistances().getMagicalResBase());
						gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getResistances().setPhysicalResMod(gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getResistances().getPhysicalResBase());
					break;
					case RED:
						// Clear Modification List
						gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getModifications().clear();
						// Reset Life, Attack and Resistance Values to the Base Values
						gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getAttack().setValueMod(gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getAttack().getValueBase());
						gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getLife().setValueMod(gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getLife().getValueBase());
						gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getLife().setValueCurrent(gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getLife().getValueBase());
						gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getResistances().setMagicalResMod(gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getResistances().getMagicalResBase());
						gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getResistances().setPhysicalResMod(gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getResistances().getPhysicalResBase());
					break;
					case GREEN:
						// Clear Modification List
						gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getModifications().clear();
						// Reset Life, Attack and Resistance Values to the Base Values
						gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getAttack().setValueMod(gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getAttack().getValueBase());
						gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getLife().setValueMod(gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getLife().getValueBase());
						gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getLife().setValueCurrent(gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getLife().getValueBase());
						gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getResistances().setMagicalResMod(gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getResistances().getMagicalResBase());
						gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getResistances().setPhysicalResMod(gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getResistances().getPhysicalResBase());
					break;
					default:
					break;
				}
			}		
		}
		
	}

}
