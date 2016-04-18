package com.mygdx.game.mistfall.model;

public class Conditions {
	
	private int burning;
	private int poison;
	private int daze;
	private int weakness;
	private int conditionCount;
	
	
	public int getBurning() {
		return burning;
	}
	public void addBurning(int count) {
		burning = burning+count;
	}
	public boolean removeBurning(int count) {
		burning = burning-count;
		if (burning>=0){
			return true;
		}
		else{
			burning=0;
			return false;
		}	
	}
	
	
	public int getPoison() {
		return poison;
	}
	public void addPoison(int count) {
		poison = poison+count;
	}
	public boolean removePoison(int count) {
		poison = poison-count;
		if (poison>=0){
			return true;
		}
		else{
			poison=0;
			return false;
		}	
	}
	
	
	public int getDaze() {
		return daze;
	}
	public void addDaze(int count) {
		daze = daze+count;
	}
	public boolean removeDaze(int count) {
		daze = daze-count;
		if (daze>=0){
			return true;
		}
		else{
			daze=0;
			return false;
		}	
	}
	
	
	public int getWeakness() {
		return weakness;
	}
	public void addWeakness(int count) {
		weakness = weakness+count;
	}
	public boolean removeWeakness(int count) {
		weakness = weakness-count;
		if (weakness>=0){
			return true;
		}
		else{
			weakness=0;
			return false;
		}	
	}
	
	
	public int getConditionCount() {
		conditionCount=burning+poison+daze+weakness;
		return conditionCount;
	}	
	
	
	
	public Conditions() {
		burning=0;
		poison=0;
		daze=0;
		weakness=0;
		conditionCount=0;
	}
	
	

}
