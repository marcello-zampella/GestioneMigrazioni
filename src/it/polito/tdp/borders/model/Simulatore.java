package it.polito.tdp.borders.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

public class Simulatore {

	//prima di scrivere il smulatore bisogna chiedesri:
	//Modello -> Stato del sistema
	
	//tipi di evento
	
	//parametri della simulazione
	
	//valori in output
	
	
	
	//statp del sistema
	private Graph<Country, DefaultEdge> grafo;
	
	
	
	
	//TIPI DI EVENTO
	//1 solo evento
	private PriorityQueue<Evento> queue;
	
	
	//parametri della simulazione
	private int N_MIGRANTI=1000;
	private Country partenza;
	
	
	//valori in output
	private int T;
	private Map<Country,Integer> stanziali;
	
	public void init(Country partenza, Graph<Country,DefaultEdge> grafo) {
		this.partenza=partenza;
		this.grafo=grafo;
		
		this.T=1; //tempo iniziale
		stanziali=new HashMap<Country,Integer>();
		for(Country c: this.grafo.vertexSet()) {
			stanziali.put(c, 0);
		}
		queue=new PriorityQueue<Evento>();
		
		//inserisco l'evento di partenza
		this.queue.add(new Evento(T,this.N_MIGRANTI,this.partenza));
		
	}
	
	public void run() {
		//estraggo un evento per volta dalla coda e lo eseguo
		//finché la coda non si svuota
		Evento e;
		while((e=queue.poll())!=null) {
			this.T=e.getT();
			int nPersone=e.getN();
			Country stato=e.getStato();
			List<Country> confinanti=Graphs.neighborListOf(this.grafo, stato);
			int migranti=(nPersone/2)/confinanti.size();
			
			if(migranti>0) {
				for(Country confinante: confinanti) {
					queue.add(new Evento(e.getT()+1,migranti,confinante));
				}
				int stanziali=nPersone-migranti*confinanti.size();
				this.stanziali.put(stato, this.stanziali.get(stato)+stanziali);
			}
		}
	}

	public int getLastT() {
		return T;
	}
	
	public Map<Country,Integer> getStanziali() {
		return this.stanziali;
	}

}
