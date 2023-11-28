package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

public class SelezionaPartecipanti extends JFrame{
   private SceltaIniziale start;
	
	public SelezionaPartecipanti() {
		
		super("Briscola");
		
		start = new SceltaIniziale();
		
		add(start, BorderLayout.CENTER);
		
		setSize(350, 200); // size della finestra
		setLocationRelativeTo(null); // farla comparire al centro
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // l'applicazione si chiude quando premi la "X"
		setVisible(true); // finestra visibile, da mettere alla fine dopo aver impostato la finestra
		
	}

}

class SceltaIniziale extends JPanel implements ActionListener {
	
	private JButton bottone2giocatori;
	private JButton bottone4giocatori;
	private JLabel labelBenvenuto;
	
	public SceltaIniziale() {
		
		labelBenvenuto = new JLabel("Benvenuto, in quanti siete a giocare?");
		bottone2giocatori = new JButton("2 Giocatori");
		bottone4giocatori = new JButton("4 Giocatori");
		
		bottone2giocatori.setPreferredSize(new Dimension(200,50));
		bottone4giocatori.setPreferredSize(new Dimension(200,50));

		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(labelBenvenuto);
		add(bottone2giocatori);
		add(bottone4giocatori);
		
		bottone2giocatori.addActionListener(this);
		bottone4giocatori.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object azione = e.getSource(); // torna l'oggetto in cui � avvenuta l'azione
		JButton premuto = (JButton) azione;
		if(premuto == bottone2giocatori) {
			new FrameDuo();
		} else if(premuto == bottone4giocatori){
			new FrameSquad();
		}
	}
}
