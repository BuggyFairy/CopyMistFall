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

public class VampireHound extends Enemy {

	public VampireHound(int ID, GameController gc)
	{
		// General
		setEnemyID(ID);
		setName("Vampire Hound");
		gc.getEnemyController().addEnemyName(getName());
		setEnemySuit(EnemySuit.BLUE);
		setResolve(1);
		setSpecialEnemy(false);
		setEnraged(false);
		setTargetRange(1);
		// Keywords
		appendEnemyKeyword(EnemyKeyword.BEAST);
		appendEnemyKeyword(EnemyKeyword.HOUND);
		appendEnemyKeyword(EnemyKeyword.RENDING);
		appendEnemyKeyword(EnemyKeyword.UNDEAD);
		appendEnemyKeyword(EnemyKeyword.VAMPIRE);
		// Vulnerabilities
		appendVunerability(Keyword.FLAME);
		appendVunerability(Keyword.LIGHTNING);
		// Life values
		getLife().setValueBase(3);
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
		getAbilities().add(new EnemyAbility(EnemyAbilityType.RELENTLESS,EnemyAbilityArea.HERO));
		getAbilities().add(new EnemyAbility(EnemyAbilityType.VAMPIRIC,EnemyAbilityArea.HERO));
	}

}