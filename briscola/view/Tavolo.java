package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
    public int scelta;
	public JButtonConImmagine primaCarta;
	public JButtonConImmagine secondaCarta;
	public JButtonConImmagine terzaCarta;
	private JLabel timerGiocatore;
	private JButton indietro;
	private JLabel labelSelezione;
	
	public Tavolo(String nomeGiocatore, String nomeGiocatore2) { 
		this.giocatore1 = nomeGiocatore;
		this.giocatore2 = nomeGiocatore2;
        this.scelta = 0;

		labelSelezione = new JLabel("Seleziona una carta");
		primaCarta = new JButtonConImmagine();
		secondaCarta = new JButtonConImmagine();
		terzaCarta = new JButtonConImmagine();
		timerGiocatore = new JLabel();
		indietro = new JButton("Esci dalla Partita");


		primaCarta.setPreferredSize(new Dimension(110,202));
		secondaCarta.setPreferredSize(new Dimension(110,202));
		terzaCarta.setPreferredSize(new Dimension(110,202));
		timerGiocatore.setPreferredSize(new Dimension(200,50));
		indietro.setPreferredSize(new Dimension(200,50));

		setLayout(new FlowLayout(FlowLayout.CENTER));

		add(labelSelezione);
		add(primaCarta);
		add(secondaCarta);
		add(terzaCarta);
		add(indietro);
		add(timerGiocatore);
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
