package com.mygdx.game.mistfall.hero;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.model.AbilityInformation;
import com.mygdx.game.mistfall.model.enums.CardArea;
import com.mygdx.game.mistfall.model.enums.FeatKeyword;
import com.mygdx.game.mistfall.model.enums.GearKeyword;
import com.mygdx.game.mistfall.model.enums.HeroCardType;

public class HeroCard {

	private String name;
	private int resolveCost;
	private CardArea actualLocation;
	private HeroCardType cardType;
	private List<FeatKeyword> featKeyword;
	private List<GearKeyword> gearKeyword;
	private HeroAreaRestriction areaRestriction;	
	private List<AbilityInformation> abilityInformation;
	
	
	public int getResolveCost() {
		return resolveCost;
	}
	public void setResolveCost(int resolveCost) {
		this.resolveCost = resolveCost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HeroCardType getCardType() {
		return cardType;
	}
	public void setCardType(HeroCardType cardType) {
		this.cardType = cardType;
	}
	public List<FeatKeyword> getFeatKeyword() {
		return featKeyword;
	}
	public void setFeatKeyword(List<FeatKeyword> featKeyword) {
		this.featKeyword = featKeyword;
	}
	public void appendFeatKeyword(FeatKeyword fk){
		if(featKeyword != null){
			this.featKeyword.add(fk);
		}
	}
	public List<GearKeyword> getGearKeyword() {
		return gearKeyword;
	}
	public void setGearKeyword(List<GearKeyword> gearKeyword) {
		this.gearKeyword = gearKeyword;
	}
	public void appendGearKeyword(GearKeyword gk){
		if(gearKeyword != null){
			this.gearKeyword.add(gk);
		}
	}
	public HeroAreaRestriction getAreaRestriction() {
		return areaRestriction;
	}
	public void setAreaRestriction(HeroAreaRestriction areaRestriction) {
		this.areaRestriction = areaRestriction;
	}
	public List<AbilityInformation> getAbilityInformation() {
		return abilityInformation;
	}
	public void setAbilityInformation(List<AbilityInformation> list) {
		this.abilityInformation = list;
	}
	
	public CardArea getActualLocation() {
		return actualLocation;
	}
	public void setActualLocation(CardArea actualLocation) {
		this.actualLocation = actualLocation;
	}
	
	//topDeck - False == Bottom getLast() of the list
	//			TRUE  == TOP get(0) of the list
//	public void changeCardPosition(Hero hero, HeroCard card, CardArea dest, boolean topDeck){
//		
//		switch (card.getActualLocation())
//		{
//			case HAND:
//				switch (dest)
//				{
//					case BURIAL:
//						card.setActualLocation(CardArea.BURIAL);
//						hero.getBurialPile().getCards().add(hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
//						
//					break;
//					case DISCARD:
//						card.setActualLocation(CardArea.DISCARD);
//						hero.getDiscardPile().getCards().add(hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
//					break;
//					case DECK:
//						card.setActualLocation(CardArea.DECK);
//						if(topDeck == false){
//							hero.getDeck().getCards().add(hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
//						}
//						else{
//							hero.getDeck().getCards().add(0, hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
//						}
//					break;
//					case EQUIPMENT:
//						card.setActualLocation(CardArea.EQUIPMENT);
//						hero.getGearAndFeats().getCards().add(hero.getHand().getCards().get(hero.getHand().getCards().indexOf(card)));
//					break;
//					default:
//					break;
//				}
//				hero.getHand().getCards().remove((hero.getHand().getCards().indexOf(card)));
//			break;
//			case BURIAL:
//				switch (dest)
//				{
//					case HAND:
//						card.setActualLocation(CardArea.HAND);
//						hero.getHand().getCards().add(hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));	
//					break;
//					case DISCARD:
//						card.setActualLocation(CardArea.DISCARD);
//						hero.getDiscardPile().getCards().add(hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));	
//					break;
//					case DECK:
//						card.setActualLocation(CardArea.DECK);
//						if(topDeck == false){
//							hero.getDeck().getCards().add(hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));	
//						}
//						else
//						{
//							hero.getDeck().getCards().add(0, hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));	
//						}
//					break;
//					case EQUIPMENT:
//						card.setActualLocation(CardArea.EQUIPMENT);
//						hero.getGearAndFeats().getCards().add(hero.getBurialPile().getCards().get(hero.getBurialPile().getCards().indexOf(card)));	
//					break;
//					default:
//					break;
//				}
//				hero.getBurialPile().getCards().remove((hero.getBurialPile().getCards().indexOf(card)));
//			break;
//			case DISCARD:
//				switch (dest)
//				{
//					case BURIAL:
//						card.setActualLocation(CardArea.BURIAL);
//						hero.getBurialPile().getCards().add(hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));	
//					break;
//					case HAND:
//						card.setActualLocation(CardArea.HAND);
//						hero.getHand().getCards().add(hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));	
//					break;
//					case DECK:
//						card.setActualLocation(CardArea.DECK);
//						if(topDeck == false){
//							hero.getDeck().getCards().add(hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));
//						}
//						else{
//							hero.getDeck().getCards().add(0, hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));
//						}
//					break;
//					case EQUIPMENT:
//						card.setActualLocation(CardArea.EQUIPMENT);
//						hero.getGearAndFeats().getCards().add(hero.getDiscardPile().getCards().get(hero.getDiscardPile().getCards().indexOf(card)));	
//					break;
//					default:
//					break;
//				}
//				hero.getDiscardPile().getCards().remove((hero.getDiscardPile().getCards().indexOf(card)));
//			break;
//			case DECK:
//				switch (dest)
//				{
//					case BURIAL:
//						card.setActualLocation(CardArea.BURIAL);
//						hero.getBurialPile().getCards().add(hero.getDeck().getCards().get(hero.getDeck().getCards().indexOf(card)));	
//					break;
//					case DISCARD:
//						card.setActualLocation(CardArea.DISCARD);
//						hero.getDiscardPile().getCards().add(hero.getDeck().getCards().get(hero.getDeck().getCards().indexOf(card)));	
//					break;
//					case HAND:
//						card.setActualLocation(CardArea.HAND);
//						hero.getHand().getCards().add(hero.getDeck().getCards().get(hero.getDeck().getCards().indexOf(card)));	
//					break;
//					case EQUIPMENT:
//						card.setActualLocation(CardArea.EQUIPMENT);
//						hero.getGearAndFeats().getCards().add(hero.getDeck().getCards().get(hero.getDeck().getCards().indexOf(card)));	
//					break;
//					default:
//					break;
//				}
//				hero.getDeck().getCards().remove((hero.getDeck().getCards().indexOf(card)));
//			break;
//			case EQUIPMENT:
//				switch (dest)
//				{
//					case BURIAL:
//						card.setActualLocation(CardArea.BURIAL);
//						hero.getBurialPile().getCards().add(hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));	
//					break;
//					case DISCARD:
//						card.setActualLocation(CardArea.DISCARD);
//						hero.getDiscardPile().getCards().add(hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));
//					break;
//					case DECK:
//						card.setActualLocation(CardArea.DECK);
//						if(topDeck == false){
//							hero.getDeck().getCards().add(hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));
//						}
//						else{
//							hero.getDeck().getCards().add(0, hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));
//						}
//					break;
//					case HAND:
//						card.setActualLocation(CardArea.HAND);
//						hero.getHand().getCards().add(hero.getGearAndFeats().getCards().get(hero.getGearAndFeats().getCards().indexOf(card)));
//					break;
//					default:
//					break;	
//				}
//				hero.getGearAndFeats().getCards().remove((hero.getGearAndFeats().getCards().indexOf(card)));
//			break;
//			default:
//				
//			break;
//		}
//			
//	}
	
	
	

	
	public String toString(){
		String text;
		
		text = "This Card contains: "+
				"\nName: "+getName()+
				"\nResolve cost: "+getResolveCost()+
				"\nCard type: "+getCardType().toString();

		
		if(getFeatKeyword() == null){
			text += "\nFeat keywords: this card has no Feat keywords";
		}
		else{
			text += "\nFeat keywords: "+getFeatKeyword().toString();
		}
		
		if(getGearKeyword() == null){
			text += "\nGear keywords: this card has no Gear keywords";
		}
		else{
			text += "\nGear keywords: "+getGearKeyword().toString();
		}
		
		if(getAreaRestriction() == null){
			text += "\nHero area restriction: There is no Hero area restriction";
		}
		else{
			text += "\nHero area restriction: "+getAreaRestriction().toString();
		}
		
		return text;
	}

	
}
