package com.mygdx.game.mistfall.enemy;

import java.util.LinkedList;

import com.mygdx.game.mistfall.model.Card;
import com.mygdx.game.mistfall.model.enums.AttackType;
import com.mygdx.game.mistfall.model.enums.EnemyKeyword;
import com.mygdx.game.mistfall.model.enums.EnemyType;
import com.mygdx.game.mistfall.model.enums.Vulnerability;

public class Enemy extends Card {
	
	private EnemyType enemyType;
	private AttackType attackType;
//	private EnemyKeyword enemyKeyword;
//	private Vunerability vunerability;
	private LinkedList<EnemyKeyword> enemyKeyword;
	private LinkedList<Vulnerability> vulnerability;
	private int attackValue;
	private int physicalRes;
	private int magicalRes;
	private int life;
	private int resolve;
	private boolean specialEnemy;
	private boolean wasActivated;
	
	
	
	
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
	
	
	
	
//	public EnemyKeyword getEnemyKeyword() {
//		return enemyKeyword;
//	}
//	public void setEnemyKeyword(EnemyKeyword enemyKeyword) {
//		this.enemyKeyword = enemyKeyword;
//	}
//	public Vunerability getVunerability() {
//		return vunerability;
//	}
//	public void setVunerability(Vunerability vunerability) {
//		this.vunerability = vunerability;
//	}
	
	
	
	
	
	
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
	public boolean wasActivated() {
		return wasActivated;
	}
	public void setWasActivated(boolean activated) {
		this.wasActivated = activated;
	}
	
	
}