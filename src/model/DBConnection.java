package model;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public final class DBConnection {

	private DBConnection() {
		// TODO Auto-generated constructor stub
	}
	
	public static DataSource getMySqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/drinkingteam");
        dataSource.setUsername("prova");
        dataSource.setPassword("prova");
        //impostare accesso in locale da phpmyadmin
        return dataSource;
	}

}
