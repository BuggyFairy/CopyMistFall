package com.mygdx.game.mistfall.hero.characters.Arani;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.enums.GearKeyword;

public class Arani extends Hero{

	private static final int BASEFOCUS = 3;
	
	public Arani (){
		super();
		setName("ARANI THE DAWNBREAKER CLERIC");
		setFocus(BASEFOCUS);
		setRestoration(2);
		setDrawLimit(5);
		setGearProficiencies(new LinkedList<GearKeyword>());
		getGearProficiencies().add(GearKeyword.AXE);
		getGearProficiencies().add(GearKeyword.HAMMER);
		getGearProficiencies().add(GearKeyword.MACE);
		getGearProficiencies().add(GearKeyword.ARMOUR);
		getGearProficiencies().add(GearKeyword.SHIELD);
		getGearProficiencies().add(GearKeyword.SYMBOL);
		getGearProficiencies().add(GearKeyword.POTION);
		getGearProficiencies().add(GearKeyword.TRINKET);
		
	}
}
