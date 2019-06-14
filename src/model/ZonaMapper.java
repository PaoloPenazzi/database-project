package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ZonaMapper implements RowMapper<Zona> {

	public ZonaMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Zona mapRow(ResultSet rs, int rowNum) throws SQLException {
		Zona zona = new Zona();
		zona.setCodice_zona(rs.getInt("Codice_zona"));
		zona.setNome(rs.getString("Nome"));
		return zona;
	}
}
