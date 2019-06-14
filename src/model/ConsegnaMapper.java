package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ConsegnaMapper implements RowMapper<Consegna> {

	public ConsegnaMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Consegna mapRow(ResultSet rs, int rowNum) throws SQLException {
		Consegna consegna = new Consegna();
		consegna.setData_consegna(rs.getDate("Data_consegna"));
		consegna.setNome_cliente(rs.getString("Nome_cliente"));
		consegna.setNome_magazzino(rs.getString("Nome_magazzino"));
		consegna.setNumero_fattura(rs.getInt("Numero_fattura"));
		return consegna;
	}
}
