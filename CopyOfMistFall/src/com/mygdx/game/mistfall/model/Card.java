package com.mygdx.game.mistfall.model;

public class Card {
	
	private static final int height=200;
	private static final int width=200;
	private String name;
	
//	public Card (int height, int width, String name)
//	{
//		this.height=height;
//		this.width=width;
//		this.name=name;
//	}

	public int getHeight() {
		return height;
	}


	public int getWidth() {
		return width;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
