package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ClienteMapper implements RowMapper<Cliente> {

	public ClienteMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setCodice_zona(rs.getInt("Codice_zona"));
		cliente.setIndirizzo(rs.getString("Indirizzo"));
		cliente.setNome(rs.getString("Nome"));
		cliente.setTelefono(rs.getInt("Telefono"));
		cliente.setTipologia(rs.getString("Tipologia"));
		return cliente;
	}
}
