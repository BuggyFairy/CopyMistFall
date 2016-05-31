package com.mygdx.game.mistfall.enemy.types.red;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.EnemyAbility;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.model.enums.AttackType;
import com.mygdx.game.mistfall.model.enums.Keyword;

public class TwistedLasher extends Enemy {

	public TwistedLasher(int ID, GameController gc)
	{
		// General
		setEnemyID(ID);
		setName("Twisted Lasher");
		gc.getEnemyController().addEnemyName(getName());
		setEnemySuit(EnemySuit.RED);
		setResolve(1);
		setSpecialEnemy(false);
		setEnraged(false);
		setTargetRange(1);
		// Keywords
		appendEnemyKeyword(EnemyKeyword.BLUNT);
		appendEnemyKeyword(EnemyKeyword.BRIGAND);
		appendEnemyKeyword(EnemyKeyword.CURSED);
		appendEnemyKeyword(EnemyKeyword.MISTCRAFTED);
		// Vulnerabilities
		appendVunerability(Keyword.DIVINE);
		appendVunerability(Keyword.LIGHTNING);
		// Life values
		getLife().setValueBase(4);
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
		getAbilities().add(new EnemyAbility(EnemyAbilityType.FLAILING,EnemyAbilityArea.HERO));
	}

}