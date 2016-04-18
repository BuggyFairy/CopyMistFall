package com.mygdx.game.mistfall.hero.characters.Hareag;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.enums.GearKeyword;

public class Hareag extends Hero{

	private static final int BASEFOCUS = 4;
	
	public Hareag (){
		super();
		setName("HAREAG THE FROST MAGE");
		setFocus(BASEFOCUS);
		setRestoration(1);
		setDrawLimit(5);
		setGearProficiencies(new LinkedList<GearKeyword>());
		getGearProficiencies().add(GearKeyword.DAGGER);
		getGearProficiencies().add(GearKeyword.STAFF);
		getGearProficiencies().add(GearKeyword.SWORD);
		getGearProficiencies().add(GearKeyword.WAND);
		getGearProficiencies().add(GearKeyword.BOOK);
		getGearProficiencies().add(GearKeyword.CLOAK);
		getGearProficiencies().add(GearKeyword.ROBE);
		getGearProficiencies().add(GearKeyword.SCROLL);
		getGearProficiencies().add(GearKeyword.POTION);
		getGearProficiencies().add(GearKeyword.TRINKET);
		
	}
}