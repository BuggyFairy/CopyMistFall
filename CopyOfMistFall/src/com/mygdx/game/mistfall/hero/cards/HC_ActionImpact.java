package com.mygdx.game.mistfall.hero.cards;

import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionEffect;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionEffectTarget;
import com.mygdx.game.mistfall.model.enums.Condition;

public class HC_ActionImpact {

	private HC_ActionEffect effect;
	private int value;
	private Condition condition;
	private HC_ActionEffectTarget target;
	
	
	
	
	public HC_ActionEffect getEffect() {
		return effect;
	}
	public void setEffect(HC_ActionEffect effect) {
		this.effect = effect;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	public HC_ActionEffectTarget getTarget() {
		return target;
	}
	public void setTarget(HC_ActionEffectTarget target) {
		this.target = target;
	}
	

	
	
	
}
