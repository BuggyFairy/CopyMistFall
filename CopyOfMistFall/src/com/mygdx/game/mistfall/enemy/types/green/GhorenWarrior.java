package com.mygdx.game.mistfall.enemy.types.green;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.model.enums.AttackType;

public class GhorenWarrior extends Enemy {

	public GhorenWarrior(int ID, GameController gc)
	{
		// General
		setEnemyID(ID);
		setName("Ghoren Warrior");
		gc.getEnemyController().addEnemyName(getName());
		setEnemySuit(EnemySuit.GREEN);
		setResolve(1);
		setSpecialEnemy(false);
		setEnraged(false);
		setTargetRange(1);
		// Keywords
		appendEnemyKeyword(EnemyKeyword.BLADED);
		appendEnemyKeyword(EnemyKeyword.BLUNT);
		appendEnemyKeyword(EnemyKeyword.BEASTMAN);
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

	}

}