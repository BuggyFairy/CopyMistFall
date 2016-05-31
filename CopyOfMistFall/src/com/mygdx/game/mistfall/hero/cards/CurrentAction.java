package com.mygdx.game.mistfall.hero.cards;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.controller.GameController;

public class CurrentAction {

	// {{ Attributes
	private HC_ActionFolder initialAction;
	private HC_ActionFolder resolvedAction;
	private List<HC_ActionFolder> modActions;
	
	private List<EnemyTarget> targetStats;
	
	private List<Integer> generatedHeroFocus;
	
	private boolean allConditionsMet;
	// }}
	
	// {{ Constructor
	public CurrentAction(GameController gc) {
		initialAction = new HC_ActionFolder();
		resolvedAction = new HC_ActionFolder();
		modActions = new LinkedList<HC_ActionFolder>();
		targetStats = new LinkedList<EnemyTarget>();
		allConditionsMet=false;
		generatedHeroFocus = new LinkedList<Integer>();
		for (int i=0;i<gc.getHeroes().size();i++){
			generatedHeroFocus.add(0);
		}
	}
	// }}
	
	// {{ Getters & Setters
	public HC_ActionFolder getMainAction() {
		return initialAction;
	}
	public void setMainAction(HC_ActionFolder mainAction) {
		this.initialAction = mainAction;
	}
	public HC_ActionFolder getResolvedAction() {
		return resolvedAction;
	}
	public void setResolvedAction(HC_ActionFolder resolvedAction) {
		this.resolvedAction = resolvedAction;
	}
	public List<HC_ActionFolder> getModAction() {
		return modActions;
	}
	public void setModAction(List<HC_ActionFolder> modAction) {
		this.modActions = modAction;
	}
	public List<EnemyTarget> getTargetStats() {
		return targetStats;
	}
	public void setTargetStats(List<EnemyTarget> targetStats) {
		this.targetStats = targetStats;
	}
	public List<Integer> getGeneratedHeroFocus() {
		return generatedHeroFocus;
	}
	public void setGeneratedHeroFocus(List<Integer> generatedHeroFocus) {
		this.generatedHeroFocus = generatedHeroFocus;
	}
	public void addGeneratedHeroFocus(int value, int heroID){
		generatedHeroFocus.set(heroID, generatedHeroFocus.get(heroID)+value);
	}
	public boolean isAllConditionsMet() {
		return allConditionsMet;
	}
	public void setAllConditionsMet(boolean allConditionsMet) {
		this.allConditionsMet = allConditionsMet;
	}
	// }}
	
	// {{ Other Methods
	public void clear(){
		initialAction.removeAction();
		resolvedAction.removeAction();
		modActions.clear();
		targetStats.clear();
		allConditionsMet=false;
		for (int i=0;i<generatedHeroFocus.size();i++){
			generatedHeroFocus.set(i, 0);
		}
	}
	// }}
	
}
