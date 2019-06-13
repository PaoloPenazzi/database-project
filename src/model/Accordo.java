package model;

public class Accordo {
	private int Codice_prodotto;
	private String Nome_fornitore;
	private double Prezzo_di_acquisto;
	private java.util.Date Data_Inizio;
	private java.util.Date Data_Fine;
	private int Codice_Dipendente;

	public int getCodice_prodotto(){
		return Codice_prodotto;
	}

	public void setCodice_prodotto(int Codice_prodotto){
		this.Codice_prodotto=Codice_prodotto;
	}

	public String getNome_fornitore(){
		return Nome_fornitore;
	}

	public void setNome_fornitore(String Nome_fornitore){
		this.Nome_fornitore=Nome_fornitore;
	}

	public double getPrezzo_di_acquisto(){
		return Prezzo_di_acquisto;
	}

	public void setPrezzo_di_acquisto(double Prezzo_di_acquisto){
		this.Prezzo_di_acquisto=Prezzo_di_acquisto;
	}

	public java.util.Date getData_inizio(){
		return Data_Inizio;
	}

	public void setData_inizio(java.util.Date Data_Inizio){
		this.Data_Inizio=Data_Inizio;
	}

	public java.util.Date getData_fine(){
		return Data_Fine;
	}

	public void setData_fine(java.util.Date Data_Fine){
		this.Data_Fine=Data_Fine;
	}

	public int getCodice_dipendente(){
		return Codice_Dipendente;
	}

	public void setCodice_dipendente(int Codice_Dipendente){
		this.Codice_Dipendente=Codice_Dipendente;
	}
}
