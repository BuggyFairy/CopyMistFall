package com.mygdx.game.mistfall.controller;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.mistfall.enemy.AttackValues;
import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.enemy.types.blue.VampireBatSwarm;
import com.mygdx.game.mistfall.model.enums.AttackType;

public class vergleichcheck {

	
		public static void main(String[] args){
			
			AttackValues attackvalues =new AttackValues();
			attackvalues.setType(AttackType.MAGICAL);
			attackvalues.setValueBase(5);
			attackvalues.setValueMod(7);
			
			blub(attackvalues);
			
			System.out.println(attackvalues.getType());
			
			List<Enemy> enemies = new ArrayList<Enemy>();
//			VampireBatSwarm v1 = new VampireBatSwarm();
//			VampireBatSwarm v2 = new VampireBatSwarm();
//			enemies.add(v1);	//Ich bin an Stelle 0 in der Liste
//			enemies.add(v2);	//Ich bin an Stelle 1 in der Liste
//			
//			System.out.println(enemies.get(0).equals(v1)); //Ist v1 gleich v1 ?
//			System.out.println(enemies.get(0).equals(v2)); //Ist v1 gleich v2 ?
//			System.out.println(enemies.get(1).equals(v1)); //Ist v2 gleich v1 ?
//			System.out.println(enemies.get(1).equals(v2)); //Ist v2 gleich v2 ?
//			VampireBatSwarm v3 = v1; //v3 ist nun kopie von v1
//			enemies.add(v3); //Ich bin an Stelle 2 in der Liste
//			System.out.println(enemies.get(2).equals(v1)); //Ist v3 gleich v1 ?
		}
		
		public static int blub (AttackValues attackValues){
			
			
			attackValues.setType(AttackType.PHYSICAL);
			return 1;
		}
}

