package com.mygdx.game.mistfall.hero.characters.Arani.cards;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.Reflexes;
import com.mygdx.game.mistfall.hero.cards.HeroCard;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Type;
import com.mygdx.game.mistfall.model.enums.FeatKeyword;

public class WayOfDawn extends HeroCard{

	public WayOfDawn(){
		
		setName("WAY OF DAWN");
		setResolveCost(4);
		setCardType(HC_Type.ADVANCED);
		setFeatKeyword(new LinkedList<FeatKeyword>());	
		appendFeatKeyword(FeatKeyword.BLESSING);
		appendFeatKeyword(FeatKeyword.DIVINE);
		appendFeatKeyword(FeatKeyword.FLAME);
		appendFeatKeyword(FeatKeyword.HOLY);
		
	}
	
	
	// TESTING CASE !!!!!
	public void reflex(Hero hero){
		
		Reflexes.reflexDrawCards(this, hero, HC_Area.DISCARD, 0, 1);
		System.out.println("i hold :"+hero.getHand().getCards().size()+" in my hands");
		System.out.println("i hold :"+hero.getDiscardPile().getCards().size()+" in my discardpile");
		System.out.println("i hold :"+hero.getDeck().getCards().size()+" in my deck\n");
	}
	
	
}
