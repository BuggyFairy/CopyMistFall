package com.mygdx.game.mistfall.hero.characters.Arani.cards;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.Reflexes;
import com.mygdx.game.mistfall.hero.cards.HeroCard;
import com.mygdx.game.mistfall.hero.cards.enums.HC_ActionType;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Type;
import com.mygdx.game.mistfall.model.AbilityInformation;
import com.mygdx.game.mistfall.model.enums.FeatKeyword;

public class Sacrifice extends HeroCard{

	public Sacrifice(){
		
		setName("SACRIFICE");
		setResolveCost(0);
		setCardType(HC_Type.BASIC);
		setFeatKeyword(new LinkedList<FeatKeyword>());	
		appendFeatKeyword(FeatKeyword.DIVINE);
		appendFeatKeyword(FeatKeyword.HOLY);
		
		setAbilityInformation(new LinkedList<AbilityInformation>());
		getAbilityInformation().add(new AbilityInformation(HC_Area.HAND, 2, HC_ActionType.REFLEX));
		
	}
	
	public void reflex(Hero hero){
		
		//TODO: add action condition check foreign hero discardpiles
		if((this.getActualLocation() == HC_Area.HAND) && ((hero.getHand().getCards().size() >= 2) || (hero.getDiscardPile().getCards().isEmpty() == false))){
			Reflexes.reflexMoveForeignCard(this, hero);
		}
	}
}
