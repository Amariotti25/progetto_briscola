package model;

import java.util.Collections;
import java.util.LinkedList;

public class Mazzo {
    LinkedList<Carte> listaCarte;
    private final String[] listaSemi = {"bastoni", "coppe", "spade", "denari"};

    public Mazzo(boolean nuovoMazzo){
        this.listaCarte = new LinkedList<Carte>();
        if(nuovoMazzo){ 
            for(String seme : listaSemi) {
			    for(int num = 1; num<= 10; num++) 
				    listaCarte.addLast(new Carte(num,seme));
		    }
            Collections.shuffle(this.listaCarte);
        }
    }

    public LinkedList<Carte> getListaCarte(){
        return this.listaCarte;
    } 
}
