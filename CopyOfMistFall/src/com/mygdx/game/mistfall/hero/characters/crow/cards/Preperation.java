package com.mygdx.game.mistfall.hero.characters.crow.cards;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.HeroCard;
import com.mygdx.game.mistfall.hero.Reflexes;
import com.mygdx.game.mistfall.model.AbilityInformation;
import com.mygdx.game.mistfall.model.enums.AbilityType;
import com.mygdx.game.mistfall.model.enums.CardArea;
import com.mygdx.game.mistfall.model.enums.FeatKeyword;
import com.mygdx.game.mistfall.model.enums.HeroCardType;

public class Preperation extends HeroCard{

	
	public Preperation(){
		setName("PREPERATION");
		setResolveCost(0);
		setCardType(HeroCardType.BASIC);
		setFeatKeyword(new LinkedList<FeatKeyword>());	
		appendFeatKeyword(FeatKeyword.COMBAT);
		appendFeatKeyword(FeatKeyword.EVASION);
		appendFeatKeyword(FeatKeyword.MOBILITY);
		appendFeatKeyword(FeatKeyword.PRECISION);
		appendFeatKeyword(FeatKeyword.STEALTH);
		
//		setAreaRestriction(new HeroAreaRestriction(null, null, null));
		
		setAbilityInformation(new LinkedList<AbilityInformation>());
		getAbilityInformation().add(new AbilityInformation(CardArea.HAND, 1, AbilityType.REFLEX));
		
	}
	
	public void reflex(Hero hero){
		
		if((this.getActualLocation() == CardArea.HAND) && (hero.getDeck().getCards().isEmpty() == false)){
			
			Reflexes.reflexDrawCards(this, hero, CardArea.DISCARD, 0, 1);
			
		}
		
	}
}