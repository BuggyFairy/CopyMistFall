package com.mygdx.game.mistfall.enemy.Abilities;

import com.mygdx.game.mistfall.controller.GameController;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.enums.EnemyKeyword;
import com.mygdx.game.mistfall.model.modifications.ModSource;
import com.mygdx.game.mistfall.model.modifications.ModTarget;
import com.mygdx.game.mistfall.model.modifications.ModType;

public class Skirmisher {
	
	
	
	public static void update(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource){
		
		int enemyPos;
		int heroIdDest;
		
		// If a Skirmisher Enemy enters a Hero Area check for other range enemies
		if (dest==EnemyArea.HERO){
			heroIdDest=heroDest.getHeroID();
			enemyPos=gc.getHeroes().get(heroIdDest).getHeroEnemies().getEnemyPos(enemy);
			boolean onlyRanged=true;
			// Check the Hero Area for Enemies with the RANGED Keyword
			for (Enemy e : gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards()){
				if (e.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
					onlyRanged=false;
					break;
				}
			}
			// If there are only RANGED Enemies, remove the SKIRMISHER Modification if possible
			if (onlyRanged==true){
				gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemy.getEnemyID());
			}
			// If there is at least 1 Enemy without the RANGED Keyword, add the SKIRMISHER Modification if possible
			else{
				gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(enemyPos).updateModification(ModSource.ENEMY, ModType.SKIRMISHER, ModTarget.RANGE, 1, enemy.getEnemyID(), enemy.getName());
			}
		}
		// If a Skirmisher Enemy enters a Quest Area check for other range enemies
		if (dest==EnemyArea.QUEST){
			enemyPos=gc.getQuestArea().getEnemyPos(enemy);
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
				gc.getQuestArea().getQuestAreaEnemies().get(enemyPos).removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemy.getEnemyID());
			}
			// If there is at least 1 Enemy without the RANGED Keyword, add the SKIRMISHER Modification if possible
			else{
				gc.getQuestArea().getQuestAreaEnemies().get(enemyPos).updateModification(ModSource.ENEMY, ModType.SKIRMISHER, ModTarget.RANGE, 1, enemy.getEnemyID(), enemy.getName());
			}
		}
	}
	
	
	public static void updateRanged(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource){
		
		int enemyID;
		int heroIdDest;
		int heroIDsource;
		
		// If a Enemy without the RANGED Ability moves in the Hero Area and there is a Skirmisher
		if(enemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false && dest==EnemyArea.HERO){
			heroIdDest=heroDest.getHeroID();
			// Search for Enemies with the Skirmisher Ability and remove the Skirmisher Modification if possible
			for (int i=0;i<gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().size();i++){
				if (gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).searchAbility(EnemyAbilityType.SKIRMISHER)){
					enemyID=gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).getEnemyID();
					gc.getHeroes().get(heroIdDest).getHeroEnemies().getCards().get(i).removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemyID);
				}
			}
		}
		// If a Enemy without the RANGED Ability moves in the Quest Area and there is a Skirmisher
		if(enemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false && dest==EnemyArea.HERO){
			// Search for Enemies with the Skirmisher Ability and remove the Skirmisher Modification if possible
			for (int i=0;i<gc.getQuestArea().getQuestAreaEnemies().size();i++){
				if (gc.getQuestArea().getQuestAreaEnemies().get(i).searchAbility(EnemyAbilityType.SKIRMISHER)){
					enemyID=gc.getQuestArea().getQuestAreaEnemies().get(i).getEnemyID();
					gc.getQuestArea().getQuestAreaEnemies().get(i).removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemyID);
				}
			}
		}
		// If a Enemy without the RANGED Ability moves out of a Hero Area and there is a Skirmisher
		if(enemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false && source==EnemyArea.HERO){
			heroIDsource=heroSource.getHeroID();
			boolean onlyRanged=true;
			// Check if there is still a Enemy without the RANGED Ability left
			for (int i=0;i<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();i++){
				if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
					onlyRanged=false;
					break;
				}
			}	
			// If there are only RANGED Enemies left Search for Enemies with the Skirmisher Ability and remove the Skirmisher Modification if possible
			if (onlyRanged==true){
				for (int i=0;i<gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().size();i++){
					if (gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).searchAbility(EnemyAbilityType.SKIRMISHER)){
						enemyID=gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).getEnemyID();
						gc.getHeroes().get(heroIDsource).getHeroEnemies().getCards().get(i).removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemyID);
					}
				} 
			}
			
		}
		// If a Enemy without the RANGED Ability moves out of the Quest Area and there is a Skirmisher
		if(enemy.getEnemyKeyword().contains(EnemyKeyword.RANGED)==false && source==EnemyArea.QUEST){
			boolean onlyRanged=true;
			// Check if there is still a Enemy without the RANGED Ability left
			for (int i=0;i<gc.getQuestArea().getQuestAreaEnemies().size();i++){
				if (gc.getQuestArea().getQuestAreaEnemies().get(i).getEnemyKeyword().contains(EnemyKeyword.RANGED)==false){
					onlyRanged=false;
					break;
				}
			}	
			// If there are only RANGED Enemies left Search for Enemies with the Skirmisher Ability and remove the Skirmisher Modification if possible
			if (onlyRanged==true){
				for (int i=0;i<gc.getQuestArea().getQuestAreaEnemies().size();i++){
					if (gc.getQuestArea().getQuestAreaEnemies().get(i).searchAbility(EnemyAbilityType.SKIRMISHER)){
						enemyID=gc.getQuestArea().getQuestAreaEnemies().get(i).getEnemyID();
						gc.getQuestArea().getQuestAreaEnemies().get(i).removeModification(ModType.SKIRMISHER, ModSource.ENEMY, enemyID);
					}
				} 
			}
		}
	}
}