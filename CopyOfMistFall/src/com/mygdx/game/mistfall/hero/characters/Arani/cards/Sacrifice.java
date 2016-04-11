package com.mygdx.game.mistfall.hero.characters.Arani.cards;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.HeroCard;
import com.mygdx.game.mistfall.hero.Reflexes;
import com.mygdx.game.mistfall.model.AbilityInformation;
import com.mygdx.game.mistfall.model.enums.AbilityType;
import com.mygdx.game.mistfall.model.enums.CardArea;
import com.mygdx.game.mistfall.model.enums.FeatKeyword;
import com.mygdx.game.mistfall.model.enums.HeroCardType;

public class Sacrifice extends HeroCard{

	public Sacrifice(){
		
		setName("SACRIFICE");
		setResolveCost(0);
		setCardType(HeroCardType.BASIC);
		setFeatKeyword(new LinkedList<FeatKeyword>());	
		appendFeatKeyword(FeatKeyword.DIVINE);
		appendFeatKeyword(FeatKeyword.HOLY);
		
		setAbilityInformation(new LinkedList<AbilityInformation>());
		getAbilityInformation().add(new AbilityInformation(CardArea.HAND, 2, AbilityType.REFLEX));
		
	}
	
	public void reflex(Hero hero){
		
		//TODO: add action condition check foreign hero discardpiles
		if((this.getActualLocation() == CardArea.HAND) && ((hero.getHand().getCards().size() >= 2) || (hero.getDiscardPile().getCards().isEmpty() == false))){
			Reflexes.reflexMoveForeignCard(this, hero);
		}
	}
}
