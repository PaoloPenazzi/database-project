package model;

public class Consegna {
	private int Numero_Fattura;
	private String Nome_magazzino;
	private String Nome_cliente;
	private java.util.Date Data_Consegna;

	public int getNumero_fattura(){
		return Numero_Fattura;
	}

	public void setNumero_fattura(int Numero_Fattura){
		this.Numero_Fattura=Numero_Fattura;
	}

	public String getNome_magazzino(){
		return Nome_magazzino;
	}

	public void setNome_magazzino(String Nome_magazzino){
		this.Nome_magazzino=Nome_magazzino;
	}

	public String getNome_cliente(){
		return Nome_cliente;
	}

	public void setNome_cliente(String Nome_cliente){
		this.Nome_cliente=Nome_cliente;
	}

	public java.util.Date getData_consegna(){
		return Data_Consegna;
	}

	public void setData_consegna(java.util.Date Data_Consegna){
		this.Data_Consegna=Data_Consegna;
	}
}
