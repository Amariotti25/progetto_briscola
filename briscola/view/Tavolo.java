package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.Partita;

public class Tavolo extends JPanel implements ActionListener {
	private String giocatore1;
	private String giocatore2;
	private Partita game;
	private JButtonConImmagine primaCarta;
	private JButtonConImmagine secondaCarta;
	private JButtonConImmagine terzaCarta;
	private JLabelConImmagine briscola;
	private JLabelConImmagine cartasultavolo; //ultima carta giocata dai giocatori
	private JLabelConImmagine cartacoperta1;
	private JLabelConImmagine cartacoperta2;
	private JLabelConImmagine cartacoperta3;
	private JLabel timerGiocatore;
	private JButton indietro;
	private JLabel labelSelezione;
	private JLabel punteggio1; //punteggio giocatore 1
	private JLabel punteggio2; //punteggio giocatore 2
	

	public Tavolo(String nomeGiocatore, String nomeGiocatore2) {
		this.setBackground(Color.green.darker());
		this.giocatore1 = nomeGiocatore;
		this.giocatore2 = nomeGiocatore2;
        
		labelSelezione = new JLabel("Seleziona una carta");
		primaCarta = new JButtonConImmagine();
		secondaCarta = new JButtonConImmagine();
		terzaCarta = new JButtonConImmagine();
		timerGiocatore = new JLabel();
		briscola = new JLabelConImmagine();
		cartasultavolo = new JLabelConImmagine();
		cartacoperta1 = new JLabelConImmagine("../progetto_briscola/briscola/immagini/dorso.jpg");
		cartacoperta2 = new JLabelConImmagine("../progetto_briscola/briscola/immagini/dorso.jpg");
		cartacoperta3 = new JLabelConImmagine("../progetto_briscola/briscola/immagini/dorso.jpg");
		indietro = new JButton("Esci dalla Partita");
		punteggio1 = new JLabel();
		punteggio2 = new JLabel();
		
		setLayout(null);
		
		cartacoperta1.setBounds(5,5,110,202);
		cartacoperta2.setBounds(120,5,110,202);
		cartacoperta3.setBounds(235,5,110,202);
		indietro.setBounds(350,5,150,50);
		briscola.setBounds(5,213,110,202);
		cartasultavolo.setBounds(190,213,110,202);
		labelSelezione.setBounds(5,420,150,50);
		primaCarta.setBounds(5,475,110,202);
		secondaCarta.setBounds(120,475,110,202);
		terzaCarta.setBounds(235,475,110,202);
		timerGiocatore.setBounds(420,100,150,50);
		punteggio1.setBounds(350, 475, 200, 50);
		punteggio2.setBounds(350, 525, 200, 50);

		add(labelSelezione);
		add(primaCarta);
		add(secondaCarta);
		add(terzaCarta);
		add(indietro);
		add(timerGiocatore);
		add(briscola);
		add(cartasultavolo);
		add(cartacoperta1);
		add(cartacoperta2);
		add(cartacoperta3);
		add(punteggio1);
		add(punteggio2);
		timerGiocatore.setVisible(false);

		primaCarta.addActionListener(this);
		secondaCarta.addActionListener(this);
		terzaCarta.addActionListener(this);
		indietro.addActionListener(this);

		game = new Partita(giocatore1, giocatore2, this);
		game.execute();

		SwingUtilities.invokeLater(() -> {
            primaCarta.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getCarteDisp().get(0).getSeme()
																				+game.getCarteDisp().get(0).getNumero()+".jpg");
			secondaCarta.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getCarteDisp().get(1).getSeme()
																				+game.getCarteDisp().get(1).getNumero()+".jpg");
			terzaCarta.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getCarteDisp().get(2).getSeme()
																				+game.getCarteDisp().get(2).getNumero()+".jpg");
			briscola.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getBriscola().getSeme()
																			  +game.getBriscola().getNumero()+".jpg");
			punteggio1.setText(giocatore1 + " punteggio = " + game.player1.getPunteggio());
			punteggio2.setText(giocatore2 + " punteggio = " + game.player2.getPunteggio());
		});
	}

	public void notificaFineGioco() {
        // Questo metodo viene chiamato quando il gioco è finito
        // Puoi eseguire le azioni necessarie qui, come aggiornare l'interfaccia utente

        // Esempio: mostra un messaggio di fine gioco
        JOptionPane.showMessageDialog(this, "Fine gioco!");
    }

	public synchronized void attesaScelta() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void notificaScelta() {
		notifyAll();
	}

	public void actionPerformed(ActionEvent e) {
        Object azione = e.getSource();
        JButton premuto = (JButton) azione;
		if (premuto == primaCarta) {
			game.setSceltaUtente(1);
			labelSelezione.setVisible(false);
			primaCarta.setVisible(false);
			secondaCarta.setVisible(false);
			terzaCarta.setVisible(false);
			timerGiocatore.setVisible(true);
			avviaTimer();
			primaCarta.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getCarteDisp().get(0).getSeme()
																				+game.getCarteDisp().get(0).getNumero()+".jpg");
			secondaCarta.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getCarteDisp().get(1).getSeme()
																				+game.getCarteDisp().get(1).getNumero()+".jpg");
			terzaCarta.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getCarteDisp().get(2).getSeme()
																				+game.getCarteDisp().get(2).getNumero()+".jpg");
			if(game.getcartasultavolo()!= null){
				cartasultavolo.setVisible(true);
				cartasultavolo.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getcartasultavolo().getSeme()
																				+game.getcartasultavolo().getNumero()+".jpg");
			}else
				cartasultavolo.setVisible(false);
			punteggio1.setText(giocatore1 + " punteggio = " + game.player1.getPunteggio());
			punteggio2.setText(giocatore2 + " punteggio = " + game.player2.getPunteggio());

		}else if (premuto == secondaCarta) {
			game.setSceltaUtente(2);
			labelSelezione.setVisible(false);
            primaCarta.setVisible(false);
            secondaCarta.setVisible(false);
            terzaCarta.setVisible(false);
			timerGiocatore.setVisible(true);
			avviaTimer();
			primaCarta.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getCarteDisp().get(0).getSeme()
																				+game.getCarteDisp().get(0).getNumero()+".jpg");
			secondaCarta.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getCarteDisp().get(1).getSeme()
																				+game.getCarteDisp().get(1).getNumero()+".jpg");
			terzaCarta.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getCarteDisp().get(2).getSeme()
																				+game.getCarteDisp().get(2).getNumero()+".jpg");
			if(game.getcartasultavolo()!= null){
				cartasultavolo.setVisible(true);
				cartasultavolo.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getcartasultavolo().getSeme()
																				+game.getcartasultavolo().getNumero()+".jpg");
			}else
				cartasultavolo.setVisible(false);
			punteggio1.setText(giocatore1 + " punteggio = " + game.player1.getPunteggio());
			punteggio2.setText(giocatore2 + " punteggio = " + game.player2.getPunteggio());

		} else if (premuto == terzaCarta) {
			game.setSceltaUtente(3);
			labelSelezione.setVisible(false);
            primaCarta.setVisible(false);
            secondaCarta.setVisible(false);
            terzaCarta.setVisible(false);
			timerGiocatore.setVisible(true);
			avviaTimer();
			primaCarta.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getCarteDisp().get(0).getSeme()
																				+game.getCarteDisp().get(0).getNumero()+".jpg");
			secondaCarta.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getCarteDisp().get(1).getSeme()
																				+game.getCarteDisp().get(1).getNumero()+".jpg");
			terzaCarta.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getCarteDisp().get(2).getSeme()
																				+game.getCarteDisp().get(2).getNumero()+".jpg");
			if(game.getcartasultavolo()!= null){
				cartasultavolo.setVisible(true);
				cartasultavolo.impostaImmagine("../progetto_briscola/briscola/immagini/"+game.getcartasultavolo().getSeme()
																				+game.getcartasultavolo().getNumero()+".jpg");
			}else
				cartasultavolo.setVisible(false);
			punteggio1.setText(giocatore1 + " punteggio = " + game.player1.getPunteggio());
			punteggio2.setText(giocatore2 + " punteggio = " + game.player2.getPunteggio());
																			
		} else if (premuto == indietro) {
			SwingUtilities.getWindowAncestor(this).setVisible(false);
			new SelezionaPartecipanti();
		}
	}
	// Aggiungi questo metodo alla tua classe Tavolo
public void avviaTimer() {
    javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
        private int count = 5;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (count > 0) {
                timerGiocatore.setText(Integer.toString(count));
                count--;
            } else {
                ((javax.swing.Timer) e.getSource()).stop();
                labelSelezione.setVisible(true);
                primaCarta.setVisible(true);
                secondaCarta.setVisible(true);
                terzaCarta.setVisible(true);
                timerGiocatore.setVisible(false);
            }
        }
    });
	timerGiocatore.setText("5");
    timer.start();
	}
}
