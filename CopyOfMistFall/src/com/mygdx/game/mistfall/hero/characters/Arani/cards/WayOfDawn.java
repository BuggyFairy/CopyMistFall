package com.mygdx.game.mistfall.hero.characters.Arani.cards;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.HeroCard;
import com.mygdx.game.mistfall.hero.Reflexes;
import com.mygdx.game.mistfall.model.enums.CardArea;
import com.mygdx.game.mistfall.model.enums.FeatKeyword;
import com.mygdx.game.mistfall.model.enums.HeroCardType;

public class WayOfDawn extends HeroCard{

	public WayOfDawn(){
		
		setName("WAY OF DAWN");
		setResolveCost(4);
		setCardType(HeroCardType.ADVANCED);
		setFeatKeyword(new LinkedList<FeatKeyword>());	
		appendFeatKeyword(FeatKeyword.BLESSING);
		appendFeatKeyword(FeatKeyword.DIVINE);
		appendFeatKeyword(FeatKeyword.FLAME);
		appendFeatKeyword(FeatKeyword.HOLY);
		
	}
	
	
	// TESTING CASE !!!!!
	public void reflex(Hero hero){
		
		Reflexes.reflexDrawCards(this, hero, CardArea.DISCARD, 0, 1);
		System.out.println("i hold :"+hero.getHand().getCards().size()+" in my hands");
		System.out.println("i hold :"+hero.getDiscardPile().getCards().size()+" in my discardpile");
		System.out.println("i hold :"+hero.getDeck().getCards().size()+" in my deck\n");
	}
	
	
}
