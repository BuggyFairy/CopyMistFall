package com.mygdx.game.mistfall.hero.characters.Celenthia.cards;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.HeroAreaRestriction;
import com.mygdx.game.mistfall.hero.Reflexes;
import com.mygdx.game.mistfall.hero.cards.HeroCard;
import com.mygdx.game.mistfall.hero.cards.enums.HC_ActionType;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Type;
import com.mygdx.game.mistfall.model.AbilityInformation;
import com.mygdx.game.mistfall.model.enums.FeatKeyword;

public class ArcaneWisdom extends HeroCard{

	public ArcaneWisdom(){
		setName("ARCANE WISDOM");
		setResolveCost(3);
		setCardType(HC_Type.ADVANCED);
		setFeatKeyword(new LinkedList<FeatKeyword>());	
		appendFeatKeyword(FeatKeyword.ARCANE);
		appendFeatKeyword(FeatKeyword.ENCHANTMENT);
		
		setAreaRestriction(new HeroAreaRestriction('#', Integer.MAX_VALUE, 0));
		
		setAbilityInformation(new LinkedList<AbilityInformation>());
		getAbilityInformation().add(new AbilityInformation(HC_Area.HAND, 1, HC_ActionType.FAST));
		getAbilityInformation().add(new AbilityInformation(HC_Area.HERO_AREA, 2, HC_ActionType.REFLEX));
		
	}
	
	public void reflex(Hero hero){
		
		if(this.getActualLocation() == HC_Area.HERO_AREA){
			Reflexes.reflexPickDiscardPile(this, hero, HC_Area.HAND, 3);
		}
		
	}
}
