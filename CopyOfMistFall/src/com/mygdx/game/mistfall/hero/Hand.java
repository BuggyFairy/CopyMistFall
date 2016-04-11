package com.mygdx.game.mistfall.hero;

import java.util.LinkedList;
import java.util.List;

public class Hand {

	private List<HeroCard> cards;
	
	public Hand(){
		this.cards=new LinkedList<HeroCard>();
	}
	
	public List<HeroCard> getCards() {
		return cards;
	}

	public void setCards(List<HeroCard> cards) {
		this.cards = cards;
	}
	
	
}
