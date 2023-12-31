package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JButtonConImmagine extends JButton {

    private BufferedImage sfondo; // Immagine di sfondo del JButton

    // Costruttore vuoto
    public JButtonConImmagine(){
        
    }
    
    // Costruttore con percorso immagine
    public JButtonConImmagine(String percorsoImmagine) {
        try {
            // Carica l'immagine dal percorso specificato
            this.sfondo = ImageIO.read(new File(percorsoImmagine));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
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
