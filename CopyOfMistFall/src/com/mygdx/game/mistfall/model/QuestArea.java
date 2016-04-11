package com.mygdx.game.mistfall.model;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.enemy.Enemy;

public class QuestArea {
	
	private List<Enemy> questAreaEnemies;
	
	public QuestArea(){
		setQuestAreaEnemies(new LinkedList<Enemy>());
	}

	public List<Enemy> getQuestAreaEnemies() {
		return questAreaEnemies;
	}

	public void setQuestAreaEnemies(List<Enemy> questAreaEnemies) {
		this.questAreaEnemies = questAreaEnemies;
	}
}
