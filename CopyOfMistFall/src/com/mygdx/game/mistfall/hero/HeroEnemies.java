package com.mygdx.game.mistfall.hero;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.enemy.Enemy;

public class HeroEnemies {

	
private List<Enemy> cards;
	
	public HeroEnemies(){
		this.cards=new LinkedList<Enemy>();
	}
	
	public List<Enemy> getCards() {
		return cards;
	}

	public void setCards(List<Enemy> cards) {
		this.cards = cards;
	}
}
