package com.mygdx.game.mistfall.model;

import java.awt.Point;

import com.mygdx.game.mistfall.model.enums.LocationStatus;
import com.mygdx.game.mistfall.model.enums.LocationType;

public class Location {
	
	private boolean revealed;
	private int restoration;
	private String name;
	private Point coordinates;
	private LocationType locationType;
	private LocationStatus locationStatus;
	
	
	
	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public LocationStatus getLocationStatus() {
		return locationStatus;
	}

	public void setLocationStatus(LocationStatus locationStatus) {
		this.locationStatus = locationStatus;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}

	public int getRestoration() {
		return restoration;
	}

	public void setRestoration(int restoration) {
		this.restoration = restoration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}
	
	

}
