package model;

public class Carte {
    private int numero;
	private int valore;
	private String seme;
	private Giocatore giocatore; 

    public Carte(int numero, String seme) { 
		this.numero = numero;
		this.seme = seme;
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
						System.out.println("valore 0");
						break;
                    }
                }
                this.giocatore = null;
            }
            public int getValore() {
                return this.valore;
            }
            public String toString() {
                return this.seme + " " + String.valueOf(numero);
            }
        
            public int compareTo(Carte carta, String seme, String briscola) {
                // Controllo se la carta corrente e la carta avversaria sono della briscola
                boolean isBriscolaCorrente = this.seme.equals(briscola);
                boolean isBriscolaAvversaria = carta.seme.equals(briscola);
            
                // Controllo se la carta corrente e la carta avversaria sono della stessa mossa
                boolean isStessaMossaCorrente = this.seme.equals(seme);
                boolean isStessaMossaAvversaria = carta.seme.equals(seme);
            
                // Comparazione in base alla briscola e alla mossa corrente
                if (isBriscolaCorrente && !isBriscolaAvversaria) {
                    return 1;
                } else if (isStessaMossaCorrente && !isStessaMossaAvversaria) {
                    return 1;
                } else if (isBriscolaAvversaria && !isBriscolaCorrente) {
                    return -1;
                } else if (isStessaMossaAvversaria && !isStessaMossaCorrente) {
                    return -1;
                } else {
                    // Se le carte non sono né della briscola né della stessa mossa, confronta i valori
                    if (this.valore == carta.valore) {
                        return Integer.compare(this.numero, carta.numero);
                    } else {
                        return Integer.compare(this.valore, carta.valore);
                    }
                }
            }            
        
            public Giocatore getPlayer(){
                return this.giocatore;
            }
        
            public String getSeme(){
                return this.seme;
            }

            public int getNumero(){
                return this.numero;
            }
        
        }
        

