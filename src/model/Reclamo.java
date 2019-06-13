package model;

public class Reclamo {
	private int Numero_Fattura;
	private int ID_Reclamo;
	private String Tipologia;

	public int getNumero_fattura(){
		return Numero_Fattura;
	}

	public void setNumero_fattura(int Numero_Fattura){
		this.Numero_Fattura=Numero_Fattura;
	}

	public int getId_reclamo(){
		return ID_Reclamo;
	}

	public void setId_reclamo(int ID_Reclamo){
		this.ID_Reclamo=ID_Reclamo;
	}

	public String getTipologia(){
		return Tipologia;
	}

	public void setTipologia(String Tipologia){
		this.Tipologia=Tipologia;
	}

}
