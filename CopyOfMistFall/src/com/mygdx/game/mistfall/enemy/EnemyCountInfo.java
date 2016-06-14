package com.mygdx.game.mistfall.enemy;

import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.enemy.enums.EnemyType;

public class EnemyCountInfo {

	private int count;
	private EnemySuit enemySuit;
	private EnemyType enemyType;
	

	public EnemyCountInfo(EnemySuit enemySuit, EnemyType enemyType, int count) {
		super();
		this.count = count;
		this.enemySuit = enemySuit;
		this.enemyType = enemyType;
	}
	
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public EnemySuit getEnemySuit() {
		return enemySuit;
	}
	public void setEnemySuit(EnemySuit enemySuit) {
		this.enemySuit = enemySuit;
	}
	public EnemyType getEnemyType() {
		return enemyType;
	}
	public void setEnemyType(EnemyType enemyType) {
		this.enemyType = enemyType;
	}
	
	
	
}
