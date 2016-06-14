package com.mygdx.game.mistfall.hero;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.hero.cards.HeroCard;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;
import com.mygdx.game.mistfall.hero.cards.enums.HC_AreaRestriction;
import com.mygdx.game.mistfall.hero.enums.HeroIdentifyEnum;
import com.mygdx.game.mistfall.model.Conditions;
import com.mygdx.game.mistfall.model.enums.Keyword;
import com.mygdx.game.mistfall.model.modifications.Modification;

public class Hero {

	// {{ Attributes

	private String name;
	private HeroIdentifyEnum identifyEnum;
	private int heroID; // Position of the Hero in the heroes List of the Game Controller. Does not change once set
	
	private int focus;
	private int restoration;
	private int drawLimit;
	private int regularActionsLeft;
	
	private LinkedList<Keyword> gearProficiencies;
	
	private Conditions conditions;
	
	private LinkedList<Modification> modifications;
	
	private List<Enemy> enemies;
	
	private List<HeroCard> deck;
	private List<HeroCard> hand;
	private List<HeroCard> heroArea;
	private List<HeroCard> discard;
	private List<HeroCard> burial;
	private List<HeroCard> advancedFeats;
	
	private Map<HC_AreaRestriction, Integer> heroAreaRestriction;
	
	// }}
	
	// {{ Constructor
	public Hero(){
		enemies = new LinkedList<Enemy>();
		deck = new LinkedList<HeroCard>();
		hand = new LinkedList<HeroCard>();
		heroArea = new LinkedList<HeroCard>();
		discard = new LinkedList<HeroCard>();
		burial = new LinkedList<HeroCard>();
		advancedFeats = new LinkedList<HeroCard>();
		
		modifications = new LinkedList<Modification>();
		heroAreaRestriction = new HashMap<HC_AreaRestriction,Integer>();
	}
	// }}		

	// {{ Getters & Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public HeroIdentifyEnum getIdentifyEnum() {
		return identifyEnum;
	}
	public void setIdentifyEnum(HeroIdentifyEnum identifyEnum) {
		this.identifyEnum = identifyEnum;
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
	public int getRegularActionsLeft() {
		return regularActionsLeft;
	}
	public void setRegularActionsLeft(int regularActionsLeft) {
		this.regularActionsLeft = regularActionsLeft;
	}
	public LinkedList<Keyword> getGearProficiencies() {
		return gearProficiencies;
	}
	public void setGearProficiencies(LinkedList<Keyword> gearProficiencies) {
		this.gearProficiencies = gearProficiencies;
	}
	public Conditions getConditions() {
		return conditions;
	}
	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}
	public LinkedList<Modification> getModifications() {
		return modifications;
	}
	public void setModifications(LinkedList<Modification> modifications) {
		this.modifications = modifications;
	}
	public List<Enemy> getEnemies() {
		return enemies;
	}
	public void setEnemies(List<Enemy> enemies) {
		this.enemies = enemies;
	}
	public List<HeroCard> getDeck() {
		return deck;
	}
	public void setDeck(List<HeroCard> deck) {
		this.deck = deck;
	}
	public List<HeroCard> getHand() {
		return hand;
	}
	public void setHand(List<HeroCard> hand) {
		this.hand = hand;
	}
	public List<HeroCard> getHeroArea() {
		return heroArea;
	}
	public void setHeroArea(List<HeroCard> heroArea) {
		this.heroArea = heroArea;
	}
	public List<HeroCard> getDiscard() {
		return discard;
	}
	public void setDiscard(List<HeroCard> discard) {
		this.discard = discard;
	}
	public List<HeroCard> getBurial() {
		return burial;
	}
	public void setBurial(List<HeroCard> burial) {
		this.burial = burial;
	}
	public List<HeroCard> getAdvancedFeats() {
		return advancedFeats;
	}
	public void setAdvancedFeats(List<HeroCard> advancedFeats) {
		this.advancedFeats = advancedFeats;
	}
	public Map<HC_AreaRestriction, Integer> getHeroAreaRestriction() {
		return heroAreaRestriction;
	}

	public void setHeroAreaRestriction(Map<HC_AreaRestriction, Integer> heroAreaRestriction) {
		this.heroAreaRestriction = heroAreaRestriction;
	}
	// }}

	
	// {{ Other Methods
	
	public List<HeroCard> getSpecificArea(HC_Area area){
		switch (area){
			case HERO_AREA:
				return heroArea;
			case HAND:
				return hand;
			case DECK:
				return deck;
			case DISCARD:
				return discard;
			case BURIAL:
				return burial;
			case ADVANCED_FEAT:
				return advancedFeats;
			default:
				return null;
		
		}
	}
	
	public boolean spaceInHeroArea(HC_AreaRestriction areaRes){
		
		int count = 0;
		
		for (HeroCard card : getHand()){
			if (card.getAreaRestriction()==areaRes){
				count ++;
			}
		}
		
		if (count < this.getHeroAreaRestriction().get(areaRes)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void focusChange(GameController gc, int value){
		int focusTemp = focus + value;
		if (focusTemp <0){
			focus=0;
		}
		else if (focusTemp>12){
			while (focusTemp>12){
				focusTemp=focusTemp-7;
			}
			focus=focusTemp;
		}
		
		// TODO: Reinforcements + Erange
	}
	// }}
	
}
