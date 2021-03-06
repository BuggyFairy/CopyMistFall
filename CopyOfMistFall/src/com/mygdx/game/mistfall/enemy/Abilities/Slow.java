package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Slow {
	
	public static void update(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest){
		
		// If a Enemy with the SLOW Ability enters a Hero Area add the SLOW Modification
		if (dest==EnemyArea.HERO){
			enemy.updateModification(ModSource.ENEMY, ModType.SLOW, ModTarget.GENERAL, 0, enemy.getEnemyID());
		}
		// If a Enemy with the SLOW Ability moves from a Hero Area in the Quest Area, remove the SLOW Modification if possible
		if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
			enemy.removeModification(ModType.SLOW, ModSource.ENEMY, enemy.getEnemyID());
		}
	}
}