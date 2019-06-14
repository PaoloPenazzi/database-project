package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FornitoreMapper implements RowMapper<Fornitore> {

	public FornitoreMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fornitore mapRow(ResultSet rs, int rowNum) throws SQLException {
		Fornitore fornitore = new Fornitore();
		fornitore.setIndirizzo(rs.getString("Indirizzo"));
		fornitore.setNome(rs.getString("Nome"));
		fornitore.setPartita_iva(rs.getInt("Partita_iva"));
		fornitore.setTelefono(rs.getInt("Telefono"));
		return fornitore;
	}
}
