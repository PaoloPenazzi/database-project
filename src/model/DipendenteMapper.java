package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DipendenteMapper implements RowMapper<Dipendente> {

	public DipendenteMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Dipendente mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dipendente dipendente = new Dipendente();
		dipendente.setCodice_dipendente(rs.getInt("Codice_dipendente"));
		dipendente.setCognome(rs.getString("Cognome"));
		dipendente.setIndirizzo(rs.getString("Indirizzo"));
		dipendente.setNome(rs.getString("Nome"));
		dipendente.setRuolo(rs.getString("Ruolo"));
		dipendente.setTelefono(rs.getInt("Telefono"));
		dipendente.setStipendio(rs.getInt("Stipendio"));
		dipendente.setUfficio(rs.getString("Ufficio"));
		return dipendente;
	}
}
