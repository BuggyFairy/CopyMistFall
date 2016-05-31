package com.mygdx.game.mistfall.model;

import com.mygdx.game.mistfall.hero.cards.enums.HC_ActionType;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;

public class AbilityInformation {
	
	private HC_Area cardArea;
	private int range;
	private HC_ActionType abilityType;
	
	
	public AbilityInformation(HC_Area cardArea, int range, HC_ActionType abilityType){
		this.cardArea=cardArea;
		this.range=range;
		this.abilityType=abilityType;
	}
	
	
	public HC_Area getCardArea() {
		return cardArea;
	}
	public void setCardArea(HC_Area cardArea) {
		this.cardArea = cardArea;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public HC_ActionType getAbilityType() {
		return abilityType;
	}
	public void setAbilityType(HC_ActionType abilityType) {
		this.abilityType = abilityType;
	}
	
	

}
