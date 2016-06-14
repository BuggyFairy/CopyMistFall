package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyKeyword;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Skirmisher {
	
	
	
	public static void update(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest){

		// If a Skirmisher Enemy enters a Hero Area check for other range enemies
		if (dest==EnemyArea.HERO){

			// Check the Hero Area for Enemies with the RANGED Keyword
			boolean onlyRanged=true;
			for (Enemy e : heroDest.getEnemies()){
				if (e.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
					onlyRanged=false;
					break;
				}
			}
			// If there are only RANGED Enemies, remove the SKIRMISHER Modification if possible
			if (onlyRanged==true){
				enemy.removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemy.getEnemyID());
			}
			// If there is at least 1 Enemy without the RANGED Keyword, add the SKIRMISHER Modification if possible
			else{
				enemy.updateModification(ModSource.ENEMY, ModType.SKIRMISHER, ModTarget.RANGE, 1, enemy.getEnemyID());
			}
			
		}
		// If a Skirmisher Enemy enters a Quest Area check for other range enemies
		if (dest==EnemyArea.QUEST){
			boolean onlyRanged=true;
			// Check the Quest Area for Enemies with the RANGED Keyword
			for (Enemy e : gc.getQuestArea().getQuestAreaEnemies()){
				if (e.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
					onlyRanged=false;
					break;
				}
			}
			// If there are only RANGED Enemies, remove the SKIRMISHER Modification if possible
			if (onlyRanged==true){
				enemy.removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemy.getEnemyID());
			}
			// If there is at least 1 Enemy without the RANGED Keyword, add the SKIRMISHER Modification if possible
			else{
				enemy.updateModification(ModSource.ENEMY, ModType.SKIRMISHER, ModTarget.RANGE, 1, enemy.getEnemyID());
			}
		}
	}
	
	
	public static void updateRanged(GameController gc,Enemy movedEnemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource){
		
		switch (dest){
			case HERO:
				
				// If a Enemy without the RANGED Ability moves in the Hero Area and there is a Skirmisher
				if(movedEnemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
					// Search for Enemies with the Skirmisher Ability and update the Skirmisher Modification if possible
					for (Enemy enemy : heroDest.getEnemies()){
						if (enemy.searchAbility(EnemyAbilityType.SKIRMISHER)){
							enemy.updateModification(ModSource.ENEMY, ModType.SKIRMISHER, ModTarget.RANGE, 1, enemy.getEnemyID());
						}
					}
				}
				
				
				
			break;
			case QUEST:
				
				// If a Enemy without the RANGED Ability moves in the Quest Area and there is a Skirmisher
				if(movedEnemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
					// Search for Enemies with the Skirmisher Ability and update the Skirmisher Modification if possible
					for (Enemy enemy : gc.getQuestArea().getQuestAreaEnemies()){
						if (enemy.searchAbility(EnemyAbilityType.SKIRMISHER)){
							enemy.updateModification(ModSource.ENEMY, ModType.SKIRMISHER, ModTarget.RANGE, 1, enemy.getEnemyID());
						}
					}
				}
				
				
			break;
			default:
			break;
		}	
		switch (source){
			case HERO:
				// If a Enemy without the RANGED Ability moves out of a Hero Area and there is a Skirmisher
				if(movedEnemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
					boolean onlyRanged=true;
					// Check if there is still a Enemy without the RANGED Ability left
					for (Enemy enemy : heroSource.getEnemies()){
						if (enemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
							onlyRanged=false;
							break;
						}
					}	
					// If there are only RANGED Enemies left Search for Enemies with the Skirmisher Ability and remove the Skirmisher Modification if possible
					if (onlyRanged==true){
						for (Enemy enemy : heroSource.getEnemies()){
							if (enemy.searchAbility(EnemyAbilityType.SKIRMISHER)){
								enemy.removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemy.getEnemyID());
							}
						} 
					}	
				}
			break;
			case QUEST:
				// If a Enemy without the RANGED Ability moves out of the Quest Area and there is a Skirmisher
				if(movedEnemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
					boolean onlyRanged=true;
					// Check if there is still a Enemy without the RANGED Ability left
					for (Enemy enemy : gc.getQuestArea().getQuestAreaEnemies()){
						if (enemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
							onlyRanged=false;
							break;
						}
					}	
					// If there are only RANGED Enemies left Search for Enemies with the Skirmisher Ability and remove the Skirmisher Modification if possible
					if (onlyRanged==true){
						for (Enemy enemy : gc.getQuestArea().getQuestAreaEnemies()){
							if (enemy.searchAbility(EnemyAbilityType.SKIRMISHER)){
								enemy.removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemy.getEnemyID());
							}
						} 
					}
				}
			break;
			default:
			break;
				
		}
	}
}