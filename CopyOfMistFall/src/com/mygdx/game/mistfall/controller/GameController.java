package com.mygdx.game.mistfall.controller;

import java.util.List;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.Encounter;
import com.mygdx.game.mistfall.model.Location;
import com.mygdx.game.mistfall.model.QuestArea;
import com.mygdx.game.mistfall.model.QuestCharter;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class GameController {

	
	private List<Hero> heroes;
	private int activeHero;
	private boolean travelPhaseCompleted;
	private QuestArea questArea;
	private Encounter activeEncounter;
	private TurnController turnController;
	private GameSetupController gameSetupController;
	private Location activeLovation;
	private QuestCharter questCharter;
	private ViewController viewController;
	private EnemyController enemyController;
	
	public GameController(){
		
	}
	

		
	public void moveTimeTrack(int count){
		for (int i=0;i<Math.abs(count);i++){
			if (count>0){
				questCharter.moveTimeTrackPositionRight(true);
			}
			else{
				questCharter.moveTimeTrackPositionRight(false);
			}
			if (questCharter.getTimeTrackPosition()>19){
				// TRIGGER GAME END
			}
			else if (questCharter.getTimeTrackPosition()<0){
				questCharter.setTimeTrackPosition(1);
				// TODO: Add +1 Resolve
			}
			else{
				// Check if Enrage Symbol is reached on the Time Track
				if (questCharter.getTimeTrack()[questCharter.getTimeTrackPosition()][1]==1){
					//TODO TRIGGER ENRAGE
				}
				// Check if "Move Reinforcements Symbol" is reached on the Time Track
				if (questCharter.getTimeTrack()[questCharter.getTimeTrackPosition()][2]==1){
					this.moveReinforcementTrack(1);
				}
			}
		}
	}
	
	public void moveReinforcementTrack(int count){
		for (int i=0;i<Math.abs(count);i++){
			if (count>0){
				questCharter.moveReinforcementTrackPositionRight(true);
			}
			else{
				questCharter.moveReinforcementTrackPositionRight(false);
			}
			if (questCharter.getTimeTrackPosition()>7){
				
				moveTimeTrack(1);
				questCharter.setReinforcementTrackPosition(7);
			}
			if (questCharter.getReinforcementTrackPosition()<0){
				questCharter.setReinforcementTrackPosition(0);
			}
		}
	}

	
	public void turn(){
		turnController.defencePhase(this);
	}




	public List<Hero> getHeroes() {
		return heroes;
	}




	public void setHeroes(List<Hero> heroes) {
		this.heroes = heroes;
	}




	public int getActiveHero() {
		return activeHero;
	}




	public void setActiveHero(int activeHero) {
		this.activeHero = activeHero;
	}




	public QuestArea getQuestArea() {
		return questArea;
	}




	public void setQuestArea(QuestArea questArea) {
		this.questArea = questArea;
	}




	public Encounter getActiveEncounter() {
		return activeEncounter;
	}




	public void setActiveEncounter(Encounter activeEncounter) {
		this.activeEncounter = activeEncounter;
	}




	public TurnController getTurnController() {
		return turnController;
	}




	public void setTurnController(TurnController turnController) {
		this.turnController = turnController;
	}




	public GameSetupController getGameSetupController() {
		return gameSetupController;
	}




	public void setGameSetupController(GameSetupController gameSetupController) {
		this.gameSetupController = gameSetupController;
	}




	public Location getActiveLovation() {
		return activeLovation;
	}




	public void setActiveLovation(Location activeLovation) {
		this.activeLovation = activeLovation;
	}

	public QuestCharter getQuestCharter() {
		return questCharter;
	}

	public void setQuestCharter(QuestCharter questCharter) {
		this.questCharter = questCharter;
	}



	public ViewController getViewController() {
		return viewController;
	}



	public void setViewController(ViewController viewController) {
		this.viewController = viewController;
	}



	public boolean isTravelPhaseCompleted() {
		return travelPhaseCompleted;
	}



	public void setTravelPhaseCompleted(boolean travelPhaseCompleted) {
		this.travelPhaseCompleted = travelPhaseCompleted;
	}
	

	/**
	 * Disperse all Enemies without the "Relentless" Ability from the Quest area and all Hero areas
	 */
	public void disperseEnemies(){
		int i=0;
		Boolean isRelentless;
		// Disperse Quest Area
		while(i<questArea.getQuestAreaEnemies().size()){
			switch (questArea.getQuestAreaEnemies().get(i).getEnemySuit()){
				case BLUE:
					gameSetupController.getBlueEnemiesDiscard().add(questArea.getQuestAreaEnemies().get(i));
				break;
				case RED:
					gameSetupController.getRedEnemiesDiscard().add(questArea.getQuestAreaEnemies().get(i));
				break;
				case GREEN:
					gameSetupController.getGreenEnemiesDiscard().add(questArea.getQuestAreaEnemies().get(i));
				break;
			}
			questArea.getQuestAreaEnemies().remove(i);	
		}
		// Disperse Hero Area's
		for (int j=0;j<heroes.size();j++){
			i=0;
			while(i<heroes.get(j).getHeroEnemies().getCards().size()){
				// If the Current Enemy does no possess the RELENTLESS Modification, discard him
				if (heroes.get(j).getHeroEnemies().getCards().get(i).searchModification(ModType.RELENTLESS)==false){
					switch (heroes.get(j).getHeroEnemies().getCards().get(i).getEnemySuit()){
						case BLUE:
							gameSetupController.getBlueEnemiesDiscard().add(heroes.get(j).getHeroEnemies().getCards().get(i));
						break;
						case RED:
							gameSetupController.getRedEnemiesDiscard().add(heroes.get(j).getHeroEnemies().getCards().get(i));
						break;
						case GREEN:
							gameSetupController.getGreenEnemiesDiscard().add(heroes.get(j).getHeroEnemies().getCards().get(i));
						break;
					}
					heroes.get(j).getHeroEnemies().getCards().remove(i);
				}
				// If RELENTLESS move on to the next enemy
				else{
					i++;
				}
			}
		}
	}	
}
