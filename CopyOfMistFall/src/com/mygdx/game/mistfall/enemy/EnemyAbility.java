package com.mygdx.game.mistfall.enemy;

import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;

public class EnemyAbility {
	
	private EnemyAbilityType type;
	private EnemyAbilityArea area;
	
	
	
	
	
	
	public EnemyAbility(EnemyAbilityType type, EnemyAbilityArea area) {
		this.type = type;
		this.area = area;
	}
	
	
	
	
	
	
	
	
	
	public EnemyAbilityType getType() {
		return type;
	}
	public void setType(EnemyAbilityType type) {
		this.type = type;
	}
	public EnemyAbilityArea getArea() {
		return area;
	}
	public void setArea(EnemyAbilityArea area) {
		this.area = area;
	}
	
	
	
	
	
	
	
	public String getString(){
		switch (type){
			case SHIELD_SLAM:
				return "<Hero Area> Shield Slam: Whenever a player Buries any cards as a result of this Enemy's attack, place 2<Daze Token> on that player's Hero Charter.";
			case THIEVERY:
				return "<Hero Area> Thievery: Whenever a player Buries any cards as a result of this Enemy's attack, attach 1 GEAR card from their <Hero Area> to this card. Return all Attachments to owners' <Hand> when this Enemy is discarded.";
			case SKULDUGGERY:
				return "<Hero Area> Skulduggery: Whenever a player Buries any cards as a result of this Enemy's attack, remove 1<Objectiv Token> from the active Encounter card an place it on this card. Return all <Objectiv Token> to the Encounter card when this Enemy is discarded.";
			case MANA_DRAIN:
				return "<Hero Area> Mana Drain: Whenever a player Buries any cards as a result of this Enemy's attack, that player discards 1 ARCANE or SPELL card, if able.";
			case FLAILING:
				return "<Hero Area> Flailing: After this Enemy enters a <Hero Area> during any Pursuit Phase the player places 2<Daze Token> on their Hero Charter.";
			case AMBUSH:
				return "<Hero Area> Ambush: This Enemy attacks immediately after entering a <Hero Area>. Any player may discard 2 cards to ignore this ability.";
			case VENOMOUS:
				return "<Hero Area> Venomous: Whenever a player Buries any cards as a result of this Enemy's attack, place 1<Poison Token> on that player's Hero Charter";
			case SKIRMISHER:
				return "<Hero Area><Quest Area> Skirmisher: Requires an extra <1 Range> to target. Ignore this ability if all Enemies in the same Area are RANGED.";
			case CURSED_BOLT:
				return "<Hero Area> Cursed Bolt: Whenever a palyer Buries any cards as a result of this Enemy's attacl, that player Buries another card.";
			case FIREBOLT:
				return "<Hero Area> Firebolt: Whenever a player Buries any cards as a result of this Enemy's attack, place 1<Burning Token> on that player's Hero Charter.";
			case CURSE_OF_WEAKNESS:
				return "<Hero Area> Curse of Weakness: After this Enemy enters a <Hero Area>, place 1<Weakness Token> on that Hero.";
			case SHAPESHIFT:
				return "<Hero Area> Shapeshift: This Enemy may not be targeted by any effect that deals any <Physical Damage> or <Magical Damage> until this Enemy performs an attack. As a Reflex a player may discard 2 cards to ignore this ability.";
			case DARK_PRESENCE:
				return "<Hero Area> Dark Presence: This Enemy reduces the Draw of a player by 1 when in a <Hero Area> belonging to that player. Multiple Dark Presence abilities do not stack";
			case RATTLE:
				return "<Hero Area> Rattle: At the end of the Defence Phase, Erange all BEASTS in the same <Hero Area>.";
			case BLOOD_FURY:
				return "<Hero Area> Blood Fury: This Enemy deals +1<Physical Damage> for each 1<Wound Token> on this card.";
			case SCAVENGER:
				return "<Hero Area> Scavenger: After this Enemy enters a <Hero Area>, the player Buries 2 cards from their discard pile if able.";
			case SLOW:
				return "<Hero Area> Slow: This Enemy does not attack on the same round it enters a <Hero Area>.";
			case RELENTLESS:
				return "<Hero Area> Relentless: Do not discard this Enemy when Dispersing Enemies.";
			case BEAST_RIDE:
				return "<Hero Area> Beast Ride: Any 1 WILDLANDER (that does not have the Relentless ability) in the same <Hero Area> receives the Relentless ability.";
			case PURSUIT:
				return "<Hero Area> Pursuit: Any 1 other BEAST (that does not have the Relentless ability) in the same <Hero Area> receives the Relentless ability.";
			case PACK_HUNTER:
				return "<Hero Area> Pack Hunter: This Enemy deals +1<Physical Damage> for each other HOUND in the same <Hero Area>.";
			case SWARM:
				return "<Hero Area> Swarm: This Enemy adds +1<Physical Damage> for each 1<Life>.";
			case VAMPIRIC:
				return "<Hero Area> Vampiric: Whenever a player Buries a card as a result of this Enemy's attack, rempve 1<Wound Token> from this Enemy.";
			case RAVENOUS:
				return "<Hero Area> Ravenous: At the end of the Defence Phase, if this Enemy is in a <Hero Area> of a player with any cards in their Burial pile, Enrage this Enemy.";
			case DARK_HEALER:
				return "<Hero Area> Dark Healer: At the start of each Defence Phase, remove 2<Wound Token> from each UNDEAD in the same <Hero Area> (including this Enemy), and 1<Wound Token> from each UNDEAD Enemy in all other <Hero Area>.";
			case VENGEFUL_SHRIEK:
				return "<Hero Area> Vengeful Shriek: After this Enemy enters a <Hero Area>, Erange 1 other UNDEAD Enemy in that <Hero Area> if able.";
			case REANIMATE:
				return "<Hero Area> Reanimate: Whenever an UNDEAD Enemy is eliminated in the same Area, draw Blue Enemies until a SKELETON is revealed. Place that SKELETON in this Area, discard the rest.";
				
			default:
				return "";
		}	
	}
	
	
	

}
