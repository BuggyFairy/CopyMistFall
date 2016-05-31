package com.mygdx.game.mistfall.hero;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.hero.cards.HeroCard;

public class Deck {
private List<HeroCard> cards;
	
	public Deck(){
		this.cards=new LinkedList<HeroCard>();
	}
	
	public List<HeroCard> getCards() {
		return cards;
	}

	public void setCards(List<HeroCard> cards) {
		this.cards = cards;
	}
	
}
