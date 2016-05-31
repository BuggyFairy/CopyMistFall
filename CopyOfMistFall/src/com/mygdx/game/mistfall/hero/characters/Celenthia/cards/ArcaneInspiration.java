package com.mygdx.game.mistfall.hero.characters.Celenthia.cards;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.Reflexes;
import com.mygdx.game.mistfall.hero.cards.HeroCard;
import com.mygdx.game.mistfall.hero.cards.enums.HC_ActionType;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Type;
import com.mygdx.game.mistfall.model.AbilityInformation;
import com.mygdx.game.mistfall.model.enums.FeatKeyword;

public class ArcaneInspiration extends HeroCard {
	
	public ArcaneInspiration(){
		setName("ARCANE INSPIRATION");
		setResolveCost(0);
		setCardType(HC_Type.BASIC);
		setFeatKeyword(new LinkedList<FeatKeyword>());	
		appendFeatKeyword(FeatKeyword.ARCANE);
		appendFeatKeyword(FeatKeyword.FOCUS);
		
//		setAreaRestriction(new HeroAreaRestriction(null, null, null));
		
		setAbilityInformation(new LinkedList<AbilityInformation>());
		getAbilityInformation().add(new AbilityInformation(HC_Area.HAND, 1, HC_ActionType.REFLEX));
		
	}
	
	public void reflex(Hero hero){
		
		if((this.getActualLocation() == HC_Area.HAND) && (hero.getDeck().getCards().isEmpty() == false)){
			
			Reflexes.reflexDrawCards(this, hero, HC_Area.DISCARD, 0, 1);
			
		}
		
	}

}
