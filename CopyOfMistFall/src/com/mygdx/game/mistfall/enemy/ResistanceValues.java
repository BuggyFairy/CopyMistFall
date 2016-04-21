package com.mygdx.game.mistfall.enemy;

import java.util.LinkedList;

import com.mygdx.game.mistfall.model.modifications.Modification;

public class ResistanceValues {
	
	private int physicalResBase;
	private int physicalResMod;
	private LinkedList<Modification> physicalResModifications;
	
	private int magicalResBase;
	private int magicalResMod;
	private LinkedList<Modification> magicalResModifications;
	
	
	public ResistanceValues() {
		physicalResModifications = new LinkedList<Modification>();
		
		magicalResModifications = new LinkedList<Modification>();
	}
	
	
	public int getPhysicalResBase() {
		return physicalResBase;
	}
	public void setPhysicalResBase(int physicalResBase) {
		this.physicalResBase = physicalResBase;
	}
	public int getPhysicalResMod() {
		return physicalResMod;
	}
	public void setPhysicalResMod(int physicalResMod) {
		this.physicalResMod = physicalResMod;
	}
	public LinkedList<Modification> getPhysicalResModifications() {
		return physicalResModifications;
	}

	
	
	public int getMagicalResBase() {
		return magicalResBase;
	}
	public void setMagicalResBase(int magicalResBase) {
		this.magicalResBase = magicalResBase;
	}
	public int getMagicalResMod() {
		return magicalResMod;
	}
	public void setMagicalResMod(int magicalResMod) {
		this.magicalResMod = magicalResMod;
	}
	public LinkedList<Modification> getMagicalResModifications() {
		return magicalResModifications;
	}

}
