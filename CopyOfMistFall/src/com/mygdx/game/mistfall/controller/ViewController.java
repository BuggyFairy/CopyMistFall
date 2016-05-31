package com.mygdx.game.mistfall.controller;

import java.util.List;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.cards.HeroCard;
import com.mygdx.game.mistfall.model.Location;
import com.mygdx.game.mistfall.model.enums.PickControllerCharacters;

public class ViewController {

	public Location selectedLocation;
	public Boolean scoutConfirmed;
	public Boolean skipRelocation;
	public Boolean confirmRelocation;
	public Location getSelectedLocation() {
		return selectedLocation;
	}
	public void setSelectedLocation(Location selectedLocation) {
		this.selectedLocation = selectedLocation;
	}
	public Boolean getScoutConfirmed() {
		return scoutConfirmed;
	}
	public void setScoutConfirmed(Boolean scoutConfirmed) {
		this.scoutConfirmed = scoutConfirmed;
	}
	public Boolean getSkipRelocation() {
		return skipRelocation;
	}
	public void setSkipRelocation(Boolean skipRelocation) {
		this.skipRelocation = skipRelocation;
	}
	public Boolean getConfirmRelocation() {
		return confirmRelocation;
	}
	public void setConfirmRelocation(Boolean confirmRelocation) {
		this.confirmRelocation = confirmRelocation;
	}
	public ViewController() {
		selectedLocation = null;
		scoutConfirmed = false;
		skipRelocation = false;
		confirmRelocation = false;
	}
	
	
	
	public int pickCharacter(PickControllerCharacters pickControllerCharacters, List<?> sourceList, Hero selectedHero ){
		int characterPosition=-1;
		
		switch (pickControllerCharacters){
			case HEROES:
			
			break;
			case ENEMIES_HERO_AREA:
				
			break;
			case ENEMIES_QUEST_AREA:
				
			break;
			default:
			break;
		}
		
		return characterPosition;
	}
	public int pickHeroCard(List<HeroCard> cardsAvailable) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	

}
