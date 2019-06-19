package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AccordoMapper implements RowMapper<Accordo> {

	public AccordoMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Accordo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Accordo accordo = new Accordo();
		accordo.setCodice_dipendente(rs.getInt("Codice_dipendente"));
		accordo.setCodice_prodotto(rs.getInt("Codice_prodotto"));
		accordo.setData_Scadenza(rs.getDate("Data_Scadenza"));
		accordo.setNome_fornitore(rs.getString("Nome_fornitore"));
		accordo.setPrezzo_di_acquisto(rs.getDouble("Prezzo_di_acquisto"));
		return accordo;
	}
}