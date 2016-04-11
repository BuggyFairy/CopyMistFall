package com.mygdx.game.mistfall.hero.characters.Celenthia.cards;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.HeroAreaRestriction;
import com.mygdx.game.mistfall.hero.HeroCard;
import com.mygdx.game.mistfall.hero.Reflexes;
import com.mygdx.game.mistfall.model.AbilityInformation;
import com.mygdx.game.mistfall.model.enums.AbilityType;
import com.mygdx.game.mistfall.model.enums.CardArea;
import com.mygdx.game.mistfall.model.enums.FeatKeyword;
import com.mygdx.game.mistfall.model.enums.HeroCardType;

public class ArcaneWisdom extends HeroCard{

	public ArcaneWisdom(){
		setName("ARCANE WISDOM");
		setResolveCost(3);
		setCardType(HeroCardType.ADVANCED);
		setFeatKeyword(new LinkedList<FeatKeyword>());	
		appendFeatKeyword(FeatKeyword.ARCANE);
		appendFeatKeyword(FeatKeyword.ENCHANTMENT);
		
		setAreaRestriction(new HeroAreaRestriction('#', Integer.MAX_VALUE, 0));
		
		setAbilityInformation(new LinkedList<AbilityInformation>());
		getAbilityInformation().add(new AbilityInformation(CardArea.HAND, 1, AbilityType.FAST));
		getAbilityInformation().add(new AbilityInformation(CardArea.EQUIPMENT, 2, AbilityType.REFLEX));
		
	}
	
	public void reflex(Hero hero){
		
		if(this.getActualLocation() == CardArea.EQUIPMENT){
			Reflexes.reflexPickDiscardPile(this, hero, CardArea.HAND, 3);
		}
		
	}
}
