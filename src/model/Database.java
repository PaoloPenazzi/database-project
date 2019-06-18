package model;


import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.metadata.TableMetaDataContext;

public class Database {
	private JdbcTemplate jdbc;
	private DataSource ds;
	
	
	public Database() {
		this.ds = DBConnection.getMySqlDataSource();
		this.jdbc = new JdbcTemplate(this.ds);
	}
	
	public List<String> getColumnNamesOf(String tableName) {
		TableMetaDataContext metaData = new TableMetaDataContext();
	    metaData.setTableName(tableName);
	    metaData.processMetaData(this.ds, Collections.<String>emptyList(), new String[0]);
	    return metaData.getTableColumns();
	   }
	
	public JdbcTemplate getJdbc() {
		return this.jdbc;
	}

	public <T> List<T> selectFrom(String tableName, RowMapper<T> mapper) {
	      String SQL = "select * from " + tableName;
	      List <T> elements = jdbc.query(SQL, mapper);
	      return elements;
	   }
}
