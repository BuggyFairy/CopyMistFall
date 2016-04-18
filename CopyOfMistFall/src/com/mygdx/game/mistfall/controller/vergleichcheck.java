package com.mygdx.game.mistfall.controller;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.blue.VampireBatSwarm;

public class vergleichcheck {

	
		public static void main(String[] args){
			
			List<Enemy> enemies = new ArrayList<Enemy>();
			VampireBatSwarm v1 = new VampireBatSwarm();
			VampireBatSwarm v2 = new VampireBatSwarm();
			enemies.add(v1);	//Ich bin an Stelle 0 in der Liste
			enemies.add(v2);	//Ich bin an Stelle 1 in der Liste
			
			System.out.println(enemies.get(0).equals(v1)); //Ist v1 gleich v1 ?
			System.out.println(enemies.get(0).equals(v2)); //Ist v1 gleich v2 ?
			System.out.println(enemies.get(1).equals(v1)); //Ist v2 gleich v1 ?
			System.out.println(enemies.get(1).equals(v2)); //Ist v2 gleich v2 ?
		}
}
