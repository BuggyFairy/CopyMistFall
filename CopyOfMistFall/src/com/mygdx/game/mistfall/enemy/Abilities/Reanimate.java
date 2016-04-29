package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Reanimate {
	
	public static void updateMoved(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource){
		
		int enemyPos;
		int heroIdDest;
		
		// IF an Enemy with the REANIMATE Ability enters a Hero Area, add the REANIMATE Modification
		if(dest==EnemyArea.HERO){
			heroIdDest=heroDest.getHeroID();
			enemyPos=gc.getHeroes().get(heroIdDest).getHeroEnemies().getEnemyPos(enemy);
			gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.REANIMATE, ModTarget.GENERAL, 0, enemy.getEnemyID());
		}
		// If a Enemy with the REANIMATE Ability moves from a Hero Area in the Quest Area, remove the REANIMATE Modification if possible
		if (dest==EnemyArea.QUEST && source==EnemyArea.HERO){
			enemyPos=gc.getQuestArea().getEnemyPos(enemy);
			gc.getQuestArea().getQuestAreaEnemies().get(enemyPos).removeModification(ModType.REANIMATE, ModSource.ENEMY, enemy.getEnemyID());
		}
	}
	
	public static void updateEnemyEliminated(GameController gc,Enemy enemy,Hero heroSource){
		
		int heroIDsource=heroSource.getHeroID();
		
		// If the Eliminated Enemy was in a Hero Area and possesses the UNDEAD Keyword
		if (enemy.getEnemyKeyword().contains(EnemyKeyword.UNDEAD)){
			//Check if there is an Enemy with the REANIMATE Ability in the Hero Area
			for (int i=0;i<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();i++){
				if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).searchModification(ModType.REANIMATE)){
					// Draw a SKELETON from the Blue Enemies and place in the Hero Area
					// TODO: Draw Skeleton
					break;
				}
			}
		}
	}

}
