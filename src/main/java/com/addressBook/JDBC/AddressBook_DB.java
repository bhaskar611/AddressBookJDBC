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
import com.addressBook.JDBC.AddressBook;

import com.mysql.cj.jdbc.Driver;

public class AddressBook_DB {
	AddressBook book = new AddressBook();
	
	
	
	 private AddressBook_DataBaseService addressBookService;
		List<AddressBookData> addressBookList = new ArrayList<>();


		public AddressBook_DB() {
			addressBookService = AddressBook_DataBaseService.getInstance();
		}



	public AddressBook_DB(List<AddressBookData> addressBookList) {
		this();
		this.addressBookList = addressBookList;
	}
	
	public List<AddressBookData> readData() {
		List<AddressBookData> addressBookList = new ArrayList<>();
		this.addressBookList = book.readData(); 
		return  addressBookList;
	}

	public void updateAddressBookData(String firstname, String city)
	{
		int result = new AddressBook().updateEmployeeSalaryResult(firstname, city);
		if(result == 0) return;
		AddressBookData AddressBookData = this.getAddressBookData(firstname);
		if(AddressBookData != null) AddressBookData.setCity(city);
	}
	
	public AddressBookData getAddressBookData(String firstname) {
      AddressBookData addressBookData ;
      addressBookData = this.addressBookList.stream()
    		             .filter(addressBookEntry  ->  (addressBookEntry.getFirstname()).equals(firstname))
    		             .findFirst()
    		             .orElse(null);
		return addressBookData;
	}

	public boolean checkAddressBookDataSyncWithDB(String firstname) {
		try {
			return book.getAddressBookData(firstname).get(0).getFirstname().equals(getAddressBookData(firstname).getFirstname());
		} catch (IndexOutOfBoundsException e) {
		}
		return false;
	}
	
}
