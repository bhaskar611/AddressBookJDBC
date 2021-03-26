package com.addressBook.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import com.addressBook.JDBC.AddressBook_DataBaseService;

import com.mysql.cj.jdbc.Driver;

public class AddressBook_DB {

	AddressBook_DataBaseService addressBookService = new AddressBook_DataBaseService();
	
	
	public List<AddressBookData> readData()
	{
		String sql = "Select * from addressBook;";
		List<AddressBookData> addressBookList = new ArrayList<>();
		
		try(Connection connection = addressBookService.getConnection();)
		
		{
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
			addressBookList.add(new AddressBookData(resultSet.getString("firstname"), resultSet.getString("lastname"),
					resultSet.getString("address"),resultSet.getString("city"),resultSet.getString("state"),resultSet.getInt("zip"),
					resultSet.getInt("phonenumber"),resultSet.getString("email") ));
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return addressBookList;
	}
	
}
