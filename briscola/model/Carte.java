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
        
            public int compareTo(Carte carta, String seme, String briscola){ 
                if((this.seme.equals(briscola))&&(!carta.seme.equals(briscola)))
                    return 1;
                if(this.seme.equals(seme)&&(!carta.seme.equals(briscola)&&!(carta.seme.equals(seme))))
                    return 1;
                if((this.seme.equals(briscola)&&(carta.seme.equals(briscola))||(this.seme.equals(seme)&&carta.seme.equals(seme)))){
                    if(this.valore==carta.valore)
                        if(this.numero>carta.numero)
                            return 1;
                        else 
                            return -1;
                    else
                        if(this.valore>carta.valore)
                            return 1;
                        else
                            return -1;
                }
                return -1;
            }
        
            public Giocatore getPlayer(){
                return this.giocatore;
            }
        
            public String getSeme(){
                return this.seme;
            }
        
        }
        

