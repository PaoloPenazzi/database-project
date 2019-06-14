package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AgenteMapper implements RowMapper<Agente> {

	public AgenteMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Agente mapRow(ResultSet rs, int rowNum) throws SQLException {
		Agente agente = new Agente();
		agente.setCodice_zona(rs.getInt("Codice_zona"));
		agente.setCognome(rs.getString("Cognome"));
		agente.setNome(rs.getString("Nome"));
		agente.setPartita_iva(rs.getInt("Partita_iva"));
		agente.setProvvigione(rs.getDouble("Provvigione"));
		agente.setTelefono(rs.getInt("Telefono"));
		return agente;
	}
}
