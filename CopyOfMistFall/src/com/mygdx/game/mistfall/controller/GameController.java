package com.mygdx.game.mistfall.controller;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.Encounter;
import com.mygdx.game.mistfall.model.Location;
import com.mygdx.game.mistfall.model.QuestArea;
import com.mygdx.game.mistfall.model.QuestCharter;

public class GameController {

	// {{ Attributes
	private List<Hero> heroes;
	private QuestArea questArea;
	private Encounter activeEncounter;
	
	private TurnController turnController;
	private GameSetupController gameSetupController;
	private HeroCardController heroCardController;
	
	


	private QuestCharter questCharter;
	private ViewController viewController;
	private EnemyController enemyController;
	
	private int activeHero;
	private Location activeLovation;
	private GamePhase gamePhase;
	
	// }}
	
	// {{ Constructor
	public GameController(){
		questArea = new QuestArea();
		enemyController = new EnemyController();
		gameSetupController = new GameSetupController(this);
		heroCardController = new HeroCardController();
		heroes = new LinkedList<Hero>();
		setActiveHero(-1);
		setGamePhase(new GamePhase());
	}
	
	// }}

	// {{ Getters & Setters
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
	
	public HeroCardController getHeroCardController() {
		return heroCardController;
	}

	public void setHeroCardController(HeroCardController heroCardController) {
		this.heroCardController = heroCardController;
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
	public EnemyController getEnemyController() {
		return enemyController;
	}
	public void setEnemyController(EnemyController enemyController) {
		this.enemyController = enemyController;
	}
	public GamePhase getGamePhase() {
		return gamePhase;
	}

	public void setGamePhase(GamePhase gamePhase) {
		this.gamePhase = gamePhase;
	}
	// }}
	
	// {{ Other Methods
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



	// }}
	



	
}
