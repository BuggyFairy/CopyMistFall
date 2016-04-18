package com.mygdx.game.mistfall.enemy;

import java.util.LinkedList;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.model.Card;
import com.mygdx.game.mistfall.model.Conditions;
import com.mygdx.game.mistfall.model.enums.AttackType;
import com.mygdx.game.mistfall.model.enums.EnemyKeyword;
import com.mygdx.game.mistfall.model.enums.EnemyType;
import com.mygdx.game.mistfall.model.enums.Vulnerability;

public class Enemy extends Card {
	
	private EnemyType enemyType;
	private AttackType attackType;
	
	private LinkedList<EnemyKeyword> enemyKeyword;
	private LinkedList<Vulnerability> vulnerability;
	private Conditions conditions;
	private int attackValue;
	
	private int physicalRes;
	private int magicalRes;
	private int life;
	private int resolve;
	private boolean specialEnemy;
	private boolean wasActivated;
	private boolean isRelentless;
	private boolean isSlowed;
	
	
	
	
	public Enemy(){
		enemyKeyword=new LinkedList<EnemyKeyword>();
		vulnerability=new LinkedList<Vulnerability>();
		conditions=new Conditions();
	}
	
	
	public LinkedList<EnemyKeyword> getEnemyKeyword() {
		return enemyKeyword;
	}
	public void setEnemyKeyword(LinkedList<EnemyKeyword> enemyKeyword) {
		this.enemyKeyword = enemyKeyword;
	}
	public void appendEnemyKeyword(EnemyKeyword ek){
		if(this.enemyKeyword != null){
			this.enemyKeyword.add(ek);
		}
	}
	
	public LinkedList<Vulnerability> getVunerability() {
		return vulnerability;
	}
	public void setVunerability(LinkedList<Vulnerability> vulnerability) {
		this.vulnerability = vulnerability;
	}
	public void appendVunerability(Vulnerability v){
		if(this.vulnerability != null){
			this.vulnerability.add(v);
		}
	}
	
	public EnemyType getEnemyType() {
		return enemyType;
	}
	public void setEnemyType(EnemyType enemyType) {
		this.enemyType = enemyType;
	}
	
	public AttackType getAttackType() {
		return attackType;
	}
	public void setAttackType(AttackType attackType) {
		this.attackType = attackType;
	}
	
	public int getAttackValue() {
		return attackValue;
	}
	public void setAttackValue(int attackValue) {
		this.attackValue = attackValue;
	}
	
	public int getPhysicalRes() {
		return physicalRes;
	}
	public void setPhysicalRes(int physicalRes) {
		this.physicalRes = physicalRes;
	}
	
	public int getMagicalRes() {
		return magicalRes;
	}
	public void setMagicalRes(int magicalRes) {
		this.magicalRes = magicalRes;
	}
	
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	
	public int getResolve() {
		return resolve;
	}
	public void setResolve(int resolve) {
		this.resolve = resolve;
	}
	
	public boolean isSpecialEnemy() {
		return specialEnemy;
	}
	public void setSpecialEnemy(boolean specialEnemy) {
		this.specialEnemy = specialEnemy;
	}
	
	public boolean wasActivated() {
		return wasActivated;
	}
	public void setWasActivated(boolean activated) {
		this.wasActivated = activated;
	}
	
	public boolean getIsRelentless() {
		return isRelentless;
	}
	public void setIsRelentless(boolean isRelentless) {
		this.isRelentless = isRelentless;
	}
	
	public String toString(){
		String text;
//		System.out.println("This Enemy contains: \n"+ "Name: "+getName()+"\nLife: "+getLife()+"\nAttack Type: "+ getAttackType().toString()+
//		"\nAttack Value: "+getAttackValue()+"\nPhysical Resistance: "+getPhysicalRes()+"\nMagical Resistance: "+getMagicalRes()+
//		"\nResolve: "+getResolve()+"\nEnemy Type: "+getEnemyType().toString()+"\nEnemy Keywords: "+getEnemyKeyword().toString()+
//		"\nVunerabilities: "+getVunerability().toString());
		text = "This Enemy contains: \n"+ "Name: "+getName()+"\nLife: "+getLife()+"\nAttack Type: "+ getAttackType().toString()+
				"\nAttack Value: "+getAttackValue()+"\nPhysical Resistance: "+getPhysicalRes()+"\nMagical Resistance: "+getMagicalRes()+
				"\nResolve: "+getResolve()+"\nEnemy Type: "+getEnemyType().toString()+"\nEnemy Keywords: "+getEnemyKeyword().toString()+
				"\nVunerabilities: "+getVunerability().toString();
		return text;
	}
	public boolean isSlowed() {
		return isSlowed;
	}
	public void setIsSlowed(boolean isSlowed) {
		this.isSlowed = isSlowed;
	}
	public Conditions getConditions() {
		return conditions;
	}
	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}

	
	/**
	 * @param woundCount
	 * @param gc
	 * @return true if the enemy died
	 * 
	 * Removes the specified "woundCount" value from the current life of the enemy
	 * if the enemy has zero or less life left, the resolve count is added to the resolve pool
	 */
	public boolean applyWounds(int woundCount, GameController gc){
		boolean enemyDead=false;
		// Apply Wounds
		setLife(getLife()-woundCount);
		// Check if the Enemy died
		if (getLife()<=0){
			// Reward Resolve
			gc.getGameSetupController().changeResolvePool(resolve);
			enemyDead=true;
		}
		
		return enemyDead;
	}
	
	
}
