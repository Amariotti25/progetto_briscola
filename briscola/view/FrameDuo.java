package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

public class FrameDuo extends JFrame{
	private Tavolo start;
	
	public FrameDuo() {
		
		super("Briscola");
		
		start = new Tavolo();
		
		add(start, BorderLayout.CENTER);
		
		setSize(500, 350); // size della finestra
		setLocationRelativeTo(null); // farla comparire al centro
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // l'applicazione si chiude quando premi la "X"
		setVisible(true); // finestra visibile, da mettere alla fine dopo aver impostato la finestra
		
	}

}

class Tavolo extends JPanel implements ActionListener {
	
	private JButtonConImmagine primaCarta;
	private JButton secondaCarta;
    private JButton terzaCarta;
    private JButton confermaGiocatore;
    private JButton indietro;
	private JLabel labelSelezione;
    private String percorsoImmagine = "C://Users/Andrea/Desktop/progetto_briscola/briscola/immagini/00.jpg";
	
	public Tavolo() {
		
		labelSelezione = new JLabel("Seleziona una carta");
		primaCarta = new JButtonConImmagine("1",percorsoImmagine);
        secondaCarta = new JButton("2");
		terzaCarta = new JButton("3");
        confermaGiocatore = new JButton("Conferma");
        indietro = new JButton("Esci dalla Partita");


		primaCarta.setPreferredSize(new Dimension(110,202));
		secondaCarta.setPreferredSize(new Dimension(110,202));
        terzaCarta.setPreferredSize(new Dimension(110,202));
        confermaGiocatore.setPreferredSize(new Dimension(200,50));
        indietro.setPreferredSize(new Dimension(200,50));

		setLayout(new FlowLayout(FlowLayout.CENTER));

		add(labelSelezione);
		add(primaCarta);
        add(secondaCarta);
        add(terzaCarta);
        add(indietro);
		
        primaCarta.addActionListener(this);
		secondaCarta.addActionListener(this);
        terzaCarta.addActionListener(this);
        confermaGiocatore.addActionListener(this);
        indietro.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object azione = e.getSource(); // torna l'oggetto in cui � avvenuta l'azione
		JButton premuto = (JButton) azione;
		if(premuto == primaCarta || premuto == secondaCarta || premuto == terzaCarta) {
            labelSelezione.setVisible(false);
            primaCarta.setVisible(false);
            secondaCarta.setVisible(false);
            terzaCarta.setVisible(false);
			add(confermaGiocatore);
		} else if(premuto == confermaGiocatore){
            labelSelezione.setVisible(true);
			primaCarta.setVisible(true);
            secondaCarta.setVisible(true);
            terzaCarta.setVisible(true);
		} else if(premuto == indietro){
            SwingUtilities.getWindowAncestor(this).setVisible(false);
            new SelezionaPartecipanti();
        }
    }
}
