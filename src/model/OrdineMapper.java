package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrdineMapper implements RowMapper<Ordine> {

	public OrdineMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Ordine mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ordine ordine = new Ordine();
		ordine.setCodice_prodotto(rs.getInt("Codice_prodotto"));
		ordine.setData_consegna(rs.getDate("Data_consegna"));
		ordine.setData_ordine(rs.getDate("Data_ordine"));
		ordine.setNome(rs.getString("Nome"));
		ordine.setNome_fornitore(rs.getString("Nome_fornitore"));
		ordine.setNumero_fattura(rs.getInt("Numero_fattura"));
		ordine.setQuantita(rs.getInt("Quantita"));
		return ordine;
	}
}
