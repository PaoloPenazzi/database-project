package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VenditaMapper implements RowMapper<Vendita> {

	public VenditaMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vendita mapRow(ResultSet rs, int rowNum) throws SQLException {
		Vendita vendita = new Vendita();
		vendita.setCodice_prodotto(rs.getInt("Codice_Prodotto"));
		vendita.setData(rs.getDate("Data"));
		vendita.setNome_cliente(rs.getString("Nome_Cliente"));
		vendita.setNumero_fattura(rs.getInt("Numero_fattura"));
		vendita.setPartita_IVA_agente(rs.getString("Partita_IVA_agente"));
		vendita.setQuantita(rs.getInt("Quantita"));
		vendita.setSconto(rs.getDouble("Sconto"));
		vendita.setTelefono(rs.getString("Telefono"));
		return vendita;
	}

}
