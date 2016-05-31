package com.mygdx.game.mistfall.hero.cards;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.hero.cards.enums.HC_ActionType;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionKeyword_1;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionKeyword_2;

public class HC_Action {
	
//	private int ID;
	
	private HC_ActionType type;
	private int range;
	private HC_Area cardArea;
	private boolean sourceAction;

	private HC_ActionKeyword_1 actionKeyword1;
	private HC_ActionKeyword_2 actionKeyword2;
	private String text;
	
	private List<HC_ActionStructure2> options;
	
	private HC_Area destAfterUse;
	
	private int errorCode;
	
	
	
	
	
	
	public HC_Action() {
		options = new LinkedList<HC_ActionStructure2>();
		errorCode=0;
	}



//	public int getID() {
//		return ID;
//	}
//	public void setID(int iD) {
//		ID = iD;
//	}
	public HC_ActionType getType() {
		return type;
	}
	public void setType(HC_ActionType type) {
		this.type = type;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public HC_Area getCardArea() {
		return cardArea;
	}
	public void setCardArea(HC_Area cardArea) {
		this.cardArea = cardArea;
	}
	public HC_ActionKeyword_1 getActionKeyword1() {
		return actionKeyword1;
	}
	public void setActionKeyword1(HC_ActionKeyword_1 actionKeyword1) {
		this.actionKeyword1 = actionKeyword1;
	}
	public HC_ActionKeyword_2 getActionKeyword2() {
		return actionKeyword2;
	}
	public void setActionKeyword2(HC_ActionKeyword_2 actionKeyword2) {
		this.actionKeyword2 = actionKeyword2;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<HC_ActionStructure2> getOptions() {
		return options;
	}
	public void setOptions(List<HC_ActionStructure2> options) {
		this.options = options;
	}
	public HC_Area getDestAfterUse() {
		return destAfterUse;
	}
	public void setDestAfterUse(HC_Area destAfterUse) {
		this.destAfterUse = destAfterUse;
	}



	public int getErrorCode() {
		return errorCode;
	}



	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}



	public boolean isSourceAction() {
		return sourceAction;
	}



	public void setSourceAction(boolean sourceAction) {
		this.sourceAction = sourceAction;
	}

	
}
