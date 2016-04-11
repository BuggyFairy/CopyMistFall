package com.mygdx.game.mistfall.enemy.blue;

import java.util.LinkedList;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.model.enums.AttackType;
import com.mygdx.game.mistfall.model.enums.EnemyKeyword;
import com.mygdx.game.mistfall.model.enums.EnemyType;
import com.mygdx.game.mistfall.model.enums.Vulnerability;

public class BloodscorneVampire extends Enemy {

	public BloodscorneVampire()
	{
		setName("Bloodscorne Vampire");
		setEnemyType(EnemyType.RED);
		setAttackType(AttackType.MAGICAL);
		
		setEnemyKeyword(new LinkedList<EnemyKeyword>());
		appendEnemyKeyword(EnemyKeyword.SORCERER);
		appendEnemyKeyword(EnemyKeyword.UNDEAD);
		appendEnemyKeyword(EnemyKeyword.VAMPIRE);
		
		setVunerability(new LinkedList<Vulnerability>());
		appendVunerability(Vulnerability.FLAME);
		appendVunerability(Vulnerability.DIVINE);
		setAttackValue(2);
		setPhysicalRes(2);
		setMagicalRes(2);
		setLife(4);
		setResolve(2);
		setSpecialEnemy(false);
	}

}