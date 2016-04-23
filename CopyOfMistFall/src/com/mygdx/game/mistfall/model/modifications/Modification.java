package com.mygdx.game.mistfall.model.modifications;

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
	

}
