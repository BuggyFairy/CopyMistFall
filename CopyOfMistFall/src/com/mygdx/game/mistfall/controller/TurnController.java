package com.mygdx.game.mistfall.controller;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.Encounter;
import com.mygdx.game.mistfall.model.Location;
import com.mygdx.game.mistfall.model.enums.EnemyKeyword;
import com.mygdx.game.mistfall.model.enums.LocationEffectUse;
import com.mygdx.game.mistfall.model.enums.LocationStatus;


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
			spawnEnemies(gc,gc.getQuestCharter().getReinforcementTrack()[gc.getQuestCharter().getReinforcementTrackPosition()]);
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
			gc.disperseEnemies();
			
			//Apply Location effects
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
			//Apply Location effects
			if (gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getActiveLovation().getCoordinates()).getLocationsEffectUse().contains(LocationEffectUse.DRAW_NEW_ENCOUNTER)){
				gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getActiveLovation().getCoordinates()).applyEffect();
			}
		}
		// Setup Encounter
		if (gc.getActiveEncounter()!=null){
			// Spawn initial Enemies
			spawnEnemies(gc,gc.getActiveEncounter().getInitialEnemyCount());
			// Follow special setup rules
			// TODO: special setup rules
		}
	}
	
	public void pursuitPhase(GameController gc){
		
		List<Hero> heroesWithHighestFocus = new LinkedList<Hero>();
		int highestFocusValue=0;
		boolean heroWithFocusLeft=true;
		
		// Run as long as there are Enemies left in the Questing Area and at least 1 hero has a focus >0
		while (gc.getQuestArea().getQuestAreaEnemies().size()>0 && heroWithFocusLeft==true){ 
			// Clear List
			heroesWithHighestFocus.clear();
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
				// Add Enemy from the top of the quest area list to the hero's area
				gc.getHeroByName(heroesWithHighestFocus.get(0).getName()).getHeroEnemies().getCards().add(gc.getQuestArea().getQuestAreaEnemies().get(0));
				gc.getQuestArea().getQuestAreaEnemies().remove(0);
				// Halve the Hero Focus
				gc.getHeroByName(heroesWithHighestFocus.get(0).getName()).halveFocus();
			}
			// If there are 2 or more heroes in the list, the players must decide which hero will be pursuited by the enemy at the top of the list
			else if (heroesWithHighestFocus.size()>1){
				// Get Chosen Hero of the players
				Hero selectedHero = PickHeroController.pickHero(heroesWithHighestFocus);
				// Add Enemy from the top of the quest area list to the hero's area
				gc.getHeroByName(selectedHero.getName()).getHeroEnemies().getCards().add(gc.getQuestArea().getQuestAreaEnemies().get(0));
				gc.getQuestArea().getQuestAreaEnemies().remove(0);
				// Halve the Hero Focus
				gc.getHeroByName(selectedHero.getName()).halveFocus();
			}
			// If there is no hero in the list the pursuitPhase ends
			else{
				heroWithFocusLeft=false;
			}
		}
	}
	
	public void heroPhase(){
		
	}
	
	public void defencePhase(List<Hero> heroes){
		Hero activeHero;
		Enemy activeEnemy;
		List<Hero> heroesWithEnemies = new LinkedList<Hero>();
		List<Enemy> localHeroEnemies = new LinkedList<Enemy>();

			for(Hero h : heroes){
				if(h.getHeroEnemies().getCards().isEmpty() == false){
					heroesWithEnemies.add(h);
				}
			}
			while(heroesWithEnemies.isEmpty()==false){
				localHeroEnemies.clear();
				activeHero = PickHeroController.pickHero(heroesWithEnemies);
				
				localHeroEnemies.addAll(activeHero.getHeroEnemies().getCards());
				
				while(localHeroEnemies.isEmpty() == false){
					
					activeEnemy = CardController.pickEnemyCard();		
				}
				
				
				heroesWithEnemies.remove(activeHero);
		}
		
		
	}
	
	public void encounterPhase(){
		
	}
	
	public void timePhase(){

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @param gc, spawnEnemiesCount
	 * 		   false if spawning was not finished
	 * 
	 * draw specified number of enemies from the type of the active Encounter if possible and apply time penalty if necessary 
	 */
	public void spawnEnemies(GameController gc, int spawnEnemiesCount){
	
		int shuffleCount=0;
		boolean timePenalty=false;
		
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
						gc.getGameSetupController().getBlueEnemies().remove(0);
						spawnEnemiesCount--;
					}
					// Draw enemy with specified Keyword
					else{
						if(gc.getGameSetupController().getBlueEnemies().get(0).getEnemyKeyword().contains(gc.getActiveEncounter().getEnemyKeyword())){
							gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getBlueEnemies().get(0));
							gc.getGameSetupController().getBlueEnemies().remove(0);
							spawnEnemiesCount--;
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
						gc.getGameSetupController().getRedEnemies().remove(0);
						spawnEnemiesCount--;
					}
					// Draw enemy with specified Keyword
					else{
						if(gc.getGameSetupController().getRedEnemies().get(0).getEnemyKeyword().contains(gc.getActiveEncounter().getEnemyKeyword())){
							gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getRedEnemies().get(0));
							gc.getGameSetupController().getRedEnemies().remove(0);
							spawnEnemiesCount--;
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
						gc.getGameSetupController().getGreenEnemies().remove(0);
						spawnEnemiesCount--;
					}
					// Draw enemy with specified Keyword
					else{
						if(gc.getGameSetupController().getGreenEnemies().get(0).getEnemyKeyword().contains(gc.getActiveEncounter().getEnemyKeyword())){
							gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getGreenEnemies().get(0));
							gc.getGameSetupController().getGreenEnemies().remove(0);
							spawnEnemiesCount--;
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
	
	
}
