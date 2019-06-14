package model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JDBC {
	private JdbcTemplate jdbc;
	
	
	public JDBC() {
		jdbc = new JdbcTemplate(this.mysqlDataSource());
	}

	public List<Ufficio> listUffici() {
	      String SQL = "select * from UFFICIO";
	      List <Ufficio> uffici = jdbc.query(SQL, new UfficioMapper());
	      return uffici;
	   }
	
	private DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springjdbc");
        dataSource.setUsername("DrinkingTeam");
        dataSource.setPassword("DrinkingTeam");
 
        return dataSource;
    }
}
