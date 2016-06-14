package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Swarm {

	public static void updateMoved(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest){
		
		// IF an Enemy with the SWARM Ability enters a Hero Area, add the SWARM Modification if the Value is >0
		if(dest==EnemyArea.HERO){
			if (enemy.getLife().getValueCurrent()>0){
				enemy.updateModification(ModSource.ENEMY, ModType.SWARM, ModTarget.ATTACK, enemy.getLife().getValueCurrent(), enemy.getEnemyID());
			}
		}
		// If a Enemy with the SWARM Ability moves from a Hero Area in the Quest Area, remove the SWARM Modification if possible
		if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
			enemy.removeModification(ModType.SWARM, ModSource.ENEMY, enemy.getEnemyID());
		}
	}
	
	public static void updateTakenDamge(GameController gc,Enemy enemy){
		enemy.updateModification(ModSource.ENEMY, ModType.SWARM, ModTarget.ATTACK, enemy.getLife().getValueCurrent(), enemy.getEnemyID());
	}
	
}
