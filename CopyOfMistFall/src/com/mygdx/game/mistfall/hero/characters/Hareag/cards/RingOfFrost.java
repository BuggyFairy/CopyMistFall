package com.mygdx.game.mistfall.hero.characters.Hareag.cards;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.HeroAreaRestriction;
import com.mygdx.game.mistfall.hero.HeroCard;
import com.mygdx.game.mistfall.hero.Reflexes;
import com.mygdx.game.mistfall.model.AbilityInformation;
import com.mygdx.game.mistfall.model.enums.AbilityType;
import com.mygdx.game.mistfall.model.enums.CardArea;
import com.mygdx.game.mistfall.model.enums.GearKeyword;
import com.mygdx.game.mistfall.model.enums.HeroCardType;

public class RingOfFrost extends HeroCard {
	
	public RingOfFrost(){
		setName("RING OF FROST");
		setResolveCost(2);
		setCardType(HeroCardType.REWARD);
		setGearKeyword(new LinkedList<GearKeyword>());	
		appendGearKeyword(GearKeyword.ARCANE);
		appendGearKeyword(GearKeyword.GEAR);
		appendGearKeyword(GearKeyword.REWARD);
		appendGearKeyword(GearKeyword.RING);
		appendGearKeyword(GearKeyword.TRINKET);
		
		setAreaRestriction(new HeroAreaRestriction('E', 3, 0));
		
		setAbilityInformation(new LinkedList<AbilityInformation>());
		getAbilityInformation().add(new AbilityInformation(CardArea.HAND, 1, AbilityType.FAST));
		getAbilityInformation().add(new AbilityInformation(CardArea.EQUIPMENT, 1, AbilityType.REFLEX));
		
	}
	
	//TODO: Can Only be used if a Frost Card was drawn 
	public void reflex(Hero hero){
		
		if((this.getActualLocation() == CardArea.HAND) && (hero.getDeck().getCards().isEmpty() == false)){
			
			Reflexes.reflexDrawCards(this, hero, CardArea.DISCARD, 0, 3);
			
		}
		
	}

}
