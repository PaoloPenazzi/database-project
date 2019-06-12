package model;

public class Consegna {
	private String Cre_Nome;
	private int Numero_Fattura;
	private String Nome;
	private java.util.Date Data_Consegna;

	public String getCre_nome(){
		return Cre_Nome;
	}

	public void setCre_nome(String Cre_Nome){
		this.Cre_Nome=Cre_Nome;
	}

	public int getNumero_fattura(){
		return Numero_Fattura;
	}

	public void setNumero_fattura(int Numero_Fattura){
		this.Numero_Fattura=Numero_Fattura;
	}

	public String getNome(){
		return Nome;
	}

	public void setNome(String Nome){
		this.Nome=Nome;
	}

	public java.util.Date getData_consegna(){
		return Data_Consegna;
	}

	public void setData_consegna(java.util.Date Data_Consegna){
		this.Data_Consegna=Data_Consegna;
	}
}
