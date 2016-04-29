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

public class RavenousDraugr extends Enemy {

	public RavenousDraugr(int ID, GameController gc)
	{
		// General
		setEnemyID(ID);
		setName("Ravenous Draugr");
		gc.getEnemyController().addEnemyName(getName());
		setEnemySuit(EnemySuit.BLUE);
		setResolve(2);
		setSpecialEnemy(false);
		setEnraged(false);
		setTargetRange(1);
		// Keywords
		appendEnemyKeyword(EnemyKeyword.BLADED);
		appendEnemyKeyword(EnemyKeyword.MINDLESS);
		appendEnemyKeyword(EnemyKeyword.RENDING);
		appendEnemyKeyword(EnemyKeyword.UNDEAD);
		appendEnemyKeyword(EnemyKeyword.ZOMBIE);
		// Vulnerabilities
		appendVunerability(EnemyVunerability.FLAME);
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
		getResistances().setMagicalResBase(2);
		getResistances().setMagicalResMod(getResistances().getMagicalResBase());
		// Abilities
		getAbilities().add(new EnemyAbility(EnemyAbilityType.RAVENOUS,EnemyAbilityArea.HERO));
	}

}