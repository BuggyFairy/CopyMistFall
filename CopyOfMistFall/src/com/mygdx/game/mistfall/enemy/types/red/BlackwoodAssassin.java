package com.mygdx.game.mistfall.enemy.types.red;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.EnemyAbility;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.model.enums.AttackType;

public class BlackwoodAssassin extends Enemy {

	public BlackwoodAssassin(int ID, GameController gc)
	{
		// General
		setEnemyID(ID);
		setName("Blackwood Assassin");
		gc.getEnemyController().addEnemyName(getName());
		setEnemySuit(EnemySuit.RED);
		setResolve(2);
		setSpecialEnemy(false);
		setEnraged(false);
		setTargetRange(1);
		// Keywords
		appendEnemyKeyword(EnemyKeyword.BRIGAND);
		appendEnemyKeyword(EnemyKeyword.BLADED);
		appendEnemyKeyword(EnemyKeyword.PIERCING);
		appendEnemyKeyword(EnemyKeyword.POISON);
		appendEnemyKeyword(EnemyKeyword.RANGED);
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
		getResistances().setMagicalResBase(2);
		getResistances().setMagicalResMod(getResistances().getMagicalResBase());
		// Abilities
		getAbilities().add(new EnemyAbility(EnemyAbilityType.VENOMOUS,EnemyAbilityArea.HERO));
		getAbilities().add(new EnemyAbility(EnemyAbilityType.SKIRMISHER,EnemyAbilityArea.ANY));
	}

}