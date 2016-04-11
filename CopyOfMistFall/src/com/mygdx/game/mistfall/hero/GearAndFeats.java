package com.mygdx.game.mistfall.hero;

import java.util.LinkedList;
import java.util.List;

public class GearAndFeats {
private List<HeroCard> cards;
	
	public GearAndFeats(){
		this.cards=new LinkedList<HeroCard>();
	}
	
	public List<HeroCard> getCards() {
		return cards;
	}

	public void setCards(List<HeroCard> cards) {
		this.cards = cards;
	}

}
