package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.enemy.enums.EnemySuit;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Reanimate {
	
	public static void updateMoved(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest){	
		// IF an Enemy with the REANIMATE Ability enters a Hero Area, add the REANIMATE Modification
		if(dest==EnemyArea.HERO){
			enemy.updateModification(ModSource.ENEMY, ModType.REANIMATE, ModTarget.GENERAL, 0, enemy.getEnemyID());
		}
		// If a Enemy with the REANIMATE Ability moves from a Hero Area in the Quest Area, remove the REANIMATE Modification if possible
		if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
			enemy.removeModification(ModType.REANIMATE, ModSource.ENEMY, enemy.getEnemyID());
		}
	}
	
	public static void updateEnemyEliminated(GameController gc, EnemyArea enemyArea,Enemy enemy,Hero hero){
			
		if (enemyArea==EnemyArea.HERO){
			// If the Eliminated Enemy was in a Hero Area and possesses the UNDEAD Keyword
			if (enemy.getEnemyKeyword().contains(EnemyKeyword.UNDEAD)){
				//Check if there is an Enemy with the REANIMATE Ability in the Hero Area
				for(Enemy tempEnemy : hero.getEnemies()){
					if (tempEnemy.searchModification(ModType.REANIMATE)){
						gc.getEnemyController().drawEnemy(gc, EnemyArea.HERO, hero, EnemySuit.BLUE, EnemyKeyword.SKELETON, 1, false);
						break;
					}
				}
			}
		}
	}

}
