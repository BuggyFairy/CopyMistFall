package com.mygdx.game.mistfall.hero.characters.Celenthia;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.enums.GearKeyword;

public class Celenthia extends Hero{

	private static final int BASEFOCUS = 4;
	
	public Celenthia (){
		super();
		setName("CELENTHIA THE ARCANE MAGE");
		setFocus(BASEFOCUS);
		setRestoration(1);
		setGearProficiencies(new LinkedList<GearKeyword>());
		getGearProficiencies().add(GearKeyword.STAFF);
		getGearProficiencies().add(GearKeyword.WAND);
		getGearProficiencies().add(GearKeyword.DAGGER);
		getGearProficiencies().add(GearKeyword.BOOK);
		getGearProficiencies().add(GearKeyword.CLOAK);
		getGearProficiencies().add(GearKeyword.ROBE);
		getGearProficiencies().add(GearKeyword.SCROLL);
		getGearProficiencies().add(GearKeyword.POTION);
		getGearProficiencies().add(GearKeyword.TRINKET);
		
	}
}