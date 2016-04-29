package com.mygdx.game.mistfall.enemy.types.blue;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.EnemyAbility;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.enemy.enums.EnemyVunerability;
import com.mygdx.game.mistfall.model.enums.AttackType;

public class VengefulBanshee extends Enemy {

	public VengefulBanshee(int ID, GameController gc)
	{
		// General
		setEnemyID(ID);
		setName("VengefulBanshee");
		gc.getEnemyController().addEnemyName(getName());
		setEnemySuit(EnemySuit.BLUE);
		setResolve(1);
		setSpecialEnemy(false);
		setEnraged(false);
		setTargetRange(1);
		// Keywords
		appendEnemyKeyword(EnemyKeyword.SORCERER);
		appendEnemyKeyword(EnemyKeyword.UNDEAD);
		appendEnemyKeyword(EnemyKeyword.WRAITH);
		// Vulnerabilities
		appendVunerability(EnemyVunerability.ARCANE);
		appendVunerability(EnemyVunerability.DIVINE);
		// Life values
		getLife().setValueBase(2);
		getLife().setValueMod(getLife().getValueBase());
		getLife().setValueCurrent(getLife().getValueBase());
		// Attack values
		getAttack().setType(AttackType.MAGICAL);
		getAttack().setValueBase(2);
		getAttack().setValueMod(getAttack().getValueBase());
		// Resistances Values
		getResistances().setPhysicalResBase(1);
		getResistances().setPhysicalResMod(getResistances().getPhysicalResBase());
		getResistances().setMagicalResBase(2);
		getResistances().setMagicalResMod(getResistances().getMagicalResBase());
		// Abilities
		getAbilities().add(new EnemyAbility(EnemyAbilityType.VENGEFUL_SHRIEK,EnemyAbilityArea.HERO));
	}

}