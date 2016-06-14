package com.mygdx.game.mistfall.model.modifications;

import com.mygdx.game.mistfall.controller.GameController;

public class Modification {
	
	private ModSource modSource;
	private ModType modType;
	private ModTarget modTarget;
	
	private int value;
	
//	private String text;
//	private String sourceName;
	private int sourceID;
	
	
	
	public Modification(ModSource modSource, ModType modType,ModTarget modTarget,int value,int sourceID) {
		this.modSource = modSource;
		this.modType = modType;
		this.modTarget=modTarget;
		this.value=value;
		this.sourceID=sourceID;
	}
	

	
	
	public ModTarget getModTarget() {
		return modTarget;
	}
	public void setModTarget(ModTarget modTarget) {
		this.modTarget = modTarget;
	}
	public ModSource getModSource() {
		return modSource;
	}
	public void setModSource(ModSource modSource) {
		this.modSource = modSource;
	}
	public ModType getModType() {
		return modType;
	}
	public void setModType(ModType modType) {
		this.modType = modType;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

	public int getSourceID() {
		return sourceID;
	}

	public void setSourceID(int sourceID) {
		this.sourceID = sourceID;
	}

	public String getString(GameController gc){
		switch(modType){
			case SKIRMISHER:
				return "Skirmisher";
			case BLOOD_FURY:
				return "Blood Fury: +"+value+"<Physical Damage>";
			case PACK_HUNTER:
				return "Pack Hunter: +"+value+"<Physical Damage>";
			case SWARM:
				return "Swarm: +"+value+"<Physical Damage>";
			case DARK_PRESENCE:
				return "Dark Presence";
			case SLOW:
				return "Slowed";
			case RELENTLESS:
				return "Relentless";
			case REANIMATE:
				return "Reanimate";
			case MANA_DRAIN:
				return "Mana Drain";
			case CURSED_BOLT:
				return "Cursed Bolt";
			case VAMPIRIC:
				return "Vampiric";
			case VENOMOUS:
				return "Venomous";
			case FIREBOLT:
				return "Firebolt";
			case SHIELD_SLAM:
				return "Shield Slam";
			case SKULDUGGERY:
				return "Skulduggery";
			case THIEVERY:
				return "Thievery";
			case RAGE:
				switch (gc.getGameSetupController().getEnemyTypeByID().get(sourceID)){	
					case RENEGADE_FLAMECASTER:
						return "Enraged: +1<Magical Resistance>.";
					case BLACKWOOD_FIGHTER:
						if (modTarget==ModTarget.PHYSICAL_RESISTANCE){
							return "Enraged: +1<Physical Resistance>.";
						}
						else{
							return "Enraged: +1<Magical Resistance>.";
						}
					case BLACKWOOD_AMBUSHER:
					case BLACKWOOD_ASSASSIN:
					case BONESORROW_SHOOTER:
					case BONESORROW_WARRIOR:
					case DIRE_WOLF:
					case TRACKER_HOUND:
						return "Enraged: Deals +1<Physical Damage>.";
					case TWISTED_CURSEBEARER:
					case BLACKWOOD_CHANGELING:
					case BLOODSCORNE_VAMPIRE:
						return "Enraged: Deals +1<Magical Damage>.";
					case CURSED_WALKER:
					case ICE_REAVER:
						return "Enraged: Ignore Slow.";
					default:
						return "";
				}
			default:
				return "";
		}
	}
	

}
