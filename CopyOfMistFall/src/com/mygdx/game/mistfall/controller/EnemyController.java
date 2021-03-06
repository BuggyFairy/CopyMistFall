package com.mygdx.game.mistfall.controller;

import java.util.LinkedList;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.Abilities.BloodFury;
import com.mygdx.game.mistfall.enemy.Abilities.CursedBolt;
import com.mygdx.game.mistfall.enemy.Abilities.Firebolt;
import com.mygdx.game.mistfall.enemy.Abilities.Flailing;
import com.mygdx.game.mistfall.enemy.Abilities.InduceRelentless;
import com.mygdx.game.mistfall.enemy.Abilities.ManaDrain;
import com.mygdx.game.mistfall.enemy.Abilities.PackHunter;
import com.mygdx.game.mistfall.enemy.Abilities.Reanimate;
import com.mygdx.game.mistfall.enemy.Abilities.Relentless;
import com.mygdx.game.mistfall.enemy.Abilities.Scavenger;
import com.mygdx.game.mistfall.enemy.Abilities.ShieldSlam;
import com.mygdx.game.mistfall.enemy.Abilities.Skirmisher;
import com.mygdx.game.mistfall.enemy.Abilities.Skulduggery;
import com.mygdx.game.mistfall.enemy.Abilities.Slow;
import com.mygdx.game.mistfall.enemy.Abilities.Swarm;
import com.mygdx.game.mistfall.enemy.Abilities.Thievery;
import com.mygdx.game.mistfall.enemy.Abilities.Vampiric;
import com.mygdx.game.mistfall.enemy.Abilities.VengefulShriek;
import com.mygdx.game.mistfall.enemy.Abilities.Venomous;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemyOperation;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class EnemyController {	
	
	// {{ Attributes, Getters & Setters
	
	private LinkedList<String> enemyNames=new LinkedList<String>();
	private int shuffleCount;
	private boolean timePenalty;
	private int enemiesDrawn;
	
	public void addEnemyName(String name){
		enemyNames.add(name);
	}
	public String getEnemyNameByID(int enemyID){
		return enemyNames.get(enemyID);
	}

	// }}
	
	// {{ Enemy Movement
	
	/**
	 * Draws an enemy and moves it to the specified Area
	 */
	public void drawEnemy(GameController gc, EnemyArea areaDest, Hero heroDest, EnemySuit enemySuit, EnemyKeyword keyword, int enemyCount, boolean useTimePenalty){
		shuffleCount=0;
		enemiesDrawn=0;
		timePenalty=false;
		
		while(enemiesDrawn<enemyCount && timePenalty == false){
			// Draw Enemies till all Enemies have been drawn or the time penalty kicks in (Because Enemies can't be drawn)
			switch(enemySuit){	
				// Draw from the blue enemy deck
				case BLUE :
					// shuffle discard pile and form new deck if the deck is empty
					if(gc.getGameSetupController().getBlueEnemies().isEmpty()){
						gc.getGameSetupController().setBlueEnemies(gc.getGameSetupController().shuffleCardsEnemy(gc.getGameSetupController().getBlueEnemiesDiscard()));
						shuffleCount++;		
						// Clear Discard
						gc.getGameSetupController().getBlueEnemiesDiscard().clear();
					}
					// Draw enemy with any Keyword
					if(keyword == EnemyKeyword.ANY){
						moveEnemy(gc, gc.getGameSetupController().getBlueEnemies().get(0), EnemyArea.DECK, areaDest, enemySuit, null, heroDest, EnemyOperation.DRAW);
						enemiesDrawn++;
					}
					// Draw enemy with specified Keyword
					else{
						if(gc.getGameSetupController().getBlueEnemies().get(0).getEnemyKeyword().contains(keyword)){
							moveEnemy(gc, gc.getGameSetupController().getBlueEnemies().get(0), EnemyArea.DECK, areaDest, enemySuit, null, heroDest, EnemyOperation.DRAW);
							enemiesDrawn++;
						}
						else{
							moveEnemy(gc, gc.getGameSetupController().getBlueEnemies().get(0), EnemyArea.DECK, EnemyArea.DISCARD, enemySuit, null, null, EnemyOperation.DRAW);
						}
					}
					// If the Deck and the Discard pile from the selected Enemy type is empty set time Penalty and stop drawing
					if(gc.getGameSetupController().getBlueEnemies().isEmpty() && gc.getGameSetupController().getBlueEnemiesDiscard().isEmpty()){
						timePenalty=true;
					}
					break;
					
				// Draw from the red enemy deck	
				case RED :
					// shuffle discard pile and form new deck if the deck is empty
					if(gc.getGameSetupController().getRedEnemies().isEmpty()){
						gc.getGameSetupController().setRedEnemies(gc.getGameSetupController().shuffleCardsEnemy(gc.getGameSetupController().getRedEnemiesDiscard()));
						shuffleCount++;
						// Clear Discard
						gc.getGameSetupController().getRedEnemiesDiscard().clear();
					}
					// Draw enemy with any Keyword
					if(keyword == EnemyKeyword.ANY){
						moveEnemy(gc, gc.getGameSetupController().getRedEnemies().get(0), EnemyArea.DECK, areaDest, enemySuit, null, heroDest, EnemyOperation.DRAW);
						enemiesDrawn++;
					}
					// Draw enemy with specified Keyword
					else{
						if(gc.getGameSetupController().getRedEnemies().get(0).getEnemyKeyword().contains(keyword)){
							moveEnemy(gc, gc.getGameSetupController().getRedEnemies().get(0), EnemyArea.DECK, areaDest, enemySuit, null, heroDest, EnemyOperation.DRAW);
							enemiesDrawn++;
						}
						else{
							moveEnemy(gc, gc.getGameSetupController().getRedEnemies().get(0), EnemyArea.DECK, EnemyArea.DISCARD, enemySuit, null, null, EnemyOperation.DRAW);
						}
					}
					// If the Deck and the Discard pile from the selected Enemy type is empty set time Penalty and stop drawing
					if(gc.getGameSetupController().getRedEnemies().isEmpty() && gc.getGameSetupController().getRedEnemiesDiscard().isEmpty()){
						timePenalty=true;
					}
					break;
					
				// Draw from the green enemy deck
				case GREEN : 
					// shuffle discard pile and form new deck if the deck is empty
					if(gc.getGameSetupController().getGreenEnemies().isEmpty()){
						gc.getGameSetupController().setGreenEnemies(gc.getGameSetupController().shuffleCardsEnemy(gc.getGameSetupController().getGreenEnemiesDiscard()));
						shuffleCount++;
						// Clear Discard
						gc.getGameSetupController().getGreenEnemiesDiscard().clear();
					}
					// Draw enemy with any Keyword
					if(keyword == EnemyKeyword.ANY){
						moveEnemy(gc, gc.getGameSetupController().getGreenEnemies().get(0), EnemyArea.DECK, areaDest, enemySuit, null, heroDest, EnemyOperation.DRAW);
						enemiesDrawn++;
					}
					// Draw enemy with specified Keyword
					else{
						if(gc.getGameSetupController().getGreenEnemies().get(0).getEnemyKeyword().contains(keyword)){
							moveEnemy(gc, gc.getGameSetupController().getGreenEnemies().get(0), EnemyArea.DECK, areaDest, enemySuit, null, heroDest, EnemyOperation.DRAW);
							enemiesDrawn++;
						}
						else{
							moveEnemy(gc, gc.getGameSetupController().getGreenEnemies().get(0), EnemyArea.DECK, EnemyArea.DISCARD, enemySuit, null, null, EnemyOperation.DRAW);
						}
					}
					// If the Deck and the Discard pile from the selected Enemy type is empty set time Penalty and stop drawing
					if(gc.getGameSetupController().getGreenEnemies().isEmpty() && gc.getGameSetupController().getGreenEnemiesDiscard().isEmpty()){
						timePenalty=true;
					}
					break;
					
					default :
					//TODO: Exception handling
					break;
				}	
			// When the enemy Deck was shuffled 2 times or more set time penalty and stop drawing
			if(shuffleCount>=2){
				timePenalty=true;
			}
		}
		
		// Apply time penalty and shuffle all enemy Decks if time penalty is used
		if (timePenalty==true && useTimePenalty==true){
			//Shuffle Blue enemy cards
			gc.getGameSetupController().setBlueEnemies(gc.getGameSetupController().shuffleCardsEnemy(gc.getGameSetupController().getBlueEnemiesDiscard()));
			//Shuffle Red enemy cards
			gc.getGameSetupController().setRedEnemies(gc.getGameSetupController().shuffleCardsEnemy(gc.getGameSetupController().getRedEnemiesDiscard()));
			//Shuffle Green enemy cards
			gc.getGameSetupController().setGreenEnemies(gc.getGameSetupController().shuffleCardsEnemy(gc.getGameSetupController().getGreenEnemiesDiscard()));
			//Move Timetrack to steps to the right
			gc.moveTimeTrack(2);
		}
	}
	
	/**
	 * Draw specified number of enemies from the type of the active Encounter if possible and apply time penalty if necessary.
	 * Updates Enemies in the Quest Area
	 */
	public void spawnEnemies(GameController gc, int spawnEnemiesCount){
		drawEnemy(gc, EnemyArea.QUEST, null, gc.getActiveEncounter().getEnemyType(), gc.getActiveEncounter().getEnemyKeyword(), spawnEnemiesCount, true);
	}
	
	/**
	 * Disperse all Enemies without the "Relentless" Ability from the Quest area and all Hero areas.
	 * Updates Enemies.
	 */
	public void disperseEnemies(GameController gc){
		int i=0;
		// Disperse Quest Area and update Enemies
		while(i<gc.getQuestArea().getQuestAreaEnemies().size()){
			moveEnemy(gc, gc.getQuestArea().getQuestAreaEnemies().get(0), EnemyArea.QUEST, EnemyArea.DISCARD, gc.getQuestArea().getQuestAreaEnemies().get(0).getEnemySuit(), null, null, EnemyOperation.DISPERSE);
		}
		// Disperse Hero Area's
		for (Hero hero : gc.getHeroes()){
			i=0;
			while(i<hero.getEnemies().size()){
				// If the Current Enemy does no possess the RELENTLESS Modification, discard him and update Enemies
				if (hero.getEnemies().get(i).searchModification(ModType.RELENTLESS)==false){
					moveEnemy(gc, hero.getEnemies().get(i), EnemyArea.HERO, EnemyArea.DISCARD, hero.getEnemies().get(i).getEnemySuit(), hero, null, EnemyOperation.DISPERSE);
				}
				// If RELENTLESS move on to the next enemy
				else{
					i++;
				}
			}
		}
	}
	
	public void discardEnemy(GameController gc, Enemy enemy, EnemyArea sourceArea, Hero sourceHero){
		moveEnemy(gc, enemy, sourceArea, EnemyArea.DISCARD, enemy.getEnemySuit(), sourceHero, null, EnemyOperation.DISCARD);
	}

	/**
	 * Moves the specified enemy and updates all enemies if necessary
	 */
	public void moveEnemy(GameController gc, Enemy enemy, EnemyArea source, EnemyArea dest, EnemySuit suit, Hero heroSource, Hero heroDest, EnemyOperation operation){
		
		// Move to Destination
		switch(dest){
			case QUEST:
				gc.getQuestArea().getQuestAreaEnemies().add(enemy);
			break;
			case HERO:
				heroDest.getEnemies().add(enemy);
			break;
			case DISCARD:
				switch (suit){
					case GREEN:
						gc.getGameSetupController().getGreenEnemiesDiscard().add(enemy);
					break;
					case BLUE:
						gc.getGameSetupController().getBlueEnemiesDiscard().add(enemy);
					break;
					case RED:
						gc.getGameSetupController().getRedEnemiesDiscard().add(enemy);
					break;
					default:
				}
			break;
			default:
		}
		
		// Remove Enemy from source
		switch(source){
			case QUEST:
				gc.getQuestArea().getQuestAreaEnemies().remove(enemy);
			break;
			case HERO:
				heroSource.getEnemies().remove(enemy);
			break;
			case DECK:
				switch (suit){
					case GREEN:
						gc.getGameSetupController().getGreenEnemies().remove(enemy);
					break;
					case BLUE:
						gc.getGameSetupController().getBlueEnemies().remove(enemy);
					break;
					case RED:
						gc.getGameSetupController().getRedEnemies().remove(enemy);
					break;
					default:
				}
			break;
			default:
		}
		
		// Update Enemies
		updateEnemiesSwitchArea(gc, enemy, source, dest, heroSource, heroDest, operation);
		
	}
	
	// }}
	
	// {{ Update
	
	private void updateEnemiesRecievingWounds(GameController gc,Enemy enemy,Hero hero){
		
		for (int i=0;i<enemy.getAbilities().size();i++){
			switch (enemy.getAbilities().get(i).getType()){
				// Blood Fury: "<Hero Area> This Enemy deals +1 Physical Damage for each Wound on this card."
				case BLOOD_FURY:
					BloodFury.updateTakenDamage(gc, enemy);
				break;
				// Swarm: "<Hero Area> This Enemy adds +1 Physical Damage for each 1 Lifepoint"
				case SWARM:
					Swarm.updateTakenDamge(gc, enemy);
				break;
				default:
				break;
			}
		}	
	}
	
	private void updateEnemiesEnemyEliminated(GameController gc,EnemyArea enemyArea, Enemy enemy, Hero hero){
		Reanimate.updateEnemyEliminated(gc,enemyArea, enemy, hero);
	}
	
	private void updateEnemiesSwitchArea(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroSource,Hero heroDest, EnemyOperation enemyOperation){
		
		for (int abilityCount=0;abilityCount<enemy.getAbilities().size();abilityCount++){
			switch (enemy.getAbilities().get(abilityCount).getType()){
			
				case AMBUSH:
				break;
				
				// Beast Ride:"<Hero Area> Any 1 WILDLANDER (that does not have the RELENTLESS ability) in the same <Hero Area> receives the RELENTLESS ability"
				case BEAST_RIDE:
					InduceRelentless.update(gc,enemy,source, dest,heroDest,heroSource, EnemyAbilityType.BEAST_RIDE, EnemyKeyword.WILDLANDER);
				break;
				
				// Blood Fury: "<Hero Area> This Enemy deals +1 Physical Damage for each Wound on this card."
				case BLOOD_FURY:
					BloodFury.updateMoved(gc, enemy, source, dest);
				break;
				
				case CURSE_OF_WEAKNESS:
				break;
					
				// Cursed Bolt: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, that Player Buries another Card"
				case CURSED_BOLT:
					CursedBolt.updateMoved(gc, enemy,source, dest);
				break;
				
				case DARK_PRESENCE:
				break;
					
				// Firebolt: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, place 1 Burning Token on that player's Hero Charter"
				case FIREBOLT:
					Firebolt.updateMoved(gc, enemy,source, dest);
				break;
				
				// Flailing: "<Hero Area> After this Enemy enters a <Hero Area> during any Pursuit Phase the player places 2 daze Tokens on their Hero Charter"
				case FLAILING:
					if (dest==EnemyArea.HERO && enemyOperation==EnemyOperation.PURSUIT){
						Flailing.activate(gc, enemy, heroDest);
					}
				break;
					
				// Mana Drain: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, that player discards 1 ARCANE or SPELL card, if able"
				case MANA_DRAIN:
					ManaDrain.updateMoved(gc, enemy,source, dest);
				break;
				
				// Pack Hunter: "<Hero Area> This Enemy deals +1 physical damage for each other HOUND in the same Hero Area"
				case PACK_HUNTER:
					PackHunter.update(gc, enemy, source, dest, heroDest);
				break;
				
				// Pursuit: "<Hero Area> Any 1 other BEAST (that does not have the RELENTLESS Ability) in the same Hero Area receives the RELENTLESS Ability"
				case PURSUIT:
					InduceRelentless.update(gc,enemy,source, dest,heroDest,heroSource, EnemyAbilityType.PURSUIT, EnemyKeyword.BEAST);
				break;
		
				// Reanimate: "<Hero Area> Whenever an UNDEAD Enemy is eliminated in the same Area, draw BLUE ENEMIES until a SKELETON is revealed. Place that SKELETON in this Area, discard the rest."
				case REANIMATE:
					Reanimate.updateMoved(gc, enemy, source, dest);
				break;
				
				// Relentless: "<Hero Area> Do not Discard this Enemy when Dispersing Enemies"
				case RELENTLESS:
					Relentless.update(gc, enemy, source, dest);
				break;
				
				// Scavenger: "<Hero Area> After this Enemy enters a <Hero Area> the Player Buries 2 cards from their discard pile if able."
				case SCAVENGER:
					if (dest==EnemyArea.HERO){
						Scavenger.activate(gc, enemy, heroDest);
					}
				break;
				
				// Shield Slam: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, place 2 Daze Tokens on that player's Hero Charter"
				case SHIELD_SLAM:
					ShieldSlam.updateMoved(gc, enemy,source, dest);
				break;
				
				// Skirmisher: "<Quest Area> <Hero Area> Requires an extra 1 Range to target. Ignore this Ability if all Enemies in the same Area are Ranged."
				case SKIRMISHER:
					Skirmisher.update(gc,enemy,source, dest,heroDest);	
				break;
				
				// Skulduggery: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, remove 1 Objectiv Token from the active Encounter
				// 				card and place it on this card. Return all Objective Tokens to the Encounter card when this Enemy is discarded"
				case SKULDUGGERY:
					Skulduggery.updateMoved(gc, enemy,source, dest);
				break;
				
				// Slow: "<Hero Area> This Enemy does not attack on the same round it enters a Hero Area"
				case SLOW:
					Slow.update(gc, enemy, source, dest);
				break;
				
				// Swarm: "<Hero Area> This Enemy adds +1 Physical Damage for each 1 Lifepoint"
				case SWARM:
					Swarm.updateMoved(gc, enemy, source, dest);
				break;
				
				// Thievery: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, attach 1 GEAR card from their <Hero Area> to this card.
				//			  Return all Attachments to owners' HAND when this Enemy is discarded"
				case THIEVERY:
					Thievery.updateMoved(gc, enemy,source, dest);
				break;			
				
				// Vampiric: "<Hero Area> Whenever a player Buries 1 card as a result of this Enemy's attack, remove 1 Wound Token from this Enemy"
				case VAMPIRIC:
					Vampiric.updateMoved(gc, enemy,source, dest);
				break;
				
				// Vengeful Shriek: "<Hero Area> After this Enemy enters a <Hero Area>, enrage 1 other UNDEAD Enemy in that <Hero Area> if able."
				case VENGEFUL_SHRIEK:
					if (dest==EnemyArea.HERO){
						VengefulShriek.activate(gc, enemy, heroDest);
					}
				break;
					
				// Venomous: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, place 1 Poison Token on that player's Hero Charter"
				case VENOMOUS:
					Venomous.updateMoved(gc, enemy,source, dest);
				break;
				
				default:
				break;
			}
		}
		
		
		// If a BEAST Enemy without the RELENTLESS Ability moves in or out of a Hero Area with a PURSUIT Enemy update the RELENTLESS Modification
		InduceRelentless.updateEnemyKeyword(gc,enemy,source,dest,heroDest,heroSource, EnemyAbilityType.PURSUIT, EnemyKeyword.BEAST);
		
		// If a WILDLANDER Enemy without the RELENTLESS Ability moves in or out of a Hero Area with a BEAST_RIDE Enemy update the RELENTLESS Modification
		InduceRelentless.updateEnemyKeyword(gc,enemy,source,dest,heroDest,heroSource, EnemyAbilityType.BEAST_RIDE, EnemyKeyword.WILDLANDER);
		
		// If a Non-RANGED Enemy leaves or enters a Hero Area or the Quest Area update the SKIRMISHER Enemies
		Skirmisher.updateRanged(gc,enemy,source, dest,heroDest,heroSource);
		
		// Update Pack Hunter if a HOUND moved or left a Hero Area with a Enemy with the PACK_HUNTER Ability
		PackHunter.updateHound(gc, enemy, source, dest, heroDest, heroSource);
		
		// Remove all Modifications if a Enemy is discarded or defeated
		if (dest==EnemyArea.DISCARD){
			int enemyPos=gc.getGameSetupController().getEnemyPositionDiscard(enemy);
			if (enemyPos!=-1){
				switch (enemy.getEnemySuit()){
					case BLUE:
						// Clear Modification List and  Reset Life, Attack and Resistance Values to the Base Values
						gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).clearModifications();		
					break;
					case RED:
						// Clear Modification List and  Reset Life, Attack and Resistance Values to the Base Values
						gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).clearModifications();	
					break;
					case GREEN:
						// Clear Modification List and  Reset Life, Attack and Resistance Values to the Base Values
						gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).clearModifications();	
					break;
					default:
					break;
				}
			}		
		}
	}
		
	// }}
	
	public void attackHero(){
		// TODO
	}
		
	/**
	 * Resolves all Conditions that deal damage from a specified Enemy.
	 * Returns true if the Enemy died in the process.
	 * The player will be asked to select a Condition to Discard from the Enemy if possible.
	 */
	public boolean resolveCondition(GameController gc, Enemy enemy, Hero hero, EnemyArea enemyArea){
		switch (enemyArea){
			case HERO:
				// Resolve Burning Conditions
				if (enemy.getConditions().getBurning()>0){
					if (enemyApplyWounds(gc, enemy, EnemyArea.HERO, hero, enemy.getConditions().getBurning())){
						return true;
					}
				}
				// Resolve Poison Conditions
				if (enemy.getConditions().getPoison()>0){
					if (enemyApplyWounds(gc, enemy, EnemyArea.HERO, hero, enemy.getConditions().getPoison())){
						return true;
					}
				}
			break;
			case QUEST:
				// Resolve Burning Conditions
				if (enemy.getConditions().getBurning()>0){
					if (enemyApplyWounds(gc, enemy, EnemyArea.QUEST, null, enemy.getConditions().getBurning())){
						return true;
					}
				}
				// Resolve Poison Conditions
				if (enemy.getConditions().getPoison()>0){
					if (enemyApplyWounds(gc, enemy, EnemyArea.QUEST, null, enemy.getConditions().getPoison())){
						return true;
					}
				}
			break;
			default:
			break;
		}
		
		// Player must chose a Condition to discard from the Enemy
		// TODO
		return false;
	}

	/**
	 * Adds the Enemy at the first position of the quest area to the hero area of the specified hero.
	 * Halves the focus of the Hero and updates enemies
	 */
	public void pursuitHero(GameController gc, Hero hero){
		// Add Enemy from the top of the quest area list to the hero's area
		hero.getEnemies().add(gc.getQuestArea().getQuestAreaEnemies().get(0));
		gc.getQuestArea().getQuestAreaEnemies().remove(0);
		// Halve the Hero Focus
		hero.setFocus((int)(hero.getFocus()/2));
		// Update Enemies
		updateEnemiesSwitchArea(gc,hero.getEnemies().get(hero.getEnemies().size()-1),EnemyArea.QUEST,EnemyArea.HERO,hero,null,EnemyOperation.PURSUIT);
	}
	
	/**
	 * Applies the specified number of wounds to the specified enemy in the specified enemy area. 
	 * Enemy will be discarded and his resolve added to the common resolve pool if he dies.
	 * Enemies are updated.
	 */
	public boolean enemyApplyWounds(GameController gc,Enemy enemy,EnemyArea enemyArea, Hero hero, int woundCount){
		boolean enemyDead=false;
		switch (enemyArea){
			case HERO:
				// Apply wounds an check if the Enemy died
				enemy.applyWounds(woundCount);
				if (enemy.getLife().getValueCurrent()<=0){
					enemyDead=true;
					// Add Resolve to the Resolve Pool
					gc.getGameSetupController().changeResolvePool(enemy.getResolve());
					// Discard Enemy and update
					moveEnemy(gc, enemy, EnemyArea.HERO, EnemyArea.DISCARD, enemy.getEnemySuit(), hero, null, EnemyOperation.DEFEATED);
					updateEnemiesEnemyEliminated(gc, enemyArea, enemy, hero);
				}
				else{
					// Update Enemies
					updateEnemiesRecievingWounds(gc, enemy, hero);
				}	
			break;
			case QUEST:
				// Apply wounds an check if the Enemy died
				enemy.applyWounds(woundCount);
				if (enemy.getLife().getValueCurrent()<=0){
					enemyDead=true;
					// Add Resolve to the Resolve Pool
					gc.getGameSetupController().changeResolvePool(enemy.getResolve());
					// Discard Enemy and update
					moveEnemy(gc, enemy, EnemyArea.QUEST, EnemyArea.DISCARD, enemy.getEnemySuit(), hero, null, EnemyOperation.DEFEATED);
					updateEnemiesEnemyEliminated(gc, enemyArea, enemy, hero);
				}
				else{
					// Update Enemies
					updateEnemiesRecievingWounds(gc, enemy, hero);
				}
			break;
			default:
			break;
		}
		
		return enemyDead;
	}
	
	/**
	 * The specified enemy will be enraged if possible and effects are carried out.
	 * Returns true if the enemy was enraged successfully.
	 */
	public void enrageEnemy(GameController gc, EnemyArea enemyArea, Enemy enemy, Hero hero){
		
		// If the Enemy is not already enraged
		if (enemy.isEnraged()==false){
			switch (enemy.getEnemyType()){	
				// Add +1 Magical Resistance
				case RENEGADE_FLAMECASTER:
					enemy.updateModification(ModSource.ENEMY, ModType.RAGE, ModTarget.MAGICAL_RESISTANCE, 1, enemy.getEnemyID());
					enemy.setEnraged(true);
				break;
				
				// Add +1 Magical Resistance and +1 Physical Resistance
				case BLACKWOOD_FIGHTER:
					enemy.updateModification(ModSource.ENEMY, ModType.RAGE, ModTarget.MAGICAL_RESISTANCE, 1, enemy.getEnemyID());
					enemy.updateModification(ModSource.ENEMY, ModType.RAGE, ModTarget.PHYSICAL_RESISTANCE, 1, enemy.getEnemyID());
					enemy.setEnraged(true);
				break;
				
				// Add +1 Damage
				case BLACKWOOD_AMBUSHER:
				case BLACKWOOD_ASSASSIN:
				case BLACKWOOD_CHANGELING:
				case BLOODSCORNE_VAMPIRE:
				case BONESORROW_SHOOTER:
				case BONESORROW_WARRIOR:
				case DIRE_WOLF:
				case TRACKER_HOUND:
				case TWISTED_CURSEBEARER:
					enemy.updateModification(ModSource.ENEMY, ModType.RAGE, ModTarget.ATTACK, 1, enemy.getEnemyID());
					enemy.setEnraged(true);
				break;
				
				// Ignore Slow
				case CURSED_WALKER:
				case ICE_REAVER:
					enemy.updateModification(ModSource.ENEMY, ModType.RAGE, ModTarget.ABILITY, 0, enemy.getEnemyID());
					enemy.removeModification(ModType.SLOW, ModSource.ENEMY, -1);
					enemy.setEnraged(true);
				break;
				
				// Attack and Calm after
				case BLACK_COVEN_CASTER:
				case GHOREN_RAGECALLER:
				case GHOREN_SMALLHORN:
				case GHOREN_WARRIOR:
				case RAVENOUS_DRAUGR:
				case VAMPIRE_BAT_SWARM:
				case WILD_ICEHOUND:
					enemy.setEnraged(true);
					// TODO: Attack the Hero
					enemy.setEnraged(false);
				break;
				
				// Remove all Wounds from this enemy and calm after
				case UNDEAD_LOREMASTER:
					enemy.setEnraged(true);
					enemy.getLife().setValueCurrent(enemy.getLife().getValueMod());
					enemy.setEnraged(false);
				break;
				
				// Remove 1 Wound from this enemy and calm after
				case GHOREN_SLINGER:
				case GHOREN_BLOOD_TRACKER:
					enemy.setEnraged(true);
					if (enemy.getLife().getValueCurrent()<enemy.getLife().getValueMod()){
						enemy.getLife().setValueCurrent(enemy.getLife().getValueCurrent()+1);
					}
					enemy.setEnraged(false);
				break;
				
				// Remove 1 Wound from all UNDEAD Enemies in the same Area, calm after
				case BONESORROW_MAGUS:
					enemy.setEnraged(true);
					switch (enemyArea){
						case HERO:
							for (Enemy loopEnemy : hero.getEnemies()){
								if (loopEnemy.getEnemyKeyword().contains(EnemyKeyword.UNDEAD)){
									if (loopEnemy.getLife().getValueCurrent()<loopEnemy.getLife().getValueMod()){
										loopEnemy.getLife().setValueCurrent(loopEnemy.getLife().getValueCurrent()+1);
									}
									
								}
							}
						break;
						case QUEST:
							for (Enemy loopEnemy : gc.getQuestArea().getQuestAreaEnemies()){
								if (loopEnemy.getEnemyKeyword().contains(EnemyKeyword.UNDEAD)){
									if (loopEnemy.getLife().getValueCurrent()<loopEnemy.getLife().getValueMod()){
										loopEnemy.getLife().setValueCurrent(loopEnemy.getLife().getValueCurrent()+1);
									}
									
								}
							}
						break;
						default:
					}
					enemy.setEnraged(false);
				break;
				
				// The Hero discards 1 Card, calm after
				case WILDLANDS_SHAMAN:
					enemy.setEnraged(true);
					// TODO: Discard 1 card
					enemy.setEnraged(false);
				break;
				
				// Apply Thievery effect as if a card was Buried, calm after
				case BLACKWOOD_CUTTPURSE:
					enemy.setEnraged(true);
					Thievery.applyEffect();
					enemy.setEnraged(false);
				break;
				
				// Apply Skulduggery effect as if a card was Buried, calm after
				case BLACKWOOD_HARASSER:
					enemy.setEnraged(true);
					Skulduggery.applyEffect();
					enemy.setEnraged(false);
				break;
				
				// Apply Mana Drain effect as if a card was Buried, calm after
				case BLACKWOOD_MAGEHUNTER:
					enemy.setEnraged(true);
					ManaDrain.applyEffect();
					enemy.setEnraged(false);
				break;
				
				// Activate the Flailing ability, calm after
				case TWISTED_LASHER:
					enemy.setEnraged(true);
					Flailing.activate(gc, enemy, hero);
					enemy.setEnraged(false);
				break;
				
				// Activate the Scavenger ability, calm after
				case FELLSTALKER:
					enemy.setEnraged(true);
					Scavenger.activate(gc, enemy, hero);
					enemy.setEnraged(false);
				break;
				
				// Activate Vengeful Shriek, calm after
				case VENGEFUL_BANSHEE:
					enemy.setEnraged(true);
					VengefulShriek.activate(gc, enemy, hero);
					enemy.setEnraged(false);
				break;
				
				default:	
				break;
			}
		}
	}

}
