package com.mygdx.game.mistfall.hero.cards;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.hero.cards.enums.HC_AreaRestriction;
import com.mygdx.game.mistfall.hero.cards.enums.HC_IdentifierEnum;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Type;
import com.mygdx.game.mistfall.model.enums.Keyword;

public class HeroCard {

	// {{ Attributes
	private String name;
	private int ID;
	private HC_IdentifierEnum cardEnum;
	
	private List<HC_Action> actions;
	private List<Keyword> keywords;
	private HC_Type heroCardType;
	private int resolveCost;
	private HC_AreaRestriction areaRestriction;
	
	private boolean inUse;
	private boolean selected;
	
	private int discardValue;
	// }}
	
	// {{ Constructor
	public HeroCard() {

		actions = new LinkedList<HC_Action>();
		keywords = new LinkedList<Keyword>();
		inUse = false;
		selected = false;
	}
	// }}
	
	// {{ Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<HC_Action> getActions() {
		return actions;
	}
	public void setActions(List<HC_Action> actions) {
		this.actions = actions;
	}
	public HC_Type getHeroCardType() {
		return heroCardType;
	}
	public void setHeroCardType(HC_Type heroCardType) {
		this.heroCardType = heroCardType;
	}
	public int getResolveCost() {
		return resolveCost;
	}
	public void setResolveCost(int resolveCost) {
		this.resolveCost = resolveCost;
	}
	public List<Keyword> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public boolean isInUse() {
		return inUse;
	}
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public HC_AreaRestriction getAreaRestriction() {
		return areaRestriction;
	}

	public void setAreaRestriction(HC_AreaRestriction areaRestriction) {
		this.areaRestriction = areaRestriction;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getDiscardValue() {
		return discardValue;
	}

	public void setDiscardValue(int discardValue) {
		this.discardValue = discardValue;
	}

	public HC_IdentifierEnum getCardEnum() {
		return cardEnum;
	}

	public void setCardEnum(HC_IdentifierEnum cardEnum) {
		this.cardEnum = cardEnum;
	}

	// }}

	// {{ Other Methods
	
	// }}

}
