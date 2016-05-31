package com.mygdx.game.mistfall.enemy.types.blue;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.EnemyAbility;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.model.enums.AttackType;
import com.mygdx.game.mistfall.model.enums.Keyword;

public class BonesorrowMagus extends Enemy {

	public BonesorrowMagus(int ID, GameController gc)
	{
		// General
		setEnemyID(ID);
		setName("Boneosorrow Magus");
		gc.getEnemyController().addEnemyName(getName());
		setEnemySuit(EnemySuit.BLUE);
		setResolve(2);
		setSpecialEnemy(false);
		setEnraged(false);
		setTargetRange(1);
		// Keywords
		appendEnemyKeyword(EnemyKeyword.SKELETON);
		appendEnemyKeyword(EnemyKeyword.SORCERER);
		appendEnemyKeyword(EnemyKeyword.UNDEAD);
		// Vulnerabilities
		appendVunerability(Keyword.BLUNT);
		appendVunerability(Keyword.FLAME);
		// Life values
		getLife().setValueBase(3);
		getLife().setValueMod(getLife().getValueBase());
		getLife().setValueCurrent(getLife().getValueBase());
		// Attack values
		getAttack().setType(AttackType.MAGICAL);
		getAttack().setValueBase(2);
		getAttack().setValueMod(getAttack().getValueBase());
		// Resistances Values
		getResistances().setPhysicalResBase(2);
		getResistances().setPhysicalResMod(getResistances().getPhysicalResBase());
		getResistances().setMagicalResBase(2);
		getResistances().setMagicalResMod(getResistances().getMagicalResBase());
		// Abilities
		getAbilities().add(new EnemyAbility(EnemyAbilityType.REANIMATE,EnemyAbilityArea.HERO));
	}

}