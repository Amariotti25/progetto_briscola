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

public class AvviaPartita extends JFrame{
   private SceltaIniziale start;
	
	public AvviaPartita() {
		
		super("Briscola");
		
		//Creazione del pannello di "SceltaIniziale"
		start = new SceltaIniziale();
		
		// Aggiunta del pannello di "SceltaIniziale" al frame principale
		add(start, BorderLayout.CENTER);
		
		//Impostazioni della finestra principale
		setSize(400,200); // dimensioni della finestra
		setLocationRelativeTo(null); // posiziona la finestra al centro
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
	private JButton conferma;
	private JLabel inserisci;

	public SceltaIniziale() {

		//Inizializzazione degli oggetti presenti nella "SceltaIniziale"
		labelBenvenuto = new JLabel("Benvenuto nel gioco della Briscola!");
		avvia = new JButton("Avvia Partita");
		chiudi = new JButton("Chiudi il Gioco");
		conferma = new JButton("Salva");

		//Impostaszione delle dimensioni per i pulsanti
		avvia.setPreferredSize(new Dimension(200,50));
		chiudi.setPreferredSize(new Dimension(200,50));

		//Impostazione del layout del pannello
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Aggiunta degli oggetti al pannello
		add(labelBenvenuto);
		add(avvia);
		add(chiudi);
		add(conferma);

		//Aggiunta dei listener per gestire gli eventi dei pulsanti
		avvia.addActionListener(this);
		chiudi.addActionListener(this);
		conferma.addActionListener(this);

		conferma.setVisible(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object azione = e.getSource(); // torna l'oggetto in cui è avvenuta l'azione
		JButton premuto = (JButton) azione;
		String nick;
		String nick2;
		if(premuto == avvia) {
			// Nascondi e mostra gli elementi necessari per l'avvio della partita
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
			conferma.setVisible(true);
			inserisci = new JLabel("Inserisci nome del secondo giocatore");
			add(inserisci);
			inserisci.setVisible(true);
			nome2 = new JTextField();
			add(nome2);
			nome2.setPreferredSize(new Dimension(200,35));
			nome2.setVisible(true);
			conferma.setVisible(true);
		}else if( premuto== conferma ){
			// Ottieni i nomi inseriti e avvia un nuovo frame di gioco
			nick = nome.getText();
			nick2 = nome2.getText();
			new FrameDuo(nick,nick2);
			SwingUtilities.getWindowAncestor(this).setVisible(false);	
		} else if(premuto == chiudi){
			// Chiudi l'applicazione quando viene premuto il pulsante "Chiudi"
			System.exit(0);
		}
	}
}