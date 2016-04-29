package com.mygdx.game.mistfall.controller;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.types.blue.BloodscorneVampire;
import com.mygdx.game.mistfall.enemy.types.blue.BonesorrowMagus;
import com.mygdx.game.mistfall.enemy.types.blue.BonesorrowShooter;
import com.mygdx.game.mistfall.enemy.types.blue.BonesorrowWarrior;
import com.mygdx.game.mistfall.enemy.types.blue.CursedWalker;
import com.mygdx.game.mistfall.enemy.types.blue.RavenousDraugr;
import com.mygdx.game.mistfall.enemy.types.blue.UndeadLoremaster;
import com.mygdx.game.mistfall.enemy.types.blue.VampireBatSwarm;
import com.mygdx.game.mistfall.enemy.types.blue.VampireHound;
import com.mygdx.game.mistfall.enemy.types.blue.VengefulBanshee;
import com.mygdx.game.mistfall.enemy.types.green.DireWolf;
import com.mygdx.game.mistfall.enemy.types.green.Fellstalker;
import com.mygdx.game.mistfall.enemy.types.green.GhorenBloodTracker;
import com.mygdx.game.mistfall.enemy.types.green.GhorenRagecaller;
import com.mygdx.game.mistfall.enemy.types.green.GhorenSlinger;
import com.mygdx.game.mistfall.enemy.types.green.GhorenSmallhorn;
import com.mygdx.game.mistfall.enemy.types.green.GhorenWarrior;
import com.mygdx.game.mistfall.enemy.types.green.IceReaver;
import com.mygdx.game.mistfall.enemy.types.green.TrackerHound;
import com.mygdx.game.mistfall.enemy.types.green.WildIcehound;
import com.mygdx.game.mistfall.enemy.types.green.WildlandsShaman;
import com.mygdx.game.mistfall.enemy.types.red.BlackCovenCaster;
import com.mygdx.game.mistfall.enemy.types.red.BlackwoodAmbusher;
import com.mygdx.game.mistfall.enemy.types.red.BlackwoodAssassin;
import com.mygdx.game.mistfall.enemy.types.red.BlackwoodChangeling;
import com.mygdx.game.mistfall.enemy.types.red.BlackwoodCuttpurse;
import com.mygdx.game.mistfall.enemy.types.red.BlackwoodFighter;
import com.mygdx.game.mistfall.enemy.types.red.BlackwoodHarasser;
import com.mygdx.game.mistfall.enemy.types.red.BlackwoodMagehunter;
import com.mygdx.game.mistfall.enemy.types.red.RenegadeFlamecaster;
import com.mygdx.game.mistfall.enemy.types.red.TwistedCursebearer;
import com.mygdx.game.mistfall.enemy.types.red.TwistedLasher;
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
	
	public GameSetupController(GameController gc){
		int enemyID=1;
		int i;
		
		// Add Red Enemies
		redEnemies=new LinkedList<Enemy>();
		for (i=0;i<6;i++){
			redEnemies.add(new RenegadeFlamecaster(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<6;i++){
			redEnemies.add(new BlackwoodFighter(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<4;i++){
			redEnemies.add(new BlackwoodAssassin(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			redEnemies.add(new BlackwoodAmbusher(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			redEnemies.add(new BlackwoodHarasser(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			redEnemies.add(new BlackwoodCuttpurse(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			redEnemies.add(new BlackwoodMagehunter(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			redEnemies.add(new BlackwoodChangeling(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			redEnemies.add(new BlackCovenCaster(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			redEnemies.add(new TwistedLasher(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			redEnemies.add(new TwistedCursebearer(enemyID,gc));
			enemyID++;
		}
		
		
		// Add Green Enemies
		greenEnemies=new LinkedList<Enemy>();
		for (i=0;i<6;i++){
			greenEnemies.add(new GhorenWarrior(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<6;i++){
			greenEnemies.add(new WildIcehound(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<4;i++){
			greenEnemies.add(new DireWolf(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			greenEnemies.add(new TrackerHound(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			greenEnemies.add(new GhorenBloodTracker(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			greenEnemies.add(new GhorenSlinger(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			greenEnemies.add(new GhorenSmallhorn(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			greenEnemies.add(new GhorenRagecaller(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			greenEnemies.add(new WildlandsShaman(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			greenEnemies.add(new Fellstalker(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			greenEnemies.add(new IceReaver(enemyID,gc));
			enemyID++;
		}
		
		// Add Blue Enemies
		blueEnemies=new LinkedList<Enemy>();
		for (i=0;i<6;i++){
			blueEnemies.add(new BonesorrowShooter(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<6;i++){
			blueEnemies.add(new CursedWalker(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<6;i++){
			blueEnemies.add(new BonesorrowWarrior(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<4;i++){
			blueEnemies.add(new VampireBatSwarm(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			blueEnemies.add(new VampireHound(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			blueEnemies.add(new BloodscorneVampire(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			blueEnemies.add(new RavenousDraugr(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			blueEnemies.add(new VengefulBanshee(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			blueEnemies.add(new UndeadLoremaster(enemyID,gc));
			enemyID++;
		}
		for (i=0;i<3;i++){
			blueEnemies.add(new BonesorrowMagus(enemyID,gc));
			enemyID++;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
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
	
	public int getEnemyPositionDiscard(Enemy enemy){
		int pos=-1;
		switch (enemy.getEnemySuit()){
		case BLUE:
			for (int i=0;i<blueEnemiesDiscard.size();i++){
				if (blueEnemiesDiscard.get(i).getEnemyID()==enemy.getEnemyID()){
					pos=i;
					break;
				}
			}
		break;
		case RED:
			for (int i=0;i<redEnemiesDiscard.size();i++){
				if (redEnemiesDiscard.get(i).getEnemyID()==enemy.getEnemyID()){
					pos=i;
					break;
				}
			}
		break;
		case GREEN:
			for (int i=0;i<greenEnemiesDiscard.size();i++){
				if (greenEnemiesDiscard.get(i).getEnemyID()==enemy.getEnemyID()){
					pos=i;
					break;
				}
			}
		break;
		default:
		break;
	}
		
		return pos;
	}
	
}
