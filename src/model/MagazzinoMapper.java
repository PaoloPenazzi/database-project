package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MagazzinoMapper implements RowMapper<Magazzino> {

	public MagazzinoMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Magazzino mapRow(ResultSet rs, int rowNum) throws SQLException {
		Magazzino magazzino = new Magazzino();
		magazzino.setCodice_zona(rs.getInt("Codice_zona"));
		magazzino.setNome(rs.getString("Nome"));
		magazzino.setIndirizzo(rs.getString("Indirizzo"));
		magazzino.setDimensioni(rs.getInt("Dimensioni"));
		return magazzino;
	}
}
