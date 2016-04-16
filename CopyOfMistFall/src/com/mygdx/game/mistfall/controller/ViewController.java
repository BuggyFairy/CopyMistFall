package com.mygdx.game.mistfall.controller;

import com.mygdx.game.mistfall.model.Location;

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
	
	
	
	

}
