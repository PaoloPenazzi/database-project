package model;

public class Fornitore {
	private String Nome;
	private String Indirizzo;
	private String Telefono;
	private String Partita_IVA;

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

	public String getTelefono(){
		return Telefono;
	}

	public void setTelefono(String Telefono){
		this.Telefono=Telefono;
	}

	public String getPartita_iva(){
		return Partita_IVA;
	}

	public void setPartita_iva(String Partita_IVA){
		this.Partita_IVA=Partita_IVA;
	}
}
