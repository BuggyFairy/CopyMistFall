package com.mygdx.game.mistfall.hero;

import java.util.LinkedList;
import java.util.List;

import com.mygdx.game.mistfall.hero.cards.HeroCard;

public class HeroArea {
	
	private List<HeroCard> a;
	private List<HeroCard> b;
	private List<HeroCard> c;
	private List<HeroCard> d;
	private List<HeroCard> e;
	private List<HeroCard> f;
	private List<HeroCard> g;
	private List<HeroCard> inf;
	
	private int a_max;
	private int b_max;
	private int c_max;
	private int d_max;
	private int e_max;
	private int f_max;
	private int g_max;
	
	
	public HeroArea() {
		super();
		a = new LinkedList<HeroCard>();
		b = new LinkedList<HeroCard>();
		c = new LinkedList<HeroCard>();
		d = new LinkedList<HeroCard>();
		e = new LinkedList<HeroCard>();
		f = new LinkedList<HeroCard>();
		g = new LinkedList<HeroCard>();
		inf = new LinkedList<HeroCard>();
	}


	public List<HeroCard> getA() {
		return a;
	}


	public void setA(List<HeroCard> a) {
		this.a = a;
	}


	public List<HeroCard> getB() {
		return b;
	}


	public void setB(List<HeroCard> b) {
		this.b = b;
	}


	public List<HeroCard> getC() {
		return c;
	}


	public void setC(List<HeroCard> c) {
		this.c = c;
	}


	public List<HeroCard> getD() {
		return d;
	}


	public void setD(List<HeroCard> d) {
		this.d = d;
	}


	public List<HeroCard> getE() {
		return e;
	}


	public void setE(List<HeroCard> e) {
		this.e = e;
	}


	public List<HeroCard> getF() {
		return f;
	}


	public void setF(List<HeroCard> f) {
		this.f = f;
	}


	public List<HeroCard> getG() {
		return g;
	}


	public void setG(List<HeroCard> g) {
		this.g = g;
	}


	public List<HeroCard> getInf() {
		return inf;
	}


	public void setInf(List<HeroCard> inf) {
		this.inf = inf;
	}


	public int getA_max() {
		return a_max;
	}


	public void setA_max(int a_max) {
		this.a_max = a_max;
	}


	public int getB_max() {
		return b_max;
	}


	public void setB_max(int b_max) {
		this.b_max = b_max;
	}


	public int getC_max() {
		return c_max;
	}


	public void setC_max(int c_max) {
		this.c_max = c_max;
	}


	public int getD_max() {
		return d_max;
	}


	public void setD_max(int d_max) {
		this.d_max = d_max;
	}


	public int getE_max() {
		return e_max;
	}


	public void setE_max(int e_max) {
		this.e_max = e_max;
	}


	public int getF_max() {
		return f_max;
	}


	public void setF_max(int f_max) {
		this.f_max = f_max;
	}


	public int getG_max() {
		return g_max;
	}


	public void setG_max(int g_max) {
		this.g_max = g_max;
	}
	
	

}
