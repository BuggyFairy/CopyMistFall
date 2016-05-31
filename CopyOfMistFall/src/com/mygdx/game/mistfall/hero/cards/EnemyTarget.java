package com.mygdx.game.mistfall.hero.cards;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionEffectTarget;
import com.mygdx.game.mistfall.model.Conditions;
import com.mygdx.game.mistfall.model.enums.AttackType;
import com.mygdx.game.mistfall.model.enums.Keyword;

public class EnemyTarget {
	
	// {{ Attributes
	private Enemy enemy;
	private int range;
	private AttackType attackType;
	private int attackValue;
	private Conditions conditions;
	private List<Keyword> attackKeywords;
	private HC_ActionEffectTarget targetKeyword;
	// }}
	
	// {{ Constructor
	public EnemyTarget(HC_ActionEffectTarget targetKeyword) {
		super();
		enemy=null;
		range = 0;
		attackType = AttackType.NONE;
		attackValue = 0;
		conditions = new Conditions();
		this.targetKeyword = targetKeyword;
		attackKeywords = new LinkedList<Keyword>();
	}
	// }}
	
	// {{ Getters & Setters
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
	public void addAttackValue(int attackValue) {
		this.attackValue=this.attackValue+attackValue;
	}
	public Conditions getConditions() {
		return conditions;
	}
	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public HC_ActionEffectTarget getTargetKeyword() {
		return targetKeyword;
	}
	public void setTargetKeyword(HC_ActionEffectTarget targetKeyword) {
		this.targetKeyword = targetKeyword;
	}
	public Enemy getEnemy() {
		return enemy;
	}
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	public List<Keyword> getAttackKeywords() {
		return attackKeywords;
	}
	public void setAttackKeywords(List<Keyword> attackKeywords) {
		this.attackKeywords = attackKeywords;
	}
	// }}
	
	// {{ Other Methods
	
	// }}
	

}
