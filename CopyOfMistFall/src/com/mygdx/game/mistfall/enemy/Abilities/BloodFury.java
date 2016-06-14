package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class BloodFury {

	public static void updateMoved(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest){
		// IF an Enemy with the BLOOD_FURY Ability enters a Hero Area, add the BLOOD_FURY Modification if the Value is >0
		if(dest==EnemyArea.HERO){
			int value = enemy.getLife().getValueMod()-enemy.getLife().getValueCurrent();
			if (value>0){
				enemy.updateModification(ModSource.ENEMY, ModType.BLOOD_FURY, ModTarget.ATTACK, value, enemy.getEnemyID());
			}
		}
		// If a Enemy with the BLOOD_FURY Ability moves from a Hero Area in the Quest Area, remove the BLOOD_FURY Modification if possible
		if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
			enemy.removeModification(ModType.BLOOD_FURY, ModSource.ENEMY, enemy.getEnemyID());
		}
	}
	
	public static void updateTakenDamage(GameController gc,Enemy enemy){
		int value=enemy.getLife().getWounds();
		enemy.updateModification(ModSource.ENEMY, ModType.BLOOD_FURY, ModTarget.ATTACK, value, enemy.getEnemyID());
	}
	
}
