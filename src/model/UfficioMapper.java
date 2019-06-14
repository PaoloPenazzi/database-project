package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UfficioMapper implements RowMapper<Ufficio> {

	public UfficioMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Ufficio mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ufficio ufficio = new Ufficio();
		ufficio.setNome(rs.getString("Nome"));
		ufficio.setDescrizione(rs.getString("Descrizione"));
		return ufficio;
	}

}
