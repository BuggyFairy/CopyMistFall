package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Vampiric {
	
	// Vampiric: "<Hero Area> Whenever a player Buries 1 card as a result of this Enemy's attack, remove 1 Wound Token from this Enemy"
	
	public static void updateMoved(GameController gc,Enemy enemy, EnemyArea source, EnemyArea dest){
		
		// IF an Enemy with the Vampiric Ability enters a Hero Area, add the Cursed Bolt Modification
		if(dest==EnemyArea.HERO){
			enemy.updateModification(ModSource.ENEMY, ModType.VAMPIRIC, ModTarget.GENERAL, 0, enemy.getEnemyID());
		}
		// If a Enemy with the Vampiric Ability moves from a Hero Area in the Quest Area, remove the Cursed Bolt Modification if possible
		if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
			enemy.removeModification(ModType.VAMPIRIC, ModSource.ENEMY, enemy.getEnemyID());
		}
	}
	
	
	public static void applyEffect(){
		
	}

}
