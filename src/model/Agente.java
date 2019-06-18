package model;

public class Agente {
	private String Nome;
	private String Cognome;
	private String Partita_IVA;
	private double Provvigione;
	private String Telefono;
	private int Codice_Zona;

	public String getNome(){
		return Nome;
	}

	public void setNome(String Nome){
		this.Nome=Nome;
	}

	public String getCognome(){
		return Cognome;
	}

	public void setCognome(String Cognome){
		this.Cognome=Cognome;
	}

	public String getPartita_iva(){
		return Partita_IVA;
	}

	public void setPartita_iva(String Partita_IVA){
		this.Partita_IVA=Partita_IVA;
	}

	public double getProvvigione(){
		return Provvigione;
	}

	public void setProvvigione(double Provvigione){
		this.Provvigione=Provvigione;
	}

	public String getTelefono(){
		return Telefono;
	}

	public void setTelefono(String Telefono){
		this.Telefono=Telefono;
	}

	public int getCodice_zona(){
		return Codice_Zona;
	}

	public void setCodice_zona(int Codice_Zona){
		this.Codice_Zona=Codice_Zona;
	}
}
