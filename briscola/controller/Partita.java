package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.SwingWorker;

import model.Carte;
import model.Giocatore;
import model.Mazzo;
import view.Tavolo;

/**
 * La classe Partita gestisce la logica del gioco Briscola.
 * Estende SwingWorker per eseguire operazioni asincrone in background.
 */
public class Partita extends SwingWorker <Void, Void>{

	private final String nomePlayer1 ;
	private final String nomePlayer2 ;

	private Tavolo gioco;
	private int sceltaUtente;
	private Giocatore primo;
	

	private ArrayList<Carte> mazzoPlayer1 = new ArrayList<>();
	private ArrayList<Carte> mazzoPlayer2 = new ArrayList<>();
	
	private Giocatore player1; 
	private Giocatore player2;
	private Giocatore attuale;
	private String turnoPlayerAttuale;
	private Carte ultimaCartaGiocata = null;
	private Carte briscola;

	/**
     * Costruttore della classe Partita.
     *
     * @param nome Il nome del primo giocatore.
     * @param nome2 Il nome del secondo giocatore.
     * @param game L'istanza della classe Tavolo associata alla partita.
     */
	public Partita(String nome, String nome2,Tavolo game) {
		this.gioco = game;
		this.sceltaUtente = 0;
		this.primo = null;

		this.nomePlayer1 = nome;
		this.nomePlayer2 = nome2;
	}

	/**
     * Implementazione del metodo doInBackground di SwingWorker.
     * Avvia il gioco chiamando il metodo startGame.
     *
     * @return null
     * @throws Exception
     */
	@Override
	protected Void doInBackground() throws Exception {
        startGame();
        return null;
    }

	/**
     * Avvia il gioco inizializzando i mazzi, distribuendo le carte ai giocatori,
     * gestendo le mosse dei giocatori e determinando il vincitore.
     */
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
	
		for (int i = 2; i <= 18; i++) {
			Carte carta1, carta2;
	
			if (turnoPlayerAttuale.equals(player1.getNome())) {
				ultimaCartaGiocata = null;
				this.primo = player1;
				carta1 = mossa(player1, briscola, i);
				ultimaCartaGiocata = carta1;
				carta2 = mossa(player2, briscola, i);
	
			} else {
				ultimaCartaGiocata = null;
				this.primo = player2;
				carta2 = mossa(player2, briscola, i);
				ultimaCartaGiocata = carta2;
				carta1 = mossa(player1, briscola, i);
			}
			if(this.primo.equals(player1)){
				if (carta1.compareTo(carta2, briscola.getSeme()) == carta1) {
					turnoPlayerAttuale = player1.getNome();
					player1.setPunteggio(player1.getPunteggio() + carta1.getValore() + carta2.getValore());
				} else {
					turnoPlayerAttuale = player2.getNome();
					player2.setPunteggio(player2.getPunteggio() + carta1.getValore() + carta2.getValore());
				}
			} else{
				if (carta2.compareTo(carta1, briscola.getSeme()) == carta2) {
					turnoPlayerAttuale = player2.getNome();
					player2.setPunteggio(player2.getPunteggio() + carta1.getValore() + carta2.getValore());
				} else {
					turnoPlayerAttuale = player1.getNome();
					player1.setPunteggio(player1.getPunteggio() + carta1.getValore() + carta2.getValore());
				}
			}
		}
	
		// Rimane invariato fino alla fine del gioco
		for (int j = 1; j <= 3; j++) {
			Carte carta1, carta2;
			ultimaCartaGiocata = null;
			if (turnoPlayerAttuale.equals(player1.getNome())) {
				carta1 = mossaUltimoTurn(player1);
				ultimaCartaGiocata = carta1;
				carta2 = mossaUltimoTurn(player2);
	
			} else {
				carta1 = mossaUltimoTurn(player2);
				ultimaCartaGiocata = carta1;
				carta2 = mossaUltimoTurn(player1);
			}
			if(this.primo.equals(player1)){
				if (carta1.compareTo(carta2, briscola.getSeme()) == carta1) {
					turnoPlayerAttuale = player1.getNome();
					player1.setPunteggio(player1.getPunteggio() + carta1.getValore() + carta2.getValore());
				} else {
					turnoPlayerAttuale = player2.getNome();
					player2.setPunteggio(player2.getPunteggio() + carta1.getValore() + carta2.getValore());
				}
			}else {
				if (carta2.compareTo(carta1, briscola.getSeme()) == carta2) {
					turnoPlayerAttuale = player2.getNome();
					player2.setPunteggio(player2.getPunteggio() + carta1.getValore() + carta2.getValore());
				} else {
					turnoPlayerAttuale = player1.getNome();
					player1.setPunteggio(player1.getPunteggio() + carta1.getValore() + carta2.getValore());
				}
			}
		}
		gioco.notificaFineGioco();
	}
	
	/**
     * Gestisce la mossa di un giocatore durante il gioco.
     *
     * @param playerAttuale Il giocatore che effettua la mossa.
     * @param briscola La carta briscola del turno.
     * @param indiceScorrimento L'indice di scorrimento delle carte.
     * @return La carta scelta dal giocatore.
     */
	public Carte mossa(Giocatore playerAttuale, Carte briscola, int indiceScorrimento) {
		this.attuale =playerAttuale;
		LinkedList<Carte> carteDisponibili = playerAttuale.getCarteDisponibili();
		
		int indiceMossa = mossaScelta();
		//controllo se l'indice è valido
		while(indiceMossa < 1 || indiceMossa > 3) {
			indiceMossa = mossaScelta();
		}
		indiceMossa--;
		Carte daRestituire = carteDisponibili.get(indiceMossa);

		sostituisciCarta(playerAttuale, indiceScorrimento, indiceMossa);
		return daRestituire;
	}

	/**
     * Gestisce la scelta dell'utente durante una mossa.
     *
     * @return La scelta dell'utente.
     */
	public synchronized int mossaScelta() {
		attesaScelta();
        return this.sceltaUtente;
    }

	/**
     * Gestisce la mossa durante l'ultimo turno di gioco.
     *
     * @param playerAttuale Il giocatore che effettua la mossa.
     * @return La carta scelta dal giocatore.
     */
	public Carte mossaUltimoTurn(Giocatore playerAttuale){

		//Numero carte Rimaste
		this.attuale = playerAttuale;
		int numeroCarteRimaste = playerAttuale.getCarteDisponibili().size();

		LinkedList<Carte> carteDisponibili = playerAttuale.getCarteDisponibili();
		
		int indiceMossa = mossaScelta();
		//controllo se l'indice è valido
		while(((numeroCarteRimaste==3)&&(indiceMossa < 1 || indiceMossa > 3))||((numeroCarteRimaste==2)&&(indiceMossa<1||indiceMossa>2))||((numeroCarteRimaste==1)&&(indiceMossa!=1))) {
			indiceMossa = mossaScelta();
		}
		indiceMossa--;
		
		Carte cartaRet = carteDisponibili.get(indiceMossa);
		eliminaCarta(playerAttuale,indiceMossa);
		return cartaRet;
	}

	/**
     * Sostituisce la carta giocata con la successiva nel mazzo del giocatore.
     *
     * @param giocatore Il giocatore che ha effettuato la mossa.
     * @param indiceScorrimento L'indice di scorrimento delle carte.
     * @param indicePosizione L'indice della carta giocata.
     */
	public void sostituisciCarta(Giocatore giocatore, int indiceScorrimento, int indicePosizione) {
 		giocatore.getCarteDisponibili().set(indicePosizione, giocatore.getMazzo().get(indiceScorrimento + 1));
	}

	/**
     * Elimina la carta giocata dal mazzo del giocatore.
     *
     * @param playerAttuale Il giocatore che ha effettuato la mossa.
     * @param indiceMossa L'indice della carta giocata.
     */
	public void eliminaCarta(Giocatore playerAttuale,int indiceMossa){
		playerAttuale.getCarteDisponibili().remove(indiceMossa);
	}

    // Aspetta la scelta dell'utente
	public void attesaScelta() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Notifica la fine della scelta dell'utente
    public void notificaScelta() {
		notifyAll();
	}

	/**
     * Imposta la scelta dell'utente.
     *
     * @param scelta La scelta dell'utente.
     */
    public synchronized void setSceltaUtente(int scelta) {
		this.sceltaUtente = scelta;
		notificaScelta();
	}

	/**
     * Restituisce la lista delle carte disponibili al giocatore.
     *
     * @return La lista delle carte disponibili al giocatore attuale.
     */
	public LinkedList<Carte> getCarteDisp (){
		return this.attuale.getCarteDisponibili();
	}
    
	// Restituisce la carta briscola della partita
	public Carte getBriscola(){
		return this.briscola;
	}

	//Restituisce l'ultima carta giocata sul tavolo
	public Carte getCartaSulTavolo(){
		return this.ultimaCartaGiocata;
	}

	//Restituisce il giocatore di turno
	public Giocatore getTurnoGiocatore(){
		return this.attuale;
	}

	//Restituisce il giocatore1
	public Giocatore getGiocatore1(){
		return this.player1;
	}

	//Restituisce il giocatore2
	public Giocatore getGiocatore2(){
		return this.player2;
	}
}
