package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Firebolt {
	
	// Firebolt: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, place 1 Burning Token on that player's Hero Charter"
	
	public static void updateMoved(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest){
		
		// IF an Enemy with the Firebolt Ability enters a Hero Area, add the Cursed Bolt Modification
		if(dest==EnemyArea.HERO){
			enemy.updateModification(ModSource.ENEMY, ModType.FIREBOLT, ModTarget.GENERAL, 0, enemy.getEnemyID());
		}
		// If a Enemy with the Firebolt Ability moves from a Hero Area in the Quest Area, remove the Cursed Bolt Modification if possible
		if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
			enemy.removeModification(ModType.FIREBOLT, ModSource.ENEMY, enemy.getEnemyID());
		}
	}
	
	
	public static void applyEffect(){
		
	}

}
