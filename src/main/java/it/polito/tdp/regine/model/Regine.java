package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {
	

	// N è il numero di righe e colonne della scacchiera
	//   (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
	// 		List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	
	private int N;
	private List<Integer> soluzione;
	
	public List<Integer> risolvi(int N){
		this.N=N;
		List<Integer> parziale=new ArrayList<Integer>();
		//NON LINKEDLIST, abbiamo dei get e ci metterebbe
		//TROPPO
		
		this.soluzione=null;
		cerca(parziale, 0);
		return this.soluzione;
	}
	
	//cerca==true : trovato ; cerca==false: cerca ancora
	private boolean cerca(List<Integer>parziale, int livello) {
		if(livello==N) {
			// caso terminale
		//System.out.println(parziale);
			//this.soluzione=parziale;
			//non un riferimento a parziale,
			//che cambierà, ma una copia separata
			this.soluzione=new ArrayList<Integer>(parziale);
			return true;
		} else {
			for(int colonna=0; colonna<N; colonna++) {
				// if la possa nella casella [livello][colonna] è valida
				// se sì, aggiungi a parziale e fai ricorsione
				
				
				//questo controllo non ci fa esplorare 
				//proprio tutte le soluzioni
				if(posValida(parziale, colonna)) { //[0,6,4,7]
					parziale.add(colonna); //[0,6,4,7,1]
					
					boolean trovato=cerca(parziale, livello+1); 
					//attenzione, ciclo for andrà avanti, ma
					//il 5 non lo aggiungo dopo l'1, questa
					//era solo una prova, l'1 va tolto e poi 
					//si aggiunge il 5
				
					if(trovato)
						return true;
					parziale.remove(parziale.size()-1);
					//backtracking
				}
			}
			//se ne io, ne la mia chiamata abbiamo trovato
			//la soluzione, qualcosa dobbiamo ritornare e 
			//ritorniamo false e ciclo for del chiamante
			//continuerà a fare prove
			return false;
		}
	}
	
	private boolean posValida(List<Integer> parziale, int colonna) {
		//controlla se viene mangiata in verticale
		if(parziale.contains(colonna))
			return false;
		//controllo per riga insito in come ho costruito la 
		//soluzione
		
		//controllo per diagonale
		
		//le diagonali della matrice hanno proprietà che
		//riga+colonna=costante (quelle orientate cosi /)
		//Quelle orientate in modo inverso riga-colonna=k
		
		//controlla le diagonali: confronta la posizione
		//(livello, colonna) con (r,c) delle regine esistenti
		int livello=parziale.size();
		for(int r=0; r<livello;r++) {
			int c=parziale.get(r);
			
			if(r+c==livello+colonna || r-c==livello-colonna)
				return false;
		}
		
		return true;
	}
	
	
	
}
