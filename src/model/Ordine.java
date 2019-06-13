package model;

public class Ordine {
	private int Numero_Fattura;
	private int Quantita;
	private java.util.Date Data_ordine;
	private java.util.Date Data_consegna;
	private String Nome;
	private String Nome_fornitore;
	private int Codice_prodotto;

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

	public java.util.Date getData_ordine(){
		return Data_ordine;
	}

	public void setData_ordine(java.util.Date Data_ordine){
		this.Data_ordine=Data_ordine;
	}

	public java.util.Date getData_consegna(){
		return Data_consegna;
	}

	public void setData_consegna(java.util.Date Data_consegna){
		this.Data_consegna=Data_consegna;
	}

	public String getNome(){
		return Nome;
	}

	public void setNome(String Nome){
		this.Nome=Nome;
	}

	public String getNome_fornitore(){
		return Nome_fornitore;
	}

	public void setNome_fornitore(String Nome_fornitore){
		this.Nome_fornitore=Nome_fornitore;
	}

	public int getCodice_prodotto(){
		return Codice_prodotto;
	}

	public void setCodice_prodotto(int Codice_prodotto){
		this.Codice_prodotto=Codice_prodotto;
	}
}
