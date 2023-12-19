package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.SwingWorker;

import model.Carte;
import model.Giocatore;
import model.Mazzo;
import view.Tavolo;

public class Partita extends SwingWorker <Void, Void>{

	private final String nomePlayer1 ;
	private final String nomePlayer2 ;

	private Tavolo gioco;
	private int sceltaUtente;
	private LinkedList<Carte> carteDisp;
	

	private ArrayList<Carte> mazzoPlayer1 = new ArrayList<>();
	private ArrayList<Carte> mazzoPlayer2 = new ArrayList<>();
	
	private Giocatore giocatoreVincente;
	public Giocatore player1; 
	public Giocatore player2;
	private Giocatore attuale;
	private String turnoPlayerAttuale; 
	private boolean isStoppable = false;
	private Carte ultimaCartaGiocata = null;
	private Mazzo mazzo;
	private Carte briscola;

	public Partita(String nome, String nome2,Tavolo game) {
		this.gioco = game;
		this.sceltaUtente = 0;

		this.nomePlayer1 = nome;
		this.nomePlayer2 = nome2;
	}

	@Override
	protected Void doInBackground() throws Exception {
        startGame();
        return null;
    }

	private void startGame() {
		Mazzo mazzo = new Mazzo(true);
		LinkedList<Carte> listaCarte = mazzo.getListaCarte();
		ListIterator<Carte> it1 = listaCarte.listIterator();
	
		// Assegnamento delle carte ai giocatori
		for (int i = 1; i <= 20; i++)
			mazzoPlayer1.add(it1.next());
	
		for (int i = 21; i <= 40; i++)
			mazzoPlayer2.add(it1.next());
	
		player1 = new Giocatore(nomePlayer1, mazzoPlayer1);
		player2 = new Giocatore(nomePlayer2, mazzoPlayer2);
		this.turnoPlayerAttuale = player1.getNome();
		this.briscola = mazzo.getListaCarte().getLast();
	
		for (int i = 2; i <= 18; i++) {  // Aggiunta di questa riga
			Carte carta1, carta2;
			System.out.println("la briscola è: " + briscola);
	
			if (turnoPlayerAttuale.equals(player1.getNome())) {
				ultimaCartaGiocata = null;
				System.out.println("\nUltima carta giocata: " + ultimaCartaGiocata);
				carta1 = mossa(player1, briscola, i);
				ultimaCartaGiocata = carta1; // Serve per il messaggio da console
				System.out.println("\nUltima carta giocata: " + ultimaCartaGiocata);
				carta2 = mossa(player2, briscola, i);
	
			} else {
				ultimaCartaGiocata = null;
				System.out.println("\nUltima carta giocata: " + ultimaCartaGiocata);
				carta2 = mossa(player2, briscola, i);
				ultimaCartaGiocata = carta2; // Serve per il messaggio da console
				carta1 = mossa(player1, briscola, i);
			}
	
			if (carta1.compareTo(carta2, briscola.getSeme(), briscola.getSeme()) == 1) {
				giocatoreVincente = player1;
				turnoPlayerAttuale = player1.getNome();
				player1.setPunteggio(player1.getPunteggio() + carta1.getValore() + carta2.getValore());
			} else {
				giocatoreVincente = player2;
				turnoPlayerAttuale = player2.getNome();
				player2.setPunteggio(player2.getPunteggio() + carta1.getValore() + carta2.getValore());
			}
		}
	
		// Rimane invariato fino alla fine del gioco
		for (int j = 1; j <= 3; j++) {
			Carte carta1, carta2;
	
			if (turnoPlayerAttuale.equals(player1.getNome())) {
				System.out.println("\nUltima carta giocata: " + ultimaCartaGiocata);
				carta1 = mossaUltimoTurn(player1);
				carta2 = mossaUltimoTurn(player2);
	
			} else {
				System.out.println("\nUltima carta giocata: " + ultimaCartaGiocata);
				carta2 = mossaUltimoTurn(player2);
				carta1 = mossaUltimoTurn(player1);
			}
	
			if (carta1.compareTo(carta2, briscola.getSeme(), briscola.getSeme()) == 1) {
				giocatoreVincente = player1;
				turnoPlayerAttuale = player1.getNome();
				player1.setPunteggio(player1.getPunteggio() + carta1.getValore() + carta2.getValore());
			} else {
				giocatoreVincente = player2;
				turnoPlayerAttuale = player2.getNome();
				player2.setPunteggio(player2.getPunteggio() + carta1.getValore() + carta2.getValore());
			}
		}
	
		System.out.println("Punteggio di bianco: " + player1.getPunteggio() + "\n Punteggio di grigio: "
				+ player2.getPunteggio());
		if (player1.getPunteggio() > player2.getPunteggio()) {
			System.out.println("Vince: " + player1.getNome());
			isStoppable = true;
	
		} else if (player2.getPunteggio() > player1.getPunteggio()) {
			System.out.println("Vince: " + player2.getNome());
			isStoppable = true;
		} else {
			System.out.println("Pareggio");
			isStoppable = true;
		}
		gioco.notificaFineGioco();
	}
	
	public Carte mossa(Giocatore playerAttuale, Carte briscola, int indiceScorrimento) {
		this.attuale =playerAttuale;
		System.out.println("È il turno di " + playerAttuale.getNome() + "!");
		System.out.println("Scegli una carta dal mazzo! [1], [2], [3] ");
		LinkedList<Carte> carteDisponibili = playerAttuale.getCarteDisponibili();
		System.out.print(carteDisponibili + "  ");
		
		int indiceMossa = mossaScelta();
		//controllo se l'indice è valido
		while(indiceMossa < 1 || indiceMossa > 3) {
			indiceMossa = mossaScelta();
		}
		indiceMossa--;

		System.out.println(playerAttuale.getNome() + " butta sul tavolo " + carteDisponibili.get(indiceMossa));
		Carte daRestituire = carteDisponibili.get(indiceMossa);

		sostituisciCarta(playerAttuale, indiceScorrimento, indiceMossa);
		System.out.println("Punteggio di " + playerAttuale.getNome() + " = " + playerAttuale.getPunteggio());
		return daRestituire;
	}

	public synchronized int mossaScelta() {
		attesaScelta();
        return getSceltaUtente();
    }

	public Carte mossaUltimoTurn(Giocatore playerAttuale){

		//Numero carte Rimaste
		this.attuale = playerAttuale;
		int numeroCarteRimaste = playerAttuale.getCarteDisponibili().size();
		System.out.println("È il turno di " + playerAttuale.getNome() + "!");
		if(numeroCarteRimaste==3)
			System.out.println("Scegli una carta dal mazzo! [1], [2], [3] ");
		if(numeroCarteRimaste==2)
			System.out.println("Scegli una carta dal mazzo! [1], [2]");
		if(numeroCarteRimaste==1)
			System.out.println("Scegli una carta dal mazzo! [1]");

		System.out.println("Numero Carte Rimaste: "+numeroCarteRimaste+" "+playerAttuale.getCarteDisponibili());

		LinkedList<Carte> carteDisponibili = playerAttuale.getCarteDisponibili();
		System.out.print(carteDisponibili + "  ");
		
		int indiceMossa = mossaScelta();
		//controllo se l'indice è valido
		while(((numeroCarteRimaste==3)&&(indiceMossa < 1 || indiceMossa > 3))||((numeroCarteRimaste==2)&&(indiceMossa<1||indiceMossa>2))||((numeroCarteRimaste==1)&&(indiceMossa!=1))) {
			indiceMossa = mossaScelta();
		}
		indiceMossa--;

		System.out.println("\n" + playerAttuale.getNome() + " butta sul tavolo " + carteDisponibili.get(indiceMossa));
		
		Carte cartaRet = carteDisponibili.get(indiceMossa);
	eliminaCarta(playerAttuale,indiceMossa);
		return cartaRet;
	}

	public boolean controllaGameOver() {
		return isStoppable;
	} 

	public void sostituisciCarta(Giocatore giocatore, int indiceScorrimento, int indicePosizione) {
 		giocatore.getCarteDisponibili().set(indicePosizione, giocatore.getMazzo().get(indiceScorrimento + 1));
	}

	public void eliminaCarta(Giocatore playerAttuale,int indiceMossa){
		playerAttuale.getCarteDisponibili().remove(indiceMossa);
	}

	public void attesaScelta() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    public void notificaScelta() {
		notifyAll();
	}

    public void setGioco(Tavolo gioco) {
        this.gioco = gioco;
    }

	public int getSceltaUtente() {
		return sceltaUtente;
	}

    public synchronized void setSceltaUtente(int scelta) {
		this.sceltaUtente = scelta;
		notificaScelta();
	}

	public LinkedList<Carte> getCarteDisp (){
		return carteDisp = this.attuale.getCarteDisponibili();
	}
    
	public Carte getBriscola(){
		return this.briscola;
	}

	public Carte getcartasultavolo(){
		return this.ultimaCartaGiocata;
	}
}
