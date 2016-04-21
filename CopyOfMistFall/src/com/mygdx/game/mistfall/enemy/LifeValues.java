package com.mygdx.game.mistfall.enemy;

import java.util.LinkedList;

import com.mygdx.game.mistfall.model.modifications.Modification;

public class LifeValues {
	
	private int valueBase;
	private int valueMod;
	private int valueCurrent;
	private LinkedList<Modification> modificationsList;
	
	

	
	public LifeValues() {
		modificationsList = new LinkedList<Modification>();
	}




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
	public int getValueCurrent() {
		return valueCurrent;
	}
	public void setValueCurrent(int valueCurrent) {
		this.valueCurrent = valueCurrent;
	}
	public LinkedList<Modification> getModificationsList() {
		return modificationsList;
	}




	public int getWounds(){
		return (valueMod-valueCurrent);
	}
}
