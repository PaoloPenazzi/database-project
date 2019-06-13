package model;

public class Magazzino {
	private String Nome;
	private String Indirizzo;
	private int Dimensioni;
	private int Codice_Zona;

	public String getNome(){
		return Nome;
	}

	public void setNome(String Nome){
		this.Nome=Nome;
	}

	public String getIndirizzo(){
		return Indirizzo;
	}

	public void setIndirizzo(String Indirizzo){
		this.Indirizzo=Indirizzo;
	}

	public int getDimensioni(){
		return Dimensioni;
	}

	public void setDimensioni(int Dimensioni){
		this.Dimensioni=Dimensioni;
	}

	public int getCodice_zona(){
		return Codice_Zona;
	}

	public void setCodice_zona(int Codice_Zona){
		this.Codice_Zona=Codice_Zona;
	}
}
