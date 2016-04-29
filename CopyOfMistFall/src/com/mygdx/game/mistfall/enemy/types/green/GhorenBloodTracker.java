package com.mygdx.game.mistfall.enemy.types.green;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.EnemyAbility;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.model.enums.AttackType;

public class GhorenBloodTracker extends Enemy {

	public GhorenBloodTracker(int ID, GameController gc)
	{
		// General
		setEnemyID(ID);
		setName("Ghoren Blood Tracker");
		gc.getEnemyController().addEnemyName(getName());
		setEnemySuit(EnemySuit.GREEN);
		setResolve(2);
		setSpecialEnemy(false);
		setEnraged(false);
		setTargetRange(1);
		// Keywords
		appendEnemyKeyword(EnemyKeyword.BEASTMAN);
		appendEnemyKeyword(EnemyKeyword.BLADED);
		appendEnemyKeyword(EnemyKeyword.HUNTER);
		appendEnemyKeyword(EnemyKeyword.RANGED);
		appendEnemyKeyword(EnemyKeyword.WILDLANDER);
		// Vulnerabilities
		
		// Life values
		getLife().setValueBase(3);
		getLife().setValueMod(getLife().getValueBase());
		getLife().setValueCurrent(getLife().getValueBase());
		// Attack values
		getAttack().setType(AttackType.PHYSICAL);
		getAttack().setValueBase(3);
		getAttack().setValueMod(getAttack().getValueBase());
		// Resistances Values
		getResistances().setPhysicalResBase(2);
		getResistances().setPhysicalResMod(getResistances().getPhysicalResBase());
		getResistances().setMagicalResBase(1);
		getResistances().setMagicalResMod(getResistances().getMagicalResBase());
		// Abilities
		getAbilities().add(new EnemyAbility(EnemyAbilityType.RELENTLESS,EnemyAbilityArea.HERO));
		getAbilities().add(new EnemyAbility(EnemyAbilityType.SKIRMISHER,EnemyAbilityArea.ANY));
	}

}