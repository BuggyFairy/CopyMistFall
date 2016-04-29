package com.mygdx.game.mistfall.enemy.types.red;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.EnemyAbility;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.model.enums.AttackType;

public class BlackwoodMagehunter extends Enemy {

	public BlackwoodMagehunter(int ID, GameController gc)
	{
		// General
		setEnemyID(ID);
		setName("Blackwood Magehunter");
		gc.getEnemyController().addEnemyName(getName());
		setEnemySuit(EnemySuit.RED);
		setResolve(1);
		setSpecialEnemy(false);
		setEnraged(false);
		setTargetRange(1);
		// Keywords
		appendEnemyKeyword(EnemyKeyword.BRIGAND);
		appendEnemyKeyword(EnemyKeyword.HUNTER);
		appendEnemyKeyword(EnemyKeyword.PIERCING);
		appendEnemyKeyword(EnemyKeyword.RANGED);
		// Vulnerabilities

		// Life values
		getLife().setValueBase(2);
		getLife().setValueMod(getLife().getValueBase());
		getLife().setValueCurrent(getLife().getValueBase());
		// Attack values
		getAttack().setType(AttackType.PHYSICAL);
		getAttack().setValueBase(2);
		getAttack().setValueMod(getAttack().getValueBase());
		// Resistances Values
		getResistances().setPhysicalResBase(1);
		getResistances().setPhysicalResMod(getResistances().getPhysicalResBase());
		getResistances().setMagicalResBase(3);
		getResistances().setMagicalResMod(getResistances().getMagicalResBase());
		// Abilities
		getAbilities().add(new EnemyAbility(EnemyAbilityType.MANA_DRAIN,EnemyAbilityArea.HERO));
	}

}