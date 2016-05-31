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

public class ScrollOfWisdom extends HeroCard{
	
	public ScrollOfWisdom(){
		setName("SCROLL OF WISDOM");
		setResolveCost(0);
		setCardType(HC_Type.GEAR);

		setGearKeyword(new LinkedList<GearKeyword>());	
		appendGearKeyword(GearKeyword.ARCANE);
		appendGearKeyword(GearKeyword.GEAR);
		appendGearKeyword(GearKeyword.ITEM);
		appendGearKeyword(GearKeyword.SCROLL);
		
		setAreaRestriction(new HeroAreaRestriction('U', 1, 0));
		
		setAbilityInformation(new LinkedList<AbilityInformation>());
		getAbilityInformation().add(new AbilityInformation(HC_Area.HAND, 1, HC_ActionType.FAST));
		getAbilityInformation().add(new AbilityInformation(HC_Area.HERO_AREA, 1, HC_ActionType.REFLEX));
		
	}
	
	public void reflex(Hero hero){
		
		if(this.getActualLocation() == HC_Area.HERO_AREA){
			Reflexes.reflexPickDiscardPile(this, hero, HC_Area.DECK, 3);
		}
		
	}
}
