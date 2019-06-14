package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GiacenzaMapper implements RowMapper<Giacenza> {

	public GiacenzaMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Giacenza mapRow(ResultSet rs, int rowNum) throws SQLException {
		Giacenza giacenza = new Giacenza();
		giacenza.setCodice_prodotto(rs.getInt("Codice_prodotto"));
		giacenza.setGiacenza_minima(rs.getInt("Giacenza_minima"));
		giacenza.setMagazzino(rs.getString("Magazzino"));
		giacenza.setQuantita(rs.getInt("Quantita"));
		giacenza.setSettore(rs.getInt("Settore"));		
		return giacenza;
	}
}
