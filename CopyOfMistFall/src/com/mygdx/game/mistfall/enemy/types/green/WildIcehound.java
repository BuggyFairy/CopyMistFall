package com.mygdx.game.mistfall.enemy.types.green;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.EnemyAbility;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.enemy.enums.EnemyVunerability;
import com.mygdx.game.mistfall.model.enums.AttackType;

public class WildIcehound extends Enemy {

	public WildIcehound(int ID, GameController gc)
	{
		// General
		setEnemyID(ID);
		setName("Wild Icehound");
		gc.getEnemyController().addEnemyName(getName());
		setEnemySuit(EnemySuit.GREEN);
		setResolve(1);
		setSpecialEnemy(false);
		setEnraged(false);
		setTargetRange(1);
		// Keywords
		appendEnemyKeyword(EnemyKeyword.BEAST);
		appendEnemyKeyword(EnemyKeyword.HOUND);
		appendEnemyKeyword(EnemyKeyword.FROST);
		appendEnemyKeyword(EnemyKeyword.RENDING);
		// Vulnerabilities
		appendVunerability(EnemyVunerability.FLAME);
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
		getAbilities().add(new EnemyAbility(EnemyAbilityType.PACK_HUNTER,EnemyAbilityArea.HERO));
	}

}