package com.mygdx.game.mistfall.hero;

import java.util.LinkedList;

import com.mygdx.game.mistfall.model.enums.GearKeyword;

public class Hero {

		private String name;
		private int focus;
		private int restoration;
		private boolean didTurn;
		private boolean isActive;
		private Deck deck;
		private DiscardPile discardPile;
		private BurialPile burialPile;
		private Hand hand;
		private GearAndFeats gearAndFeats;
		private HeroEnemies heroEnemies;
		private LinkedList<GearKeyword> gearProficiencies;
		
		public Hero(){
			this.hand = new Hand();
			this.deck = new Deck();
			this.discardPile = new DiscardPile();
			this.burialPile = new BurialPile();
			this.gearAndFeats = new GearAndFeats();
			this.heroEnemies = new HeroEnemies();
		}
						
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getFocus() {
			return focus;
		}
		public void setFocus(int focus) {
			this.focus = focus;
		}
		public int getRestoration() {
			return restoration;
		}
		public void setRestoration(int restoration) {
			this.restoration = restoration;
		}
		public Deck getDeck() {
			return deck;
		}
		public void setDeck(Deck deck) {
			this.deck = deck;
		}
		public DiscardPile getDiscardPile() {
			return discardPile;
		}
		public void setDiscardPile(DiscardPile discardPile) {
			this.discardPile = discardPile;
		}
		public BurialPile getBurialPile() {
			return burialPile;
		}
		public void setBurialPile(BurialPile burialPile) {
			this.burialPile = burialPile;
		}
		public Hand getHand() {
			return hand;
		}
		public void setHand(Hand hand) {
			this.hand = hand;
		}
		public GearAndFeats getGearAndFeats() {
			return gearAndFeats;
		}
		public void setGearAndFeats(GearAndFeats gearAndFeats) {
			this.gearAndFeats = gearAndFeats;
		}
		public HeroEnemies getHeroEnemies() {
			return heroEnemies;
		}
		public void setHeroEnemies(HeroEnemies heroEnemies) {
			this.heroEnemies = heroEnemies;
		}
		public LinkedList<GearKeyword> getGearProficiencies() {
			return gearProficiencies;
		}
		public void setGearProficiencies(LinkedList<GearKeyword> gearProficiencies) {
			this.gearProficiencies = gearProficiencies;
		}
		public void appendGearProficiencies(GearKeyword gt){
			if(this.gearProficiencies != null){
				this.gearProficiencies.add(gt);
			}
		}
		public boolean isDidTurn() {
			return didTurn;
		}


		public void setDidTurn(boolean didTurn) {
			this.didTurn = didTurn;
		}


		public boolean isActive() {
			return isActive;
		}


		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}

		public String toString(){
			String text = "";
			
			text = "Hero description : "+
				   "\n"+getName()+
				   "\nFokus :"+getFocus()+
				   "\nRestauration : "+getRestoration();
			
			if(!deck.getCards().isEmpty()){
				text += "\nIm Deck sind : "+deck.getCards().toString();
			}
			
			if(!discardPile.getCards().isEmpty()){
				text += "\nIm Discard Pile sind : "+discardPile.getCards().toString();
			}
			
			if(!burialPile.getCards().isEmpty()){
				text += "\nIm Burial Pile sind : "+burialPile.getCards().toString();
			}
						
			if(!hand.getCards().isEmpty()){
				text += "\nIn der Hand sind : "+hand.getCards().toString();
			}
			
			if(!gearAndFeats.getCards().isEmpty()){
				text += "\nIn Gear und Feats sind : "+gearAndFeats.getCards().toString();
			}
			
			if(!heroEnemies.getCards().isEmpty()){
				text += "\nFolgende Gegner liegen bei mir: "+heroEnemies.getCards().toString();
			}
			
			if(!gearProficiencies.isEmpty()){
				text += "\nIch kann folgendes benutzen: "+gearProficiencies.toString();
			}
			
			return text;
		}



		
		
}