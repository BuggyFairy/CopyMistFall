package com.mygdx.game.mistfall.model;

import java.awt.Point;

public class LocationGrid {
	
	private Location[][] locationGrid;
	
	public LocationGrid(int x, int y){
		locationGrid = new Location[x][y];
	}

	public Location[][] getLocationGrid() {
		return locationGrid;
	}

	public void setLocationGrid(Location[][] locationGrid) {
		this.locationGrid = locationGrid;
	}
	
	public Location getLocationAt(Point p){
		return locationGrid[p.x][p.y];
	}
	
	public void setLocationAt(Point p, Location loc){
		locationGrid[p.x][p.y] = loc;
	}
	
	
}
