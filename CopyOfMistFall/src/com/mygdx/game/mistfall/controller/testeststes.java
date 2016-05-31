package com.mygdx.game.mistfall.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.enemy.Enemy;
import com.mygdx.game.mistfall.hero.Hero;

public class testeststes {
	public static void main(String[] shimon){
	GameController gc = new GameController();
	   Hero hero = new Hero();
	   List list = new LinkedList<Hero>();
	   list.add(hero);
	   gc.setHeroes(list);
	   List<Enemy> enemies = new ArrayList<Enemy>();
	   enemies = gc.getHeroes().get(0).getHeroEnemies().getCards();
	   for(Enemy e : enemies){
	    System.out.println(enemies.toString());
	    System.out.println("hurensohn");
	   }
	   addEnemy(gc);
	   for(Enemy e : enemies){
	    System.out.println(enemies.toString());
	    System.out.println("Nach der Methode");
	   }
	  }
	  
	  public static void addEnemy(GameController gc){
	   Hero hero = gc.getHeroes().get(0);
	   Enemy e = new Enemy();
	   e.setEnemyID(5);
	   hero.getHeroEnemies().getCards().add(e);
	  }
	  }
