package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class SelezionaPartecipanti extends JFrame{
   private SceltaIniziale start;
	
	public SelezionaPartecipanti() {
		
		super("Briscola");
		
		start = new SceltaIniziale();
		
		add(start, BorderLayout.CENTER);
		
		setSize(400,200); // size della finestra
		setLocationRelativeTo(null); // farla comparire al centro
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // l'applicazione si chiude quando premi la "X"
		setVisible(true); // finestra visibile, da mettere alla fine dopo aver impostato la finestra
	}

}

class SceltaIniziale extends JPanel implements ActionListener {
	
	private JButton avvia;
	private JButton chiudi;
	private JLabel labelBenvenuto;
	private JTextField nome;
	private JTextField nome2;
	private JButton salvanome;
	private JLabel inserisci;

	public SceltaIniziale() {

		labelBenvenuto = new JLabel("Benvenuto, seleziona partecipanti");
		avvia = new JButton("Avvia Partita");
		chiudi = new JButton("Chiudi il Gioco");
		salvanome = new JButton("Salva");

		avvia.setPreferredSize(new Dimension(200,50));
		chiudi.setPreferredSize(new Dimension(200,50));

		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(labelBenvenuto);
		add(avvia);
		add(chiudi);
		add(salvanome);

		avvia.addActionListener(this);
		chiudi.addActionListener(this);
		salvanome.addActionListener(this);

		salvanome.setVisible(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object azione = e.getSource(); // torna l'oggetto in cui � avvenuta l'azione
		JButton premuto = (JButton) azione;
		String nick;
		String nick2;
		if(premuto == avvia) {
			avvia.setVisible(false);
			chiudi.setVisible(false);
			labelBenvenuto.setVisible(false);
			inserisci = new JLabel("Inserisci nome del primo giocatore");
			add(inserisci);
			inserisci.setVisible(true);
			nome = new JTextField();
			add(nome);
			nome.setPreferredSize(new Dimension(200,35));
			nome.setVisible(true);
			salvanome.setVisible(true);
			inserisci = new JLabel("Inserisci nome del secondo giocatore");
			add(inserisci);
			inserisci.setVisible(true);
			nome2 = new JTextField();
			add(nome2);
			nome2.setPreferredSize(new Dimension(200,35));
			nome2.setVisible(true);
			salvanome.setVisible(true);
		}else if( premuto== salvanome ){
			nick = nome.getText();
			nick2 = nome2.getText();
			new FrameDuo(nick,nick2);
			SwingUtilities.getWindowAncestor(this).setVisible(false);	
		} else if(premuto == chiudi){
			System.exit(0);
		}
	}
}
