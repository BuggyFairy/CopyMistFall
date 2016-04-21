package com.mygdx.game.mistfall.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.HeroCard;
import com.mygdx.game.mistfall.model.enums.CardArea;

public class CardController {

	
	public static HeroCard pickCard(Hero performer, List<HeroCard> cards, List<HeroCard> cards2, Object object,
			Object object2, Object object3) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * @param hero
	 * @param card
	 * @param dest
	 * @param topDeck (false == Bottom of the Deck, true  == Top of the Deck)
	 * @return returns true if the card Position was changed successfully
	 * 
	 * Changes the position of a specified "card" of a specified "hero" from the "card" position to the specified "destination"
	 */
	public static boolean changeCardPosition(Hero hero, HeroCard card, CardArea dest, boolean topDeck){
		
		boolean cardPositionChanged=false;
		
		switch (card.getActualLocation())
		{
			case HAND:
				switch (dest)
				{
					case BURIAL:
						card.setActualLocation(CardArea.BURIAL);
						hero.getBurialPile().getCards().add(hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
						cardPositionChanged=true;
						
					break;
					case DISCARD:
						card.setActualLocation(CardArea.DISCARD);
						hero.getDiscardPile().getCards().add(hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
						cardPositionChanged=true;
					break;
					case DECK:
						if(topDeck == false){
							hero.getDeck().getCards().add(hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
							card.setActualLocation(CardArea.DECK);
							cardPositionChanged=true;
						}
						else{
							hero.getDeck().getCards().add(0, hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
							card.setActualLocation(CardArea.DECK);
							cardPositionChanged=true;
						}
					break;
					case EQUIPMENT:
						card.setActualLocation(CardArea.EQUIPMENT);
						hero.getGearAndFeats().getCards().add(hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
						cardPositionChanged=true;
					break;
					default:
					break;
				}
				hero.getHand().getCards().remove((hero.getHand().getCards().indexOf(card)));
			break;
			case BURIAL:
				switch (dest)
				{
					case HAND:
						card.setActualLocation(CardArea.HAND);
						hero.getHand().getCards().add(hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));	
						cardPositionChanged=true;
					break;
					case DISCARD:
						card.setActualLocation(CardArea.DISCARD);
						hero.getDiscardPile().getCards().add(hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));	
						cardPositionChanged=true;
					break;
					case DECK:
						
						if(topDeck == false){
							hero.getDeck().getCards().add(hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));	
							card.setActualLocation(CardArea.DECK);
							cardPositionChanged=true;
						}
						else
						{
							hero.getDeck().getCards().add(0, hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));
							card.setActualLocation(CardArea.DECK);
							cardPositionChanged=true;
						}
					break;
					case EQUIPMENT:
						card.setActualLocation(CardArea.EQUIPMENT);
						hero.getGearAndFeats().getCards().add(hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));	
						cardPositionChanged=true;
					break;
					default:
					break;
				}
				hero.getBurialPile().getCards().remove((hero.getBurialPile().getCards().indexOf(card)));
			break;
			case DISCARD:
				switch (dest)
				{
					case BURIAL:
						card.setActualLocation(CardArea.BURIAL);
						hero.getBurialPile().getCards().add(hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));
						cardPositionChanged=true;
					break;
					case HAND:
						card.setActualLocation(CardArea.HAND);
						hero.getHand().getCards().add(hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));	
						cardPositionChanged=true;
					break;
					case DECK:						
						if(topDeck == false){
							hero.getDeck().getCards().add(hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));
							card.setActualLocation(CardArea.DECK);
							cardPositionChanged=true;
						}
						else{
							hero.getDeck().getCards().add(0, hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));
							card.setActualLocation(CardArea.DECK);
							cardPositionChanged=true;
						}
					break;
					case EQUIPMENT:
						card.setActualLocation(CardArea.EQUIPMENT);
						hero.getGearAndFeats().getCards().add(hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));
						cardPositionChanged=true;
					break;
					default:
					break;
				}
				hero.getDiscardPile().getCards().remove((hero.getDiscardPile().getCards().indexOf(card)));
			break;
			case DECK:
				switch (dest)
				{
					case BURIAL:
						card.setActualLocation(CardArea.BURIAL);
						hero.getBurialPile().getCards().add(hero.getDeck().getCards().get(hero.getDeck().getCards().indexOf(card)));	
						cardPositionChanged=true;
					break;
					case DISCARD:
						card.setActualLocation(CardArea.DISCARD);
						hero.getDiscardPile().getCards().add(hero.getDeck().getCards().get(hero.getDeck().getCards().indexOf(card)));	
						cardPositionChanged=true;
					break;
					case HAND:
						card.setActualLocation(CardArea.HAND);
						hero.getHand().getCards().add(hero.getDeck().getCards().get(hero.getDeck().getCards().indexOf(card)));	
						cardPositionChanged=true;
					break;
					case EQUIPMENT:
						card.setActualLocation(CardArea.EQUIPMENT);
						hero.getGearAndFeats().getCards().add(hero.getDeck().getCards().get(hero.getDeck().getCards().indexOf(card)));	
						cardPositionChanged=true;
					break;
					default:
					break;
				}
				hero.getDeck().getCards().remove((hero.getDeck().getCards().indexOf(card)));
			break;
			case EQUIPMENT:
				switch (dest)
				{
					case BURIAL:
						card.setActualLocation(CardArea.BURIAL);
						hero.getBurialPile().getCards().add(hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));	
						cardPositionChanged=true;
					break;
					case DISCARD:
						card.setActualLocation(CardArea.DISCARD);
						hero.getDiscardPile().getCards().add(hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));
						cardPositionChanged=true;
					break;
					case DECK:
						if(topDeck == false){
							hero.getDeck().getCards().add(hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));
							card.setActualLocation(CardArea.DECK);
							cardPositionChanged=true;
						}
						else{
							hero.getDeck().getCards().add(0, hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));
							card.setActualLocation(CardArea.DECK);
							cardPositionChanged=true;
						}
					break;
					case HAND:
						card.setActualLocation(CardArea.HAND);
						hero.getHand().getCards().add(hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));
						cardPositionChanged=true;
					break;
					default:
					break;	
				}
				hero.getGearAndFeats().getCards().remove((hero.getGearAndFeats().getCards().indexOf(card)));
			break;
			default:
				
			break;
		}
		return cardPositionChanged;	
	}

	

	/**
	 * @param source
	 * @return Shuffled List
	 * 
	 * This method takes a source list, which is shuffled and then returned
	 * You have to do a typecast!
	 */
	public static List<?> shuffleCards(List<?> source) {
		List<Object> dest = new LinkedList<Object>();
		Random random = new Random(System.currentTimeMillis());
		int randomInt;
		while (source.isEmpty()==false){
			randomInt=random.nextInt(source.size());
			dest.add(source.get(randomInt));
			source.remove(randomInt);
		}
		
		return dest;

	}
	
	/**
	 * @param hero
	 * @return returns true if the hero was able to draw until the draw limit was reached
	 * 
	 * Draws cards from the top of the deck of the specified "hero" until the deck is empty or
	 * the draw limit was reached. Considers the special Ability "Does not count against the draw limit"
	 */
	public static boolean drawToDrawLimit(Hero hero){
		int handCountToLimit=0;
		// Get Count of Cards in the Hand which count against the draw limit
		for (HeroCard c: hero.getHand().getCards()){
			if (c.getCountsAgainstDrawLimit()){
				handCountToLimit++;
			}
		}
		// Draw Cards from the Deck into the Hand as long as the draw limit is not reached
		// or their are no more cards in the Deck
		while (handCountToLimit<hero.getDrawLimit() && hero.getDeck().getCards().isEmpty()==false){
			// Draw Card
			changeCardPosition(hero, hero.getDeck().getCards().get(0),CardArea.HAND, false);
			// Update draw limit count
			if (hero.getHand().getCards().get(hero.getHand().getCards().size()-1).getCountsAgainstDrawLimit()){
				handCountToLimit++;
			}
		}
		if (handCountToLimit==hero.getDrawLimit()){
			return true;
		}
		else{
			return false;
		}
	}
	
}




