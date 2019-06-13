package model;

public class Fornitore {
	private String Nome;
	private String Indirizzo;
	private int Telefono;
	private int Partita_IVA;

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

	public int getTelefono(){
		return Telefono;
	}

	public void setTelefono(int Telefono){
		this.Telefono=Telefono;
	}

	public int getPartita_iva(){
		return Partita_IVA;
	}

	public void setPartita_iva(int Partita_IVA){
		this.Partita_IVA=Partita_IVA;
	}
}
