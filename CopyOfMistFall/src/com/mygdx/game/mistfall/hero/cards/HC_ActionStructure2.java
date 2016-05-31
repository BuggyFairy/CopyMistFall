package com.mygdx.game.mistfall.hero.cards;

import java.util.LinkedList;
import java.util.List;

public class HC_ActionStructure2 {

	// {{ Attributes
	private List<HC_ActionStructure3> choices;
	private int choiceChosenPos;
	
	private boolean necessity;
	private boolean conditionsMet;
	// }}
	
	// {{ Constructor
	public HC_ActionStructure2() {
		choices = new LinkedList<HC_ActionStructure3>();
		setChoiceChosenPos(0);
	}
	// }}
	
	// {{ Getters and Setters
	public List<HC_ActionStructure3> getChoices() {
		return choices;
	}

	public void setChoices(List<HC_ActionStructure3> choices) {
		this.choices = choices;
	}

	public boolean isNecessity() {
		return necessity;
	}

	public void setNecessity(boolean necessity) {
		this.necessity = necessity;
	}

	public boolean isConditionsMet() {
		return conditionsMet;
	}

	public void setConditionsMet(boolean conditionsMet) {
		this.conditionsMet = conditionsMet;
	}

	public int getChoiceChosenPos() {
		return choiceChosenPos;
	}
	public void setChoiceChosenPos(int choiceChosenPos) {
		this.choiceChosenPos = choiceChosenPos;
	}
	
	public HC_ActionStructure3 getChoiceChosen(){
		return choices.get(choiceChosenPos);
	}
	// }}
	
	// {{ Other Methods
	
	// }}
}
