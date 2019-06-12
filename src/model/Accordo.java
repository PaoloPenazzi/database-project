package model;

public class Accordo {
	private String Nome;
	private int Capacita;
	private String Marca;
	private double Prezzo_di_acquisto;
	private java.util.Date Data_Inizio;
	private java.util.Date Data_Fine;
	private int Codice_Dipendente;

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
