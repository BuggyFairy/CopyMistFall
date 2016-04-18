package com.mygdx.game.mistfall.hero.characters.venda;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.enums.GearKeyword;

public class Venda extends Hero{

	private static final int BASEFOCUS = 4;
	
	public Venda (){
		super();
		setName("VENDA THE RAVENCRAG FURY");
		setFocus(BASEFOCUS);
		setRestoration(1);
		setDrawLimit(5);
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
