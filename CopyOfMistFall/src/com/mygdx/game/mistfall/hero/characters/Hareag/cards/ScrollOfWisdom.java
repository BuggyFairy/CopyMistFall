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

public class ScrollOfWisdom extends HeroCard{
	
	public ScrollOfWisdom(){
		setName("SCROLL OF WISDOM");
		setResolveCost(0);
		setCardType(HeroCardType.GEAR);

		setGearKeyword(new LinkedList<GearKeyword>());	
		appendGearKeyword(GearKeyword.ARCANE);
		appendGearKeyword(GearKeyword.GEAR);
		appendGearKeyword(GearKeyword.ITEM);
		appendGearKeyword(GearKeyword.SCROLL);
		
		setAreaRestriction(new HeroAreaRestriction('U', 1, 0));
		
		setAbilityInformation(new LinkedList<AbilityInformation>());
		getAbilityInformation().add(new AbilityInformation(CardArea.HAND, 1, AbilityType.FAST));
		getAbilityInformation().add(new AbilityInformation(CardArea.EQUIPMENT, 1, AbilityType.REFLEX));
		
	}
	
	public void reflex(Hero hero){
		
		if(this.getActualLocation() == CardArea.EQUIPMENT){
			Reflexes.reflexPickDiscardPile(this, hero, CardArea.DECK, 3);
		}
		
	}
}
