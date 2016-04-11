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
	
	
	//topDeck - False == Bottom getLast() of the list
	//			TRUE  == TOP get(0) of the list	
	public static void changeCardPosition(Hero hero, HeroCard card, CardArea dest, boolean topDeck){
		
		switch (card.getActualLocation())
		{
			case HAND:
				switch (dest)
				{
					case BURIAL:
						card.setActualLocation(CardArea.BURIAL);
						hero.getBurialPile().getCards().add(hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
						
					break;
					case DISCARD:
						card.setActualLocation(CardArea.DISCARD);
						hero.getDiscardPile().getCards().add(hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
					break;
					case DECK:
						card.setActualLocation(CardArea.DECK);
						if(topDeck == false){
							hero.getDeck().getCards().add(hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
						}
						else{
							hero.getDeck().getCards().add(0, hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
						}
					break;
					case EQUIPMENT:
						card.setActualLocation(CardArea.EQUIPMENT);
						hero.getGearAndFeats().getCards().add(hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
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
					break;
					case DISCARD:
						card.setActualLocation(CardArea.DISCARD);
						hero.getDiscardPile().getCards().add(hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));	
					break;
					case DECK:
						card.setActualLocation(CardArea.DECK);
						if(topDeck == false){
							hero.getDeck().getCards().add(hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));	
						}
						else
						{
							hero.getDeck().getCards().add(0, hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));	
						}
					break;
					case EQUIPMENT:
						card.setActualLocation(CardArea.EQUIPMENT);
						hero.getGearAndFeats().getCards().add(hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));	
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
					break;
					case HAND:
						card.setActualLocation(CardArea.HAND);
						hero.getHand().getCards().add(hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));	
					break;
					case DECK:
						card.setActualLocation(CardArea.DECK);
						if(topDeck == false){
							hero.getDeck().getCards().add(hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));
						}
						else{
							hero.getDeck().getCards().add(0, hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));
						}
					break;
					case EQUIPMENT:
						card.setActualLocation(CardArea.EQUIPMENT);
						hero.getGearAndFeats().getCards().add(hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));	
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
					break;
					case DISCARD:
						card.setActualLocation(CardArea.DISCARD);
						hero.getDiscardPile().getCards().add(hero.getDeck().getCards().get(hero.getDeck().getCards().indexOf(card)));	
					break;
					case HAND:
						card.setActualLocation(CardArea.HAND);
						hero.getHand().getCards().add(hero.getDeck().getCards().get(hero.getDeck().getCards().indexOf(card)));	
					break;
					case EQUIPMENT:
						card.setActualLocation(CardArea.EQUIPMENT);
						hero.getGearAndFeats().getCards().add(hero.getDeck().getCards().get(hero.getDeck().getCards().indexOf(card)));	
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
					break;
					case DISCARD:
						card.setActualLocation(CardArea.DISCARD);
						hero.getDiscardPile().getCards().add(hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));
					break;
					case DECK:
						card.setActualLocation(CardArea.DECK);
						if(topDeck == false){
							hero.getDeck().getCards().add(hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));
						}
						else{
							hero.getDeck().getCards().add(0, hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));
						}
					break;
					case HAND:
						card.setActualLocation(CardArea.HAND);
						hero.getHand().getCards().add(hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));
					break;
					default:
					break;	
				}
				hero.getGearAndFeats().getCards().remove((hero.getGearAndFeats().getCards().indexOf(card)));
			break;
			default:
				
			break;
		}
			
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
	
	
	public static Enemy pickEnemyCard() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
