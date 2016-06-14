package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class CursedBolt {
	
	// Cursed Bolt: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, that Player Buries another Card"
	
	public static void updateMoved(GameController gc,Enemy enemy, EnemyArea source, EnemyArea dest){
	
		// IF an Enemy with the Cursed Bolt Ability enters a Hero Area, add the Cursed Bolt Modification
		if(dest==EnemyArea.HERO){
			enemy.updateModification(ModSource.ENEMY, ModType.CURSED_BOLT, ModTarget.GENERAL, 0, enemy.getEnemyID());
		}
		// If a Enemy with the Cursed Bolt Ability moves from a Hero Area in the Quest Area, remove the Cursed Bolt Modification if possible
		if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
			enemy.removeModification(ModType.CURSED_BOLT, ModSource.ENEMY, enemy.getEnemyID());
		}
	}
	
	
	public static void applyEffect(){
		
	}

}
