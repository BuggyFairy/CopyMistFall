package com.mygdx.game.mistfall.hero;

import java.util.LinkedList;

import com.mygdx.game.mistfall.model.Conditions;
import com.mygdx.game.mistfall.model.enums.GearKeyword;
import com.mygdx.game.mistfall.model.modifications.Modification;

public class Hero {

		private String name;
		private int heroID; // Position of the Hero in the heroes List of the Game Controller. Does not change once set
		private int focus;
		private int restoration;
		private int drawLimit;
		//private boolean didTurn;
		//private boolean isActive;
		private Deck deck;
		private DiscardPile discardPile;
		private BurialPile burialPile;
		private Hand hand;
		private GearAndFeats gearAndFeats;
		private HeroEnemies heroEnemies;
		private LinkedList<GearKeyword> gearProficiencies;
		private Conditions conditions;
		
		private LinkedList<Modification> modifications;
		
		public Hero(){
			this.hand = new Hand();
			this.deck = new Deck();
			this.discardPile = new DiscardPile();
			this.burialPile = new BurialPile();
			this.gearAndFeats = new GearAndFeats();
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


		public LinkedList<Modification> getModifications() {
			return modifications;
		}

}
