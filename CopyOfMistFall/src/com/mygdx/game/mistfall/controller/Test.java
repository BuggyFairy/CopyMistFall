package com.mygdx.game.mistfall.controller;

import com.mygdx.game.mistfall.hero.Hero;
import com.mygdx.game.mistfall.hero.HeroCard;
import com.mygdx.game.mistfall.hero.characters.Arani.Arani;
import com.mygdx.game.mistfall.hero.characters.Arani.cards.WayOfDawn;
import com.mygdx.game.mistfall.hero.characters.Celenthia.Celenthia;
import com.mygdx.game.mistfall.hero.characters.Hareag.Hareag;
import com.mygdx.game.mistfall.hero.characters.ardenai.Ardenai;
import com.mygdx.game.mistfall.hero.characters.crow.Crow;
import com.mygdx.game.mistfall.hero.characters.fengray.Fengray;
import com.mygdx.game.mistfall.hero.characters.venda.Venda;
import com.mygdx.game.mistfall.model.enums.CardArea;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		VampireBatSwarm vbs = new VampireBatSwarm();
//		BloodscorneVampire bv = new BloodscorneVampire();
//		System.out.println(vbs.toString());
//		System.out.println("\n\n"+bv.toString());
//		WayOfDawn wod = new WayOfDawn();
//		System.out.println("\n"+wod.toString());
//		Reflexes r = new Reflexes();
//		Hero hero = new Hero();
//		wod.reflex(hero);
//		wod.reflex(hero);
//		WayOfDawn wod2 = new WayOfDawn();
//		BurialPile bp = new BurialPile();
//		bp.addBurial(wod);
//		bp.addBurial(wod2);
//		System.out.println("\n"+bp.toString());
		
		WayOfDawn wob1 = new WayOfDawn();
		wob1.setActualLocation(CardArea.HAND);
		WayOfDawn wob2 = new WayOfDawn();
		wob2.setActualLocation(CardArea.HAND);
		WayOfDawn wob3 = new WayOfDawn();
		wob3.setActualLocation(CardArea.HAND);
		
		WayOfDawn wob4 = new WayOfDawn();
		wob4.setActualLocation(CardArea.DECK);
		WayOfDawn wob5 = new WayOfDawn();
		wob5.setActualLocation(CardArea.DECK);
		WayOfDawn wob6 = new WayOfDawn();
		wob6.setActualLocation(CardArea.DECK);
		
		Hero hero = new Hero();

		hero.getHand().getCards().add(wob1);
		hero.getHand().getCards().add(wob2);
		hero.getHand().getCards().add(wob3);
		
		hero.getDeck().getCards().add(wob4);
		hero.getDeck().getCards().add(wob5);
		hero.getDeck().getCards().add(wob6);
		
		wob1.reflex(hero);
		for(HeroCard c : hero.getHand().getCards()){
			System.out.println(c.getActualLocation());
		}
		for(HeroCard c : hero.getDeck().getCards()){
			System.out.println(c.getActualLocation());
		}
		for(HeroCard c : hero.getDiscardPile().getCards()){
			System.out.println(c.getActualLocation());
		}
		wob2.reflex(hero);
		for(HeroCard c : hero.getHand().getCards()){
			System.out.println(c.getActualLocation());
		}
		for(HeroCard c : hero.getDeck().getCards()){
			System.out.println(c.getActualLocation());
		}
		for(HeroCard c : hero.getDiscardPile().getCards()){
			System.out.println(c.getActualLocation());
		}
		wob3.reflex(hero);
		for(HeroCard c : hero.getHand().getCards()){
			System.out.println(c.getActualLocation());
		}
		for(HeroCard c : hero.getDeck().getCards()){
			System.out.println(c.getActualLocation());
		}
		for(HeroCard c : hero.getDiscardPile().getCards()){
			System.out.println(c.getActualLocation());
		}
		
		Arani arani = new Arani();
		System.out.println("\n\n\n"+arani.toString());
		
		Ardenai ardenai = new Ardenai();
		System.out.println("\n\n\n"+ardenai.toString());
		
		Celenthia cel = new Celenthia();
		System.out.println("\n\n\n"+cel.toString());
		
		Crow crow = new Crow();
		System.out.println("\n\n\n"+crow.toString());
		
		Fengray fen = new Fengray();
		System.out.println("\n\n\n"+fen.toString());
		
		Hareag hareg = new Hareag();
		System.out.println("\n\n\n"+hareg.toString());
		
		Venda vendi = new Venda();
		System.out.println("\n\n\n"+vendi.toString());
//		char c;
//		for(int i=0 ; i<256 ; i++){
//			c=(char)i;
//			System.out.println(c);
//		}
		

		
		
		
		
		
		
	}

}
