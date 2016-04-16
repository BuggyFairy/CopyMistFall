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
		
		//Drawing Reinforcements Phase 1
		if(gc.getActiveEncounter() == null){
			gc.getQuestCharter().setReinforcementTrackPosition(0);
		}
		//Drawing Reinforcements Phase 2
		else{
			gc.moveReinforcementTrack(gc.getActiveEncounter().getReinforcment());
		}
		
		//Drawing Reinforcements Phase 3 AND 4 AND 5
		boolean succeded = spawnReinforcements(gc);
		
		//if we could not spawn enough enemies active the phase 10
		if(!succeded){
			
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
			
			// reveal new Location if unrevealed
			if (gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getViewController().getSelectedLocation().getCoordinates()).isRevealed()==false){
				gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getViewController().getSelectedLocation().getCoordinates()).setRevealed(true);
				gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getViewController().getSelectedLocation().getCoordinates()).setLocationStatus(LocationStatus.PERILOUS);
			}
			
			// Disperse enemy's in Quest and Hero areas
			gc.disperseEnemies();
			
			// Relocate Party to the new Location
			gc.setActiveLovation(gc.getGameSetupController().getLocationGrid().getLocationAt(gc.getViewController().getSelectedLocation().getCoordinates()));
			
			//Apply Location effects
			if (gc.getActiveLovation().getLocationsEffectUse().contains(LocationEffectUse.ENTER_NEW_LOCATION)){
				gc.getActiveLovation().applyEffect();
			}
		}	
	}
	
	public void pursuitPhase(){
		
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
	 * @param gc
	 * @return true if spawning was possible
	 * 		   false if spawning was not finished
	 * 
	 * spawns reinforcements if possible
	 */
	public boolean spawnReinforcements(GameController gc){
	
	int spawnReinforcements = gc.getQuestCharter().getReinforcementTrack()[gc.getQuestCharter().getReinforcementTrackPosition()];
	int shuffleCount=0;
	
	while(spawnReinforcements != 0){
		
		if(shuffleCount>=2){
			return false;
		}
		
		switch(gc.getActiveEncounter().getEnemyType()){
		
			case BLUE :
				if(gc.getGameSetupController().getBlueEnemies().isEmpty() && gc.getGameSetupController().getBlueEnemiesDiscard().isEmpty()){
					return false;
				}
				if(gc.getGameSetupController().getBlueEnemies().isEmpty()){
					gc.getGameSetupController().setBlueEnemies((List<Enemy>)CardController.shuffleCards(gc.getGameSetupController().getBlueEnemiesDiscard()));
					shuffleCount++;
					
				}
				if(gc.getActiveEncounter().getEnemyKeyword() == EnemyKeyword.ANY){
					gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getBlueEnemies().get(0));
					gc.getGameSetupController().getBlueEnemies().remove(0);
					spawnReinforcements--;
				}
				else{
					if(gc.getGameSetupController().getBlueEnemies().get(0).getEnemyKeyword().contains(gc.getActiveEncounter().getEnemyKeyword())){
						gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getBlueEnemies().get(0));
						gc.getGameSetupController().getBlueEnemies().remove(0);
						spawnReinforcements--;
					}
					else{
						gc.getGameSetupController().getBlueEnemiesDiscard().add(gc.getGameSetupController().getBlueEnemies().get(0));
						gc.getGameSetupController().getBlueEnemies().remove(0);
					}
				}
				break;
				
			case RED :
				if(gc.getGameSetupController().getRedEnemies().isEmpty() && gc.getGameSetupController().getRedEnemiesDiscard().isEmpty()){
					return false;
				}
				if(gc.getGameSetupController().getRedEnemies().isEmpty()){
					gc.getGameSetupController().setRedEnemies((List<Enemy>)CardController.shuffleCards(gc.getGameSetupController().getRedEnemiesDiscard()));
					shuffleCount++;
				}
				if(gc.getActiveEncounter().getEnemyKeyword() == EnemyKeyword.ANY){
					gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getRedEnemies().get(0));
					gc.getGameSetupController().getRedEnemies().remove(0);
					spawnReinforcements--;
				}
				else{
					if(gc.getGameSetupController().getRedEnemies().get(0).getEnemyKeyword().contains(gc.getActiveEncounter().getEnemyKeyword())){
						gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getRedEnemies().get(0));
						gc.getGameSetupController().getRedEnemies().remove(0);
						spawnReinforcements--;
					}
					else{
						gc.getGameSetupController().getRedEnemiesDiscard().add(gc.getGameSetupController().getRedEnemies().get(0));
						gc.getGameSetupController().getRedEnemies().remove(0);
					}
				}
				break;
				
			case GREEN : 
				if(gc.getGameSetupController().getGreenEnemies().isEmpty() && gc.getGameSetupController().getGreenEnemiesDiscard().isEmpty()){
					return false;
				}
				if(gc.getGameSetupController().getGreenEnemies().isEmpty()){
					gc.getGameSetupController().setGreenEnemies((List<Enemy>)CardController.shuffleCards(gc.getGameSetupController().getGreenEnemiesDiscard()));
					shuffleCount++;
				}
				if(gc.getActiveEncounter().getEnemyKeyword() == EnemyKeyword.ANY){
					gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getGreenEnemies().get(0));
					gc.getGameSetupController().getGreenEnemies().remove(0);
					spawnReinforcements--;
				}
				else{
					if(gc.getGameSetupController().getGreenEnemies().get(0).getEnemyKeyword().contains(gc.getActiveEncounter().getEnemyKeyword())){
						gc.getQuestArea().getQuestAreaEnemies().add(gc.getGameSetupController().getGreenEnemies().get(0));
						gc.getGameSetupController().getGreenEnemies().remove(0);
						spawnReinforcements--;
					}
					else{
						gc.getGameSetupController().getGreenEnemiesDiscard().add(gc.getGameSetupController().getGreenEnemies().get(0));
						gc.getGameSetupController().getGreenEnemies().remove(0);
					}
				}
				break;
				
			default :
				//TODO: Exception handling
				break;
			}
		}
		return true;
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
