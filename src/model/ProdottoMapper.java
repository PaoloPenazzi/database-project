package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProdottoMapper implements RowMapper<Prodotto> {

	public ProdottoMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Prodotto mapRow(ResultSet rs, int rowNum) throws SQLException {
		Prodotto prodotto = new Prodotto();
		prodotto.setCapacita(rs.getInt("Capacita"));
		prodotto.setCodice_prodotto(rs.getInt("Codice_prodotto"));
		prodotto.setConfezione(rs.getString("Confezione"));
		prodotto.setMarca(rs.getString("Marca"));
		prodotto.setNome(rs.getString("Nome"));
		prodotto.setPrezzo_unitario(rs.getDouble("Prezzo_unitario"));
		prodotto.setTipologia(rs.getString("Tipologia"));		
		return prodotto;
	}
}
