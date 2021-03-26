package com.addressBook.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.addressBook.JDBC.AddressBook_DataBaseService;
public class AddressBook {
	AddressBook book = new AddressBook();
	
	public PreparedStatement addressBookDataStatement;
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

	public int updateEmployeeSalaryResult(String firstname, String city) {
		String sql = String.format("update address_table set city ='%s' where firstname = '%s';", city,firstname);
		try(Connection connection = addressBookService.getConnection();)
		{
		Statement statement = connection.createStatement();
		return statement.executeUpdate(sql);
				}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<AddressBookData> getAddressBookData(String firstname) {
		
		List<AddressBookData> addressBookList = new ArrayList<>();
		if (addressBookDataStatement == null)
		{
			prepareStatementForAddressBookData();
		}
		try {
			
		addressBookDataStatement.setString(1, firstname);
		ResultSet resultSet = addressBookDataStatement.executeQuery();
		while (resultSet.next()) {
			addressBookList.add(new AddressBookData(resultSet.getString("firstname"), resultSet.getString("lastname"),
					resultSet.getString("address"),resultSet.getString("city"),resultSet.getString("state"),resultSet.getInt("zip"),
					resultSet.getInt("phonenumber"),resultSet.getString("email") ));
         		}
						} catch (Exception e) {
				e.printStackTrace();
			}
			return addressBookList;
		}


	private void prepareStatementForAddressBookData() {
		try {
			Connection connection = addressBookService.getConnection();
			String sql = "SELECT * FROM address_table WHERE firstname = ?";
			addressBookDataStatement = connection.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int updateCityUsingSQL(String firstname, String city) {
		String sql = "UPDATE address_table SET city = ? WHERE firstname = ? ";
		try (Connection connection = addressBookService.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, city);
			preparedStatement.setString(2, firstname);
			return preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
