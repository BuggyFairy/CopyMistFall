package com.mygdx.game.mistfall.hero.cards;

import java.util.LinkedList;
import java.util.List;

public class HC_ActionStructure3 {

	// {{ Attributes
	private List<HC_ActionImpact> impacts;
	private List<HC_ActionRequirement> requirements;

	private List<HeroCard> discardedCards;
	private int discardedCardsCount;
	// }}
	
	// {{ Constructor
	public HC_ActionStructure3() {
		discardedCardsCount = 0;
		discardedCards = new LinkedList<HeroCard>();
		impacts = new LinkedList<HC_ActionImpact>();
		requirements = new LinkedList<HC_ActionRequirement>();
	}
	// }}

	// {{ Getters & Setters
	public List<HC_ActionImpact> getImpacts() {
		return impacts;
	}
	public void setImpacts(List<HC_ActionImpact> impacts) {
		this.impacts = impacts;
	}
	public List<HC_ActionRequirement> getRequirements() {
		return requirements;
	}
	public void setRequirements(List<HC_ActionRequirement> requirements) {
		this.requirements = requirements;
	}
	public List<HeroCard> getDiscardedCards() {
		return discardedCards;
	}
	public void setDiscardedCards(List<HeroCard> discardedCards) {
		this.discardedCards = discardedCards;
	}
	public int getDiscardedCardsCount() {
		return discardedCardsCount;
	}
	public void setDiscardedCardsCount(int discardedCardsCount) {
		this.discardedCardsCount = discardedCardsCount;
	}
	// }}
	
	// {{ Other Methods
	/**
	 * Adds the specified Hero card to the list of discarded cards and updates the discard count.
	 */
	public void addDiscardedCard(HeroCard card, int maxDiscardCount){
		discardedCards.add(card);
		discardedCardsCount=discardedCardsCount+card.getDiscardValue();
		if (discardedCardsCount>maxDiscardCount){
			discardedCardsCount=maxDiscardCount;
		}
	}
	
	/**
	 * Removes the specified Hero card from the list of discarded cards and updates the discard count.
	 */
	public void removeDiscardedCard(HeroCard card){
		discardedCards.remove(card);
		discardedCardsCount=0;
		for (int i = 0; i < discardedCards.size(); i++){
			discardedCardsCount=discardedCardsCount+discardedCards.get(i).getDiscardValue();
		}
	}
	// }}


	
	
}
