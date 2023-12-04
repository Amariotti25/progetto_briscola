package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Giocatore {
	private String nome;
	private int punteggio;
	private ArrayList<Carte> mazzo; 
	private LinkedList<Carte> carteDisponibili = new LinkedList<>();

	public Giocatore(String nome, ArrayList<Carte> mazzo) {
		this.nome = nome;
		this.punteggio = 0;
		this.mazzo = new ArrayList<>(mazzo);
		int i = 0;
		while( i <= 2) {
			this.carteDisponibili.addLast(mazzo.get(i));
			i++;
		}
	}

	public LinkedList<Carte> getCarteDisponibili() {
		return carteDisponibili;
	}
	public String getNome() {
		return this.nome;
	}
	public int getPunteggio() {
		return this.punteggio;
	}

	public ArrayList<Carte> getMazzo() {
		return this.mazzo;
	}
	
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
}
