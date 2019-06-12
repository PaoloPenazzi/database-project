package model;

public class Dipendente {
	private String Nome;
	private String Cognome;
	private int Stipendio;
	private String Indirizzo;
	private int Telefono;
	private String Ruolo;
	private String Tipo;

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

	public int getTelefono(){
		return Telefono;
	}

	public void setTelefono(int Telefono){
		this.Telefono=Telefono;
	}

	public String getRuolo(){
		return Ruolo;
	}

	public void setRuolo(String Ruolo){
		this.Ruolo=Ruolo;
	}

	public String getTipo(){
		return Tipo;
	}

	public void setTipo(String Tipo){
		this.Tipo=Tipo;
	}
}
