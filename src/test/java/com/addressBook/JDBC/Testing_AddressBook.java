package com.addressBook.JDBC;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Testing_AddressBook
{
    AddressBook_DB addressBook = new AddressBook_DB() ;
    
    // UC16 - Ability to retrieve data from database
         @Test
        public void  givenAddressBookData_WhenReturned_ShouldMatchEntryCount()
        {
        	List<AddressBookData> addressBookList = addressBook.readData();
        	System.out.println(addressBookList);
        	Assert.assertEquals(3,addressBookList.size());
        }
         @Test
  		public void givenCity_WhenUpdated_ShouldSyncWithDatabaseUsingPreparedStatement()
  		{
  			List<AddressBookData> addressBookData = addressBook.readData();
  			addressBook.updateAddressBookData("Priya","Chandigarh");
  			boolean result = addressBook.checkAddressBookDataSyncWithDB("Priya");
  			Assert.assertTrue(result);
  		}
 
}
