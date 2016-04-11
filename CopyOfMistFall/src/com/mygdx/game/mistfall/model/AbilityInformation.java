package com.mygdx.game.mistfall.model;

import com.mygdx.game.mistfall.model.enums.AbilityType;
import com.mygdx.game.mistfall.model.enums.CardArea;

public class AbilityInformation {
	
	private CardArea cardArea;
	private int range;
	private AbilityType abilityType;
	
	
	public AbilityInformation(CardArea cardArea, int range, AbilityType abilityType){
		this.cardArea=cardArea;
		this.range=range;
		this.abilityType=abilityType;
	}
	
	
	public CardArea getCardArea() {
		return cardArea;
	}
	public void setCardArea(CardArea cardArea) {
		this.cardArea = cardArea;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public AbilityType getAbilityType() {
		return abilityType;
	}
	public void setAbilityType(AbilityType abilityType) {
		this.abilityType = abilityType;
	}
	
	

}
