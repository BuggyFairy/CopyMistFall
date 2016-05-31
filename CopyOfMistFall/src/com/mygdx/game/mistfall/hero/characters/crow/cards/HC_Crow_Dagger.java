package com.mygdx.game.mistfall.hero.characters.crow.cards;

import com.mygdx.game.mistfall.hero.cards.HC_Action;
import com.mygdx.game.mistfall.hero.cards.HC_ActionImpact;
import com.mygdx.game.mistfall.hero.cards.HC_ActionRequirement;
import com.mygdx.game.mistfall.hero.cards.HC_ActionStructure2;
import com.mygdx.game.mistfall.hero.cards.HC_ActionStructure3;
import com.mygdx.game.mistfall.hero.cards.HeroCard;
import com.mygdx.game.mistfall.hero.cards.enums.HC_ActionType;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Area;
import com.mygdx.game.mistfall.hero.cards.enums.HC_AreaRestriction;
import com.mygdx.game.mistfall.hero.cards.enums.HC_Type;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionEffect;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionEffectTarget;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionKeyword_1;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionKeyword_2;
import com.mygdx.game.mistfall.hero.cards.enums.actionCourse.HC_ActionRequirementKeyword;
import com.mygdx.game.mistfall.model.enums.Keyword;

public class HC_Crow_Dagger extends HeroCard{

	public HC_Crow_Dagger(int ID) {
		super();
		setID(ID);
		setName("Dagger");

		setResolveCost(0);
		setHeroCardType(HC_Type.GEAR);
		
		getKeywords().add(Keyword.DAGGER);
		getKeywords().add(Keyword.GEAR);
		getKeywords().add(Keyword.PIERCING);
		getKeywords().add(Keyword.WEAPON);
		
		setAreaRestriction(HC_AreaRestriction.B);
		
		
		// Action 1
		getActions().add(new HC_Action());
		getActions().get(0).setType(HC_ActionType.FAST);
		getActions().get(0).setCardArea(HC_Area.HAND);
		getActions().get(0).setRange(1);
		getActions().get(0).setDestAfterUse(HC_Area.NONE);
		getActions().get(0).setText("Place in your <Hero Area>. <+>1<Focus>.");
		getActions().get(0).setActionKeyword1(HC_ActionKeyword_1.PLACE_HERO_AREA);
		getActions().get(0).setActionKeyword2(HC_ActionKeyword_2.NONE);
		

		// ACTION 2
		getActions().add(new HC_Action());
		getActions().get(1).setType(HC_ActionType.FAST);
		getActions().get(1).setCardArea(HC_Area.HERO_AREA);
		getActions().get(1).setRange(1);
		getActions().get(1).setDestAfterUse(HC_Area.NONE);
		getActions().get(1).setText("Deal 1 <Physical Damage>, <+>1<Focus>. You may discard 1 Combat card to deal +1<Physical Damage>, <+>1<Focus>.");
		getActions().get(1).setActionKeyword1(HC_ActionKeyword_1.INTERACT_WITH_ENEMY);
		getActions().get(1).setActionKeyword2(HC_ActionKeyword_2.DEAL_DAMAGE);
		
		getActions().get(1).getOptions().add(new HC_ActionStructure2());
		getActions().get(1).getOptions().get(0).getChoices().add(new HC_ActionStructure3());
		getActions().get(1).getOptions().get(0).setNecessity(true);
		
		getActions().get(1).getOptions().get(0).getChoices().get(0).getImpacts().add(new HC_ActionImpact());
		getActions().get(1).getOptions().get(0).getChoices().get(0).getImpacts().get(0).setEffect(HC_ActionEffect.DAMAGE);
		getActions().get(1).getOptions().get(0).getChoices().get(0).getImpacts().get(0).setValue(1);
		getActions().get(1).getOptions().get(0).getChoices().get(0).getImpacts().get(0).setTarget(HC_ActionEffectTarget.MAIN_ENEMY);
		
		getActions().get(1).getOptions().get(0).getChoices().get(0).getImpacts().add(new HC_ActionImpact());
		getActions().get(1).getOptions().get(0).getChoices().get(0).getImpacts().get(1).setEffect(HC_ActionEffect.FOCUS);
		getActions().get(1).getOptions().get(0).getChoices().get(0).getImpacts().get(1).setValue(1);
		getActions().get(1).getOptions().get(0).getChoices().get(0).getImpacts().get(1).setTarget(HC_ActionEffectTarget.HERO_CROW);
		
		getActions().get(1).getOptions().add(new HC_ActionStructure2());
		getActions().get(1).getOptions().get(1).getChoices().add(new HC_ActionStructure3());
		getActions().get(1).getOptions().get(1).setNecessity(false);
		
		getActions().get(1).getOptions().get(1).getChoices().get(0).getImpacts().add(new HC_ActionImpact());
		getActions().get(1).getOptions().get(1).getChoices().get(0).getImpacts().get(0).setEffect(HC_ActionEffect.DAMAGE);
		getActions().get(1).getOptions().get(1).getChoices().get(0).getImpacts().get(0).setValue(1);
		getActions().get(1).getOptions().get(1).getChoices().get(0).getImpacts().get(0).setTarget(HC_ActionEffectTarget.MAIN_ENEMY);
		
		getActions().get(1).getOptions().get(1).getChoices().get(0).getImpacts().add(new HC_ActionImpact());
		getActions().get(1).getOptions().get(1).getChoices().get(0).getImpacts().get(1).setEffect(HC_ActionEffect.FOCUS);
		getActions().get(1).getOptions().get(1).getChoices().get(0).getImpacts().get(1).setValue(1);
		getActions().get(1).getOptions().get(1).getChoices().get(0).getImpacts().get(1).setTarget(HC_ActionEffectTarget.HERO_CROW);
		
		getActions().get(1).getOptions().get(1).getChoices().get(0).getRequirements().add(new HC_ActionRequirement());
		getActions().get(1).getOptions().get(1).getChoices().get(0).getRequirements().get(0).setRequirement(HC_ActionRequirementKeyword.DISCARD);
		getActions().get(1).getOptions().get(1).getChoices().get(0).getRequirements().get(0).setKeyword(Keyword.COMBAT);
		getActions().get(1).getOptions().get(1).getChoices().get(0).getRequirements().get(0).setValue(1);
		
		// ACTION 3
		getActions().add(new HC_Action());
		getActions().get(2).setType(HC_ActionType.REGULAR);
		getActions().get(2).setCardArea(HC_Area.HERO_AREA);
		getActions().get(2).setRange(1);
		getActions().get(2).setDestAfterUse(HC_Area.NONE);
		getActions().get(2).setText("Deal 2<Physical Damage>, <+>1<Focus>. You may discard up to 2 Combat cards to deal +1<Physical Damage>, <+>1<Focus> for each card so discarded.");
		getActions().get(2).setActionKeyword1(HC_ActionKeyword_1.INTERACT_WITH_ENEMY);
		getActions().get(2).setActionKeyword2(HC_ActionKeyword_2.DEAL_DAMAGE);
		
		getActions().get(2).getOptions().add(new HC_ActionStructure2());
		getActions().get(2).getOptions().get(0).getChoices().add(new HC_ActionStructure3());
		getActions().get(2).getOptions().get(0).setNecessity(true);
		
		getActions().get(2).getOptions().get(0).getChoices().get(0).getImpacts().add(new HC_ActionImpact());
		getActions().get(2).getOptions().get(0).getChoices().get(0).getImpacts().get(0).setEffect(HC_ActionEffect.DAMAGE);
		getActions().get(2).getOptions().get(0).getChoices().get(0).getImpacts().get(0).setValue(2);
		getActions().get(2).getOptions().get(0).getChoices().get(0).getImpacts().get(0).setTarget(HC_ActionEffectTarget.MAIN_ENEMY);
		
		getActions().get(2).getOptions().get(0).getChoices().get(0).getImpacts().add(new HC_ActionImpact());
		getActions().get(2).getOptions().get(0).getChoices().get(0).getImpacts().get(1).setEffect(HC_ActionEffect.FOCUS);
		getActions().get(2).getOptions().get(0).getChoices().get(0).getImpacts().get(1).setValue(1);
		getActions().get(2).getOptions().get(0).getChoices().get(0).getImpacts().get(1).setTarget(HC_ActionEffectTarget.HERO_CROW);
		
		getActions().get(2).getOptions().add(new HC_ActionStructure2());
		getActions().get(2).getOptions().get(1).getChoices().add(new HC_ActionStructure3());
		getActions().get(2).getOptions().get(1).setNecessity(false);
		
		getActions().get(2).getOptions().get(1).getChoices().get(0).getImpacts().add(new HC_ActionImpact());
		getActions().get(2).getOptions().get(1).getChoices().get(0).getImpacts().get(0).setEffect(HC_ActionEffect.DAMAGE_PER_DISCARD);
		getActions().get(2).getOptions().get(1).getChoices().get(0).getImpacts().get(0).setValue(1);
		getActions().get(2).getOptions().get(1).getChoices().get(0).getImpacts().get(0).setTarget(HC_ActionEffectTarget.MAIN_ENEMY);
		
		getActions().get(2).getOptions().get(1).getChoices().get(0).getImpacts().add(new HC_ActionImpact());
		getActions().get(2).getOptions().get(1).getChoices().get(0).getImpacts().get(1).setEffect(HC_ActionEffect.FOCUS_PER_DISCARD);
		getActions().get(2).getOptions().get(1).getChoices().get(0).getImpacts().get(1).setValue(1);
		getActions().get(2).getOptions().get(1).getChoices().get(0).getImpacts().get(1).setTarget(HC_ActionEffectTarget.HERO_CROW);
		
		getActions().get(2).getOptions().get(1).getChoices().get(0).getRequirements().add(new HC_ActionRequirement());
		getActions().get(2).getOptions().get(1).getChoices().get(0).getRequirements().get(0).setRequirement(HC_ActionRequirementKeyword.DISCARD_UP_TO);
		getActions().get(2).getOptions().get(1).getChoices().get(0).getRequirements().get(0).setKeyword(Keyword.COMBAT);
		getActions().get(2).getOptions().get(1).getChoices().get(0).getRequirements().get(0).setValue(2);
		
	}

}
