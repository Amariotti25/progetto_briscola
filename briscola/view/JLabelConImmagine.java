package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JLabelConImmagine extends JLabel{

    private BufferedImage sfondo; // Immagine di sfondo del JLabel

    // Costruttore vuoto
    public JLabelConImmagine(){
        
    }
    
    // Costruttore con percorso immagine
    public JLabelConImmagine(String percorsoImmagine) {
        try {
            // Carica l'immagine dal percorso specificato
            this.sfondo = ImageIO.read(new File(percorsoImmagine));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Metodo per impostare un'immagine dopo la creazione dell'oggetto
    public void impostaImmagine(String percorsoImmagine) {
        try {
            // Carica l'immagine dal percorso specificato
            this.sfondo = ImageIO.read(new File(percorsoImmagine));
            repaint(); // Ridisegna il componente per riflettere il cambio di immagine
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Override del metodo paintComponent per disegnare l'immagine come sfondo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Disegna l'immagine come sfondo del pulsante
        if (sfondo != null) {
            g.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
