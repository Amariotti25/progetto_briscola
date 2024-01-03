package model;

public class Carte {
    private int numero;             // Numero della carta
	private int valore;             // Valore della carta
	private String seme;            // Seme della carta

    // Costruttore della classe Carte
    public Carte(int numero, String seme) { 
        // Inizializzazione dei valori della carta
		this.numero = numero;
		this.seme = seme;

        // Calcolo del valore della carta in base al numero e al seme
		if(numero <= 7 && numero != 1 && numero != 3)
			this.valore = 0;
		else {
			switch(numero) {
				case 1: this.valore = 11;
						break;
				case 3: this.valore = 10;
						break;
				case 10: this.valore = 4;
						break;
				case 9: this.valore = 3;
						break;
				case 8: this.valore = 2;
						break;
				default:
						break;
                    }
             }
    }

    // Metodo per ottenere il valore della carta
    public int getValore() {
        return this.valore;
    }

    // Metodo per ottenere il seme della carta
    public String getSeme(){
        return this.seme;
    }

    // Metodo per ottenere il numero della carta
    public int getNumero(){
        return this.numero;
    }
    
    // Metodo per confrontare due carte in base alle regole del gioco
    public Carte compareTo(Carte altraCarta, String briscola) {
        if(this.seme.equals(briscola)&&!altraCarta.seme.equals(briscola)){
            return this;
        }else if(!this.seme.equals(briscola)&&altraCarta.seme.equals(briscola)){
            return altraCarta;
        }else if(!this.seme.equals(altraCarta.seme)){
            return this;
        }else{
            if(this.valore !=0 && altraCarta.valore != 0){
                if(this.valore>altraCarta.valore){
                    return this;
                }else{
                    return altraCarta;
                }
            }else if(this.valore != 0 && altraCarta.valore == 0){
                return this;
            }else if(this.valore == 0 && altraCarta.valore != 0){
                return altraCarta;
            }else{
                if(this.numero > altraCarta.numero){
                    return this;
                }else{
                    return altraCarta;
                }
            }
        }
    }
}
        

