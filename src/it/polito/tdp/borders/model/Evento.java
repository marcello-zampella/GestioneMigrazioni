package it.polito.tdp.borders.model;

public class Evento implements Comparable<Evento>{

	//se ci fossero vari tipi di evento, qui dentro dovrei scrivere una classe enum
	//proprio come ho fatto in Noleggio auto
	
	private int t; //momento nel quale evento si verifica
	private int n; //numero persone che sono arrivate e che si sposteranno
	private Country stato;
	
	public Evento(int t, int n, Country stato) {
		super();
		this.t = t;
		this.n = n;
		this.stato = stato;
	}

	public int getT() {
		return t;
	}

	public int getN() {
		return n;
	}

	public Country getStato() {
		return stato;
	}

	@Override
	public int compareTo(Evento o) {
		return this.t-o.t;
	}
	
	

}
