package model;

public class Dipendente {
	private int Codice_Dipendente;
	private String Nome;
	private String Cognome;
	private int Stipendio;
	private String Indirizzo;
	private String Telefono;
	private String Ruolo;
	private String Ufficio;

	public int getCodice_dipendente(){
		return Codice_Dipendente;
	}

	public void setCodice_dipendente(int Codice_Dipendente){
		this.Codice_Dipendente=Codice_Dipendente;
	}

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

	public int getStipendio(){
		return Stipendio;
	}

	public void setStipendio(int Stipendio){
		this.Stipendio=Stipendio;
	}

	public String getIndirizzo(){
		return Indirizzo;
	}

	public void setIndirizzo(String Indirizzo){
		this.Indirizzo=Indirizzo;
	}

	public String getTelefono(){
		return Telefono;
	}

	public void setTelefono(String Telefono){
		this.Telefono=Telefono;
	}

	public String getRuolo(){
		return Ruolo;
	}

	public void setRuolo(String Ruolo){
		this.Ruolo=Ruolo;
	}

	public String getUfficio(){
		return Ufficio;
	}

	public void setUfficio(String Ufficio){
		this.Ufficio=Ufficio;
	}
}
