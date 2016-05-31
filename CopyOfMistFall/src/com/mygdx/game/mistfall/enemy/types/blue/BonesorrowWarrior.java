package com.mygdx.game.mistfall.enemy.types.blue;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.model.enums.AttackType;
import com.mygdx.game.mistfall.model.enums.Keyword;

public class BonesorrowWarrior extends Enemy {

	public BonesorrowWarrior(int ID, GameController gc)
	{
		// General
		setEnemyID(ID);
		setName("Bonesorrow Warrior");
		gc.getEnemyController().addEnemyName(getName());
		setEnemySuit(EnemySuit.BLUE);
		setResolve(1);
		setSpecialEnemy(false);
		setEnraged(false);
		setTargetRange(1);
		// Keywords
		appendEnemyKeyword(EnemyKeyword.BLADED);
		appendEnemyKeyword(EnemyKeyword.MINDLESS);
		appendEnemyKeyword(EnemyKeyword.SKELETON);
		appendEnemyKeyword(EnemyKeyword.UNDEAD);
		// Vulnerabilities
		appendVunerability(Keyword.BLUNT);
		appendVunerability(Keyword.FLAME);
		// Life values
		getLife().setValueBase(4);
		getLife().setValueMod(getLife().getValueBase());
		getLife().setValueCurrent(getLife().getValueBase());
		// Attack values
		getAttack().setType(AttackType.PHYSICAL);
		getAttack().setValueBase(2);
		getAttack().setValueMod(getAttack().getValueBase());
		// Resistances Values
		getResistances().setPhysicalResBase(1);
		getResistances().setPhysicalResMod(getResistances().getPhysicalResBase());
		getResistances().setMagicalResBase(1);
		getResistances().setMagicalResMod(getResistances().getMagicalResBase());
		// Abilities

	}

}