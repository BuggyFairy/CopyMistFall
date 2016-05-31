package com.mygdx.game.mistfall.controller;

import java.util.List;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.cards.CurrentAction;
import com.mygdx.game.mistfall.hero.cards.EnemyTarget;
import com.mygdx.game.mistfall.hero.cards.HC_Action;
import com.mygdx.game.mistfall.hero.cards.HC_ActionFolder;
import com.mygdx.game.mistfall.hero.cards.HC_ActionRequirement;
import com.mygdx.game.mistfall.hero.cards.HC_ActionStructure2;
import com.mygdx.game.mistfall.hero.cards.HC_ActionStructure3;
import com.mygdx.game.mistfall.hero.cards.HeroCard;
import com.mygdx.game.mistfall.hero.cards.enums.HC_ActionType;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionEffectTarget;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionRequirementKeyword;
import com.mygdx.game.mistfall.model.enums.GamePhase;
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
	private int errorCode;
	
	private List<HeroCard> tempList;
	
	
	// {{ Getters and Setters
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	// }}
	
	
	
	
	// {{ Source Action
	
	/**
	 * updates the error code of all actions for all heroes.
	 */
	public void sourceActionUpdatePlayability(GameController gc){
		
		for (Hero hero : gc.getHeroes()){
			// Check Hero Area
			for (HeroCard HC : hero.getGearAndFeats().getA()){
				for (HC_Action action : HC.getActions()){
					action.setErrorCode(actionPlayabilitySource(gc, hero, HC, action));
				}
			}
			for (HeroCard HC : hero.getGearAndFeats().getB()){
				for (HC_Action action : HC.getActions()){
					action.setErrorCode(actionPlayabilitySource(gc, hero, HC, action));
				}
			}
			for (HeroCard HC : hero.getGearAndFeats().getC()){
				for (HC_Action action : HC.getActions()){
					action.setErrorCode(actionPlayabilitySource(gc, hero, HC, action));
				}
			}
			for (HeroCard HC : hero.getGearAndFeats().getD()){
				for (HC_Action action : HC.getActions()){
					action.setErrorCode(actionPlayabilitySource(gc, hero, HC, action));
				}
			}
			for (HeroCard HC : hero.getGearAndFeats().getE()){
				for (HC_Action action : HC.getActions()){
					action.setErrorCode(actionPlayabilitySource(gc, hero, HC, action));
				}
			}
			for (HeroCard HC : hero.getGearAndFeats().getF()){
				for (HC_Action action : HC.getActions()){
					action.setErrorCode(actionPlayabilitySource(gc, hero, HC, action));
				}
			}
			for (HeroCard HC : hero.getGearAndFeats().getG()){
				for (HC_Action action : HC.getActions()){
					action.setErrorCode(actionPlayabilitySource(gc, hero, HC, action));
				}
			}
			for (HeroCard HC : hero.getGearAndFeats().getInf()){
				for (HC_Action action : HC.getActions()){
					action.setErrorCode(actionPlayabilitySource(gc, hero, HC, action));
				}
			}
			
			// Check Hand
			for (HeroCard HC : hero.getHand().getCards()){
				for (HC_Action action : HC.getActions()){
					action.setErrorCode(actionPlayabilitySource(gc, hero, HC, action));
				}
			}
		}
		
	}
	
	/**
	 * Saves the chosen action as the current source action.
	 * Action playability must be checked beforehand.
	 */
	public void sourceActionChosen(GameController gc, Hero hero, HeroCard heroCardChosen, HC_Action actionChosen){
		// 1. Add action to the current action structure
		addAction(gc, hero, heroCardChosen, actionChosen);
		// 2. Refresh enemy targets
		refreshTargets();
	}

	/**
	 * Carries out the current action.
	 * If the action can be carried out must be checked beforehand.
	 */
	public void sourceActionConfirm(){
		// 1. Carry out current action
		carryOutCurentAction();
		// 2. Clear current action
		currentAction.clear();
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
	
	public void EnemyTargetChoose(GameController gc, Hero sourceHero, HC_ActionEffectTarget enemyTarget){
		
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
		}
		switch (enemyTarget){
			case MAIN_ENEMY:
				
			break;
			
			case SECOND_ENEMY:
			break;
			
			default:
			break;
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
		refreshTargets();
	}
	
	public void EnemyTargetCancel(){
		//TODO
	}
	// }}
	
	
	
	
	
	
	
	
	

	
	
	/**
	 * adds action to the current action structure
	 */
	private void addAction(GameController gc, Hero hero, HeroCard heroCardChosen, HC_Action actionChosen){
		switch (actionChosen.getActionKeyword1()){
		
			case INTERACT_WITH_ENEMY:
				switch(actionChosen.getActionKeyword2()){		
					case MODIFY_ACTION:
						currentAction.getModAction().add(new HC_ActionFolder());
						currentAction.getModAction().get(currentAction.getModAction().size()-1).setAction(heroCardChosen, actionChosen, hero);
					break;
					default:
						currentAction.getMainAction().setAction(heroCardChosen, actionChosen, hero);
					break;				
				}
			break;
			default:
				currentAction.getMainAction().setAction(heroCardChosen, actionChosen, hero);
			break;		
		}
	}
	

	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
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
		
		// {{ 2. Check if the action is playable based on it's action type
		if (action.getType()==HC_ActionType.FAST || action.getType()==HC_ActionType.REGULAR){
			if (gc.getCurrentGamePhase()!=GamePhase.HERO_PHASE ){
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
		
		// {{ 3. Check if the action is playable based on the Action Keywords
		switch (action.getActionKeyword1()){		
			case PLACE_HERO_AREA:
				switch (HC.getAreaRestriction()){
					case A:
						if (hero.getGearAndFeats().getA().size()>=hero.getGearAndFeats().getA_max()){
							return 101; // Not enough space in Area A
						}
					case B:
						if (hero.getGearAndFeats().getB().size()>=hero.getGearAndFeats().getB_max()){
							return 102; // Not enough space in Area B
						}
					case C:
						if (hero.getGearAndFeats().getC().size()>=hero.getGearAndFeats().getC_max()){
							return 103; // Not enough space in Area C
						}
					case D:
						if (hero.getGearAndFeats().getD().size()>=hero.getGearAndFeats().getD_max()){
							return 104; // Not enough space in Area D
						}
					case E:
						if (hero.getGearAndFeats().getE().size()>=hero.getGearAndFeats().getE_max()){
							return 105; // Not enough space in Area E
						}
					case F:
						if (hero.getGearAndFeats().getF().size()>=hero.getGearAndFeats().getF_max()){
							return 106; // Not enough space in Area F
						}
					case G:
						if (hero.getGearAndFeats().getG().size()>=hero.getGearAndFeats().getG_max()){
							return 107; // Not enough space in Area G
						}

					default:
					break;
				}			
			break;
			
			default:
			break;		
		}
		
		// }}
		
		return 0;
	}
	
	
	
	
	
	
	
	
	
	private void refreshTargets(){
		
		// TODO: set "allConditionsMet" in "currentAction"
		
		// Clear Targets
		currentAction.getTargetStats().clear();
		//
		if (currentAction.getMainAction().actionExistent()){
			
			course = currentAction.getMainAction().getAction().getCourse().get(currentAction.getMainAction().getAction().getPosInCourse());
			
			switch (course.getActionKeyword1()){
				case INTERACT_WITH_ENEMY:
					
					// Go Through all parallel under-actions of the action and check Requirements
					applyShit=true;
					for (int i=0; i<course.getOptions().size();i++){
						if(course.getOptions().get(i).getChoiceChosen()>-1){
							if(checkRequirements(course.getOptions().get(i).getChoices().get(course.getOptions().get(i).getChoiceChosen()))==true){
								course.getOptions().get(i).setConditionsMet(true);
							}
							else{
								course.getOptions().get(i).setConditionsMet(false);
								if (course.getOptions().get(i).isNecessity()==true){
									applyShit=false;
								}
							}
						}
						else{
							applyShit=false;
						}
					}
					
					
					// Apply Effects of under-actions to targets if all requirements of necessary options have been met
					if(applyShit==true){
						for (int i=0; i<course.getOptions().size();i++){
							if(course.getOptions().get(i).getChoiceChosen()>-1){
								carryOutCurentAction(course.getOptions().get(i).getChoices().get(course.getOptions().get(i).getChoiceChosen()),currentAction.getMainAction().getHero());
							}
						}
					}
					
					
					
					// Check If Chosen Enemies are in Range
					for (int i=0; i<currentAction.getTargetStats().size();i++){
						if (currentAction.getTargetStats().get(i).isEnemyChosen()==true){
							if(currentAction.getTargetStats().get(i).getEnemy().getTargetRange()<=currentAction.getTargetStats().get(i).getRange()){
								
							}
							else{
								// Enemy not in Range
							}
						}
					}
					
				break;
				default:
				break;
			}
		}
		
	}
	
	
	
	private boolean checkRequirements(HC_ActionStructure3 choice){
		for (int i=0; i<choice.getRequirements().size();i++){
			switch (choice.getRequirements().get(i).getRequirement()){
				case CARD_IN:
				break;
				default:
				break;
			}
		}
		return true;
	}
	
	
	private void carryOutCurentAction(){
		
		// Carry out action 
		switch (currentAction.getMainAction().getAction().getActionKeyword1()){
		
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
			
			case PLACE_HERO_AREA:
				
			break;
			
			default:
			break;
		}
		
		// Discard Card
		
		
	}
	
	
	
	
}
