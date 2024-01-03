package model;

import java.util.Collections;
import java.util.LinkedList;

public class Mazzo {
    // Lista di carte nel mazzo
    LinkedList<Carte> listaCarte;

    // Array di semi possibili
    private final String[] listaSemi = {"bastoni", "coppe", "spade", "denari"};

    // Costruttore del mazzo
    public Mazzo(boolean nuovoMazzo){
        // Inizializzazione della lista di carte
        this.listaCarte = new LinkedList<Carte>();

        // Se il parametro è true, crea un nuovo mazzo
        if(nuovoMazzo){ 
            // Per ogni seme nella lista dei semi
            for(String seme : listaSemi) {
                // Per ogni numero da 1 a 10
			    for(int num = 1; num<= 10; num++) 
				    listaCarte.addLast(new Carte(num,seme));
		    }
            // Mescola le carte nel mazzo
            Collections.shuffle(this.listaCarte);
        }
    }

    // Metodo per ottenere la lista di carte nel mazzo
    public LinkedList<Carte> getListaCarte(){
        return this.listaCarte;
    } 
}
