package com.mygdx.game.mistfall.enemy;

import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityArea;
import com.mygdx.game.mistfall.enemy.enums.EnemyAbilityType;

public class EnemyAbility {
	
	private EnemyAbilityType type;
	private EnemyAbilityArea area;
	private String description;
	
	
	public EnemyAbilityType getType() {
		return type;
	}
	public void setType(EnemyAbilityType type) {
		this.type = type;
	}
	public EnemyAbilityArea getArea() {
		return area;
	}
	public void setArea(EnemyAbilityArea area) {
		this.area = area;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public EnemyAbility(EnemyAbilityType type, EnemyAbilityArea area) {
		this.type = type;
		this.area = area;
		
	}

}
