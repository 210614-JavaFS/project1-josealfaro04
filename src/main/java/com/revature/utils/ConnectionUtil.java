package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://java-react-210614.ceywaohkyxhh.us-west-1.rds.amazonaws.com:5432/project1";
		String username = "postgres";
		String password = "password";
		
		return DriverManager.getConnection(url, username, password);
		
	}
	
}