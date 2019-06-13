package model;

public class Prodotto {
	private int Codice_prodotto;
	private String Nome;
	private int Capacita;
	private String Marca;
	private double Prezzo_unitario;
	private String Tipologia;
	private String Confezione;

	public int getCodice_prodotto(){
		return Codice_prodotto;
	}

	public void setCodice_prodotto(int Codice_prodotto){
		this.Codice_prodotto=Codice_prodotto;
	}

	public String getNome(){
		return Nome;
	}

	public void setNome(String Nome){
		this.Nome=Nome;
	}

	public int getCapacita(){
		return Capacita;
	}

	public void setCapacita(int Capacita){
		this.Capacita=Capacita;
	}

	public String getMarca(){
		return Marca;
	}

	public void setMarca(String Marca){
		this.Marca=Marca;
	}

	public double getPrezzo_unitario(){
		return Prezzo_unitario;
	}

	public void setPrezzo_unitario(double Prezzo_unitario){
		this.Prezzo_unitario=Prezzo_unitario;
	}

	public String getTipologia(){
		return Tipologia;
	}

	public void setTipologia(String Tipologia){
		this.Tipologia=Tipologia;
	}

	public String getConfezione(){
		return Confezione;
	}

	public void setConfezione(String Confezione){
		this.Confezione=Confezione;
	}
}
