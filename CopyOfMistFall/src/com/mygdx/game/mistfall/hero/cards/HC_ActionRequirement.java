package com.mygdx.game.mistfall.hero.cards;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionRequirementKeyword;
import com.mygdx.game.mistfall.model.enums.Keyword;

public class HC_ActionRequirement {

	
	private HC_ActionRequirementKeyword requirement;
	private int value;
	private List<Keyword> keyword_HC;
	private HC_Area area;
	
	

	public HC_ActionRequirement() {

		keyword_HC = new LinkedList<Keyword>();

	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public HC_Area getArea() {
		return area;
	}
	public void setArea(HC_Area area) {
		this.area = area;
	}
	public HC_ActionRequirementKeyword getRequirement() {
		return requirement;
	}
	public void setRequirement(HC_ActionRequirementKeyword requirement) {
		this.requirement = requirement;
	}
	public List<Keyword> getKeyword_HC() {
		return keyword_HC;
	}
	public void setKeyword_HC(List<Keyword> keyword_HC) {
		this.keyword_HC = keyword_HC;
	}
	
	
	
}
