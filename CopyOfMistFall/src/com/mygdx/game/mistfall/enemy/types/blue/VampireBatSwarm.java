package com.mygdx.game.mistfall.enemy.types.blue;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.EnemyAbility;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.model.enums.AttackType;
import com.mygdx.game.mistfall.model.enums.Vulnerability;

public class VampireBatSwarm extends Enemy {

	public VampireBatSwarm(int ID, GameController gc)
	{
		// General
		setEnemyID(ID);
		setName("Vampire Bat Swarm");
		gc.getEnemyController().addEnemyName(getName());
		setEnemySuit(EnemySuit.RED);
		setResolve(1);
		setSpecialEnemy(false);
		setEnraged(false);
		setTargetRange(1);
		// Keywords
		appendEnemyKeyword(EnemyKeyword.BEAST);
		appendEnemyKeyword(EnemyKeyword.PIERCING);
		appendEnemyKeyword(EnemyKeyword.UNDEAD);
		appendEnemyKeyword(EnemyKeyword.VAMPIRE);
		// Vulnerabilities
		appendVunerability(Vulnerability.FLAME);
		appendVunerability(Vulnerability.LIGHTNING);
		// Life values
		getLife().setValueBase(3);
		getLife().setValueMod(getLife().getValueBase());
		getLife().setValueCurrent(getLife().getValueBase());
		// Attack values
		getAttack().setType(AttackType.PHYSICAL);
		getAttack().setValueBase(0);
		getAttack().setValueMod(getAttack().getValueBase());
		// Resistances Values
		getResistances().setPhysicalResBase(1);
		getResistances().setPhysicalResMod(getResistances().getPhysicalResBase());
		getResistances().setMagicalResBase(0);
		getResistances().setMagicalResMod(getResistances().getMagicalResBase());
		// Abilities
		getAbilities().add(new EnemyAbility(EnemyAbilityType.SWARM,EnemyAbilityArea.HERO));
		getAbilities().add(new EnemyAbility(EnemyAbilityType.VAMPIRIC,EnemyAbilityArea.HERO));
		
	}

}