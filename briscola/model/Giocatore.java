package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Giocatore {
	private String nome;												// Nome del giocatore
	private int punteggio;												// Punteggio del giocatore
	private ArrayList<Carte> mazzo; 									// Mazzo del giocatore
	private LinkedList<Carte> carteDisponibili = new LinkedList<>();	// Carte disponibili per il giocatore

	// Costruttore del giocatore
	public Giocatore(String nome, ArrayList<Carte> mazzo) {
		// Inizializzazione delle variabili di istanza
		this.nome = nome;
		this.punteggio = 0;
		this.mazzo = new ArrayList<>(mazzo);

		// Aggiunta delle prime tre carte disponibili al mazzo del giocatore
		int i = 0;
		while( i <= 2) {
			this.carteDisponibili.addLast(mazzo.get(i));
			i++;
		}
	}

	// Metodo per ottenere le carte disponibili del giocatore
	public LinkedList<Carte> getCarteDisponibili() {
		return carteDisponibili;
	}

	// Metodo per ottenere il nome del giocatore
	public String getNome() {
		return this.nome;
	}

	// Metodo per ottenere il punteggio del giocatore
	public int getPunteggio() {
		return this.punteggio;
	}

	// Metodo per ottenere il mazzo del giocatore
	public ArrayList<Carte> getMazzo() {
		return this.mazzo;
	}
	
	// Metodo per impostare il punteggio del giocatore
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
}
