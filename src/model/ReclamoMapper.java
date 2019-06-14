package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ReclamoMapper implements RowMapper<Reclamo> {

	public ReclamoMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Reclamo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Reclamo reclamo = new Reclamo();
		reclamo.setId_reclamo(rs.getInt("Id_reclamo"));
		reclamo.setNumero_fattura(rs.getInt("Numero_fattura"));
		reclamo.setTipologia(rs.getString("Tipologia"));
		return reclamo;
	}
}
