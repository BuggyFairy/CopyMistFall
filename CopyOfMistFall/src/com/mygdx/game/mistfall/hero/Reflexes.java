package com.mygdx.game.mistfall.hero;

import com.mygdx.game.mistfall.controller.CardController;
import com.mygdx.game.mistfall.model.enums.CardArea;

public class Reflexes {

	/**Reflex used by following cards - heroes / ...
	 * 
	 * Arcane Inspiration - Celenthia / Hareag
	 * Toughness - Fengray
	 * Positioning
	 * Replenish
	 * Resilience
	 * Wrath Call
	 * Versatility
	 * Preperation
	 */
	public static void reflexDrawCards(HeroCard playedCard, Hero hero, CardArea dest, int focusChange, int drawCount){
		
		//draw card from the top of the deck
		for(int i=0 ; i<drawCount ; i++){
			if(!hero.getDeck().getCards().isEmpty()){
				CardController.changeCardPosition(hero, hero.getDeck().getCards().get(0), CardArea.HAND, false);
			}
		}
		//Move played card from source to dest after effect was resolved
		
		CardController.changeCardPosition(hero, playedCard, dest, false);
		
	}
	
	//Sacrifice
	public static void reflexMoveForeignCard(HeroCard playedCard, Hero performer){
	
//		Hero affected = PickHeroController.pickHero(null);
//		HeroCard affectedCard = CardController.pickCard(affected, affected.getDiscardPile().getCards(), null, null, null, null);
//		CardController.changeCardPosition(affected, affectedCard, CardArea.HAND, false);
//		HeroCard performerCard = CardController.pickCard(performer, performer.getHand().getCards(), performer.getDiscardPile().getCards(), null, null, null);
//		CardController.changeCardPosition(performer, performerCard,  CardArea.BURIAL, false);
//		CardController.changeCardPosition(performer, playedCard,  CardArea.DISCARD, false);
		
	}
	
	
	//Arcane Wisdom
	//Scroll of Wisdom
	public static void reflexPickDiscardPile(HeroCard playedCard, Hero performer, CardArea dest, int drawCount){
		HeroCard pickedCard;
		for(int i=0 ; i<drawCount ; i++){
			pickedCard = CardController.pickCard(performer, performer.getDiscardPile().getCards(), null, null, null, null);
			CardController.changeCardPosition(performer, pickedCard, dest, false);
		}
		CardController.changeCardPosition(performer, playedCard, CardArea.BURIAL, false);
	}
}
