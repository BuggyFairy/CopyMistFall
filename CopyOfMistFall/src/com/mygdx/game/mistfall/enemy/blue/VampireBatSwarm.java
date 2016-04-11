package com.mygdx.game.mistfall.enemy.blue;

import java.util.LinkedList;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.model.enums.AttackType;
import com.mygdx.game.mistfall.model.enums.EnemyKeyword;
import com.mygdx.game.mistfall.model.enums.EnemyType;
import com.mygdx.game.mistfall.model.enums.Vulnerability;

public class VampireBatSwarm extends Enemy {

	public VampireBatSwarm()
	{
		setName("Vampire Bat Swarm");
		setEnemyType(EnemyType.RED);
		setAttackType(AttackType.PHYSICAL);
		
		setEnemyKeyword(new LinkedList<EnemyKeyword>());
		appendEnemyKeyword(EnemyKeyword.BEAST);
		appendEnemyKeyword(EnemyKeyword.PIERCING);
		appendEnemyKeyword(EnemyKeyword.UNDEAD);
		appendEnemyKeyword(EnemyKeyword.VAMPIRE);
		
		setVunerability(new LinkedList<Vulnerability>());
		appendVunerability(Vulnerability.FLAME);
		appendVunerability(Vulnerability.LIGHTNING);
		setAttackValue(0);
		setPhysicalRes(1);
		setMagicalRes(0);
		setLife(3);
		setResolve(1);
		setSpecialEnemy(false);
	}

}