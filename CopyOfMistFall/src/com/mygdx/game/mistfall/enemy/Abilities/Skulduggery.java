package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Skulduggery {
	
	// Skulduggery: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, remove 1 Objectiv Token from the active Encounter
	// 				card and place it on this card. Return all Objective Tokens to the Encounter card when this Enemy is discarded"
	
	public static void updateMoved(GameController gc,Enemy enemy, EnemyArea source, EnemyArea dest){
		
		// IF an Enemy with the Skulduggery Ability enters a Hero Area, add the Cursed Bolt Modification
		if(dest==EnemyArea.HERO){
			enemy.updateModification(ModSource.ENEMY, ModType.SKULDUGGERY, ModTarget.GENERAL, 0, enemy.getEnemyID());
		}
		// If a Enemy with the Skulduggery Ability moves from a Hero Area in the Quest Area, remove the Cursed Bolt Modification if possible
		if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
			enemy.removeModification(ModType.SKULDUGGERY, ModSource.ENEMY, enemy.getEnemyID());
		}
	}
	
	
	public static void applyEffect(){
		
	}

}
