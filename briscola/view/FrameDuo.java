package view;

import javax.swing.JFrame;

import java.awt.*;

public class FrameDuo extends JFrame{
	String giocatore1;
	String giocatore2;
	
	public FrameDuo(String nomeGiocatore, String nomeGiocatore2) {
		super("Briscola");

		this.giocatore1 = nomeGiocatore;
		this.giocatore2 = nomeGiocatore2;

		Tavolo start = new Tavolo(giocatore1,giocatore2);
		add(start, BorderLayout.CENTER);
		
		setSize(500, 350); // size della finestra
		setLocationRelativeTo(null); // farla comparire al centro
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // l'applicazione si chiude quando premi la "X"
		setVisible(true); // finestra visibile, da mettere alla fine dopo aver impostato la finestra

	}
}

