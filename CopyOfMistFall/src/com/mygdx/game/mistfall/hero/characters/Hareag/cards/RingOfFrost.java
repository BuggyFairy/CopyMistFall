package com.mygdx.game.mistfall.hero.characters.Hareag.cards;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.HeroAreaRestriction;
import com.mygdx.game.mistfall.hero.Reflexes;
import com.mygdx.game.mistfall.hero.cards.HeroCard;
import com.mygdx.game.mistfall.hero.cards.enums.HC_ActionType;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Type;
import com.mygdx.game.mistfall.model.AbilityInformation;
import com.mygdx.game.mistfall.model.enums.GearKeyword;

public class RingOfFrost extends HeroCard {
	
	public RingOfFrost(){
		setName("RING OF FROST");
		setResolveCost(2);
		setCardType(HC_Type.REWARD);
		setGearKeyword(new LinkedList<GearKeyword>());	
		appendGearKeyword(GearKeyword.ARCANE);
		appendGearKeyword(GearKeyword.GEAR);
		appendGearKeyword(GearKeyword.REWARD);
		appendGearKeyword(GearKeyword.RING);
		appendGearKeyword(GearKeyword.TRINKET);
		
		setAreaRestriction(new HeroAreaRestriction('E', 3, 0));
		
		setAbilityInformation(new LinkedList<AbilityInformation>());
		getAbilityInformation().add(new AbilityInformation(HC_Area.HAND, 1, HC_ActionType.FAST));
		getAbilityInformation().add(new AbilityInformation(HC_Area.HERO_AREA, 1, HC_ActionType.REFLEX));
		
	}
	
	//TODO: Can Only be used if a Frost Card was drawn 
	public void reflex(Hero hero){
		
		if((this.getActualLocation() == HC_Area.HAND) && (hero.getDeck().getCards().isEmpty() == false)){
			
			Reflexes.reflexDrawCards(this, hero, HC_Area.DISCARD, 0, 3);
			
		}
		
	}

}
