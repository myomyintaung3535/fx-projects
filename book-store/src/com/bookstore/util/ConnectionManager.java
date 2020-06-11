package com.bookstore.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	private ConnectionManager() {

	}

	private static String url;
	private static String usr;
	private static String pass;

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,usr,pass);
	}

	static {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(new File("bookstore.properties")));
			url = prop.getProperty("database.url");
			usr = prop.getProperty("database.usr");
			pass = prop.getProperty("database.pass");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
