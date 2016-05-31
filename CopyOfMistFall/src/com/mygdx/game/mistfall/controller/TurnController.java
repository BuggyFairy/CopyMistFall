package com.mygdx.game.mistfall.controller;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.Encounter;
import com.mygdx.game.mistfall.model.Location;
import com.mygdx.game.mistfall.model.enums.LocationEffectUse;
import com.mygdx.game.mistfall.model.enums.LocationStatus;
import com.mygdx.game.mistfall.model.enums.PickControllerCharacters;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModType;


public class TurnController {

	
	public void reinforcmentPhase(GameController gc){
		
		//Reset Reinforcement Track if no active Encounter in play
		if(gc.getActiveEncounter() == null){
			gc.getQuestCharter().setReinforcementTrackPosition(0);
		}
		// if active Encounter in play
		else{
			//Move Reinforcement Track
			gc.moveReinforcementTrack(gc.getActiveEncounter().getReinforcment());
			
			//spawn Reinforcement as specified by the Reinforcement track
			gc.getEnemyController().spawnEnemies(gc,gc.getQuestCharter().getReinforcementTrack()[gc.getQuestCharter().getReinforcementTrackPosition()]);
		}		
	}
	
	public void travelPhase(GameController gc){
		

		while (gc.getViewController().getConfirmRelocation()==false || gc.getViewController().getSkipRelocation()==false){
			// Wait for User Input
			
			Location selectedLocation = gc.getViewController().getSelectedLocation();
			
			// Scout Chosen Location
			if(gc.getViewController().getScoutConfirmed()){
				gc.getGameSetupController().getLocationGrid().getLocationAt(selectedLocation.getCoordinates()).setRevealed(true);
				gc.getGameSetupController().getLocationGrid().getLocationAt(selectedLocation.getCoordinates()).setLocationStatus(LocationStatus.PERILOUS);
				gc.getGameSetupController().setResolvePool(gc.getGameSetupController().getResolvePool()-1);
				gc.getViewController().setScoutConfirmed(false);
			}
		}
		
		// Relocation Confirmed, proceeding with Entering New Location
		if (gc.getViewController().getConfirmRelocation()){
			
			// Check for active Encounter of the "old" position and applying retreat penalty and discard
			if (gc.getActiveEncounter()!=null){
				gc.getActiveEncounter().retreatPenalty();
				gc.getGameSetupController().getEncountersDiscard().add(gc.getActiveEncounter());
				gc.setActiveEncounter(null);
			}
			
			// reveal new Location if not revealed
			if (gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getViewController().getSelectedLocation().getCoordinates()).isRevealed()==false){
				gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getViewController().getSelectedLocation().getCoordinates()).setRevealed(true);
				gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getViewController().getSelectedLocation().getCoordinates()).setLocationStatus(LocationStatus.PERILOUS);
			}
			
			// Disperse enemy's in Quest and Hero areas
			gc.getEnemyController().disperseEnemies(gc);
			
			//Apply Location effects from the type ENTER_NEW_LOCATION
			if (gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getViewController().getSelectedLocation().getCoordinates()).getLocationsEffectUse().contains(LocationEffectUse.ENTER_NEW_LOCATION)){
				gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getViewController().getSelectedLocation().getCoordinates()).applyEffect();
			}
			
			// Relocate Party to the new Location
			gc.setActiveLovation(gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getViewController().getSelectedLocation().getCoordinates()));		
		}	
		
		// Check for Encounter and draw new Encounter if there is none
		if (gc.getActiveEncounter()==null && gc.getActiveLovation().getLocationStatus()!=LocationStatus.SAFE){
			// draw encounter
			drawEncounter(gc);
			//Apply Location effects from the type DRAW_NEW_ENCOUNTER
			if (gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getActiveLovation().getCoordinates()).getLocationsEffectUse().contains(LocationEffectUse.DRAW_NEW_ENCOUNTER)){
				gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getActiveLovation().getCoordinates()).applyEffect();
			}
		}
		// Setup Encounter
		if (gc.getActiveEncounter()!=null){
			// Spawn initial Enemies
			gc.getEnemyController().spawnEnemies(gc,gc.getActiveEncounter().getInitialEnemyCount());
			// Follow special setup rules of Encounter
			// TODO: special setup rules
		}
	}
	
	public void pursuitPhase(GameController gc){
		
		List<Hero> heroesWithHighestFocus = new LinkedList<Hero>();
		int highestFocusValue;
		boolean heroWithFocusLeft=true;
		
		// Run as long as there are Enemies left in the Questing Area and at least 1 hero has a focus >0
		while (gc.getQuestArea().getQuestAreaEnemies().size()>0 && heroWithFocusLeft==true){ 
			// Clear List
			heroesWithHighestFocus.clear();
			highestFocusValue=0;
			// Get highest Focus Value among all heroes
			for(Hero h:gc.getHeroes()){
				if (h.getFocus()>highestFocusValue){
					highestFocusValue=h.getFocus();
				}
			}
			// Add all heroes with the highest focus Value to a List if its > 0
			if (highestFocusValue>0){
				for(Hero h:gc.getHeroes()){
					if (h.getFocus()==highestFocusValue){
						heroesWithHighestFocus.add(h);
					}
				}
			}
			// If there is 1 hero in the List, the Enemy at the top of the list enter the hero's area
			if (heroesWithHighestFocus.size()==1){
				// Move Enemy, halve hero focus and update enemies
				gc.getEnemyController().pursuitHero(gc, heroesWithHighestFocus.get(0));
			}
			// If there are 2 or more heroes in the list, the players must decide which hero will be pursuited by the enemy at the top of the list
			else if (heroesWithHighestFocus.size()>1){
				// Get Chosen Hero of the players
				Hero selectedHero = heroesWithHighestFocus.get(gc.getViewController().pickCharacter(PickControllerCharacters.HEROES, heroesWithHighestFocus, null));
				// Move Enemy, halve hero focus and update enemies
				gc.getEnemyController().pursuitHero(gc,selectedHero);	
			}
			// If there is no hero in the list the pursuitPhase ends
			else{
				heroWithFocusLeft=false;
			}
		}
	}
	
	public void heroPhase(GameController gc){
		// Set List of Heroes who haven't done their hero turn
		List<Hero> heroesWithTurnLeft = new LinkedList<Hero>();
		heroesWithTurnLeft.addAll(gc.getHeroes());
		// As long as there is a Hero left in the List, proceed with the heroPhase
		while (heroesWithTurnLeft.isEmpty()==false){
			// Get active Hero from the players and set the active hero ID
			gc.setActiveHero(heroesWithTurnLeft.get(gc.getViewController().pickCharacter(PickControllerCharacters.HEROES, heroesWithTurnLeft, null)).getHeroID());
			// Resolve "At the start of your Turn Abilities"
			//TODO: s.o.
			
	
			boolean heroTurnActive=true;
			// Run the Hero turn until the player ends it
			while (heroTurnActive==true){
				//TODO: Herophase
			}
			
			// Resolve "At the end of your Turn Abilities"
			//TODO: s.o.
			
			// Draw Cards back to the draw limit
			CardController.drawToDrawLimit(gc.getHeroes().get(gc.getActiveHero()));
			// Remove Hero from List heroesWithTurnLeft
			for (Hero h:heroesWithTurnLeft){
				if (h.getHeroID()==gc.getActiveHero()){
					heroesWithTurnLeft.remove(h);
					break;
				}
			}
			// Reset Active Hero
			gc.setActiveHero(-1);
		}
	}
	
	public void defencePhase(GameController gc){
		
		List<Hero> heroesWithEnemies = new LinkedList<Hero>();
		List<Enemy> localHeroEnemies = new LinkedList<Enemy>();
		Enemy activeEnemy;
		Boolean isSlowed;
		
		// Get all Heroes with Enemies in their Hero Area that are not slowed
		for (Hero h : gc.getHeroes()){
			// If there are any enemies in the Hero area
			if (h.getHeroEnemies().getCards().isEmpty()==false){
				// If at least one enemy is NOT slowed
				for (Enemy e : h.getHeroEnemies().getCards()){
					if (e.searchModification(ModType.SLOW)==false){
						// Add Hero to list "heroesWithEnemies" 
						heroesWithEnemies.add(h);
						break;
					}
				}
			}
		}
		
		// Runs until every Hero of "heroesWithEnemies" was activated once
		while(heroesWithEnemies.isEmpty()==false){
			
			localHeroEnemies.clear();
			
			// Players choose which hero they want to activate; set active Hero
			gc.setActiveHero(heroesWithEnemies.get(gc.getViewController().pickCharacter(PickControllerCharacters.HEROES, heroesWithEnemies, null)).getHeroID());
			
			// Add all enemies that are not slowed to "localHeroEnemies" list
			for (Enemy e : gc.getHeroes().get(gc.getActiveHero()).getHeroEnemies().getCards()){
				if (e.searchModification(ModType.SLOW)==false){
					localHeroEnemies.add(e);
				}
			}
			
			// Runs until every enemy of "localHeroEnemies" was activated once
			while (localHeroEnemies.isEmpty()==false){
				// Player activates a enemy in their hero area
				activeEnemy = localHeroEnemies.get(gc.getViewController().pickCharacter(PickControllerCharacters.ENEMIES_HERO_AREA, localHeroEnemies, gc.getHeroes().get(gc.getActiveHero())));
				
				// Enemy Attacks
				//TODO: Enemy attack + Reflex
				
				// Remove Enemy from the list after he was activated
				localHeroEnemies.remove(activeEnemy);
			}
			
			// Remove active Hero from list "localHeroEnemies"
			localHeroEnemies.remove(gc.getHeroes().get(gc.getActiveHero()));
			gc.setActiveHero(-1);		
		}
		
		
		// Remove Slow Modification from all enemies if possible
		for (int i=0;i<gc.getHeroes().size();i++){
			for (int j=0;j<gc.getHeroes().get(i).getHeroEnemies().getCards().size();j++){
				if (gc.getHeroes().get(i).getHeroEnemies().getCards().get(j).getAbilities().contains(EnemyAbilityType.SLOW)){
					gc.getHeroes().get(i).getHeroEnemies().getCards().get(j).removeModification(ModType.SLOW, ModSource.ENEMY, gc.getHeroes().get(i).getHeroEnemies().getCards().get(j).getEnemyID());
				}
			}
		}
		
		// Resolve Conditions
		List<Enemy> enemiesWithConditions = new LinkedList<Enemy>();
		Enemy selectedEnemy;
		int enemyPos;
		// Check All Heroes
		for (Hero h: gc.getHeroes()){
			enemiesWithConditions.clear();
			// Check all enemies of the current hero for conditions and add to list "enemiesWithConditions"
			for (Enemy e: h.getHeroEnemies().getCards()){
				if (e.getConditions().getConditionCount()>0){
					enemiesWithConditions.add(e);
				}
			}
			// Resolve enemy conditions if there are any
			while (enemiesWithConditions.isEmpty()==false){
				selectedEnemy=enemiesWithConditions.get(gc.getViewController().pickCharacter(PickControllerCharacters.ENEMIES_HERO_AREA, enemiesWithConditions, h));
				enemyPos=gc.getHeroes().get(h.getHeroID()).getHeroEnemies().getEnemyPos(selectedEnemy);
				
				// ERROR: If the selected enemy is not in the Heros list go to the next enemy
				if (enemyPos==-1){
					enemiesWithConditions.remove(selectedEnemy);
					continue;
				}
				// Resolve Condition effects and Discard 1 Condition if possible
				gc.getEnemyController().resolveCondition(gc, selectedEnemy, h, EnemyArea.HERO);
				enemiesWithConditions.remove(selectedEnemy);
			}
			// Resolve Hero Conditions
			// TODO
		}
		// Check Enemies in the Quest Area
		enemiesWithConditions.clear();
		// Check all enemies in the Quest Area for conditions and add to list "enemiesWithConditions"
		for (Enemy e: gc.getQuestArea().getQuestAreaEnemies()){
			if (e.getConditions().getConditionCount()>0){
				enemiesWithConditions.add(e);
			}
		}
		// Resolve enemy conditions if there are any
		while (enemiesWithConditions.isEmpty()==false){
			selectedEnemy=enemiesWithConditions.get(gc.getViewController().pickCharacter(PickControllerCharacters.ENEMIES_QUEST_AREA, enemiesWithConditions, null));
			enemyPos=gc.getQuestArea().getEnemyPos(selectedEnemy);
			// ERROR: If the selected enemy is not in the Heros list go to the next enemy
			if (enemyPos==-1){
				enemiesWithConditions.remove(selectedEnemy);
				continue;
			}
			// Resolve Condition effects and Discard 1 Condition if possible
			gc.getEnemyController().resolveCondition(gc, selectedEnemy, null, EnemyArea.QUEST);
			enemiesWithConditions.remove(selectedEnemy);
		}
	}
	
	
	
	
	
	
	
//	public void defencePhase(List<Hero> heroes){
//		Hero activeHero;
//		Enemy activeEnemy;
//		List<Hero> heroesWithEnemies = new LinkedList<Hero>();
//		List<Enemy> localHeroEnemies = new LinkedList<Enemy>();
//
//			for(Hero h : heroes){
//				if(h.getHeroEnemies().getCards().isEmpty() == false){
//					heroesWithEnemies.add(h);
//				}
//			}
//			while(heroesWithEnemies.isEmpty()==false){
//				localHeroEnemies.clear();
//				activeHero = PickHeroController.pickHero(heroesWithEnemies);
//				
//				localHeroEnemies.addAll(activeHero.getHeroEnemies().getCards());
//				
//				while(localHeroEnemies.isEmpty() == false){
//					
//					activeEnemy = CardController.pickEnemyCard();		
//				}
//				heroesWithEnemies.remove(activeHero);
//		}
//	}
//	
	public void encounterPhase(){
		
	}
	
	public void timePhase(){

	}
	
	
	

	
	
	/**
	 * @param gc
	 * 
	 * If there is no encounter draw one, who fits the location keyword
	 */
	@SuppressWarnings("unchecked")
	public void drawEncounter(GameController gc){
		while (gc.getActiveEncounter()==null){
			if (gc.getGameSetupController().getEncounters().isEmpty()){
				gc.getGameSetupController().setEncounters((List<Encounter>)CardController.shuffleCards(gc.getGameSetupController().getEncountersDiscard()));
			}
			if (gc.getGameSetupController().getEncounters().get(0).getLocationTypes().contains(gc.getActiveLovation().getLocationType())){
				gc.setActiveEncounter(gc.getGameSetupController().getEncounters().get(0)); 	
				gc.getGameSetupController().getEncounters().remove(0);
			}
			else{
				gc.getGameSetupController().getEncountersDiscard().add(gc.getGameSetupController().getEncounters().get(0));
				gc.getGameSetupController().getEncounters().remove(0);
			}
		}
	}
	
	//HURENSOHN
	
}
