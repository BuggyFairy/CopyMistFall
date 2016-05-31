package com.mygdx.game.mistfall.hero;

import java.util.LinkedList;

import com.mygdx.game.mistfall.model.Conditions;
import com.mygdx.game.mistfall.model.enums.Keyword;
import com.mygdx.game.mistfall.model.modifications.Modification;

public class Hero {

		private String name;
		private int heroID; // Position of the Hero in the heroes List of the Game Controller. Does not change once set
		private int focus;
		private int restoration;
		private int drawLimit;
		//private boolean didTurn;
		//private boolean isActive;
		private int regularActionsLeft;
		private Deck deck;
		private DiscardPile discardPile;
		private BurialPile burialPile;
		private Hand hand;
		private HeroArea gearAndFeats;
		private HeroEnemies heroEnemies;
		private LinkedList<Keyword> gearProficiencies;
		private Conditions conditions;
		
		private LinkedList<Modification> modifications;
		
		public Hero(){
			this.hand = new Hand();
			this.deck = new Deck();
			this.discardPile = new DiscardPile();
			this.burialPile = new BurialPile();
			this.gearAndFeats = new HeroArea();
			this.heroEnemies = new HeroEnemies();
			this.modifications = new LinkedList<Modification>();
		}
						
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public int getHeroID() {
			return heroID;
		}


		public void setHeroID(int heroID) {
			this.heroID = heroID;
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
		
		public int getDrawLimit() {
			return drawLimit;
		}
		public void setDrawLimit(int drawLimit) {
			this.drawLimit = drawLimit;
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
		
		public HeroArea getGearAndFeats() {
			return gearAndFeats;
		}
		public void setGearAndFeats(HeroArea gearAndFeats) {
			this.gearAndFeats = gearAndFeats;
		}
		
		public HeroEnemies getHeroEnemies() {
			return heroEnemies;
		}
		public void setHeroEnemies(HeroEnemies heroEnemies) {
			this.heroEnemies = heroEnemies;
		}
		
		public LinkedList<Keyword> getGearProficiencies() {
			return gearProficiencies;
		}
		public void setGearProficiencies(LinkedList<Keyword> gearProficiencies) {
			this.gearProficiencies = gearProficiencies;
		}
		
		public void appendGearProficiencies(Keyword gt){
			if(this.gearProficiencies != null){
				this.gearProficiencies.add(gt);
			}
		}
		
		
		
//		public boolean isDidTurn() {
//			return didTurn;
//		}
//
//
//		public void setDidTurn(boolean didTurn) {
//			this.didTurn = didTurn;
//		}


//		public boolean isActive() {
//			return isActive;
//		}
//
//
//		public void setActive(boolean isActive) {
//			this.isActive = isActive;
//		}
		
		
		public Conditions getConditions() {
			return conditions;
		}


		public void setConditions(Conditions conditions) {
			this.conditions = conditions;
		}


		public void halveFocus(){
			focus=Math.floorDiv(focus, 2);
		}	

		

		public LinkedList<Modification> getModifications() {
			return modifications;
		}


		public int getRegularActionsLeft() {
			return regularActionsLeft;
		}


		public void setRegularActionsLeft(int regularActionsLeft) {
			this.regularActionsLeft = regularActionsLeft;
		}
}
