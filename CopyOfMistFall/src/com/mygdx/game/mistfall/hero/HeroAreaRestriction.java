package com.mygdx.game.mistfall.hero;

public class HeroAreaRestriction {
	
	private char areaType;
	private int capacity;
	private int actualCount;
	
	public HeroAreaRestriction(char areaType, int capacity, int actualCount){
		
		this.areaType = areaType;
		this.capacity = capacity;
		this.actualCount = actualCount;
		
	}

	public char getAreaType() {
		return areaType;
	}

	public void setAreaType(char areaType) {
		this.areaType = areaType;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getActualCount() {
		return actualCount;
	}

	public void setActualCount(int actualCount) {
		this.actualCount = actualCount;
	}
	
	public String toString(){
		String text;
//		text = "The Area: "+areaType+" can hold up to "+capacity+" cards of this type. There are actually "+actualCount+" cards of this Type";
		text = "Thea Area: "+areaType+
			   "\nCan hold: "+capacity+
			   "\nActual contains :"+actualCount;
		return text;
	}
	
}
