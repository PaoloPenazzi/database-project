package model;

public class Agente {
	private String Cognome;
	private int Partita_IVA;
	private double Provvigione;
	private int Telefono;
	private int Codice_Zona;

	public String getCognome(){
		return Cognome;
	}

	public void setCognome(String Cognome){
		this.Cognome=Cognome;
	}

	public int getPartita_iva(){
		return Partita_IVA;
	}

	public void setPartita_iva(int Partita_IVA){
		this.Partita_IVA=Partita_IVA;
	}

	public double getProvvigione(){
		return Provvigione;
	}

	public void setProvvigione(double Provvigione){
		this.Provvigione=Provvigione;
	}

	public int getTelefono(){
		return Telefono;
	}

	public void setTelefono(int Telefono){
		this.Telefono=Telefono;
	}

	public int getCodice_zona(){
		return Codice_Zona;
	}

	public void setCodice_zona(int Codice_Zona){
		this.Codice_Zona=Codice_Zona;
	}
}
