package com.mygdx.game.mistfall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.cards.CurrentAction;
import com.mygdx.game.mistfall.hero.cards.EnemyTarget;
import com.mygdx.game.mistfall.hero.cards.HC_Action;
import com.mygdx.game.mistfall.hero.cards.HC_ActionFolder;
import com.mygdx.game.mistfall.hero.cards.HC_ActionRequirement;
import com.mygdx.game.mistfall.hero.cards.HC_ActionStructure2;
import com.mygdx.game.mistfall.hero.cards.HeroCard;
import com.mygdx.game.mistfall.hero.cards.enums.HC_ActionType;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionEffectTarget;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionRequirementKeyword;
import com.mygdx.game.mistfall.model.enums.GamePhaseType;
import com.mygdx.game.mistfall.model.enums.Keyword;
import com.mygdx.game.mistfall.model.modifications.ModType;
import com.mygdx.game.mistfall.model.modifications.Modification;

/**
 * @author DADDYisHOME
 *
 */
public class HeroCardController {
	
	
	private CurrentAction currentAction;
	private boolean applyShit;
	private Map<Integer,String> errorCodeString;
	
	private List<HeroCard> tempList;
	
	
	// {{ Getters and Setters
	public Map<Integer,String> getErrorCodeString() {
		return errorCodeString;
	}
	public void setErrorCodeString(Map<Integer,String> errorCodeString) {
		this.errorCodeString = errorCodeString;
	}
	// }}
	
	// {{ Constructor
	public HeroCardController() {
		
		errorCodeString = new HashMap<Integer,String>();
		errorCodeString.put(1, "The action is not a valid source action.");
		errorCodeString.put(5, "Action must be played from the Hero Area");
		errorCodeString.put(6, "Action must be played from the Hand");
		errorCodeString.put(11, "Only playable in the Hero Phase.");
		errorCodeString.put(12, "Only playable in your Hero Turn.");
		errorCodeString.put(13, "No more Regular Actions left.");
		errorCodeString.put(101, "Not enough Space in the Hero Area.");
	}
	// }}
	
	// {{ Initialize
	
	public void initialize(GameController gc){
		currentAction = new CurrentAction(gc);
	}
	
	// }}
	
	// {{ Source Action
	
	/**
	 * updates the error code of all actions for all heroes.
	 */
	public void sourceActionUpdatePlayability(GameController gc){
		
		for (Hero hero : gc.getHeroes()){
			// Check Hero Area
			for (HeroCard HC : hero.getHeroArea()){
				for (HC_Action action : HC.getActions()){
					action.setErrorCode(actionPlayabilitySource(gc, hero, HC, action));
				}
			}
			
			// Check Hand
			for (HeroCard HC : hero.getHand()){
				for (HC_Action action : HC.getActions()){
					action.setErrorCode(actionPlayabilitySource(gc, hero, HC, action));
				}
			}
		}
		
	}
	


	/**
	 * Saves the chosen action as the current source action.
	 * Action playability must be checked beforehand.
	 * Returns true if the source action is carried out immediately
	 */
	public boolean sourceActionChosen(GameController gc, Hero hero, HeroCard heroCardChosen, HC_Action actionChosen, HC_Area area){
		
		// 1. Add action to the current action structure
		currentAction.getMainAction().setAction(heroCardChosen, actionChosen, hero,area);
		// 2. Refresh enemy targets
		refreshCurrentAction();
		// 3. Check for instant Confirm (Action with no choices and no Requirements must not be confirmed)
		for (HC_ActionStructure2 option : actionChosen.getOptions()){
			if (option.getChoices().size()>1){
				return false;
			}
			if (option.getChoiceChosen().getRequirements().isEmpty()==false){
				return false;
			}
		}
		

		sourceActionConfirm(gc);
		
		return true;
	}

	/**
	 * Carries out the current action.
	 * If the action can be carried out must be checked beforehand.
	 */
	public void sourceActionConfirm(GameController gc){

		carryOutCurentAction(gc);

	}
	
	/**
	 * Cancels the current action.
	 */
	public void sourceActionCancel(){
		// TODO: return everything
		currentAction.clear();
	}
	
	// }}
	
	
	
	// {{ Resolved Action
	
	public void resolvedActionChoose(){
		//TODO
	}
	
	public void resolvedActionConfirm(){
		//TODO
	}
	
	public void resolvedActionCancel(){
		// TODO
	}
	// }}
	
	
	// {{ Modification Actions
	
	public void modActionAddChoose(){
		// TODO
	}
	
	public void modActionAddConfirm(){
		// TODO
	}
	
	public void modActionAddCancel(){
		// TODO
	}
	
	public void modActionDelete(){
		// TODO
	}
	// }}
	
	
	// {{ Discard Cards
	
	/**
	 * Returns a list of all Hero card that can be discarded for the current Action option
	 */
	public List<HeroCard> discardCardChoose(Hero hero, HC_ActionStructure2 currentOption ){
		tempList.clear();
		for (HC_ActionRequirement req : currentOption.getChoiceChosen().getRequirements()){
			if (req.getRequirement()==HC_ActionRequirementKeyword.DISCARD
				|| req.getRequirement()==HC_ActionRequirementKeyword.DISCARD_UP_TO){
				// save shit
				
				// Update list
				for (Keyword keyword : req.getKeyword_HC()){
					for (HeroCard card : hero.getHand().getCards()){
						if (card.getKeywords().contains(keyword) && card.isInUse()==false){
							tempList.add(card);
						}
					}
				}
					
			}
		}
		return tempList;
	}
	
	/**
	 * Updates the selected card and the discard list for the current action option for that cards are discarded.
	 * Takes Discard value, and maximum discard count into account.
	 */
	public void discardCardChooseSelected(Hero hero, HeroCard card, HC_ActionStructure2 currentOption){
		for (HC_ActionRequirement req : currentOption.getChoiceChosen().getRequirements()){
			if (req.getRequirement()==HC_ActionRequirementKeyword.DISCARD
				|| req.getRequirement()==HC_ActionRequirementKeyword.DISCARD_UP_TO){
				// If the card is not already selected and the discarded count is lower that the max discarded count
				// Add the card to the discarded list and select it
				if (currentOption.getChoiceChosen().getDiscardedCardsCount()<req.getValue()
					&& card.isSelected()==false){
					currentOption.getChoiceChosen().addDiscardedCard(card,req.getValue());
					card.setSelected(true);
				}
				// If the card is already selected, remove it from the discarded list and deselect it
				if (card.isSelected()==true){
					currentOption.getChoiceChosen().removeDiscardedCard(card);
					card.setSelected(false);
				}
				
				break;
			}
		}
	}
	
	// No need for a Confirm Method, since nothing must be done
	
	// No need for a cancel button, since cards can be manually deselcted.

	// }}
	
	
	
	// {{ Enemy Target
	
	/**
	 * Updates the Target range for all enemy card
	 */
	public void EnemyTargetChoose(GameController gc, Hero sourceHero, HC_ActionEffectTarget enemyTargetKeyword){
		// Get Range of Current Action
		int range=0;
		for (EnemyTarget target : currentAction.getTargetStats()){
			if (target.getTargetKeyword()==enemyTargetKeyword){
				range = target.getRange();
				break;
			}
		}
		
		// Set Target Range for all Hero Enemies
		for (Hero hero : gc.getHeroes()){
			for (Enemy enemy : hero.getHeroEnemies().getCards()){
				if (hero==sourceHero){
					enemy.setTargetRange(1);
				}
				else {
					enemy.setTargetRange(2);
				}
				// Add +1 Range if the enemy got the Skirmisher Mod
				for (Modification enemyMod : enemy.getModifications()){
					if (enemyMod.getModType()==ModType.SKIRMISHER){
						enemy.setTargetRange(enemy.getTargetRange()+1);
						break;
					}
				}
				// Check if in Range
				if(enemy.getTargetRange()<=range){
					enemy.setInRange(true);
				}
				else{
					enemy.setInRange(false);
				}
			}
		}
		
		// Set Target Range for Quest Area Enemies
		for (Enemy enemy : gc.getQuestArea().getQuestAreaEnemies()){
			enemy.setTargetRange(2);
			// Add +1 Range if the enemy got the Skirmisher Mod
			for (Modification enemyMod : enemy.getModifications()){
				if (enemyMod.getModType()==ModType.SKIRMISHER){
					enemy.setTargetRange(enemy.getTargetRange()+1);
					break;
				}
			}
			// Check if in Range
			if(enemy.getTargetRange()<=range){
				enemy.setInRange(true);
			}
			else{
				enemy.setInRange(false);
			}
		}
		
	}
	
	// WIE BEI DISCARDED MACHEN MIT SELECET UND SELECTED ATTRIBUT BEI ENEMIES
	
	/**
	 * Call after the user confirmed or canceled the enemy target choosing
	 * If no enemy was chosen or the choosing was canceled, set the enemy parameter null
	 */
	public void EnemyTargetConfirm(Enemy enemy){
		switch (enemyTargetChoosing){
			case MAIN_ENEMY:
				for (int i=0;i<currentAction.getTargetStats().size();i++){
					if (currentAction.getTargetStats().get(i).getTargetKeyword()==HC_ActionEffectTarget.MAIN_ENEMY){
						if (enemy==null){
							currentAction.getTargetStats().get(i).setEnemy(null);
						}
						else{
							currentAction.getTargetStats().get(i).setEnemy(enemy);
						}
						break;
					}
				}
			break;
			case SECOND_ENEMY:
				for (int i=0;i<currentAction.getTargetStats().size();i++){
					if (currentAction.getTargetStats().get(i).getTargetKeyword()==HC_ActionEffectTarget.SECOND_ENEMY){
						if (enemy==null){
							currentAction.getTargetStats().get(i).setEnemy(null);
						}
						else{
							currentAction.getTargetStats().get(i).setEnemy(enemy);
						}
						break;
					}
				}
			break;
			default:
			break;
		}
		
		// Update Stats
		refreshCurrentAction();
	}
	
	public void EnemyTargetCancel(){
		//TODO
	}
	// }}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Checks if the action is playable as source action.
	 * Returns 0 if true, returns an error code else
	 */
	private int actionPlayabilitySource(GameController gc, Hero hero, HeroCard HC, HC_Action action){
		
		// TODO: Does not take into account if an action is in progress.
		// If an action is in progress players should not be allowed to click a action of a Hero Card.
		
		// {{ 1. Check if the action is a valid source action
		if (action.isSourceAction()==false){
			return 1; // The action is not a source action
		}
		// }}
		
		// {{ 2. Check if the action is in the right Hero Area
		if (hero.getHand().contains(HC)&& action.getCardArea()!=HC_Area.HAND){
			return 5; // Must be played from the Hero Area
		}
		if (hero.getHeroArea().contains(HC)&& action.getCardArea()!=HC_Area.HERO_AREA){
			return 6; // Must be played from the Hand
		}
		// }}
		
		// {{ 3. Check if the action is playable based on it's action type
		if (action.getType()==HC_ActionType.FAST || action.getType()==HC_ActionType.REGULAR){
			if (gc.getGamePhase().getType()!=GamePhaseType.HERO_PHASE ){
				return 11; // Not in the Hero Phase
			}
			if(gc.getActiveHero()!=hero.getHeroID()){
				return 12; // Not your Hero Turn
			}
			if (action.getType()==HC_ActionType.REGULAR && hero.getRegularActionsLeft()<=0){
				return 13; // No more regular Actions left
			}
		}
		// }}
		
		// {{ 4. Check if the action is playable based on the Action Keywords
		switch (action.getActionKeyword1()){		
			case MOVE_CARD:
				switch (action.getActionKeyword2()){
					case PLACE_HERO_AREA:
						if (hero.spaceInHeroArea(HC.getAreaRestriction())==false){
							return 101; // Not enough space in the Hero Area.
						}
					break;
					default:
				}
				
			break;
			
			default:
			break;		
		}
		
		// }}
		
		return 0;
	}
	
	
	
	
	
	
	
	
	
	private void refreshCurrentAction(){
		
		// Exit if there is no source Action
		if (currentAction.getMainAction().getAction()==null){
			return;
		}
		
		// {{ Check Requirements from all active Actions an set conditions met
			
			currentAction.setAllConditionsMet(true);
			boolean conditionsMet;
			HC_ActionFolder actionFolder;
			
			int actionCount = 1+currentAction.getModAction().size();	
			if (currentAction.getResolvedAction().getAction()!=null){
				actionCount++;
			}

			for (int i=0; i<actionCount; i++){
				
				// Set Action Folder
				if (i==0){
					actionFolder=currentAction.getMainAction();
				}
				else if(i==actionCount-1 && currentAction.getResolvedAction().getAction()!=null){
					actionFolder=currentAction.getResolvedAction();
				}
				else{
					actionFolder=currentAction.getModAction().get(i-1);
				}
				
				// Check Requirements for the current Action Folder
				for (HC_ActionStructure2 option : actionFolder.getAction().getOptions()){
					option.setConditionsMet(true);
					for (HC_ActionRequirement requirement : option.getChoiceChosen().getRequirements()){
						
						conditionsMet=false;
						
						switch (requirement.getRequirement()){
							case DISCARD:
							case DISCARD_UP_TO:
								for (HeroCard card : option.getChoiceChosen().getDiscardedCards()){
									for (Keyword keyword : requirement.getKeyword_HC()){
										if (card.getKeywords().contains(keyword)){
											conditionsMet=true;
											break;
										}
									}
								}
							break;
							
							case CARD_IN:
								
								for (HeroCard card : actionFolder.getHero().getSpecificArea(requirement.getArea())){
									for (Keyword keyword : requirement.getKeyword_HC()){
										if (card.getKeywords().contains(keyword)){
											conditionsMet=true;
											break;
										}
									}
								}
								
							break;
							default:
						}
						
						if (conditionsMet==false){
							option.setConditionsMet(false);
							break;
						}
					}
					
					if (option.isNecessity()==true){
						currentAction.setAllConditionsMet(false);
					}
				}
			}
			
			
		
		// }}
		
		// {{ Update Impacts
			// Clear Targets
			currentAction.getTargetStats().clear();
			//
//			if (currentAction.getMainAction().actionExistent()){
//				
//				course = currentAction.getMainAction().getAction().getCourse().get(currentAction.getMainAction().getAction().getPosInCourse());
//				
//				switch (course.getActionKeyword1()){
//					case INTERACT_WITH_ENEMY:
//						
//						// Go Through all parallel under-actions of the action and check Requirements
//						applyShit=true;
//						for (int i=0; i<course.getOptions().size();i++){
//							if(course.getOptions().get(i).getChoiceChosen()>-1){
//								if(checkRequirements(course.getOptions().get(i).getChoices().get(course.getOptions().get(i).getChoiceChosen()))==true){
//									course.getOptions().get(i).setConditionsMet(true);
//								}
//								else{
//									course.getOptions().get(i).setConditionsMet(false);
//									if (course.getOptions().get(i).isNecessity()==true){
//										applyShit=false;
//									}
//								}
//							}
//							else{
//								applyShit=false;
//							}
//						}
//						
//						
//						// Apply Effects of under-actions to targets if all requirements of necessary options have been met
//						if(applyShit==true){
//							for (int i=0; i<course.getOptions().size();i++){
//								if(course.getOptions().get(i).getChoiceChosen()>-1){
//									carryOutCurentAction(course.getOptions().get(i).getChoices().get(course.getOptions().get(i).getChoiceChosen()),currentAction.getMainAction().getHero());
//								}
//							}
//						}
//						
//						
//						
//						// Check If Chosen Enemies are in Range
//						for (int i=0; i<currentAction.getTargetStats().size();i++){
//							if (currentAction.getTargetStats().get(i).isEnemyChosen()==true){
//								if(currentAction.getTargetStats().get(i).getEnemy().getTargetRange()<=currentAction.getTargetStats().get(i).getRange()){
//									
//								}
//								else{
//									// Enemy not in Range
//								}
//							}
//						}
//						
//					break;
//					default:
//					break;
//				}
//			}
		// }}
		
	}
	
	
	private void carryOutCurentAction(GameController gc){
		
		HC_ActionFolder actionFolder = currentAction.getMainAction();
		
		// Carry out action 
		switch (actionFolder.getAction().getActionKeyword1()){
		
			case INTERACT_WITH_ENEMY:
				for (EnemyTarget ET : currentAction.getTargetStats()){
					if (ET.getEnemy()!=null){
						// {{ 1. Apply Damage
						ET.getEnemy().applyDamage(ET.getAttackValue(), ET.getAttackType());
						// }}
						
						// {{ 2. Apply Conditions
						ET.getEnemy().getConditions().addBurning(ET.getConditions().getBurning());
						ET.getEnemy().getConditions().addPoison(ET.getConditions().getPoison());
						ET.getEnemy().getConditions().addDaze(ET.getConditions().getDaze());
						ET.getEnemy().getConditions().addWeakness(ET.getConditions().getWeakness());
						// }}
						
						// {{ 3. Apply possible Vulnerability Damage from Action Keywords
						for (Keyword enemyVulnerabilityKeyword : ET.getEnemy().getVulnerability()){
							for (Keyword attackKeyword : ET.getAttackKeywords()){
								if (enemyVulnerabilityKeyword==attackKeyword){
									ET.getEnemy().applyWounds(1);
									break;
								}
							}
						}
						// }}
					}
				}
			break;
			
			case MOVE_CARD:
				
				switch (actionFolder.getAction().getActionKeyword2()){
					case PLACE_HERO_AREA:
						// Move Card
						moveHeroCard(gc,actionFolder.getHero(),actionFolder.getCard(),actionFolder.getArea(),HC_Area.HERO_AREA);				
					break;
					default:
				}
				
			break;
			
			default:
			break;
		}
		
		// Apply Focus
		for (int i = 0; i<gc.getHeroes().size();i++){
			gc.getHeroes().get(i).focusChange(gc, currentAction.getGeneratedHeroFocus().get(i));
		}
		
		// Discard Card
		
		// Clear Current Action
		currentAction.clear();
	}
	
	
	/**
	 * Moves a Hero Card from one location to another.
	 * All Hero Card movements should be done with this method
	 */
	private void moveHeroCard(GameController gc, Hero hero, HeroCard card, HC_Area source, HC_Area target){
		// Move Card if possible
		if (hero.getSpecificArea(source).contains(card)==true){
			hero.getSpecificArea(target).add(card);
			hero.getSpecificArea(source).remove(card);
		}
		else{
			System.out.println("Card Movement not possible. Card not found in the Specified Source Area");
		}
		// Sort Hand and Hero Area
		boolean sorted=false;
		while(sorted==false){
			sorted=true;
			for (int i=0; i<hero.getHeroArea().size()-1;i++){
				if (hero.getHeroArea().get(i+1).getAreaRestriction().ordinal()<hero.getHeroArea().get(i).getAreaRestriction().ordinal()){
					hero.getHeroArea().add(i, hero.getHeroArea().get(i+1));
					hero.getHeroArea().remove(i+2);
					sorted=false;
				}
				else if(hero.getHeroArea().get(i+1).getAreaRestriction().ordinal()==hero.getHeroArea().get(i).getAreaRestriction().ordinal() &&
						hero.getHeroArea().get(i+1).getName().compareTo(hero.getHeroArea().get(i).getName())<0){
					hero.getHeroArea().add(i, hero.getHeroArea().get(i+1));
					hero.getHeroArea().remove(i+2);
					sorted=false;
				}
			}
		}
		
	}

	public void drawCard(GameController gc, Hero hero){
		if (hero.getDeck().isEmpty()==false){
			moveHeroCard(gc, hero, hero.getDeck().get(0), HC_Area.DECK, HC_Area.HAND);
		}
	}
	
	public void discardCard(GameController gc, Hero hero, HeroCard card, HC_Area source){
		moveHeroCard(gc, hero, card, source, HC_Area.DISCARD);
	}
	
}
