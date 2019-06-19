package model;

public class Vendita {
	private int Numero_Fattura;
	private int Quantita;
	private java.util.Date Data;
	private double Sconto;
	private int Codice_prodotto;
	private String Nome_cliente;
	private String Partita_IVA_agente;
	private String Telefono;

	public int getNumero_fattura(){
		return Numero_Fattura;
	}

	public void setNumero_fattura(int Numero_Fattura){
		this.Numero_Fattura=Numero_Fattura;
	}

	public int getQuantita(){
		return Quantita;
	}

	public void setQuantita(int Quantita){
		this.Quantita=Quantita;
	}

	public java.util.Date getData(){
		return Data;
	}

	public void setData(java.util.Date Data){
		this.Data=Data;
	}

	public double getSconto(){
		return Sconto;
	}

	public void setSconto(double Sconto){
		this.Sconto=Sconto;
	}

	public int getCodice_prodotto(){
		return Codice_prodotto;
	}

	public void setCodice_prodotto(int Codice_prodotto){
		this.Codice_prodotto=Codice_prodotto;
	}

	public String getNome_cliente(){
		return Nome_cliente;
	}

	public void setNome_cliente(String Nome_cliente){
		this.Nome_cliente=Nome_cliente;
	}

	public String getPartita_IVA_agente(){
		return Partita_IVA_agente;
	}

	public void setPartita_IVA_agente(String Partita_IVA){
		this.Partita_IVA_agente=Partita_IVA;
	}

	public String getTelefono(){
		return Telefono;
	}

	public void setTelefono(String Telefono){
		this.Telefono=Telefono;
	}
}
