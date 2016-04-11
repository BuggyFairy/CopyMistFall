package com.mygdx.game.mistfall.controller;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.Encounter;
import com.mygdx.game.mistfall.model.Location;
import com.mygdx.game.mistfall.model.LocationGrid;
import com.mygdx.game.mistfall.model.Reward;
import com.mygdx.game.mistfall.model.TimeCard;

public class GameSetupController {

	private Hero partyLeader;
	private int resolvePool;
	private LocationGrid locationGrid;
	private List<Encounter> encounters;
	private List<TimeCard> timeCards;
	private List<Enemy> greenEnemies;
	private List<Enemy> redEnemies;
	private List<Enemy> blueEnemies;
	private List<Location> locationList;
	private List<Reward> rewards;
	
	private List<Encounter> encountersDiscard;
	private List<TimeCard> timeCardsDiscard;
	private List<Enemy> greenEnemiesDiscard;
	private List<Enemy> redEnemiesDiscard;
	private List<Enemy> blueEnemiesDiscard;
	
	
	
	public List<Location> getLocationList() {
		return locationList;
	}
	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}

	public List<Encounter> getEncounters() {
		return encounters;
	}
	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}
	public List<TimeCard> getTimeCards() {
		return timeCards;
	}
	public void setTimeCards(List<TimeCard> timeCards) {
		this.timeCards = timeCards;
	}
	public List<Enemy> getGreenEnemies() {
		return greenEnemies;
	}
	public void setGreenEnemies(List<Enemy> greenEnemies) {
		this.greenEnemies = greenEnemies;
	}
	public List<Enemy> getRedEnemies() {
		return redEnemies;
	}
	public void setRedEnemies(List<Enemy> redEnemies) {
		this.redEnemies = redEnemies;
	}
	public List<Enemy> getBlueEnemies() {
		return blueEnemies;
	}
	public void setBlueEnemies(List<Enemy> blueEnemies) {
		this.blueEnemies = blueEnemies;
	}
	public List<Reward> getRewards() {
		return rewards;
	}
	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}
	public List<Encounter> getEncountersDiscard() {
		return encountersDiscard;
	}
	public void setEncountersDiscard(List<Encounter> encountersDiscard) {
		this.encountersDiscard = encountersDiscard;
	}
	public List<TimeCard> getTimeCardsDiscard() {
		return timeCardsDiscard;
	}
	public void setTimeCardsDiscard(List<TimeCard> timeCardsDiscard) {
		this.timeCardsDiscard = timeCardsDiscard;
	}
	public List<Enemy> getGreenEnemiesDiscard() {
		return greenEnemiesDiscard;
	}
	public void setGreenEnemiesDiscard(List<Enemy> greenEnemiesDiscard) {
		this.greenEnemiesDiscard = greenEnemiesDiscard;
	}
	public List<Enemy> getRedEnemiesDiscard() {
		return redEnemiesDiscard;
	}
	public void setRedEnemiesDiscard(List<Enemy> redEnemiesDiscard) {
		this.redEnemiesDiscard = redEnemiesDiscard;
	}
	public List<Enemy> getBlueEnemiesDiscard() {
		return blueEnemiesDiscard;
	}
	public void setBlueEnemiesDiscard(List<Enemy> blueEnemiesDiscard) {
		this.blueEnemiesDiscard = blueEnemiesDiscard;
	}
	public Hero getPartyLeader() {
		return partyLeader;
	}
	public void setPartyLeader(Hero partyLeader) {
		this.partyLeader = partyLeader;
	}
	public LocationGrid getLocationGrid() {
		return locationGrid;
	}
	public void setLocationGrid(LocationGrid locationGrid) {
		this.locationGrid = locationGrid;
	}
	public int getResolvePool() {
		return resolvePool;
	}
	public void setResolvePool(int resolvePool) {
		this.resolvePool = resolvePool;
	}
	
	
	public void changeResolvePool(int i){
		
			if((this.resolvePool += i) >= 0){
				this.resolvePool += i;
			}
			else{
				System.out.println("Resolve pool negative");
			}
			
	}
	
	
}
