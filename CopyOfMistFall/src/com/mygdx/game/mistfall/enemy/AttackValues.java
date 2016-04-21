package com.mygdx.game.mistfall.enemy;

import com.mygdx.game.mistfall.model.enums.AttackType;

public class AttackValues {

	private int valueBase;
	private int valueMod;
	private AttackType type;

	
	
	public int getValueBase() {
		return valueBase;
	}
	public void setValueBase(int valueBase) {
		this.valueBase = valueBase;
	}
	public int getValueMod() {
		return valueMod;
	}
	public void setValueMod(int valueMod) {
		this.valueMod = valueMod;
	}
	public AttackType getType() {
		return type;
	}
	public void setType(AttackType type) {
		this.type = type;
	}
	
}
