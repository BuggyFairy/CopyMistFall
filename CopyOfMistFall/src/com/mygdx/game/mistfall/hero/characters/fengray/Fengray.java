package com.mygdx.game.mistfall.hero.characters.fengray;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.enums.GearKeyword;

public class Fengray extends Hero{

	private static final int BASEFOCUS = 4;
	
	public Fengray (){
		super();
		setName("FENGRAY THE SHIELDBEARER");
		setFocus(BASEFOCUS);
		setRestoration(2);
		setGearProficiencies(new LinkedList<GearKeyword>());
		getGearProficiencies().add(GearKeyword.AXE);
		getGearProficiencies().add(GearKeyword.HAMMER);
		getGearProficiencies().add(GearKeyword.MACE);
		getGearProficiencies().add(GearKeyword.SWORD);
		getGearProficiencies().add(GearKeyword.ARMOUR);
		getGearProficiencies().add(GearKeyword.SHIELD);
		getGearProficiencies().add(GearKeyword.POTION);
		getGearProficiencies().add(GearKeyword.TRINKET);
		
	}
}