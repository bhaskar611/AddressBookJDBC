package com.addressBook.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressBook_DataBaseService {
	
	public static AddressBook_DataBaseService addressBookDBService;
	
	public static AddressBook_DataBaseService getInstance() {
		if (addressBookDBService == null)
			addressBookDBService = new AddressBook_DataBaseService();
		return addressBookDBService;
	}
	public Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/address_book_service?allowPublicKeyRetrieval=true&useSSL=false";
		String userName = "root";
		Connection connection;
		System.out.println("Connecting to database : "+jdbcURL);
		connection = DriverManager.getConnection(jdbcURL, userName, "1@Github");
		System.out.println("Connection is successful !! " + connection);
		return connection;
	}
	
	
}
