package com.mygdx.game.mistfall.hero.characters.ardenai;

import java.util.LinkedList;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.model.enums.GearKeyword;

public class Ardenai extends Hero{

	private static final int BASEFOCUS = 3;
	
	public Ardenai (){
		super();
		setName("ARDENAI THE ARCANEWEAVE ARCHER");
		setFocus(BASEFOCUS);
		setRestoration(1);
		setDrawLimit(5);
		setGearProficiencies(new LinkedList<GearKeyword>());
		getGearProficiencies().add(GearKeyword.BOW);
		getGearProficiencies().add(GearKeyword.CROSSBOW);
		getGearProficiencies().add(GearKeyword.DAGGER);
		getGearProficiencies().add(GearKeyword.STAFF);
		getGearProficiencies().add(GearKeyword.CLOAK);
		getGearProficiencies().add(GearKeyword.ITEM);
		getGearProficiencies().add(GearKeyword.POTION);
		getGearProficiencies().add(GearKeyword.TRINKET);
		
	}
}
