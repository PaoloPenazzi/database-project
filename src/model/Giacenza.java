package model;

public class Giacenza {
	private int Codice_prodotto;
	private String Magazzino;
	private int Settore;
	private int Quantita;
	private int Giacenza_minima;

	public int getCodice_prodotto(){
		return Codice_prodotto;
	}

	public void setCodice_prodotto(int Codice_prodotto){
		this.Codice_prodotto=Codice_prodotto;
	}

	public String getMagazzino(){
		return Magazzino;
	}

	public void setMagazzino(String Magazzino){
		this.Magazzino=Magazzino;
	}

	public int getSettore(){
		return Settore;
	}

	public void setSettore(int Settore){
		this.Settore=Settore;
	}

	public int getQuantita(){
		return Quantita;
	}

	public void setQuantita(int Quantita){
		this.Quantita=Quantita;
	}

	public int getGiacenza_minima(){
		return Giacenza_minima;
	}

	public void setGiacenza_minima(int Giacenza_minima){
		this.Giacenza_minima=Giacenza_minima;
	}

}
