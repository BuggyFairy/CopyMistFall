package com.mygdx.game.mistfall.controller;

import java.util.LinkedList;
import java.util.List;

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
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class EnemyController {	
	
	
	private LinkedList<String> enemyNames=new LinkedList<String>();
	
	public void addEnemyName(String name){
		enemyNames.add(name);
	}
	public String getEnemyNameByID(int enemyID){
		return enemyNames.get(enemyID);
	}
	
	
	
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
		Enemy enemy=gc.getQuestArea().getQuestAreaEnemies().get(0);
		gc.getHeroes().get(hero.getHeroID()).getHeroEnemies().getCards().add(enemy);
		gc.getQuestArea().getQuestAreaEnemies().remove(0);
		// Halve the Hero Focus
		gc.getHeroes().get(hero.getHeroID()).halveFocus();
		// Update Enemies
		updateEnemiesSwitchArea(gc,enemy,EnemyArea.QUEST,EnemyArea.HERO,hero,null,EnemyOperation.PURSUIT);
	}
	
	
	/**
	 * Disperse all Enemies without the "Relentless" Ability from the Quest area and all Hero areas.
	 * Updates Enemies.
	 */
	public void disperseEnemies(GameController gc){
		int i=0;
		// Disperse Quest Area and update Enemies
		while(i<gc.getQuestArea().getQuestAreaEnemies().size()){
			discardEnemy(gc, gc.getQuestArea().getQuestAreaEnemies().get(i), null, EnemyArea.QUEST, EnemyOperation.DISPERSE);
		}
		// Disperse Hero Area's
		for (int j=0;j<gc.getHeroes().size();j++){
			i=0;
			while(i<gc.getHeroes().get(j).getHeroEnemies().getCards().size()){
				// If the Current Enemy does no possess the RELENTLESS Modification, discard him and update Enemies
				if (gc.getHeroes().get(j).getHeroEnemies().getCards().get(i).searchModification(ModType.RELENTLESS)==false){
					discardEnemy(gc, gc.getHeroes().get(j).getHeroEnemies().getCards().get(i), gc.getHeroes().get(j), EnemyArea.HERO, EnemyOperation.DISPERSE);
				}
				// If RELENTLESS move on to the next enemy
				else{
					i++;
				}
			}
		}
	}
	
	
	/**
	 * Draw specified number of enemies from the type of the active Encounter if possible and apply time penalty if necessary.
	 * Updates Enemies in the Quest Area
	 * Returns true if the timePenalty was applied
	 */
	public void spawnEnemies(GameController gc, int spawnEnemiesCount){
	
		int shuffleCount=0;
		boolean timePenalty=false;
		Enemy enemy;
		
		// Draw Enemies till all Enemies have been drawn or the time penalty kicks in (Because Enemies can't be drawn)
		while(spawnEnemiesCount != 0 && timePenalty==false){
			switch(gc.getActiveEncounter().getEnemyType()){	
				// Draw from the blue enemy deck
				case BLUE :
					// shuffle discard pile and form new deck if the deck is empty
					if(gc.getGameSetupController().getBlueEnemies().isEmpty()){
						gc.getGameSetupController().setBlueEnemies((List<Enemy>)CardController.shuffleCards(gc.getGameSetupController().getBlueEnemiesDiscard()));
						shuffleCount++;				
					}
					// Draw enemy with any Keyword
					if(gc.getActiveEncounter().getEnemyKeyword() == EnemyKeyword.ANY){
						gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getBlueEnemies().get(0));
						enemy=gc.getGameSetupController().getGreenEnemies().get(0);
						gc.getGameSetupController().getBlueEnemies().remove(0);
						spawnEnemiesCount--;
						// Update Enemies
						updateEnemiesSwitchArea(gc,enemy,EnemyArea.DECK,EnemyArea.QUEST,null,null,EnemyOperation.DRAW);
					}
					// Draw enemy with specified Keyword
					else{
						if(gc.getGameSetupController().getBlueEnemies().get(0).getEnemyKeyword().contains(gc.getActiveEncounter().getEnemyKeyword())){
							gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getBlueEnemies().get(0));
							enemy=gc.getGameSetupController().getGreenEnemies().get(0);
							gc.getGameSetupController().getBlueEnemies().remove(0);
							spawnEnemiesCount--;
							// Update Enemies
							updateEnemiesSwitchArea(gc,enemy,EnemyArea.DECK,EnemyArea.QUEST,null,null,EnemyOperation.DRAW);
						}
						else{
							gc.getGameSetupController().getBlueEnemiesDiscard().add(gc.getGameSetupController().getBlueEnemies().get(0));
							gc.getGameSetupController().getBlueEnemies().remove(0);
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
						gc.getGameSetupController().setRedEnemies((List<Enemy>)CardController.shuffleCards(gc.getGameSetupController().getRedEnemiesDiscard()));
						shuffleCount++;
					}
					// Draw enemy with any Keyword
					if(gc.getActiveEncounter().getEnemyKeyword() == EnemyKeyword.ANY){
						gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getRedEnemies().get(0));
						enemy=gc.getGameSetupController().getGreenEnemies().get(0);
						gc.getGameSetupController().getRedEnemies().remove(0);
						spawnEnemiesCount--;
						// Update Enemies
						updateEnemiesSwitchArea(gc,enemy,EnemyArea.DECK,EnemyArea.QUEST,null,null,EnemyOperation.DRAW);
					}
					// Draw enemy with specified Keyword
					else{
						if(gc.getGameSetupController().getRedEnemies().get(0).getEnemyKeyword().contains(gc.getActiveEncounter().getEnemyKeyword())){
							gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getRedEnemies().get(0));
							enemy=gc.getGameSetupController().getGreenEnemies().get(0);
							gc.getGameSetupController().getRedEnemies().remove(0);
							spawnEnemiesCount--;
							// Update Enemies
							updateEnemiesSwitchArea(gc,enemy,EnemyArea.DECK,EnemyArea.QUEST,null,null,EnemyOperation.DRAW);
						}
						else{
							gc.getGameSetupController().getRedEnemiesDiscard().add(gc.getGameSetupController().getRedEnemies().get(0));
							gc.getGameSetupController().getRedEnemies().remove(0);
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
						gc.getGameSetupController().setGreenEnemies((List<Enemy>)CardController.shuffleCards(gc.getGameSetupController().getGreenEnemiesDiscard()));
						shuffleCount++;
					}
					// Draw enemy with any Keyword
					if(gc.getActiveEncounter().getEnemyKeyword() == EnemyKeyword.ANY){
						gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getGreenEnemies().get(0));
						enemy=gc.getGameSetupController().getGreenEnemies().get(0);
						gc.getGameSetupController().getGreenEnemies().remove(0);
						spawnEnemiesCount--;
						// Update Enemies
						updateEnemiesSwitchArea(gc,enemy,EnemyArea.DECK,EnemyArea.QUEST,null,null,EnemyOperation.DRAW);
					}
					// Draw enemy with specified Keyword
					else{
						if(gc.getGameSetupController().getGreenEnemies().get(0).getEnemyKeyword().contains(gc.getActiveEncounter().getEnemyKeyword())){
							gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getGreenEnemies().get(0));
							enemy=gc.getGameSetupController().getGreenEnemies().get(0);
							gc.getGameSetupController().getGreenEnemies().remove(0);
							spawnEnemiesCount--;
							// Update Enemies
							updateEnemiesSwitchArea(gc,enemy,EnemyArea.DECK,EnemyArea.QUEST,null,null,EnemyOperation.DRAW);
						}
						else{
							gc.getGameSetupController().getGreenEnemiesDiscard().add(gc.getGameSetupController().getGreenEnemies().get(0));
							gc.getGameSetupController().getGreenEnemies().remove(0);
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
		
		// Apply time penalty and shuffle all enemy Decks
		if (timePenalty==true){
			//Shuffle Blue enemy cards
			gc.getGameSetupController().setBlueEnemies((List<Enemy>)CardController.shuffleCards(gc.getGameSetupController().getBlueEnemiesDiscard()));
			//Shuffle Red enemy cards
			gc.getGameSetupController().setRedEnemies((List<Enemy>)CardController.shuffleCards(gc.getGameSetupController().getRedEnemiesDiscard()));
			//Shuffle Green enemy cards
			gc.getGameSetupController().setGreenEnemies((List<Enemy>)CardController.shuffleCards(gc.getGameSetupController().getGreenEnemiesDiscard()));
			//Move Timetrack to steps to the right
			gc.moveTimeTrack(2);
		}
	}
	
	
	
	
	
	
	/**
	 * Applies the specified number of wounds to the specified enemy in the specified enemy area. 
	 * Enemy will be discarded and his resolve added to the common resolve pool if he dies.
	 * Enemies are updated.
	 */
	public boolean enemyApplyWounds(GameController gc,Enemy enemy,EnemyArea enemyArea, Hero hero, int woundCount){
		int enemyPos;
		boolean enemyDead=false;
		switch (enemyArea){
			case HERO:
				enemyPos=gc.getHeroes().get(hero.getHeroID()).getHeroEnemies().getEnemyPos(enemy);
				// Apply wounds an check if the Enemy died
				if (gc.getHeroes().get(hero.getHeroID()).getHeroEnemies().getCards().get(enemyPos).applyWounds(woundCount)){
					enemyDead=true;
					// Add Resolve to the Resolve Pool
					gc.getGameSetupController().changeResolvePool(enemy.getResolve());
					// Discard Enemy and update
					discardEnemy(gc, enemy, hero, enemyArea, EnemyOperation.DEFEATED);
				}
				else{
					// Update Enemies
					updateEnemiesRecievingWounds(gc, enemy, hero);
				}	
			break;
			case QUEST:
				enemyPos=gc.getQuestArea().getEnemyPos(enemy);
				// Apply wounds an check if the Enemy died
				if (gc.getQuestArea().getQuestAreaEnemies().get(enemyPos).applyWounds(woundCount)){
					enemyDead=true;
					// Add Resolve to the Resolve Pool
					gc.getGameSetupController().changeResolvePool(enemy.getResolve());
					// Discard Enemy and update
					discardEnemy(gc, enemy, hero, enemyArea, EnemyOperation.DEFEATED);
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
	public boolean enrageEnemy(GameController gc, Enemy enemy, Hero hero){
		
		int heroID=hero.getHeroID();
		int enemyPos=gc.getHeroes().get(heroID).getHeroEnemies().getEnemyPos(enemy);
		int enemyID=gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getEnemyID();
		
		// If the Enemy was found
		if (enemyPos!=-1){
			// If the Enemy is not already enraged
			if (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).isEnraged()==false){
				switch (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getEnemyType()){
				
					// Add +1 Magical Resistance
					case RENEGADE_FLAMECASTER:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.RAGE, ModTarget.MAGICAL_RESISTANCE, 1, enemyID);
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
					break;
					
					// Add +1 Magical Resistance and +1 Physical Resistance
					case BLACKWOOD_FIGHTER:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.RAGE, ModTarget.MAGICAL_RESISTANCE, 1, enemyID);
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.RAGE, ModTarget.PHYSICAL_RESISTANCE, 1, enemyID);
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
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
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.RAGE, ModTarget.ATTACK, 1, enemyID);
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
					break;
					
					// Ignore Slow
					case CURSED_WALKER:
					case ICE_REAVER:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.RAGE, ModTarget.ABILITY, 0, enemyID);
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
					break;
					
					// Attack and Calm after
					case BLACK_COVEN_CASTER:
					case GHOREN_RAGECALLER:
					case GHOREN_SMALLHORN:
					case GHOREN_WARRIOR:
					case RAVENOUS_DRAUGR:
					case VAMPIRE_BAT_SWARM:
					case WILD_ICEHOUND:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
						// TODO: Attack the Hero
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(false);
					break;
					
					// Remove all Wounds from this enemy and calm after
					case UNDEAD_LOREMASTER:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getLife().setValueCurrent(gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getLife().getValueMod());
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(false);
					break;
					
					// Remove 1 Wound from this enemy and calm after
					case GHOREN_SLINGER:
					case GHOREN_BLOOD_TRACKER:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
						if (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getLife().getValueCurrent()<gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getLife().getValueMod()){
							gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getLife().setValueCurrent(gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).getLife().getValueCurrent()+1);
						}
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(false);
					break;
					
					// Remove 1 Wound from all UNDEAD Enemies in the same Area, calm after
					case BONESORROW_MAGUS:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
						for (int i=0;i<gc.getHeroes().get(heroID).getHeroEnemies().getCards().size();i++){
							if (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getEnemyKeyword().contains(EnemyKeyword.UNDEAD)){
								if (gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getLife().getValueCurrent()<gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getLife().getValueMod()){
									gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getLife().setValueCurrent(gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(i).getLife().getValueCurrent()+1);
								}
							}
						}		
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(false);
					break;
					
					// The Hero discards 1 Card, calm after
					case WILDLANDS_SHAMAN:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
						// TODO: Discard 1 card
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(false);
					break;
					
					// Apply Thievery effect as if a card was Buried, calm after
					case BLACKWOOD_CUTTPURSE:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
						Thievery.applyEffect();
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(false);
					break;
					
					// Apply Skulduggery effect as if a card was Buried, calm after
					case BLACKWOOD_HARASSER:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
						Skulduggery.applyEffect();
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(false);
					break;
					
					// Apply Mana Drain effect as if a card was Buried, calm after
					case BLACKWOOD_MAGEHUNTER:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
						ManaDrain.applyEffect();
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(false);
					break;
					
					// Activate the Flailing ability, calm after
					case TWISTED_LASHER:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
						Flailing.activate(gc, enemy, hero);
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(false);
					break;
					
					// Activate the Scavenger ability, calm after
					case FELLSTALKER:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
						Scavenger.activate(gc, enemy, hero);
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(false);
					break;
					
					// Activate Vengeful Shriek, calm after
					case VENGEFUL_BANSHEE:
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(true);
						VengefulShriek.activate(gc, enemy, hero);
						gc.getHeroes().get(heroID).getHeroEnemies().getCards().get(enemyPos).setEnraged(false);
					break;
					
					default:	
					break;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public void updateEnemiesDraw(GameController gc,Enemy enemy){
//	updateEnemiesSwitchArea(gc,enemy,EnemyArea.DECK,EnemyArea.QUEST,null,null,EnemyOperation.DRAW);
//}
//public void updateEnemiesPursuit(GameController gc,Enemy enemy,Hero heroDest){
//	updateEnemiesSwitchArea(gc,enemy,EnemyArea.QUEST,EnemyArea.HERO,heroDest,null,EnemyOperation.PURSUIT);
//}
//public void updateEnemiesDisperse(GameController gc,Enemy enemy,Hero heroSource){
//	updateEnemiesSwitchArea(gc,enemy,EnemyArea.HERO,EnemyArea.DISCARD,null,heroSource,EnemyOperation.DISPERSE);
//}
//public void updateEnemiesDefeated(GameController gc,Enemy enemy,Hero heroSource){
//	updateEnemiesSwitchArea(gc,enemy,EnemyArea.HERO,EnemyArea.DISCARD,null,heroSource,EnemyOperation.DEFEATED);
//}
//public void updateEnemiesHeroHero(GameController gc,Enemy enemy,Hero heroSource, Hero heroDest){
//	updateEnemiesSwitchArea(gc,enemy,EnemyArea.HERO,EnemyArea.HERO,heroSource,heroDest,EnemyOperation.MOVE);
//}
//public void updateEnemiesHeroQuest(GameController gc,Enemy enemy,Hero heroSource){
//	updateEnemiesSwitchArea(gc,enemy,EnemyArea.HERO,EnemyArea.QUEST,null,heroSource,EnemyOperation.MOVE);
//}
//public void updateEnemiesQuestHero(GameController gc,Enemy enemy,Hero heroDest){
//	updateEnemiesSwitchArea(gc,enemy,EnemyArea.QUEST,EnemyArea.HERO,heroDest,null,EnemyOperation.MOVE);
//}
	
	
	
	
	
	
	
	
	
	
	
	
	private void updateEnemiesRecievingWounds(GameController gc,Enemy enemy,Hero hero){
		
		for (int i=0;i<enemy.getAbilities().size();i++){
			switch (enemy.getAbilities().get(i).getType()){
				// Blood Fury: "<Hero Area> This Enemy deals +1 Physical Damage for each Wound on this card."
				case BLOOD_FURY:
					BloodFury.updateTakenDamage(gc, enemy, hero);
				break;
				// Swarm: "<Hero Area> This Enemy adds +1 Physical Damage for each 1 Lifepoint"
				case SWARM:
					Swarm.updateTakenDamge(gc, enemy, hero);
				break;
				default:
				break;
			}
		}	
	}
	
	private void updateEnemiesEnemyEliminated(GameController gc, Enemy enemy, Hero heroSource){
		Reanimate.updateEnemyEliminated(gc, enemy, heroSource);
	}
	
	
	private void updateEnemiesSwitchArea(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource, EnemyOperation enemyOperation){
		
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
					BloodFury.updateMoved(gc, enemy, source, dest, heroDest, heroSource);
				break;
				
				case CURSE_OF_WEAKNESS:
				break;
					
				// Cursed Bolt: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, that Player Buries another Card"
				case CURSED_BOLT:
					CursedBolt.addModification(gc, enemy, dest, heroDest);
				break;
				
				case DARK_PRESENCE:
				break;
					
				// Firebolt: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, place 1 Burning Token on that player's Hero Charter"
				case FIREBOLT:
					Firebolt.addModification(gc, enemy, dest, heroDest);
				break;
				
				// Flailing: "<Hero Area> After this Enemy enters a <Hero Area> during any Pursuit Phase the player places 2 daze Tokens on their Hero Charter"
				case FLAILING:
					if (dest==EnemyArea.HERO && enemyOperation==EnemyOperation.PURSUIT){
						Flailing.activate(gc, enemy, heroDest);
					}
				break;
					
				// Mana Drain: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, that player discards 1 ARCANE or SPELL card, if able"
				case MANA_DRAIN:
					ManaDrain.addModification(gc, enemy, dest, heroDest);
				break;
				
				// Pack Hunter: "<Hero Area> This Enemy deals +1 physical damage for each other HOUND in the same Hero Area"
				case PACK_HUNTER:
					PackHunter.update(gc, enemy, source, dest, heroDest, heroSource);
				break;
				
				// Pursuit: "<Hero Area> Any 1 other BEAST (that does not have the RELENTLESS Ability) in the same Hero Area receives the RELENTLESS Ability"
				case PURSUIT:
					InduceRelentless.update(gc,enemy,source, dest,heroDest,heroSource, EnemyAbilityType.PURSUIT, EnemyKeyword.BEAST);
				break;
		
				// Reanimate: "<Hero Area> Whenever an UNDEAD Enemy is eliminated in the same Area, draw BLUE ENEMIES until a SKELETON is revealed. Place that SKELETON in this Area, discard the rest."
				case REANIMATE:
					Reanimate.updateMoved(gc, enemy, source, dest, heroDest, heroSource);
				break;
				
				// Relentless: "<Hero Area> Do not Discard this Enemy when Dispersing Enemies"
				case RELENTLESS:
					Relentless.update(gc, enemy, source, dest, heroDest, heroSource);
				break;
				
				// Scavenger: "<Hero Area> After this Enemy enters a <Hero Area> the Player Buries 2 cards from their discard pile if able."
				case SCAVENGER:
					if (dest==EnemyArea.HERO){
						Scavenger.activate(gc, enemy, heroDest);
					}
				break;
				
				// Shield Slam: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, place 2 Daze Tokens on that player's Hero Charter"
				case SHIELD_SLAM:
					ShieldSlam.addModification(gc, enemy, dest, heroDest);
				break;
				
				// Skirmisher: "<Quest Area> <Hero Area> Requires an extra 1 Range to target. Ignore this Ability if all Enemies in the same Area are Ranged."
				case SKIRMISHER:
					Skirmisher.update(gc,enemy,source, dest,heroDest,heroSource);	
				break;
				
				// Skulduggery: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, remove 1 Objectiv Token from the active Encounter
				// 				card and place it on this card. Return all Objective Tokens to the Encounter card when this Enemy is discarded"
				case SKULDUGGERY:
					Skulduggery.addModification(gc, enemy, dest, heroDest);
				break;
				
				// Slow: "<Hero Area> This Enemy does not attack on the same round it enters a Hero Area"
				case SLOW:
					Slow.update(gc, enemy, source, dest, heroDest, heroSource);
				break;
				
				// Swarm: "<Hero Area> This Enemy adds +1 Physical Damage for each 1 Lifepoint"
				case SWARM:
					Swarm.updateMoved(gc, enemy, source, dest, heroDest, heroSource);
				break;
				
				// Thievery: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, attach 1 GEAR card from their <Hero Area> to this card.
				//			  Return all Attachments to owners' HAND when this Enemy is discarded"
				case THIEVERY:
					Thievery.addModification(gc, enemy, dest, heroDest);
				break;			
				
				// Vampiric: "<Hero Area> Whenever a player Buries 1 card as a result of this Enemy's attack, remove 1 Wound Token from this Enemy"
				case VAMPIRIC:
					Vampiric.addModification(gc, enemy, dest, heroDest);
				break;
				
				// Vengeful Shriek: "<Hero Area> After this Enemy enters a <Hero Area>, enrage 1 other UNDEAD Enemy in that <Hero Area> if able."
				case VENGEFUL_SHRIEK:
					if (dest==EnemyArea.HERO){
						VengefulShriek.activate(gc, enemy, heroDest);
					}
				break;
					
				// Venomous: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, place 1 Poison Token on that player's Hero Charter"
				case VENOMOUS:
					Venomous.addModification(gc, enemy, dest, heroDest);
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
	
	
	
	private void discardEnemy(GameController gc, Enemy enemy, Hero hero, EnemyArea enemyArea, EnemyOperation enemyOperation){
		int enemyPos;
		switch (enemyArea){
			case HERO:
				enemyPos=gc.getHeroes().get(hero.getHeroID()).getHeroEnemies().getEnemyPos(enemy);
				int heroPos=hero.getHeroID();
				// Add enemy to the discard Pile
				switch (gc.getHeroes().get(heroPos).getHeroEnemies().getCards().get(enemyPos).getEnemySuit()){
					case BLUE:
						gc.getGameSetupController().getBlueEnemiesDiscard().add(gc.getHeroes().get(heroPos).getHeroEnemies().getCards().get(enemyPos));
					break;
					case RED:
						gc.getGameSetupController().getRedEnemiesDiscard().add(gc.getHeroes().get(heroPos).getHeroEnemies().getCards().get(enemyPos));
					break;
					case GREEN:
						gc.getGameSetupController().getGreenEnemiesDiscard().add(gc.getHeroes().get(heroPos).getHeroEnemies().getCards().get(enemyPos));
					break;
				}
				// Remove enemy from the Hero Area
				gc.getHeroes().get(heroPos).getHeroEnemies().getCards().remove(enemyPos);
				// Update Enemies in the Hero Area and Reset the discarded Enemy
				updateEnemiesSwitchArea(gc,enemy,EnemyArea.HERO,EnemyArea.DISCARD,null,hero,enemyOperation);
				if (enemyOperation==EnemyOperation.DEFEATED){
					updateEnemiesEnemyEliminated(gc, enemy, hero);
				}
			break;
			case QUEST:
				enemyPos=gc.getQuestArea().getEnemyPos(enemy);
				// Add enemy to the discard Pile
				switch (gc.getQuestArea().getQuestAreaEnemies().get(enemyPos).getEnemySuit()){
				case BLUE:
						gc.getGameSetupController().getBlueEnemiesDiscard().add(gc.getQuestArea().getQuestAreaEnemies().get(enemyPos));
					break;
					case RED:
						gc.getGameSetupController().getRedEnemiesDiscard().add(gc.getQuestArea().getQuestAreaEnemies().get(enemyPos));
					break;
					case GREEN:
						gc.getGameSetupController().getGreenEnemiesDiscard().add(gc.getQuestArea().getQuestAreaEnemies().get(enemyPos));
					break;
				}
				// Remove enemy from the Quest Area
				gc.getQuestArea().getQuestAreaEnemies().remove(enemyPos);
				// Update Enemies in the Quest Area and Reset the discarded Enemy
				updateEnemiesSwitchArea(gc,enemy,EnemyArea.QUEST,EnemyArea.DISCARD,null,null,enemyOperation);
			break;
			default:
			break;
		}
	}
	
	
}
