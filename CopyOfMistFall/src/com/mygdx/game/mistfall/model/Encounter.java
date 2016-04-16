package com.mygdx.game.mistfall.model;

import java.util.List;

import com.mygdx.game.mistfall.model.enums.EnemyKeyword;
import com.mygdx.game.mistfall.model.enums.EnemyType;
import com.mygdx.game.mistfall.model.enums.LocationType;

public class Encounter extends Card{
	
	private int reinforcment;
	private int initialEnemyCount;
	private EnemyType enemyType;
	private EnemyKeyword enemyKeyword;
	private List<LocationType> locationTypes;
	
	
	
	
	
	
	
	
	public int getReinforcment() {
		return reinforcment;
	}
	public void setReinforcment(int reinforcment) {
		this.reinforcment = reinforcment;
	}
	public int getInitialEnemyCount() {
		return initialEnemyCount;
	}
	public void setInitialEnemyCount(int initialEnemyCount) {
		this.initialEnemyCount = initialEnemyCount;
	}
	public EnemyType getEnemyType() {
		return enemyType;
	}
	public void setEnemyType(EnemyType enemyType) {
		this.enemyType = enemyType;
	}
	public EnemyKeyword getEnemyKeyword() {
		return enemyKeyword;
	}
	public void setEnemyKeyword(EnemyKeyword enemyKeyword) {
		this.enemyKeyword = enemyKeyword;
	}
	public List<LocationType> getLocationTypes() {
		return locationTypes;
	}
	public void setLocationTypes(List<LocationType> locationTypes) {
		this.locationTypes = locationTypes;
	}
	public void retreatPenalty() {
		// TODO Auto-generated method stub
		
	}
	
	
}
