package com.mygdx.game.mistfall.hero.cards;

import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;

public class HeroCardCache {
	private HeroCard heroCard;
	private HC_Area destination;
	private HC_Area source;
	public HeroCard getHeroCard() {
		return heroCard;
	}
	public void setHeroCard(HeroCard heroCard) {
		this.heroCard = heroCard;
	}
	public HC_Area getDestination() {
		return destination;
	}
	public void setDestination(HC_Area destination) {
		this.destination = destination;
	}
	public HC_Area getSource() {
		return source;
	}
	public void setSource(HC_Area source) {
		this.source = source;
	}
	
	

}
