package com.mygdx.game.mistfall.hero;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.enemy.Enemy;

public class HeroEnemies {

	
private List<Enemy> cards;
	
	public HeroEnemies(){
		this.cards=new LinkedList<Enemy>();
	}
	
	public List<Enemy> getCards() {
		return cards;
	}

	public void setCards(List<Enemy> cards) {
		this.cards = cards;
	}
	
	public int getEnemyPos (Enemy enemy){
		int pos=-1;
		for (int i=0;i<cards.size();i++){
			if (cards.get(i).equals(enemy)){
				pos=i;
				break;
			}
		}
		return pos;
	}
	
//	public int[] posOfEnemyWithAbility(EnemyAbilityType enemyAbilityType){
//		int pos[] = new int[2];
//		pos[0]=-1;
//		pos[1]=-1;
//		for (int i=0;i<cards.size();i++){
//			for (int j=0;j<cards.get(i).getAbilities().size();j++){
//				if (cards.get(i).getAbilities().get(j).getType()==enemyAbilityType){
//					pos[0]=i;
//					pos[1]=j;
//					return pos;
//				}
//			}
//		}
//		return pos;
	

}
