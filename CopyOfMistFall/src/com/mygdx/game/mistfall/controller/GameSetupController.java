package com.mygdx.game.mistfall.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.EnemyAbility;
import com.mygdx.game.mistfall.enemy.EnemyCountInfo;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.enemy.enums.EnemyType;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.cards.HC_Action;
import com.mygdx.game.mistfall.hero.cards.HC_ActionImpact;
import com.mygdx.game.mistfall.hero.cards.HC_ActionStructure2;
import com.mygdx.game.mistfall.hero.cards.HC_ActionStructure3;
import com.mygdx.game.mistfall.hero.cards.HeroCard;
import com.mygdx.game.mistfall.hero.cards.enums.HC_ActionType;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;
import com.mygdx.game.mistfall.hero.cards.enums.HC_AreaRestriction;
import com.mygdx.game.mistfall.hero.cards.enums.HC_IdentifierEnum;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Type;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionEffect;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionKeyword_1;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionKeyword_2;
import com.mygdx.game.mistfall.hero.enums.HeroIdentifyEnum;
import com.mygdx.game.mistfall.model.Encounter;
import com.mygdx.game.mistfall.model.Location;
import com.mygdx.game.mistfall.model.LocationGrid;
import com.mygdx.game.mistfall.model.Reward;
import com.mygdx.game.mistfall.model.TimeCard;
import com.mygdx.game.mistfall.model.enums.AttackType;
import com.mygdx.game.mistfall.model.enums.Keyword;

public class GameSetupController {

	// {{ Attributes
	
	private Hero partyLeader;
	private int resolvePool;
	private LocationGrid locationGrid;
	private List<Encounter> encounters;
	private List<TimeCard> timeCards;
	private List<Enemy> greenEnemies;
	private List<Enemy> redEnemies;
	private List<Enemy> blueEnemies;
	private List<Location> locationList;
	private List<Reward> rewards;
	
	private List<Encounter> encountersDiscard;
	private List<TimeCard> timeCardsDiscard;
	private List<Enemy> greenEnemiesDiscard;
	private List<Enemy> redEnemiesDiscard;
	private List<Enemy> blueEnemiesDiscard;
	
	private int nextFreeEnemyID;
	private int nextFreeHeroCardID;
	
	private Map<Integer,EnemyType> enemyTypeByID;
	
	private List<EnemyCountInfo> enemyCountInfo;
	
	// }}
	
	// {{ Constructor

	public GameSetupController(GameController gc){
		
		setNextFreeEnemyID(1);
		setNextFreeHeroCardID(1000);
		greenEnemies = new LinkedList<Enemy>();
		redEnemies = new LinkedList<Enemy>();
		blueEnemies = new LinkedList<Enemy>();
		greenEnemiesDiscard = new LinkedList<Enemy>();
		redEnemiesDiscard = new LinkedList<Enemy>();
		blueEnemiesDiscard = new LinkedList<Enemy>();
		setResolvePool(2);
		
		enemyTypeByID=new HashMap<Integer,EnemyType>();
		
		setEnemyCountInfo();
		createEnemyDecks(gc);
		
		// Shuffle all Enemy Decks
		greenEnemies=shuffleCardsEnemy(greenEnemies);
		blueEnemies=shuffleCardsEnemy(blueEnemies);
		redEnemies=shuffleCardsEnemy(redEnemies);
		
	}
	
	// }}
	
	// {{ Getters & Setters
	
	public Map<Integer, EnemyType> getEnemyTypeByID() {
		return enemyTypeByID;
	}
	public void setEnemyTypeByID(Map<Integer, EnemyType> enemyTypeByID) {
		this.enemyTypeByID = enemyTypeByID;
	}
	public List<Location> getLocationList() {
		return locationList;
	}
	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}

	public List<Encounter> getEncounters() {
		return encounters;
	}
	public void setEncounters(List<Encounter> encounters) {
		this.encounters = encounters;
	}
	public List<TimeCard> getTimeCards() {
		return timeCards;
	}
	public void setTimeCards(List<TimeCard> timeCards) {
		this.timeCards = timeCards;
	}
	public List<Enemy> getGreenEnemies() {
		return greenEnemies;
	}
	public void setGreenEnemies(List<Enemy> greenEnemies) {
		this.greenEnemies = greenEnemies;
	}
	public List<Enemy> getRedEnemies() {
		return redEnemies;
	}
	public void setRedEnemies(List<Enemy> redEnemies) {
		this.redEnemies = redEnemies;
	}
	public List<Enemy> getBlueEnemies() {
		return blueEnemies;
	}
	public void setBlueEnemies(List<Enemy> blueEnemies) {
		this.blueEnemies = blueEnemies;
	}
	public List<Reward> getRewards() {
		return rewards;
	}
	public void setRewards(List<Reward> rewards) {
		this.rewards = rewards;
	}
	public List<Encounter> getEncountersDiscard() {
		return encountersDiscard;
	}
	public void setEncountersDiscard(List<Encounter> encountersDiscard) {
		this.encountersDiscard = encountersDiscard;
	}
	public List<TimeCard> getTimeCardsDiscard() {
		return timeCardsDiscard;
	}
	public void setTimeCardsDiscard(List<TimeCard> timeCardsDiscard) {
		this.timeCardsDiscard = timeCardsDiscard;
	}
	public List<Enemy> getGreenEnemiesDiscard() {
		return greenEnemiesDiscard;
	}
	public void setGreenEnemiesDiscard(List<Enemy> greenEnemiesDiscard) {
		this.greenEnemiesDiscard = greenEnemiesDiscard;
	}
	public List<Enemy> getRedEnemiesDiscard() {
		return redEnemiesDiscard;
	}
	public void setRedEnemiesDiscard(List<Enemy> redEnemiesDiscard) {
		this.redEnemiesDiscard = redEnemiesDiscard;
	}
	public List<Enemy> getBlueEnemiesDiscard() {
		return blueEnemiesDiscard;
	}
	public void setBlueEnemiesDiscard(List<Enemy> blueEnemiesDiscard) {
		this.blueEnemiesDiscard = blueEnemiesDiscard;
	}
	public Hero getPartyLeader() {
		return partyLeader;
	}
	public void setPartyLeader(Hero partyLeader) {
		this.partyLeader = partyLeader;
	}
	public LocationGrid getLocationGrid() {
		return locationGrid;
	}
	public void setLocationGrid(LocationGrid locationGrid) {
		this.locationGrid = locationGrid;
	}
	public int getResolvePool() {
		return resolvePool;
	}
	public void setResolvePool(int resolvePool) {
		this.resolvePool = resolvePool;
	}	
	public int getNextFreeEnemyID() {
		return nextFreeEnemyID;
	}

	public void setNextFreeEnemyID(int nextFreeEnemyID) {
		this.nextFreeEnemyID = nextFreeEnemyID;
	}
	public int getNextFreeHeroCardID() {
		return nextFreeHeroCardID;
	}

	public void setNextFreeHeroCardID(int nextFreeHeroCardID) {
		this.nextFreeHeroCardID = nextFreeHeroCardID;
	}
	
	// }}

	// {{ Other Methods
	
	public void changeResolvePool(int i){
		
			if((this.resolvePool += i) >= 0){
				this.resolvePool += i;
			}
			else{
				System.out.println("Resolve pool negative");
			}
			
	}
	
	public int getEnemyPositionDiscard(Enemy enemy){
		int pos=-1;
		switch (enemy.getEnemySuit()){
		case BLUE:
			for (int i=0;i<blueEnemiesDiscard.size();i++){
				if (blueEnemiesDiscard.get(i).getEnemyID()==enemy.getEnemyID()){
					pos=i;
					break;
				}
			}
		break;
		case RED:
			for (int i=0;i<redEnemiesDiscard.size();i++){
				if (redEnemiesDiscard.get(i).getEnemyID()==enemy.getEnemyID()){
					pos=i;
					break;
				}
			}
		break;
		case GREEN:
			for (int i=0;i<greenEnemiesDiscard.size();i++){
				if (greenEnemiesDiscard.get(i).getEnemyID()==enemy.getEnemyID()){
					pos=i;
					break;
				}
			}
		break;
		default:
		break;
	}
		
		return pos;
	}

	public void createNewHero(GameController gc, HeroIdentifyEnum heroEnum){
		
		Hero hero = new Hero();
		
		switch (heroEnum){
			// {{ Arani
			case ARANI:
				hero.setName("Arani the Dawnbreaker Cleric");
				hero.setIdentifyEnum(HeroIdentifyEnum.ARANI);
				hero.setHeroID(gc.getHeroes().size());
				
				hero.setFocus(3);
				hero.setRestoration(2);
				hero.setDrawLimit(5);
				hero.setGearProficiencies(new LinkedList<Keyword>());
				hero.getGearProficiencies().add(Keyword.AXE);
				hero.getGearProficiencies().add(Keyword.HAMMER);
				hero.getGearProficiencies().add(Keyword.MACE);
				hero.getGearProficiencies().add(Keyword.ARMOUR);
				hero.getGearProficiencies().add(Keyword.SHIELD);
				hero.getGearProficiencies().add(Keyword.SYMBOL);
				hero.getGearProficiencies().add(Keyword.POTION);
				hero.getGearProficiencies().add(Keyword.TRINKET);
				
				gc.getHeroes().add(hero);
				createHeroCards(hero);	
			break;
			// }}
			
			// {{ Ardenai
			case ARDENAI:
				hero.setName("Ardenai the Arcaneweave Archer");
				hero.setIdentifyEnum(HeroIdentifyEnum.ARDENAI);
				hero.setHeroID(gc.getHeroes().size());
				
				hero.setFocus(3);
				hero.setRestoration(1);
				hero.setDrawLimit(5);
				hero.setGearProficiencies(new LinkedList<Keyword>());
				hero.getGearProficiencies().add(Keyword.BOW);
				hero.getGearProficiencies().add(Keyword.CROSSBOW);
				hero.getGearProficiencies().add(Keyword.DAGGER);
				hero.getGearProficiencies().add(Keyword.STAFF);
				hero.getGearProficiencies().add(Keyword.CLOAK);
				hero.getGearProficiencies().add(Keyword.ITEM);
				hero.getGearProficiencies().add(Keyword.POTION);
				hero.getGearProficiencies().add(Keyword.TRINKET);
				
				gc.getHeroes().add(hero);
				createHeroCards(hero);	
			break;
			// }}
			
			// {{ Celenthia
			case CELENTHIA:
				hero.setName("Celenthia the Arcane Mage");
				hero.setIdentifyEnum(HeroIdentifyEnum.CELENTHIA);
				hero.setHeroID(gc.getHeroes().size());
				
				hero.setFocus(4);
				hero.setRestoration(1);
				hero.setDrawLimit(5);
				hero.setGearProficiencies(new LinkedList<Keyword>());
				hero.getGearProficiencies().add(Keyword.STAFF);
				hero.getGearProficiencies().add(Keyword.WAND);
				hero.getGearProficiencies().add(Keyword.DAGGER);
				hero.getGearProficiencies().add(Keyword.BOOK);
				hero.getGearProficiencies().add(Keyword.CLOAK);
				hero.getGearProficiencies().add(Keyword.ROBE);
				hero.getGearProficiencies().add(Keyword.SCROLL);
				hero.getGearProficiencies().add(Keyword.POTION);
				hero.getGearProficiencies().add(Keyword.TRINKET);
				
				gc.getHeroes().add(hero);
				createHeroCards(hero);	
			break;
			// }}
			
			// {{ Crow
			case CROW:
				hero.setName("Crow the Seeker");
				hero.setIdentifyEnum(HeroIdentifyEnum.CROW);
				hero.setHeroID(gc.getHeroes().size());
				
				hero.setFocus(3);
				hero.setRestoration(1);
				hero.setDrawLimit(5);
				hero.setGearProficiencies(new LinkedList<Keyword>());
				hero.getGearProficiencies().add(Keyword.BOW);
				hero.getGearProficiencies().add(Keyword.CROSSBOW);
				hero.getGearProficiencies().add(Keyword.DAGGER);
				hero.getGearProficiencies().add(Keyword.STAFF);
				hero.getGearProficiencies().add(Keyword.CLOAK);
				hero.getGearProficiencies().add(Keyword.ITEM);
				hero.getGearProficiencies().add(Keyword.POTION);
				hero.getGearProficiencies().add(Keyword.TRINKET);
				hero.getGearProficiencies().add(Keyword.TOOLKIT);
				
				hero.getHeroAreaRestriction().put(HC_AreaRestriction.B, 2);
				hero.getHeroAreaRestriction().put(HC_AreaRestriction.C, 1);
				hero.getHeroAreaRestriction().put(HC_AreaRestriction.F, 3);
				hero.getHeroAreaRestriction().put(HC_AreaRestriction.G, 1);
				
				gc.getHeroes().add(hero);
				createHeroCards(hero);				
			break;
			// }}
			
			// {{ Fengray
			case FENGRAY:
				hero.setName("Fengray the Shieldbearer");
				hero.setIdentifyEnum(HeroIdentifyEnum.FENGRAY);
				hero.setHeroID(gc.getHeroes().size());
				
				hero.setFocus(4);
				hero.setRestoration(2);
				hero.setDrawLimit(5);
				hero.setGearProficiencies(new LinkedList<Keyword>());
				hero.getGearProficiencies().add(Keyword.AXE);
				hero.getGearProficiencies().add(Keyword.HAMMER);
				hero.getGearProficiencies().add(Keyword.MACE);
				hero.getGearProficiencies().add(Keyword.SWORD);
				hero.getGearProficiencies().add(Keyword.ARMOUR);
				hero.getGearProficiencies().add(Keyword.SHIELD);
				hero.getGearProficiencies().add(Keyword.POTION);
				hero.getGearProficiencies().add(Keyword.TRINKET);
				
				gc.getHeroes().add(hero);
				createHeroCards(hero);	
			break;
			// }}
			
			// {{ Hareag
			case HAREAG:
				hero.setName("Hareag the Frost Mage");
				hero.setIdentifyEnum(HeroIdentifyEnum.HAREAG);
				hero.setHeroID(gc.getHeroes().size());
				
				hero.setFocus(4);
				hero.setRestoration(1);
				hero.setDrawLimit(5);
				hero.setGearProficiencies(new LinkedList<Keyword>());
				hero.getGearProficiencies().add(Keyword.DAGGER);
				hero.getGearProficiencies().add(Keyword.STAFF);
				hero.getGearProficiencies().add(Keyword.SWORD);
				hero.getGearProficiencies().add(Keyword.WAND);
				hero.getGearProficiencies().add(Keyword.BOOK);
				hero.getGearProficiencies().add(Keyword.CLOAK);
				hero.getGearProficiencies().add(Keyword.ROBE);
				hero.getGearProficiencies().add(Keyword.SCROLL);
				hero.getGearProficiencies().add(Keyword.POTION);
				hero.getGearProficiencies().add(Keyword.TRINKET);
				
				gc.getHeroes().add(hero);
				createHeroCards(hero);	
			break;
			// }}
			
			// {{ Venda
			case VENDA:
				hero.setName("Venda the Ravencrag Fury");
				hero.setIdentifyEnum(HeroIdentifyEnum.VENDA);
				hero.setHeroID(gc.getHeroes().size());
				
				hero.setFocus(4);
				hero.setRestoration(1);
				hero.setDrawLimit(5);
				hero.setGearProficiencies(new LinkedList<Keyword>());
				hero.getGearProficiencies().add(Keyword.AXE);
				hero.getGearProficiencies().add(Keyword.HAMMER);
				hero.getGearProficiencies().add(Keyword.MACE);
				hero.getGearProficiencies().add(Keyword.SWORD);
				hero.getGearProficiencies().add(Keyword.ARMOUR);
				hero.getGearProficiencies().add(Keyword.SHIELD);
				hero.getGearProficiencies().add(Keyword.POTION);
				hero.getGearProficiencies().add(Keyword.TRINKET);
				
				gc.getHeroes().add(hero);
				createHeroCards(hero);	
			break;
			// }}
			
			default:
		}
	}

	private void createHeroCards(Hero hero){
		
		HeroCard card;
		HC_Action action;
		HC_ActionStructure2 actionStructure2;
		HC_ActionStructure3 actionStructure3;
		HC_ActionImpact actionImpact;
		int i;
		
		switch(hero.getIdentifyEnum()){
		
			// {{ Crow the Seeker
			case CROW:
				
			// {{ Basic Gear Cards
			
				// {{ Dagger
				for (i=0; i<2; i++){
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Dagger");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_DAGGER);
	
					card.setResolveCost(0);
					card.setHeroCardType(HC_Type.GEAR);
					
					card.getKeywords().add(Keyword.DAGGER);
					card.getKeywords().add(Keyword.GEAR);
					card.getKeywords().add(Keyword.PIERCING);
					card.getKeywords().add(Keyword.WEAPON);
					
					card.setAreaRestriction(HC_AreaRestriction.B);	
					// }} 
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Place in your <Hero Area>. <+>1<Focus>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						action.setActionKeyword1(HC_ActionKeyword_1.MOVE_CARD);
						action.setActionKeyword2(HC_ActionKeyword_2.PLACE_HERO_AREA);
						action.setDestAfterUse(HC_Area.NONE);
						action.setSourceAction(true);
						
						actionStructure2 = new HC_ActionStructure2();
						actionStructure2.setNecessity(true);
						
						actionStructure3 = new HC_ActionStructure3();
						
						actionImpact = new HC_ActionImpact();
						actionImpact.setEffect(HC_ActionEffect.FOCUS);
						actionImpact.setValue(1);
						
						actionStructure3.getImpacts().add(actionImpact);
						actionStructure2.getChoices().add(actionStructure3);
						action.getOptions().add(actionStructure2);
						card.getActions().add(action);
						// }}
						
						// {{ Action 2
						action = new HC_Action();
						action.setText("Deal 1<Physical Damage>, <+>1<Focus>. You may discard 1 Combat card to deal +1<Physical Damage>, <+>1<Focus>.");
						action.setCardArea(HC_Area.HERO_AREA);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}
						
						// {{ Action 3
						action = new HC_Action();
						action.setText("Deal 2<Physical Damage>, <+>1<Focus>. You may discard up to 2 Combat cards to deal +1<Physical Damage>, <+>1<Focus> for each card so discarded. ");
						action.setCardArea(HC_Area.HERO_AREA);
						action.setRange(1);
						action.setType(HC_ActionType.REGULAR);
						
						card.getActions().add(action);
						// }}
					// }}
					
					if (i==0){
						hero.getHeroArea().add(card);
					}
					else{
						hero.getDeck().add(card);
					}
				}			
				// }}
				
				// {{ Hooded Cloak
				for (i=0; i<1; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Hodded Cloak");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_HOODED_CLOAK);
	
					card.setResolveCost(0);
					card.setHeroCardType(HC_Type.GEAR);
					
					card.getKeywords().add(Keyword.CLOAK);
					card.getKeywords().add(Keyword.GEAR);
					
					card.setAreaRestriction(HC_AreaRestriction.C);
					// }} 
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Place in your <Hero Area>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}
						
						// {{ Action 2
						action = new HC_Action();
						action.setText("Cancel 1<Physical Damage> or 1<Magical Damage>. Discard this card or place it on top of your deck.");
						action.setCardArea(HC_Area.HERO_AREA);
						action.setRange(1);
						action.setType(HC_ActionType.REFLEX);
						
						card.getActions().add(action);
						// }}
						
					// }}
						
					hero.getHeroArea().add(card);
						
				}			
				// }}
				
				// {{ Utility Belt
				for (i=0; i<1; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Utility Belt");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_UTILITY_BELT);
	
					card.setResolveCost(0);
					card.setHeroCardType(HC_Type.GEAR);
					
					card.getKeywords().add(Keyword.BELT);
					card.getKeywords().add(Keyword.GEAR);
					card.getKeywords().add(Keyword.ITEM);
					
					card.setAreaRestriction(HC_AreaRestriction.G);
					// }} 
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Place in your <Hero Area>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						action.setActionKeyword1(HC_ActionKeyword_1.MOVE_CARD);
						action.setActionKeyword2(HC_ActionKeyword_2.PLACE_HERO_AREA);
						action.setDestAfterUse(HC_Area.NONE);
						action.getOptions().add(new HC_ActionStructure2());
						
						action.getOptions().get(0).getChoices().add(new HC_ActionStructure3());
						action.getOptions().get(0).setNecessity(true);
						
						card.getActions().add(action);
						// }}
						
						// {{ Action 2
						action = new HC_Action();
						action.setText("Move 1 GEAR or STEALTH card from your discard pile to the top of your deck. Discard this card.");
						action.setCardArea(HC_Area.HERO_AREA);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}
						
					// }}
						
					hero.getHeroArea().add(card);
				}			
				// }}
			
			//}}
				
			// {{ Basic Feats
				
				// {{ Acrobatic Dodge
				for (i=0; i<2; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Acrobatic Dodge");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_ACROBATIC_DODGE);
	
					card.setResolveCost(0);
					card.setHeroCardType(HC_Type.BASIC);
					
					card.getKeywords().add(Keyword.EVASION);
					card.getKeywords().add(Keyword.MOBILITY);
					
					card.setAreaRestriction(HC_AreaRestriction.HAND);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Cancel 2<Physical Damage> or 2<Magical Damage>. <+>1<Focus>. You may discard 1 MOBILITY card to move the attacking Enemy from your <Hero Area> to the <Quest Area>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.REFLEX);
						
						card.getActions().add(action);
						// }}			
					// }}
					
					hero.getDeck().add(card);
				}			
				// }}
				
				// {{ Backstab
				for (i=0; i<3; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Backstab");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_BACKSTAB);
	
					card.setResolveCost(0);
					card.setHeroCardType(HC_Type.BASIC);
					
					card.getKeywords().add(Keyword.COMBAT);
					card.getKeywords().add(Keyword.MOBILITY);
					card.getKeywords().add(Keyword.STEALTH);
					
					card.setAreaRestriction(HC_AreaRestriction.HAND);
					// }} 
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Resolve any 1 Action of a DAGGER card in you <Hero Area>. If the Action deals any <Physical Damage>, deal additional +2<Physical Damage>, <+>1<Focus>. If the targeted Enemy is not in your <Hero Area>, reduce that Enemy's <Physical Resistance> by 2 (to a minimum of 0). Discard the DAGGER card from your <Hero Area>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.REGULAR);
						
						card.getActions().add(action);
						// }}			
					// }}
				
					hero.getDeck().add(card);
				}			
				// }}
				
				// {{ Dagger Throw
				for (i=0; i<2; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Dagger Throw");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_DAGGER_THROW);
	
					card.setResolveCost(0);
					card.setHeroCardType(HC_Type.BASIC);
					
					card.getKeywords().add(Keyword.COMBAT);
					card.getKeywords().add(Keyword.MOBILITY);
					card.getKeywords().add(Keyword.PRECISION);
					
					card.setAreaRestriction(HC_AreaRestriction.HAND);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Add +<1 Range> to any Action of a DAGGER card that your resolve. <+>1<Focus>. Discard the DAGGER card from your <Hero Area>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.REFLEX);
						
						card.getActions().add(action);
						// }}			
					// }}
				
					hero.getDeck().add(card);
				}			
				// }}
				
				// {{ Preparation
				for (i=0; i<2; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Preparation");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_PREPARATION);
	
					card.setResolveCost(0);
					card.setHeroCardType(HC_Type.BASIC);
					
					card.getKeywords().add(Keyword.COMBAT);
					card.getKeywords().add(Keyword.EVASION);
					card.getKeywords().add(Keyword.MOBILITY);
					card.getKeywords().add(Keyword.PRECISION);
					card.getKeywords().add(Keyword.STEALTH);
					
					card.setAreaRestriction(HC_AreaRestriction.HAND);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Draw 1 card.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.REFLEX);
						
						card.getActions().add(action);
						// }}			
					// }}
				
					hero.getDeck().add(card);
				}			
				// }}
				
				// {{ Shadowstep
				for (i=0; i<2; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Shadowstep");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_SHADOWSTEP);
	
					card.setResolveCost(0);
					card.setHeroCardType(HC_Type.BASIC);
					
					card.getKeywords().add(Keyword.COMBAT);
					card.getKeywords().add(Keyword.MOBILITY);
					card.getKeywords().add(Keyword.STEALTH);
					
					card.setAreaRestriction(HC_AreaRestriction.HAND);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Discard 1 MOBILITY or STEALTH card to <->1/2<Focus>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}			
					// }}
				
					hero.getDeck().add(card);
				}			
				// }}
				
			// }}

			// {{ Advanced Feats
				
				// {{ Forager
				for (i=0; i<1; i++){
					
					// {{
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Forager");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_FORAGER);
	
					card.setResolveCost(1);
					card.setHeroCardType(HC_Type.ADVANCED);
					
					card.getKeywords().add(Keyword.MOBILITY);
					card.getKeywords().add(Keyword.STEALTH);
					card.getKeywords().add(Keyword.TRAINING);
					
					card.setAreaRestriction(HC_AreaRestriction.F);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Place in your <Hero Area>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}		
						
						// {{ Action 2
						action = new HC_Action();
						action.setText("Whenever a Reward is drawn, you may draw 1 additional Reward. Place 1 under the Reward deck and resolve the other.");
						action.setCardArea(HC_Area.HERO_AREA);
						action.setRange(3);
						action.setType(HC_ActionType.REFLEX);
						
						card.getActions().add(action);
						// }}	
					// }}
				
					hero.getAdvancedFeats().add(card);
				}			
				// }}
				
				// {{ Versatility
				for (i=0; i<2; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Versatility");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_VERSATILITY);
	
					card.setResolveCost(1);
					card.setHeroCardType(HC_Type.ADVANCED);
					
					card.getKeywords().add(Keyword.ARCANE);
					card.getKeywords().add(Keyword.COMBAT);
					card.getKeywords().add(Keyword.DIVINEEVASION);
					card.getKeywords().add(Keyword.MOBILITY);
					card.getKeywords().add(Keyword.PRECISION);
					card.getKeywords().add(Keyword.STEALTH);
					
					card.setAreaRestriction(HC_AreaRestriction.HAND);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Draw 1 card.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.REFLEX);
						
						card.getActions().add(action);
						// }}		
					// }}
				
					hero.getAdvancedFeats().add(card);
				}			
				// }}
				
				// {{ Double Stab
				for (i=0; i<2; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Double Stab");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_DOUBLE_STAB);
	
					card.setResolveCost(2);
					card.setHeroCardType(HC_Type.ADVANCED);
					
					card.getKeywords().add(Keyword.COMBAT);
					card.getKeywords().add(Keyword.STEALTH);
					
					card.setAreaRestriction(HC_AreaRestriction.HAND);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("If your resolve any Action of a DAGGER card that deals <Physical Damage>, deal additional +1<Physical Damage>, <+>1<Focus>. You may discard another DAGGER card from your <Hand> or your <Hero Area>, to deal +2<Physical Damage>, <+>2<Focus>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.REFLEX);
						
						card.getActions().add(action);
						// }}		
					// }}
				
					hero.getAdvancedFeats().add(card);
				}			
				// }}
				
				// {{ Lead Astray
				for (i=0; i<2; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Lead Astray");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_LEAD_ASTRAY);
	
					card.setResolveCost(2);
					card.setHeroCardType(HC_Type.ADVANCED);
					
					card.getKeywords().add(Keyword.STEALTH);
					card.getKeywords().add(Keyword.MANIPULATION);
					card.getKeywords().add(Keyword.MOBILITY);
					
					card.setAreaRestriction(HC_AreaRestriction.HAND);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("If you have no more that 4<Focus> and there are no Enemies in your <Hero Area>, you may move 1 Enemy to the bottom of the Enemy deck. <+>1<Focus>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(2);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}		
					// }}
				
					hero.getAdvancedFeats().add(card);
				}			
				// }}
				
				// {{ Misdirection
				for (i=0; i<2; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Misdirection");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_MISDIRECTION);
	
					card.setResolveCost(2);
					card.setHeroCardType(HC_Type.ADVANCED);
					
					card.getKeywords().add(Keyword.MOBILITY);
					card.getKeywords().add(Keyword.STEALTH);
					
					card.setAreaRestriction(HC_AreaRestriction.HAND);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Discard 1 MOBILITY card to exchange the position of 2 Enemies in any 2 different Areas.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(2);
						action.setType(HC_ActionType.REFLEX);
						
						card.getActions().add(action);
						// }}		
						
						// {{ Action 2
						action = new HC_Action();
						action.setText("Discard 1 STEALTH card to move 1 Enemy from your <Hero Area> to <Quest Area>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.REFLEX);
						
						card.getActions().add(action);
						// }}		
					// }}
				
					hero.getAdvancedFeats().add(card);
				}			
				// }}
				
				// {{ Waste Not
				for (i=0; i<1; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Waste Not");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_WASTE_NOT);
	
					card.setResolveCost(2);
					card.setHeroCardType(HC_Type.ADVANCED);
					
					card.getKeywords().add(Keyword.MOBILITY);
					card.getKeywords().add(Keyword.STEALTH);
					card.getKeywords().add(Keyword.TRAINING);
					
					card.setAreaRestriction(HC_AreaRestriction.F);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Place in your <Hero Area>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}		
						
						// {{ Action 2
						action = new HC_Action();
						action.setText("If your resolve an Action of a REWARD card in your <Hero Area> and your are required to return that card to the Reward deck, you may Bury it instead. Draw 1 card. <+>1<Focus>.");
						action.setCardArea(HC_Area.HERO_AREA);
						action.setRange(1);
						action.setType(HC_ActionType.REFLEX);
						
						card.getActions().add(action);
						// }}		
					// }}
				
					hero.getAdvancedFeats().add(card);
				}			
				// }}
				
				// {{ Lost in the Shadows
				for (i=0; i<2; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Lost in the Shadows");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_LOST_IN_THE_SHADOWS);
	
					card.setResolveCost(3);
					card.setHeroCardType(HC_Type.ADVANCED);
					
					card.getKeywords().add(Keyword.COMBAT);
					card.getKeywords().add(Keyword.STEALTH);
					card.getKeywords().add(Keyword.MANIPULATION);
					card.getKeywords().add(Keyword.MOBILITY);
					card.getKeywords().add(Keyword.PRECISION);
					
					card.setAreaRestriction(HC_AreaRestriction.HAND);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("<->1<Reinforcements>, to a minimum of 0.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(2);
						action.setType(HC_ActionType.REFLEX);
						
						card.getActions().add(action);
						// }}		
						
						// {{ Action 2
						action = new HC_Action();
						action.setText("Bury this card to <->3<Reinforcements>, to a minimum of 0.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(2);
						action.setType(HC_ActionType.REFLEX);
						
						card.getActions().add(action);
						// }}		
					// }}
				
					hero.getAdvancedFeats().add(card);
				}			
				// }}
				
				// {{ Short Blade Mastery
				for (i=0; i<1; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Short Blade Mastery");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_SHORT_BLADE_MASTERY);
	
					card.setResolveCost(3);
					card.setHeroCardType(HC_Type.ADVANCED);
					
					card.getKeywords().add(Keyword.COMBAT);
					card.getKeywords().add(Keyword.STEALTH);
					
					card.setAreaRestriction(HC_AreaRestriction.F);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Place in your <Hero Area>. <+>1<Focus>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}		
						
						// {{ Action 2
						action = new HC_Action();
						action.setText("If your resolve any Action of a DAGGER card that deals <Physical Damage>, deal additional +1<Physical Damage>, <+>1<Focus>. ");
						action.setCardArea(HC_Area.HERO_AREA);
						action.setRange(1);
						action.setType(HC_ActionType.REFLEX);
						
						card.getActions().add(action);
						// }}		
					// }}
				
					hero.getAdvancedFeats().add(card);
				}			
				// }}
				
				// {{ Canny Diversion
				for (i=0; i<1; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Canny Diversion");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_CANNY_DIVERSION);
	
					card.setResolveCost(4);
					card.setHeroCardType(HC_Type.ADVANCED);
					
					card.getKeywords().add(Keyword.MOBILITY);
					card.getKeywords().add(Keyword.STEALTH);
					card.getKeywords().add(Keyword.PRECISION);
					card.getKeywords().add(Keyword.TRAINING);
					
					card.setAreaRestriction(HC_AreaRestriction.F);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Place in your <Hero Area>. <->1<Focus>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}		
						
						// {{ Action 2
						action = new HC_Action();
						action.setText("If you have no more than 2<Focus>, remove 1<Objectiv Token> from this card to perform 1 additional Regular Action.");
						action.setCardArea(HC_Area.HERO_AREA);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}		
					// }}
				
					hero.getAdvancedFeats().add(card);
				}			
				// }}
				
				// {{ I Walk Through Shadows
				for (i=0; i<1; i++){
					
					// {{ Cards
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("I Walk Through Shadows");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_I_WALK_THROUGH_SHADOWS);
	
					card.setResolveCost(4);
					card.setHeroCardType(HC_Type.ADVANCED);
					
					card.getKeywords().add(Keyword.MOBILITY);
					card.getKeywords().add(Keyword.STEALTH);
					card.getKeywords().add(Keyword.PRECISION);
					card.getKeywords().add(Keyword.TRAINING);
					
					card.setAreaRestriction(HC_AreaRestriction.F);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Place in your <Hero Area>. <->1<Focus>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}		
						
						// {{ Action 2
						action = new HC_Action();
						action.setText("Whenever you use your Shadowy Was special ability, place an additional +1<Objectiv Token>.");
						action.setCardArea(HC_Area.HERO_AREA);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}		
					// }}
				
					hero.getAdvancedFeats().add(card);
				}			
				// }}
				
			// }}
				
			// {{ Reward Gear
				
				// {{ Masterwork Toolkit
				for (i=0; i<1; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Masterwork Toolkit");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_MASTERWORK_TOOLKIT);
	
					card.setResolveCost(2);
					card.setHeroCardType(HC_Type.REWARD);
					
					card.getKeywords().add(Keyword.GEAR);
					card.getKeywords().add(Keyword.REWARD);
					card.getKeywords().add(Keyword.TOOLKIT);
					
					card.setAreaRestriction(HC_AreaRestriction.INF);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Place in your <Hero Area>. <+>1<Focus>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}		
						
						// {{ Action 2
						action = new HC_Action();
						action.setText("Reveal 4 cards from your deck. Add 1 to your hand. Place the rest back on top of your deck in any order. Place this card on the top or at the bottom of your deck.");
						action.setCardArea(HC_Area.HERO_AREA);
						action.setRange(1);
						action.setType(HC_ActionType.FREE);
						
						card.getActions().add(action);
						// }}		
					// }}
				
					hero.getAdvancedFeats().add(card);
				}			
				// }}
				
				// {{ Venomtongue
				for (i=0; i<1; i++){
					
					// {{ Card
					card=new HeroCard();
					card.setID(nextFreeHeroCardID);
					nextFreeHeroCardID++;
					
					card.setName("Venomtongue");
					card.setCardEnum(HC_IdentifierEnum.HC_CROW_VENOMTONGUE);
	
					card.setResolveCost(2);
					card.setHeroCardType(HC_Type.REWARD);
					
					card.getKeywords().add(Keyword.DAGGER);
					card.getKeywords().add(Keyword.GEAR);
					card.getKeywords().add(Keyword.PIERCING);
					card.getKeywords().add(Keyword.POISONOUS);
					card.getKeywords().add(Keyword.REWARD);
					card.getKeywords().add(Keyword.WEAPON);
					
					card.setAreaRestriction(HC_AreaRestriction.B);
					// }}
					
					// {{ Actions
						// {{ Action 1
						action = new HC_Action();
						action.setText("Place in your <Hero Area>. <+>2<Focus>.");
						action.setCardArea(HC_Area.HAND);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}		
						
						// {{ Action 2
						action = new HC_Action();
						action.setText("Deal 1<Physical Damage>, <+>2<Focus>. You may discard 1 COMBAT card to deal +1<Physical Damage>, <+>2<Focus>, or to place 1<Poison Token> on the targeted Enemy.");
						action.setCardArea(HC_Area.HERO_AREA);
						action.setRange(1);
						action.setType(HC_ActionType.FAST);
						
						card.getActions().add(action);
						// }}	
						
						// {{ Action 2
						action = new HC_Action();
						action.setText("Deal 3<Physical Damage>, <+>1<Focus>. Place 1<Posion Token> on the targeted Enemy. You may discard 2 COMBAT cards to deal +1<Physical Damage>, <+>1<Focus> for each card so discarded.");
						action.setCardArea(HC_Area.HERO_AREA);
						action.setRange(1);
						action.setType(HC_ActionType.REGULAR);
						
						card.getActions().add(action);
						// }}
					// }}
				
					hero.getAdvancedFeats().add(card);
				}			
				// }}
				
			// }}
				
			break;
			// }}
		
			// {{ Hareag the Frostmage	
			case HAREAG:
			break;
			// }}
			
			default:
		}
		
		// Shuffle Deck
		hero.setDeck(shuffleCardsHero(hero.getDeck()));
		// Draw Starting Hand
		for (int j=0; j<hero.getDrawLimit();j++){
			if (hero.getDeck().isEmpty()==false){
				hero.getHand().add(hero.getDeck().get(0));
				hero.getDeck().remove(0);
			}
		}
	}

	public List<Enemy> shuffleCardsEnemy(List<Enemy> source){
		List<Enemy> dest = new LinkedList<Enemy>();
		Random random = new Random(System.currentTimeMillis());
		int randomInt;
		while (source.isEmpty()==false){
			randomInt=random.nextInt(source.size());
			dest.add(source.get(randomInt));
			source.remove(randomInt);
		}
		
		return dest;
	}
	
	public List<HeroCard> shuffleCardsHero(List<HeroCard> source){
		List<HeroCard> dest = new LinkedList<HeroCard>();
		Random random = new Random(System.currentTimeMillis());
		int randomInt;
		while (source.isEmpty()==false){
			randomInt=random.nextInt(source.size());
			dest.add(source.get(randomInt));
			source.remove(randomInt);
		}
		
		return dest;
	}
	
	private void setEnemyCountInfo(){
		enemyCountInfo = new LinkedList<EnemyCountInfo>();
		
		// {{ Blue Enemies	
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.BLUE, EnemyType.BLOODSCORNE_VAMPIRE, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.BLUE, EnemyType.BONESORROW_MAGUS, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.BLUE, EnemyType.BONESORROW_SHOOTER, 6));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.BLUE, EnemyType.BONESORROW_WARRIOR, 6));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.BLUE, EnemyType.CURSED_WALKER, 6));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.BLUE, EnemyType.RAVENOUS_DRAUGR, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.BLUE, EnemyType.UNDEAD_LOREMASTER, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.BLUE, EnemyType.VAMPIRE_BAT_SWARM, 4));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.BLUE, EnemyType.VAMPIRE_HOUND, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.BLUE, EnemyType.VENGEFUL_BANSHEE, 3));
		// }}
		
		// {{ Green Enemies	
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.GREEN, EnemyType.DIRE_WOLF, 4));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.GREEN, EnemyType.FELLSTALKER, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.GREEN, EnemyType.GHOREN_BLOOD_TRACKER, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.GREEN, EnemyType.GHOREN_RAGECALLER, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.GREEN, EnemyType.GHOREN_SLINGER, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.GREEN, EnemyType.GHOREN_SMALLHORN, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.GREEN, EnemyType.GHOREN_WARRIOR, 6));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.GREEN, EnemyType.ICE_REAVER, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.GREEN, EnemyType.TRACKER_HOUND, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.GREEN, EnemyType.WILD_ICEHOUND, 6));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.GREEN, EnemyType.WILDLANDS_SHAMAN, 3));
		// }}
		
		// {{ Green Enemies	
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.RED, EnemyType.BLACK_COVEN_CASTER, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.RED, EnemyType.BLACKWOOD_AMBUSHER, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.RED, EnemyType.BLACKWOOD_ASSASSIN, 4));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.RED, EnemyType.BLACKWOOD_CHANGELING, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.RED, EnemyType.BLACKWOOD_CUTTPURSE, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.RED, EnemyType.BLACKWOOD_FIGHTER, 6));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.RED, EnemyType.BLACKWOOD_HARASSER, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.RED, EnemyType.BLACKWOOD_MAGEHUNTER, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.RED, EnemyType.RENEGADE_FLAMECASTER, 6));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.RED, EnemyType.TWISTED_CURSEBEARER, 3));
		enemyCountInfo.add(new EnemyCountInfo(EnemySuit.RED, EnemyType.TWISTED_LASHER, 3));
		// }}
	}
	
	private void createEnemyDecks(GameController gc){
		
		Enemy enemy;
		
		for (EnemyCountInfo countInfo : enemyCountInfo){
			for (int i=0; i<countInfo.getCount(); i++){
				switch (countInfo.getEnemySuit()){
					// {{ Blue Enemies
					case BLUE:
						switch (countInfo.getEnemyType()){
						
							// {{ Bloodscorne Vampire
							case BLOODSCORNE_VAMPIRE:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Bloodscorne Vampire");
								enemy.setEnemyType(EnemyType.BLOODSCORNE_VAMPIRE);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLOODSCORNE_VAMPIRE);
								enemy.setEnemySuit(EnemySuit.BLUE);
								enemy.setResolve(2);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
								enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
								enemy.appendEnemyKeyword(EnemyKeyword.VAMPIRE);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.FLAME);
								enemy.appendVunerability(Keyword.DIVINE);
								// Life values
								enemy.getLife().setValueBase(4);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.MAGICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(2);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.VAMPIRIC,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								blueEnemies.add(enemy);
							break;
							// }}

							// {{ Bonesorrow Magus
							case BONESORROW_MAGUS:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Bonesorrow Magus");
								enemy.setEnemyType(EnemyType.BONESORROW_MAGUS);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.BONESORROW_MAGUS);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.BLUE);
								enemy.setResolve(2);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.SKELETON);
								enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
								enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.BLUNT);
								enemy.appendVunerability(Keyword.FLAME);
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.MAGICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(2);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.REANIMATE,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								blueEnemies.add(enemy);
							break;
							// }}

							// {{ Bonesorrow Shooter
							case BONESORROW_SHOOTER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Bonesorrow Shooter");
								enemy.setEnemyType(EnemyType.BONESORROW_SHOOTER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.BONESORROW_SHOOTER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.BLUE);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.MINDLESS);
								enemy.appendEnemyKeyword(EnemyKeyword.PIERCING);
								enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
								enemy.appendEnemyKeyword(EnemyKeyword.SKELETON);
								enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.BLUNT);
								enemy.appendVunerability(Keyword.FLAME);
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SKIRMISHER,EnemyAbilityArea.ANY));
								nextFreeEnemyID++;
								blueEnemies.add(enemy);
							break;
							// }}

							// {{ Bonesorrow Warrior
							case BONESORROW_WARRIOR:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Bonesorrow Warrior");
								enemy.setEnemyType(EnemyType.BONESORROW_WARRIOR);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.BONESORROW_WARRIOR);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.BLUE);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BLADED);
								enemy.appendEnemyKeyword(EnemyKeyword.MINDLESS);
								enemy.appendEnemyKeyword(EnemyKeyword.SKELETON);
								enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.BLUNT);
								enemy.appendVunerability(Keyword.FLAME);
								// Life values
								enemy.getLife().setValueBase(4);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								nextFreeEnemyID++;
								blueEnemies.add(enemy);
							break;
							// }}

							// {{ Cursed Walker
							case CURSED_WALKER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Cursed Walker");
								enemy.setEnemyType(EnemyType.CURSED_WALKER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.CURSED_WALKER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.BLUE);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BLUNT);
								enemy.appendEnemyKeyword(EnemyKeyword.MINDLESS);
								enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
								enemy.appendEnemyKeyword(EnemyKeyword.ZOMBIE);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.FLAME);
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(3);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SLOW,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								blueEnemies.add(enemy);
							break;
							// }}

							// {{ Ravenous Draugr
							case RAVENOUS_DRAUGR:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Ravenous Draugr");
								enemy.setEnemyType(EnemyType.RAVENOUS_DRAUGR);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.RAVENOUS_DRAUGR);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.BLUE);
								enemy.setResolve(2);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BLADED);
								enemy.appendEnemyKeyword(EnemyKeyword.MINDLESS);
								enemy.appendEnemyKeyword(EnemyKeyword.RENDING);
								enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
								enemy.appendEnemyKeyword(EnemyKeyword.ZOMBIE);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.FLAME);
								// Life values
								enemy.getLife().setValueBase(4);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(3);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(2);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.RAVENOUS,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								blueEnemies.add(enemy);
							break;
							// }}

							// {{ Undead Loremaster
							case UNDEAD_LOREMASTER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Undead Loremaster");
								enemy.setEnemyType(EnemyType.UNDEAD_LOREMASTER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.UNDEAD_LOREMASTER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.BLUE);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
								enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
								enemy.appendEnemyKeyword(EnemyKeyword.ZOMBIE);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.BLUNT);
								enemy.appendVunerability(Keyword.FLAME);
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.MAGICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.DARK_HEALER,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								blueEnemies.add(enemy);
							break;
							// }}

							// {{Vampire Bat Swarm
							case VAMPIRE_BAT_SWARM:
								enemy = new Enemy();
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Vampire Bat Swarm");
								enemy.setEnemyType(EnemyType.VAMPIRE_BAT_SWARM);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.VAMPIRE_BAT_SWARM);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.BLUE);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BEAST);
								enemy.appendEnemyKeyword(EnemyKeyword.PIERCING);
								enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
								enemy.appendEnemyKeyword(EnemyKeyword.VAMPIRE);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.FLAME);
								enemy.appendVunerability(Keyword.LIGHTNING);
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(0);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(0);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SWARM,EnemyAbilityArea.HERO));
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.VAMPIRIC,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								blueEnemies.add(enemy);
							break;
							// }}

							// {{ Vampire Hound
							case VAMPIRE_HOUND:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Vampire Hound");
								enemy.setEnemyType(EnemyType.VAMPIRE_HOUND);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.VAMPIRE_HOUND);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.BLUE);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BEAST);
								enemy.appendEnemyKeyword(EnemyKeyword.HOUND);
								enemy.appendEnemyKeyword(EnemyKeyword.RENDING);
								enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
								enemy.appendEnemyKeyword(EnemyKeyword.VAMPIRE);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.FLAME);
								enemy.appendVunerability(Keyword.LIGHTNING);
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.RELENTLESS,EnemyAbilityArea.HERO));
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.VAMPIRIC,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								blueEnemies.add(enemy);
							break;
							// }}

							// {{Vengeful Banshee
							case VENGEFUL_BANSHEE:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Vengeful Banshee");
								enemy.setEnemyType(EnemyType.VENGEFUL_BANSHEE);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.VENGEFUL_BANSHEE);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.BLUE);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
								enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
								enemy.appendEnemyKeyword(EnemyKeyword.WRAITH);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.ARCANE);
								enemy.appendVunerability(Keyword.DIVINE);
								// Life values
								enemy.getLife().setValueBase(2);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.MAGICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(2);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.VENGEFUL_SHRIEK,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								blueEnemies.add(enemy);
							break;
							// }}
							
							default:
						}
					break;
					// }}
					
					// {{ Green Enemies
					case GREEN:
						switch (countInfo.getEnemyType()){
						
							// {{ Dire Wolf
							case DIRE_WOLF:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Dire Wolf");
								enemy.setEnemyType(EnemyType.DIRE_WOLF);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.DIRE_WOLF);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.GREEN);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BEAST);
								enemy.appendEnemyKeyword(EnemyKeyword.RENDING);
								enemy.appendEnemyKeyword(EnemyKeyword.WOLF);
								// Vulnerabilities
	
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.RELENTLESS,EnemyAbilityArea.HERO));
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.BEAST_RIDE,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								greenEnemies.add(enemy);
							break;
							// }}
							
							// {{ Fellstalker
							case FELLSTALKER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Fellstalker");
								enemy.setEnemyType(EnemyType.FELLSTALKER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.FELLSTALKER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.GREEN);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.FLAME);
								enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
								enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.LIGHTNING);
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(0);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SCAVENGER,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								greenEnemies.add(enemy);
							break;
							// }}
							
							// {{ Ghoren Blood Tracker
							case GHOREN_BLOOD_TRACKER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Ghoren Blood Tracker");
								enemy.setEnemyType(EnemyType.GHOREN_BLOOD_TRACKER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.GHOREN_BLOOD_TRACKER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.GREEN);
								enemy.setResolve(2);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BEASTMAN);
								enemy.appendEnemyKeyword(EnemyKeyword.BLADED);
								enemy.appendEnemyKeyword(EnemyKeyword.HUNTER);
								enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
								enemy.appendEnemyKeyword(EnemyKeyword.WILDLANDER);
								// Vulnerabilities
								
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(3);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.RELENTLESS,EnemyAbilityArea.HERO));
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SKIRMISHER,EnemyAbilityArea.ANY));
								nextFreeEnemyID++;
								greenEnemies.add(enemy);
							break;
							// }}
							
							// {{ Ghoren Ragecaller
							case GHOREN_RAGECALLER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Ghoren Ragecaller");
								enemy.setEnemyType(EnemyType.GHOREN_RAGECALLER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.GHOREN_RAGECALLER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.GREEN);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BEASTMAN);
								enemy.appendEnemyKeyword(EnemyKeyword.SHAMAN);
								enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
								enemy.appendEnemyKeyword(EnemyKeyword.WILDLANDER);
								// Vulnerabilities
								
								// Life values
								enemy.getLife().setValueBase(2);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.MAGICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.RATTLE,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								greenEnemies.add(enemy);
							break;
							// }}
							
							// {{ Ghoren Slinger
							case GHOREN_SLINGER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Ghoren Slinger");
								enemy.setEnemyType(EnemyType.GHOREN_SLINGER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.GHOREN_SLINGER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.GREEN);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BEASTMAN);
								enemy.appendEnemyKeyword(EnemyKeyword.BLUNT);
								enemy.appendEnemyKeyword(EnemyKeyword.HUNTER);
								enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
								enemy.appendEnemyKeyword(EnemyKeyword.WILDLANDER);
								// Vulnerabilities
								
								// Life values
								enemy.getLife().setValueBase(2);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SKIRMISHER,EnemyAbilityArea.ANY));
								nextFreeEnemyID++;
								greenEnemies.add(enemy);
							break;
							// }}
							
							// {{ Ghoren Smallhorn
							case GHOREN_SMALLHORN:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Ghoren Smallhorn");
								enemy.setEnemyType(EnemyType.GHOREN_SMALLHORN);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.GHOREN_SMALLHORN);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.GREEN);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BEASTMAN);
								enemy.appendEnemyKeyword(EnemyKeyword.BLUNT);
								enemy.appendEnemyKeyword(EnemyKeyword.WILDLANDER);
								// Vulnerabilities
								
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(0);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.BLOOD_FURY,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								greenEnemies.add(enemy);
							break;
							// }}
							
							// {{ Ghoren Warrior
							case GHOREN_WARRIOR:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Ghoren Warrior");
								enemy.setEnemyType(EnemyType.GHOREN_WARRIOR);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.GHOREN_WARRIOR);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.GREEN);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BLADED);
								enemy.appendEnemyKeyword(EnemyKeyword.BLUNT);
								enemy.appendEnemyKeyword(EnemyKeyword.BEASTMAN);
								enemy.appendEnemyKeyword(EnemyKeyword.WILDLANDER);
								// Vulnerabilities
								
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(3);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								nextFreeEnemyID++;
								greenEnemies.add(enemy);
							break;
							// }}
							
							// {{ Ice Reaver
							case ICE_REAVER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Ice Reaver");
								enemy.setEnemyType(EnemyType.ICE_REAVER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.ICE_REAVER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.GREEN);
								enemy.setResolve(2);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BEAST);
								enemy.appendEnemyKeyword(EnemyKeyword.FROST);
								enemy.appendEnemyKeyword(EnemyKeyword.RENDING);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.FLAME);
								// Life values
								enemy.getLife().setValueBase(4);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(4);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(3);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SLOW,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								greenEnemies.add(enemy);
							break;
							// }}
							
							// {{ Tracker Hound
							case TRACKER_HOUND:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Tracker Hound");
								enemy.setEnemyType(EnemyType.TRACKER_HOUND);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.TRACKER_HOUND);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.GREEN);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BEAST);
								enemy.appendEnemyKeyword(EnemyKeyword.HOUND);
								enemy.appendEnemyKeyword(EnemyKeyword.RENDING);
								// Vulnerabilities
	
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.RELENTLESS,EnemyAbilityArea.HERO));
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.PURSUIT,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								greenEnemies.add(enemy);
							break;
							// }}
							
							// {{ Wild Icehound
							case WILD_ICEHOUND:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Wild Icehound");
								enemy.setEnemyType(EnemyType.WILD_ICEHOUND);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.WILD_ICEHOUND);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.GREEN);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BEAST);
								enemy.appendEnemyKeyword(EnemyKeyword.HOUND);
								enemy.appendEnemyKeyword(EnemyKeyword.FROST);
								enemy.appendEnemyKeyword(EnemyKeyword.RENDING);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.FLAME);
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(1);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.PACK_HUNTER,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								greenEnemies.add(enemy);
							break;
							// }}
							
							// {{ Wildlands Shaman
							case WILDLANDS_SHAMAN:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Wildlands Shaman");
								enemy.setEnemyType(EnemyType.WILDLANDS_SHAMAN);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.WILDLANDS_SHAMAN);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.GREEN);
								enemy.setResolve(2);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BEASTMAN);
								enemy.appendEnemyKeyword(EnemyKeyword.SHAMAN);
								enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
								enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
								enemy.appendEnemyKeyword(EnemyKeyword.WILDLANDER);
								// Vulnerabilities
								
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(2);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.DARK_PRESENCE,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								greenEnemies.add(enemy);
							break;
							// }}
						
						default:
					}
					break;
					// }}
					
					// {{ Red Enemies
					case RED:
						switch (countInfo.getEnemyType()){
						
							// {{ Black Coven Caster
							case BLACK_COVEN_CASTER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Black Coven Caster");
								enemy.setEnemyType(EnemyType.BLACK_COVEN_CASTER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACK_COVEN_CASTER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.RED);
								enemy.setResolve(2);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
								enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
								enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
								// Vulnerabilities

								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.MAGICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(2);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.CURSED_BOLT,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								redEnemies.add(enemy);
							break;
							// }}
							
							// {{ Blackwood Ambusher
							case BLACKWOOD_AMBUSHER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Blackwood Ambusher");
								enemy.setEnemyType(EnemyType.BLACKWOOD_AMBUSHER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_AMBUSHER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.RED);
								enemy.setResolve(2);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
								enemy.appendEnemyKeyword(EnemyKeyword.PIERCING);
								enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
								// Vulnerabilities

								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.AMBUSH,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								redEnemies.add(enemy);
							break;
							// }}
							
							// {{ Blackwood Assassin
							case BLACKWOOD_ASSASSIN:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Blackwood Assassin");
								enemy.setEnemyType(EnemyType.BLACKWOOD_ASSASSIN);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_ASSASSIN);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.RED);
								enemy.setResolve(2);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
								enemy.appendEnemyKeyword(EnemyKeyword.BLADED);
								enemy.appendEnemyKeyword(EnemyKeyword.PIERCING);
								enemy.appendEnemyKeyword(EnemyKeyword.POISON);
								enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
								// Vulnerabilities

								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(3);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(2);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.VENOMOUS,EnemyAbilityArea.HERO));
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SKIRMISHER,EnemyAbilityArea.ANY));
								nextFreeEnemyID++;
								redEnemies.add(enemy);
							break;
							// }}
							
							// {{ Blackwood Changeling
							case BLACKWOOD_CHANGELING:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Blackwood Changeling");
								enemy.setEnemyType(EnemyType.BLACKWOOD_CHANGELING);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_CHANGELING);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.RED);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
								enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
								enemy.appendEnemyKeyword(EnemyKeyword.WRAITH);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.DIVINE);
								// Life values
								enemy.getLife().setValueBase(1);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.MAGICAL);
								enemy.getAttack().setValueBase(4);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SHAPESHIFT,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								redEnemies.add(enemy);
							break;
							// }}
							
							// {{ Blackwood Cuttpurse
							case BLACKWOOD_CUTTPURSE:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Blackwood Cuttpurse");
								enemy.setEnemyType(EnemyType.BLACKWOOD_CUTTPURSE);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_CUTTPURSE);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.RED);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
								enemy.appendEnemyKeyword(EnemyKeyword.PIERCING);
								enemy.appendEnemyKeyword(EnemyKeyword.THIEF);
								// Vulnerabilities

								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.THIEVERY,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								redEnemies.add(enemy);
							break;
							// }}
							
							// {{ Blackwood Fighter
							case BLACKWOOD_FIGHTER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Blackwood Fighter");
								enemy.setEnemyType(EnemyType.BLACKWOOD_FIGHTER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_FIGHTER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.RED);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
								enemy.appendEnemyKeyword(EnemyKeyword.BLUNT);
								enemy.appendEnemyKeyword(EnemyKeyword.SLAVER);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.LIGHTNING);
								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SHIELD_SLAM,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								redEnemies.add(enemy);
							break;
							// }}
							
							// {{ Blackwood Harasser
							case BLACKWOOD_HARASSER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Blackwood Harasser");
								enemy.setEnemyType(EnemyType.BLACKWOOD_HARASSER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_HARASSER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.RED);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
								enemy.appendEnemyKeyword(EnemyKeyword.BLADED);
								enemy.appendEnemyKeyword(EnemyKeyword.THIEF);
								// Vulnerabilities

								// Life values
								enemy.getLife().setValueBase(3);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SKULDUGGERY,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								redEnemies.add(enemy);
							break;
							// }}
							
							// {{ Blackwood Magehunter
							case BLACKWOOD_MAGEHUNTER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Blackwood Magehunter");
								enemy.setEnemyType(EnemyType.BLACKWOOD_MAGEHUNTER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_MAGEHUNTER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.RED);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
								enemy.appendEnemyKeyword(EnemyKeyword.HUNTER);
								enemy.appendEnemyKeyword(EnemyKeyword.PIERCING);
								enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
								// Vulnerabilities

								// Life values
								enemy.getLife().setValueBase(2);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(3);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.MANA_DRAIN,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								redEnemies.add(enemy);
							break;
							// }}
							
							// {{ Renegade Flamecaster
							case RENEGADE_FLAMECASTER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Renegade Flamecaster");
								enemy.setEnemyType(EnemyType.RENEGADE_FLAMECASTER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.RENEGADE_FLAMECASTER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.RED);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
								enemy.appendEnemyKeyword(EnemyKeyword.FLAME);
								enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
								enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.DIVINE);
								// Life values
								enemy.getLife().setValueBase(2);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.MAGICAL);
								enemy.getAttack().setValueBase(2);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilitiesenemy.
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.FIREBOLT,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								redEnemies.add(enemy);
							break;
							// }}
							
							// {{ Twisted Cursebearer
							case TWISTED_CURSEBEARER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Twisted Cursebearer");
								enemy.setEnemyType(EnemyType.TWISTED_CURSEBEARER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.TWISTED_CURSEBEARER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.RED);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.CURSED);
								enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.DIVINE);
								// Life values
								enemy.getLife().setValueBase(2);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.MAGICAL);
								enemy.getAttack().setValueBase(1);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(1);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(2);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.CURSE_OF_WEAKNESS,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								redEnemies.add(enemy);
							break;
							// }}
							
							// {{ Twisted Lasher
							case TWISTED_LASHER:
								enemy = new Enemy();
								// General
								enemy.setEnemyID(nextFreeEnemyID);
								enemy.setName("Twisted Lasher");
								enemy.setEnemyType(EnemyType.TWISTED_LASHER);
								enemyTypeByID.put(nextFreeEnemyID, EnemyType.TWISTED_LASHER);
								gc.getEnemyController().addEnemyName(enemy.getName());
								enemy.setEnemySuit(EnemySuit.RED);
								enemy.setResolve(1);
								enemy.setSpecialEnemy(false);
								enemy.setEnraged(false);
								enemy.setTargetRange(1);
								// Keywords
								enemy.appendEnemyKeyword(EnemyKeyword.BLUNT);
								enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
								enemy.appendEnemyKeyword(EnemyKeyword.CURSED);
								enemy.appendEnemyKeyword(EnemyKeyword.MISTCRAFTED);
								// Vulnerabilities
								enemy.appendVunerability(Keyword.DIVINE);
								enemy.appendVunerability(Keyword.LIGHTNING);
								// Life values
								enemy.getLife().setValueBase(4);
								enemy.getLife().setValueMod(enemy.getLife().getValueBase());
								enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
								// Attack values
								enemy.getAttack().setType(AttackType.PHYSICAL);
								enemy.getAttack().setValueBase(3);
								enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
								// Resistances Values
								enemy.getResistances().setPhysicalResBase(2);
								enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
								enemy.getResistances().setMagicalResBase(1);
								enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
								// Abilities
								enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.FLAILING,EnemyAbilityArea.HERO));
								nextFreeEnemyID++;
								redEnemies.add(enemy);
							break;
							// }}
							
							default:
								
						}
						
					break;
					// }}
					
					default:
				}
				
				
				
			}
		}
	}

	public Enemy createSpecificEnemy(GameController gc, EnemyType enemyType){

		Enemy enemy = new Enemy();
		
		switch (enemyType){
						
			// {{ Bloodscorne Vampire
			case BLOODSCORNE_VAMPIRE:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Bloodscorne Vampire");
				enemy.setEnemyType(EnemyType.BLOODSCORNE_VAMPIRE);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLOODSCORNE_VAMPIRE);
				enemy.setEnemySuit(EnemySuit.BLUE);
				enemy.setResolve(2);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
				enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
				enemy.appendEnemyKeyword(EnemyKeyword.VAMPIRE);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.FLAME);
				enemy.appendVunerability(Keyword.DIVINE);
				// Life values
				enemy.getLife().setValueBase(4);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.MAGICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(2);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.VAMPIRIC,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
	
			// {{ Bonesorrow Magus
			case BONESORROW_MAGUS:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Bonesorrow Magus");
				enemy.setEnemyType(EnemyType.BONESORROW_MAGUS);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.BONESORROW_MAGUS);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.BLUE);
				enemy.setResolve(2);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.SKELETON);
				enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
				enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.BLUNT);
				enemy.appendVunerability(Keyword.FLAME);
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.MAGICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(2);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.REANIMATE,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
	
			// {{ Bonesorrow Shooter
			case BONESORROW_SHOOTER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Bonesorrow Shooter");
				enemy.setEnemyType(EnemyType.BONESORROW_SHOOTER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.BONESORROW_SHOOTER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.BLUE);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.MINDLESS);
				enemy.appendEnemyKeyword(EnemyKeyword.PIERCING);
				enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
				enemy.appendEnemyKeyword(EnemyKeyword.SKELETON);
				enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.BLUNT);
				enemy.appendVunerability(Keyword.FLAME);
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SKIRMISHER,EnemyAbilityArea.ANY));
				nextFreeEnemyID++;
			break;
			// }}
	
			// {{ Bonesorrow Warrior
			case BONESORROW_WARRIOR:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Bonesorrow Warrior");
				enemy.setEnemyType(EnemyType.BONESORROW_WARRIOR);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.BONESORROW_WARRIOR);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.BLUE);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BLADED);
				enemy.appendEnemyKeyword(EnemyKeyword.MINDLESS);
				enemy.appendEnemyKeyword(EnemyKeyword.SKELETON);
				enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.BLUNT);
				enemy.appendVunerability(Keyword.FLAME);
				// Life values
				enemy.getLife().setValueBase(4);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				nextFreeEnemyID++;
			break;
			// }}
	
			// {{ Cursed Walker
			case CURSED_WALKER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Cursed Walker");
				enemy.setEnemyType(EnemyType.CURSED_WALKER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.CURSED_WALKER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.BLUE);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BLUNT);
				enemy.appendEnemyKeyword(EnemyKeyword.MINDLESS);
				enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
				enemy.appendEnemyKeyword(EnemyKeyword.ZOMBIE);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.FLAME);
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(3);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SLOW,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
	
			// {{ Ravenous Draugr
			case RAVENOUS_DRAUGR:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Ravenous Draugr");
				enemy.setEnemyType(EnemyType.RAVENOUS_DRAUGR);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.RAVENOUS_DRAUGR);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.BLUE);
				enemy.setResolve(2);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BLADED);
				enemy.appendEnemyKeyword(EnemyKeyword.MINDLESS);
				enemy.appendEnemyKeyword(EnemyKeyword.RENDING);
				enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
				enemy.appendEnemyKeyword(EnemyKeyword.ZOMBIE);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.FLAME);
				// Life values
				enemy.getLife().setValueBase(4);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(3);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(2);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.RAVENOUS,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
	
			// {{ Undead Loremaster
			case UNDEAD_LOREMASTER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Undead Loremaster");
				enemy.setEnemyType(EnemyType.UNDEAD_LOREMASTER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.UNDEAD_LOREMASTER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.BLUE);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
				enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
				enemy.appendEnemyKeyword(EnemyKeyword.ZOMBIE);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.BLUNT);
				enemy.appendVunerability(Keyword.FLAME);
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.MAGICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.DARK_HEALER,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
	
			// {{Vampire Bat Swarm
			case VAMPIRE_BAT_SWARM:
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Vampire Bat Swarm");
				enemy.setEnemyType(EnemyType.VAMPIRE_BAT_SWARM);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.VAMPIRE_BAT_SWARM);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.BLUE);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BEAST);
				enemy.appendEnemyKeyword(EnemyKeyword.PIERCING);
				enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
				enemy.appendEnemyKeyword(EnemyKeyword.VAMPIRE);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.FLAME);
				enemy.appendVunerability(Keyword.LIGHTNING);
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(0);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(0);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SWARM,EnemyAbilityArea.HERO));
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.VAMPIRIC,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
	
			// {{ Vampire Hound
			case VAMPIRE_HOUND:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Vampire Hound");
				enemy.setEnemyType(EnemyType.VAMPIRE_HOUND);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.VAMPIRE_HOUND);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.BLUE);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BEAST);
				enemy.appendEnemyKeyword(EnemyKeyword.HOUND);
				enemy.appendEnemyKeyword(EnemyKeyword.RENDING);
				enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
				enemy.appendEnemyKeyword(EnemyKeyword.VAMPIRE);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.FLAME);
				enemy.appendVunerability(Keyword.LIGHTNING);
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.RELENTLESS,EnemyAbilityArea.HERO));
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.VAMPIRIC,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
	
			// {{Vengeful Banshee
			case VENGEFUL_BANSHEE:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Vengeful Banshee");
				enemy.setEnemyType(EnemyType.VENGEFUL_BANSHEE);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.VENGEFUL_BANSHEE);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.BLUE);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
				enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
				enemy.appendEnemyKeyword(EnemyKeyword.WRAITH);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.ARCANE);
				enemy.appendVunerability(Keyword.DIVINE);
				// Life values
				enemy.getLife().setValueBase(2);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.MAGICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(2);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.VENGEFUL_SHRIEK,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Dire Wolf
			case DIRE_WOLF:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Dire Wolf");
				enemy.setEnemyType(EnemyType.DIRE_WOLF);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.DIRE_WOLF);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.GREEN);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BEAST);
				enemy.appendEnemyKeyword(EnemyKeyword.RENDING);
				enemy.appendEnemyKeyword(EnemyKeyword.WOLF);
				// Vulnerabilities
	
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.RELENTLESS,EnemyAbilityArea.HERO));
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.BEAST_RIDE,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Fellstalker
			case FELLSTALKER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Fellstalker");
				enemy.setEnemyType(EnemyType.FELLSTALKER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.FELLSTALKER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.GREEN);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.FLAME);
				enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
				enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.LIGHTNING);
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(0);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SCAVENGER,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Ghoren Blood Tracker
			case GHOREN_BLOOD_TRACKER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Ghoren Blood Tracker");
				enemy.setEnemyType(EnemyType.GHOREN_BLOOD_TRACKER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.GHOREN_BLOOD_TRACKER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.GREEN);
				enemy.setResolve(2);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BEASTMAN);
				enemy.appendEnemyKeyword(EnemyKeyword.BLADED);
				enemy.appendEnemyKeyword(EnemyKeyword.HUNTER);
				enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
				enemy.appendEnemyKeyword(EnemyKeyword.WILDLANDER);
				// Vulnerabilities
				
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(3);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.RELENTLESS,EnemyAbilityArea.HERO));
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SKIRMISHER,EnemyAbilityArea.ANY));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Ghoren Ragecaller
			case GHOREN_RAGECALLER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Ghoren Ragecaller");
				enemy.setEnemyType(EnemyType.GHOREN_RAGECALLER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.GHOREN_RAGECALLER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.GREEN);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BEASTMAN);
				enemy.appendEnemyKeyword(EnemyKeyword.SHAMAN);
				enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
				enemy.appendEnemyKeyword(EnemyKeyword.WILDLANDER);
				// Vulnerabilities
				
				// Life values
				enemy.getLife().setValueBase(2);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.MAGICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.RATTLE,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Ghoren Slinger
			case GHOREN_SLINGER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Ghoren Slinger");
				enemy.setEnemyType(EnemyType.GHOREN_SLINGER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.GHOREN_SLINGER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.GREEN);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BEASTMAN);
				enemy.appendEnemyKeyword(EnemyKeyword.BLUNT);
				enemy.appendEnemyKeyword(EnemyKeyword.HUNTER);
				enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
				enemy.appendEnemyKeyword(EnemyKeyword.WILDLANDER);
				// Vulnerabilities
				
				// Life values
				enemy.getLife().setValueBase(2);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SKIRMISHER,EnemyAbilityArea.ANY));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Ghoren Smallhorn
			case GHOREN_SMALLHORN:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Ghoren Smallhorn");
				enemy.setEnemyType(EnemyType.GHOREN_SMALLHORN);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.GHOREN_SMALLHORN);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.GREEN);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BEASTMAN);
				enemy.appendEnemyKeyword(EnemyKeyword.BLUNT);
				enemy.appendEnemyKeyword(EnemyKeyword.WILDLANDER);
				// Vulnerabilities
				
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(0);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.BLOOD_FURY,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Ghoren Warrior
			case GHOREN_WARRIOR:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Ghoren Warrior");
				enemy.setEnemyType(EnemyType.GHOREN_WARRIOR);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.GHOREN_WARRIOR);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.GREEN);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BLADED);
				enemy.appendEnemyKeyword(EnemyKeyword.BLUNT);
				enemy.appendEnemyKeyword(EnemyKeyword.BEASTMAN);
				enemy.appendEnemyKeyword(EnemyKeyword.WILDLANDER);
				// Vulnerabilities
				
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(3);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Ice Reaver
			case ICE_REAVER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Ice Reaver");
				enemy.setEnemyType(EnemyType.ICE_REAVER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.ICE_REAVER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.GREEN);
				enemy.setResolve(2);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BEAST);
				enemy.appendEnemyKeyword(EnemyKeyword.FROST);
				enemy.appendEnemyKeyword(EnemyKeyword.RENDING);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.FLAME);
				// Life values
				enemy.getLife().setValueBase(4);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(4);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(3);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SLOW,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Tracker Hound
			case TRACKER_HOUND:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Tracker Hound");
				enemy.setEnemyType(EnemyType.TRACKER_HOUND);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.TRACKER_HOUND);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.GREEN);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BEAST);
				enemy.appendEnemyKeyword(EnemyKeyword.HOUND);
				enemy.appendEnemyKeyword(EnemyKeyword.RENDING);
				// Vulnerabilities
	
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.RELENTLESS,EnemyAbilityArea.HERO));
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.PURSUIT,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Wild Icehound
			case WILD_ICEHOUND:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Wild Icehound");
				enemy.setEnemyType(EnemyType.WILD_ICEHOUND);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.WILD_ICEHOUND);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.GREEN);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BEAST);
				enemy.appendEnemyKeyword(EnemyKeyword.HOUND);
				enemy.appendEnemyKeyword(EnemyKeyword.FROST);
				enemy.appendEnemyKeyword(EnemyKeyword.RENDING);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.FLAME);
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(1);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.PACK_HUNTER,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Wildlands Shaman
			case WILDLANDS_SHAMAN:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Wildlands Shaman");
				enemy.setEnemyType(EnemyType.WILDLANDS_SHAMAN);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.WILDLANDS_SHAMAN);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.GREEN);
				enemy.setResolve(2);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BEASTMAN);
				enemy.appendEnemyKeyword(EnemyKeyword.SHAMAN);
				enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
				enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
				enemy.appendEnemyKeyword(EnemyKeyword.WILDLANDER);
				// Vulnerabilities
				
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(2);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.DARK_PRESENCE,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
		
			// {{ Black Coven Caster
			case BLACK_COVEN_CASTER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Black Coven Caster");
				enemy.setEnemyType(EnemyType.BLACK_COVEN_CASTER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACK_COVEN_CASTER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.RED);
				enemy.setResolve(2);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
				enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
				enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
				// Vulnerabilities
		
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.MAGICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(2);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.CURSED_BOLT,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Blackwood Ambusher
			case BLACKWOOD_AMBUSHER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Blackwood Ambusher");
				enemy.setEnemyType(EnemyType.BLACKWOOD_AMBUSHER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_AMBUSHER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.RED);
				enemy.setResolve(2);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
				enemy.appendEnemyKeyword(EnemyKeyword.PIERCING);
				enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
				// Vulnerabilities
		
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.AMBUSH,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Blackwood Assassin
			case BLACKWOOD_ASSASSIN:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Blackwood Assassin");
				enemy.setEnemyType(EnemyType.BLACKWOOD_ASSASSIN);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_ASSASSIN);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.RED);
				enemy.setResolve(2);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
				enemy.appendEnemyKeyword(EnemyKeyword.BLADED);
				enemy.appendEnemyKeyword(EnemyKeyword.PIERCING);
				enemy.appendEnemyKeyword(EnemyKeyword.POISON);
				enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
				// Vulnerabilities
		
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(3);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(2);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.VENOMOUS,EnemyAbilityArea.HERO));
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SKIRMISHER,EnemyAbilityArea.ANY));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Blackwood Changeling
			case BLACKWOOD_CHANGELING:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Blackwood Changeling");
				enemy.setEnemyType(EnemyType.BLACKWOOD_CHANGELING);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_CHANGELING);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.RED);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
				enemy.appendEnemyKeyword(EnemyKeyword.UNDEAD);
				enemy.appendEnemyKeyword(EnemyKeyword.WRAITH);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.DIVINE);
				// Life values
				enemy.getLife().setValueBase(1);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.MAGICAL);
				enemy.getAttack().setValueBase(4);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SHAPESHIFT,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Blackwood Cuttpurse
			case BLACKWOOD_CUTTPURSE:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Blackwood Cuttpurse");
				enemy.setEnemyType(EnemyType.BLACKWOOD_CUTTPURSE);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_CUTTPURSE);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.RED);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
				enemy.appendEnemyKeyword(EnemyKeyword.PIERCING);
				enemy.appendEnemyKeyword(EnemyKeyword.THIEF);
				// Vulnerabilities
		
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.THIEVERY,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Blackwood Fighter
			case BLACKWOOD_FIGHTER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Blackwood Fighter");
				enemy.setEnemyType(EnemyType.BLACKWOOD_FIGHTER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_FIGHTER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.RED);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
				enemy.appendEnemyKeyword(EnemyKeyword.BLUNT);
				enemy.appendEnemyKeyword(EnemyKeyword.SLAVER);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.LIGHTNING);
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SHIELD_SLAM,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Blackwood Harasser
			case BLACKWOOD_HARASSER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Blackwood Harasser");
				enemy.setEnemyType(EnemyType.BLACKWOOD_HARASSER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_HARASSER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.RED);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
				enemy.appendEnemyKeyword(EnemyKeyword.BLADED);
				enemy.appendEnemyKeyword(EnemyKeyword.THIEF);
				// Vulnerabilities
		
				// Life values
				enemy.getLife().setValueBase(3);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.SKULDUGGERY,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Blackwood Magehunter
			case BLACKWOOD_MAGEHUNTER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Blackwood Magehunter");
				enemy.setEnemyType(EnemyType.BLACKWOOD_MAGEHUNTER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.BLACKWOOD_MAGEHUNTER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.RED);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
				enemy.appendEnemyKeyword(EnemyKeyword.HUNTER);
				enemy.appendEnemyKeyword(EnemyKeyword.PIERCING);
				enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
				// Vulnerabilities
		
				// Life values
				enemy.getLife().setValueBase(2);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(3);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.MANA_DRAIN,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Renegade Flamecaster
			case RENEGADE_FLAMECASTER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Renegade Flamecaster");
				enemy.setEnemyType(EnemyType.RENEGADE_FLAMECASTER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.RENEGADE_FLAMECASTER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.RED);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
				enemy.appendEnemyKeyword(EnemyKeyword.FLAME);
				enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
				enemy.appendEnemyKeyword(EnemyKeyword.RANGED);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.DIVINE);
				// Life values
				enemy.getLife().setValueBase(2);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.MAGICAL);
				enemy.getAttack().setValueBase(2);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilitiesenemy.
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.FIREBOLT,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Twisted Cursebearer
			case TWISTED_CURSEBEARER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Twisted Cursebearer");
				enemy.setEnemyType(EnemyType.TWISTED_CURSEBEARER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.TWISTED_CURSEBEARER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.RED);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.CURSED);
				enemy.appendEnemyKeyword(EnemyKeyword.SORCERER);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.DIVINE);
				// Life values
				enemy.getLife().setValueBase(2);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.MAGICAL);
				enemy.getAttack().setValueBase(1);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(1);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(2);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.CURSE_OF_WEAKNESS,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			// {{ Twisted Lasher
			case TWISTED_LASHER:
				// General
				enemy.setEnemyID(nextFreeEnemyID);
				enemy.setName("Twisted Lasher");
				enemy.setEnemyType(EnemyType.TWISTED_LASHER);
				enemyTypeByID.put(nextFreeEnemyID, EnemyType.TWISTED_LASHER);
				gc.getEnemyController().addEnemyName(enemy.getName());
				enemy.setEnemySuit(EnemySuit.RED);
				enemy.setResolve(1);
				enemy.setSpecialEnemy(false);
				enemy.setEnraged(false);
				enemy.setTargetRange(1);
				// Keywords
				enemy.appendEnemyKeyword(EnemyKeyword.BLUNT);
				enemy.appendEnemyKeyword(EnemyKeyword.BRIGAND);
				enemy.appendEnemyKeyword(EnemyKeyword.CURSED);
				enemy.appendEnemyKeyword(EnemyKeyword.MISTCRAFTED);
				// Vulnerabilities
				enemy.appendVunerability(Keyword.DIVINE);
				enemy.appendVunerability(Keyword.LIGHTNING);
				// Life values
				enemy.getLife().setValueBase(4);
				enemy.getLife().setValueMod(enemy.getLife().getValueBase());
				enemy.getLife().setValueCurrent(enemy.getLife().getValueBase());
				// Attack values
				enemy.getAttack().setType(AttackType.PHYSICAL);
				enemy.getAttack().setValueBase(3);
				enemy.getAttack().setValueMod(enemy.getAttack().getValueBase());
				// Resistances Values
				enemy.getResistances().setPhysicalResBase(2);
				enemy.getResistances().setPhysicalResMod(enemy.getResistances().getPhysicalResBase());
				enemy.getResistances().setMagicalResBase(1);
				enemy.getResistances().setMagicalResMod(enemy.getResistances().getMagicalResBase());
				// Abilities
				enemy.getAbilities().add(new EnemyAbility(EnemyAbilityType.FLAILING,EnemyAbilityArea.HERO));
				nextFreeEnemyID++;
			break;
			// }}
			
			default:
				
		}

		return enemy;
	}
	
	// }}

}
