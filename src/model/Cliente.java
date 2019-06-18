package model;

public class Cliente {
	private String Nome;
	private String Tipologia;
	private String Indirizzo;
	private String Telefono;
	private int Codice_Zona;

	public String getNome(){
		return Nome;
	}

	public void setNome(String Nome){
		this.Nome=Nome;
	}

	public String getTipologia(){
		return Tipologia;
	}

	public void setTipologia(String Tipologia){
		this.Tipologia=Tipologia;
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

	public int getCodice_Zona(){
		return Codice_Zona;
	}

	public void setCodice_Zona(int Codice_Zona){
		this.Codice_Zona=Codice_Zona;
	}
}
