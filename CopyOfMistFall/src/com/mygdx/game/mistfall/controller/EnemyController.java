package com.mygdx.game.mistfall.controller;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.Abilities.BloodFury;
import com.mygdx.game.mistfall.enemy.Abilities.CursedBolt;
import com.mygdx.game.mistfall.enemy.Abilities.Firebolt;
import com.mygdx.game.mistfall.enemy.Abilities.InduceRelentless;
import com.mygdx.game.mistfall.enemy.Abilities.ManaDrain;
import com.mygdx.game.mistfall.enemy.Abilities.PackHunter;
import com.mygdx.game.mistfall.enemy.Abilities.Reanimate;
import com.mygdx.game.mistfall.enemy.Abilities.Relentless;
import com.mygdx.game.mistfall.enemy.Abilities.Scavenger;
import com.mygdx.game.mistfall.enemy.Abilities.ShieldSlam;
import com.mygdx.game.mistfall.enemy.Abilities.Skirmisher;
import com.mygdx.game.mistfall.enemy.Abilities.Skulduggery;
import com.mygdx.game.mistfall.enemy.Abilities.Slow;
import com.mygdx.game.mistfall.enemy.Abilities.Swarm;
import com.mygdx.game.mistfall.enemy.Abilities.Thievery;
import com.mygdx.game.mistfall.enemy.Abilities.Vampiric;
import com.mygdx.game.mistfall.enemy.Abilities.Venomous;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;
import com.mygdx.game.mistfall.enemy.enums.EnemyArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyOperation;
import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.enums.EnemyKeyword;

public class EnemyController {	
	
	int enemyPos;
//	int enemyID;
//	String enemyName;
//	int heroIdDest;
//	int heroIDsource;
//	int houndCount;
//	int value;
//	boolean modificationFound;
//	boolean updatePossible;
	
	public void updateEnemiesDraw(GameController gc,Enemy enemy){
		switchArea(gc,enemy,EnemyArea.DECK,EnemyArea.QUEST,null,null,EnemyOperation.DRAW);
	}
	public void updateEnemiesPursuit(GameController gc,Enemy enemy,Hero heroDest){
		switchArea(gc,enemy,EnemyArea.QUEST,EnemyArea.HERO,heroDest,null,EnemyOperation.PURSUIT);
	}
	public void updateEnemiesDisperse(GameController gc,Enemy enemy,Hero heroSource){
		switchArea(gc,enemy,EnemyArea.HERO,EnemyArea.DISCARD,null,heroSource,EnemyOperation.DISPERSE);
	}
	public void updateEnemiesDefeated(GameController gc,Enemy enemy,Hero heroSource){
		switchArea(gc,enemy,EnemyArea.HERO,EnemyArea.DISCARD,null,heroSource,EnemyOperation.DEFEATED);
	}
	public void updateEnemiesHeroHero(GameController gc,Enemy enemy,Hero heroSource, Hero heroDest){
		switchArea(gc,enemy,EnemyArea.HERO,EnemyArea.HERO,heroSource,heroDest,EnemyOperation.MOVE);
	}
	public void updateEnemiesHeroQuest(GameController gc,Enemy enemy,Hero heroSource){
		switchArea(gc,enemy,EnemyArea.HERO,EnemyArea.QUEST,null,heroSource,EnemyOperation.MOVE);
	}
	public void updateEnemiesQuestHero(GameController gc,Enemy enemy,Hero heroDest){
		switchArea(gc,enemy,EnemyArea.QUEST,EnemyArea.HERO,heroDest,null,EnemyOperation.MOVE);
	}
	
	public void updateEnemiesRecievingWounds(GameController gc,Enemy enemy,Hero hero){
	
		for (int i=0;i<enemy.getAbilities().size();i++){
			switch (enemy.getAbilities().get(i).getType()){
				// Blood Fury: "<Hero Area> This Enemy deals +1 Physical Damage for each Wound on this card."
				case BLOOD_FURY:
					BloodFury.updateTakenDamage(gc, enemy, hero);
				break;
				// Swarm: "<Hero Area> This Enemy adds +1 Physical Damage for each 1 Lifepoint"
				case SWARM:
					Swarm.updateTakenDamge(gc, enemy, hero);
				break;
				default:
				break;
			}
		}	
	}
	
	public void updateEnemiesEnemyEliminated(GameController gc, Enemy enemy, EnemyArea source, Hero heroSource){
		for (int i=0;i<enemy.getAbilities().size();i++){
			switch (enemy.getAbilities().get(i).getType()){
				// Reanimate: "<Hero Area> Whenever an UNDEAD Enemy is eliminated in the same Area, draw BLUE ENEMIES until a SKELETON is revealed. Place that SKELETON in this Area, discard the rest."
				case REANIMATE:
					Reanimate.updateEnemyEliminated(gc, enemy, source, heroSource);
				break;
				
				default:
				break;
			}
		}
	}
	
	
	private void switchArea(GameController gc,Enemy enemy,EnemyArea source, EnemyArea dest,Hero heroDest,Hero heroSource, EnemyOperation enemyOperation){
		
		for (int abilityCount=0;abilityCount<enemy.getAbilities().size();abilityCount++){
			switch (enemy.getAbilities().get(abilityCount).getType()){
			
				case AMBUSH:
				break;
				
				// Beast Ride:"<Hero Area> Any 1 WILDLANDER (that does not have the RELENTLESS ability) in the same <Hero Area> receives the RELENTLESS ability"
				case BEAST_RIDE:
					InduceRelentless.update(gc,enemy,source, dest,heroDest,heroSource, EnemyAbilityType.BEAST_RIDE, EnemyKeyword.WILDLANDER);
				break;
				
				// Blood Fury: "<Hero Area> This Enemy deals +1 Physical Damage for each Wound on this card."
				case BLOOD_FURY:
					BloodFury.updateMoved(gc, enemy, source, dest, heroDest, heroSource);
				break;
				
				case CURSE_OF_WEAKNESS:
				break;
					
				// Cursed Bolt: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, that Player Buries another Card"
				case CURSED_BOLT:
					CursedBolt.addModification(gc, enemy, dest, heroDest);
				break;
				
				case DARK_PRESENCE:
				break;
					
				// Firebolt: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, place 1 Burning Token on that player's Hero Charter"
				case FIREBOLT:
					Firebolt.addModification(gc, enemy, dest, heroDest);
				break;
				
				case FLAILING:
				break;
					
				// Mana Drain: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, that player discards 1 ARCANE or SPELL card, if able"
				case MANA_DRAIN:
					ManaDrain.addModification(gc, enemy, dest, heroDest);
				break;
				
				// Pack Hunter: "<Hero Area> This Enemy deals +1 physical damage for each other HOUND in the same Hero Area"
				case PACK_HUNTER:
					PackHunter.update(gc, enemy, source, dest, heroDest, heroSource);
				break;
				
				// Pursuit: "<Hero Area> Any 1 other BEAST (that does not have the RELENTLESS Ability) in the same Hero Area receives the RELENTLESS Ability"
				case PURSUIT:
					InduceRelentless.update(gc,enemy,source, dest,heroDest,heroSource, EnemyAbilityType.PURSUIT, EnemyKeyword.BEAST);
				break;
		
				// Reanimate: "<Hero Area> Whenever an UNDEAD Enemy is eliminated in the same Area, draw BLUE ENEMIES until a SKELETON is revealed. Place that SKELETON in this Area, discard the rest."
				case REANIMATE:
					Reanimate.updateMoved(gc, enemy, source, dest, heroDest, heroSource);
				break;
				
				// Relentless: "<Hero Area> Do not Discard this Enemy when Dispersing Enemies"
				case RELENTLESS:
					Relentless.update(gc, enemy, source, dest, heroDest, heroSource);
				break;
				
				// Scavenger: "<Hero Area> After this Enemy enters a <Hero Area> the Player Buries 2 cards from their discard pile if able."
				case SCAVENGER:
					Scavenger.activateScavenger(gc, enemy, source, dest, heroDest, heroSource);
				break;
				
				// Shield Slam: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, place 2 Daze Tokens on that player's Hero Charter"
				case SHIELD_SLAM:
					ShieldSlam.addModification(gc, enemy, dest, heroDest);
				break;
				
				// Skirmisher: "<Quest Area> <Hero Area> Requires an extra 1 Range to target. Ignore this Ability if all Enemies in the same Area are Ranged."
				case SKIRMISHER:
					Skirmisher.update(gc,enemy,source, dest,heroDest,heroSource);	
				break;
				
				// Skulduggery: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, remove 1 Objectiv Token from the active Encounter
				// 				card and place it on this card. Return all Objective Tokens to the Encounter card when this Enemy is discarded"
				case SKULDUGGERY:
					Skulduggery.addModification(gc, enemy, dest, heroDest);
				break;
				
				// Slow: "<Hero Area> This Enemy does not attack on the same round it enters a Hero Area"
				case SLOW:
					Slow.update(gc, enemy, source, dest, heroDest, heroSource);
				break;
				
				// Swarm: "<Hero Area> This Enemy adds +1 Physical Damage for each 1 Lifepoint"
				case SWARM:
					Swarm.updateMoved(gc, enemy, source, dest, heroDest, heroSource);
				break;
				
				// Thievery: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, attach 1 GEAR card from their <Hero Area> to this card.
				//			  Return all Attachments to owners' HAND when this Enemy is discarded"
				case THIEVERY:
					Thievery.addModification(gc, enemy, dest, heroDest);
				break;			
				
				// Vampiric: "<Hero Area> Whenever a player Buries 1 card as a result of this Enemy's attack, remove 1 Wound Token from this Enemy"
				case VAMPIRIC:
					Vampiric.addModification(gc, enemy, dest, heroDest);
				break;
				
				case VENGEFUL_SHRIEK:
				break;
					
				// Venomous: "<Hero Area> Whenever a player Buries any cards as a result of this Enemy's attack, place 1 Poison Token on that player's Hero Charter"
				case VENOMOUS:
					Venomous.addModification(gc, enemy, dest, heroDest);
				break;
				
				default:
				break;
			}
		}
		
		
		// If a BEAST Enemy without the RELENTLESS Ability moves in or out of a Hero Area with a PURSUIT Enemy update the RELENTLESS Modification
		InduceRelentless.updateEnemyKeyword(gc,enemy,source,dest,heroDest,heroSource, EnemyAbilityType.PURSUIT, EnemyKeyword.BEAST);
		
		// If a WILDLANDER Enemy without the RELENTLESS Ability moves in or out of a Hero Area with a BEAST_RIDE Enemy update the RELENTLESS Modification
		InduceRelentless.updateEnemyKeyword(gc,enemy,source,dest,heroDest,heroSource, EnemyAbilityType.BEAST_RIDE, EnemyKeyword.WILDLANDER);
		
		// If a Non-RANGED Enemy leaves or enters a Hero Area or the Quest Area update the SKIRMISHER Enemies
		Skirmisher.updateRanged(gc,enemy,source, dest,heroDest,heroSource);
		
		// Update Pack Hunter if a HOUND moved or left a Hero Area with a Enemy with the PACK_HUNTER Ability
		PackHunter.updateHound(gc, enemy, source, dest, heroDest, heroSource);
		
		
		
		
		
		// Remove all Modifications if a Enemy is discarded or defeated
		if (dest==EnemyArea.DISCARD){
			enemyPos=gc.getGameSetupController().getEnemyPositionDiscard(enemy);
			if (enemyPos!=-1){
				switch (enemy.getEnemyType()){
					case BLUE:
						// Clear Modification List
						gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getModifications().clear();
						// Reset Life, Attack and Resistance Values to the Base Values
						gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getAttack().setValueMod(gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getAttack().getValueBase());
						gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getLife().setValueMod(gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getLife().getValueBase());
						gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getLife().setValueCurrent(gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getLife().getValueBase());
						gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getResistances().setMagicalResMod(gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getResistances().getMagicalResBase());
						gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getResistances().setPhysicalResMod(gc.getGameSetupController().getBlueEnemiesDiscard().get(enemyPos).getResistances().getPhysicalResBase());
					break;
					case RED:
						// Clear Modification List
						gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getModifications().clear();
						// Reset Life, Attack and Resistance Values to the Base Values
						gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getAttack().setValueMod(gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getAttack().getValueBase());
						gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getLife().setValueMod(gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getLife().getValueBase());
						gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getLife().setValueCurrent(gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getLife().getValueBase());
						gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getResistances().setMagicalResMod(gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getResistances().getMagicalResBase());
						gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getResistances().setPhysicalResMod(gc.getGameSetupController().getRedEnemiesDiscard().get(enemyPos).getResistances().getPhysicalResBase());
					break;
					case GREEN:
						// Clear Modification List
						gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getModifications().clear();
						// Reset Life, Attack and Resistance Values to the Base Values
						gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getAttack().setValueMod(gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getAttack().getValueBase());
						gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getLife().setValueMod(gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getLife().getValueBase());
						gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getLife().setValueCurrent(gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getLife().getValueBase());
						gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getResistances().setMagicalResMod(gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getResistances().getMagicalResBase());
						gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getResistances().setPhysicalResMod(gc.getGameSetupController().getGreenEnemiesDiscard().get(enemyPos).getResistances().getPhysicalResBase());
					break;
					default:
					break;
				}
			}		
		}
	}
}
