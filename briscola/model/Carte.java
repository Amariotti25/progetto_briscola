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
            
            public Carte compareTo(Carte altraCarta, String briscola) {
                if(this.seme.equals(briscola)&&!altraCarta.seme.equals(briscola)){
                    System.out.println("prima carta lanciata briscola");
                    return this;
                }else if(!this.seme.equals(briscola)&&altraCarta.seme.equals(briscola)){
                    System.out.println("seconda carta lanciata briscola");
                    return altraCarta;
                }else if(!this.seme.equals(altraCarta.seme)){
                    System.out.println("semi diversi");
                    return this;
                }else{
                    if(this.valore !=0 && altraCarta.valore != 0){
                        System.out.println("this.valore !=0 && altraCarta.valore != 0");
                        if(this.valore>altraCarta.valore){
                            System.out.println("this.valore>altraCarta.valore");
                            return this;
                        }else{
                            System.out.println("this.valore<altraCarta.valore");
                            return altraCarta;
                        }
                    }else if(this.valore != 0 && altraCarta.valore == 0){
                        System.out.println("(this.valore != 0 && altraCarta.valore == 0)");
                        return this;
                    }else if(this.valore == 0 && altraCarta.valore != 0){
                        System.out.println("this.valore == 0 && altraCarta.valore != 0");
                        return altraCarta;
                    }else{
                        if(this.numero > altraCarta.numero){
                            System.out.println("this.numero > altraCarta.numero");
                            return this;
                        }else{
                            System.out.println("this.numero < altraCarta.numero");
                            return altraCarta;
                        }
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
        

