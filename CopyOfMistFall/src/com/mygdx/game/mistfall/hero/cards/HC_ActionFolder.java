package com.mygdx.game.mistfall.hero.cards;

import com.mygdx.game.mistfall.hero.Hero;

public class HC_ActionFolder {
	
	private HeroCard card;
	private HC_Action action;
	private Hero hero;
	
	
	
	
	
	
	public void setAction(HeroCard card, HC_Action action, Hero hero){
		this.card=card;
		this.action=action;
		this.hero=hero;
	}
	
	public void removeAction (){
		card=null;
		action=null;
		setHero(null);
	}
	
	public boolean actionExistent(){
		if (action==null){
			return false;
		}
		return true;
	}
	
	
	
	public HeroCard getCard() {
		return card;
	}
	public HC_Action getAction() {
		return action;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}
}
