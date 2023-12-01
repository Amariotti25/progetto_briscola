package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JButtonConImmagine extends JButton {

    private BufferedImage sfondo;

    public JButtonConImmagine(String testo, String percorsoImmagine) {
        super(testo);

        try {
            this.sfondo = ImageIO.read(new File(percorsoImmagine));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Disegna l'immagine come sfondo del pulsante
        if (sfondo != null) {
            g.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
