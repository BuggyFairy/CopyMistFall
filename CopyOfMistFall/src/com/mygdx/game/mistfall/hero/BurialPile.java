package com.mygdx.game.mistfall.hero;

import java.util.LinkedList;
import java.util.List;

public class BurialPile {

	private List<HeroCard> cards;
	
	public BurialPile(){
		this.cards = new LinkedList<HeroCard>();
	}
	
	public void addBurial(HeroCard card){
		cards.add(card);
	}

	public List<HeroCard> getCards() {
		return cards;
	}

	public void setCards(List<HeroCard> cards) {
		this.cards = cards;
	}
	
	public String toString(){
		String text = "";
		for(HeroCard c : cards){
			text += c.toString();
		}
		return text;
	}
	
}
