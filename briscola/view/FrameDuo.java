package view;

import javax.swing.JFrame;

import java.awt.*;

public class FrameDuo extends JFrame{
	private String giocatore1;
	private String giocatore2;
	private Tavolo start;
	
	public FrameDuo(String nomeGiocatore, String nomeGiocatore2) {
		super("Briscola");

		this.giocatore1 = nomeGiocatore;
		this.giocatore2 = nomeGiocatore2;

		//Creazione del pannello di "Tavolo"
		this.start = new Tavolo(giocatore1,giocatore2);

		// Aggiunta del pannello di "Tavolo" al frame principale
		add(start, BorderLayout.CENTER);
		
		//Impostazioni della finestra principale
		setSize(700, 725); // Dimensioni della finestra
		setLocationRelativeTo(null); // Posiziona la finestra al centro
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // l'applicazione si chiude quando premi la "X"
		setVisible(true); // finestra visibile, da mettere alla fine dopo aver impostato la finestra

	}
}

