package com.proquest.interview.phonebook;

import static org.junit.Assert.*;

import org.junit.Test;
import com.proquest.interview.*;
import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImplTest {
	@Test
	public void shouldAddFindPerson() {
		DatabaseUtil.initDB(); 
		
		PhoneBookImpl phoneBookImpl = new PhoneBookImpl();
		Person newPerson1 = new Person();
		newPerson1.name = "John Smith";
		newPerson1.address = "1234 Sand Hill Dr, Royal Oak, MI";
		newPerson1.phoneNumber = "(248) 123-4567";
		phoneBookImpl.addPerson(newPerson1);
		
		Person cynthia = phoneBookImpl.findPerson("John", "Smith");
		System.out.println("Found Name : " + cynthia.name + " : Address : " + cynthia.address + " : phone : "  + cynthia.phoneNumber);

		assertEquals(cynthia.name, newPerson1.name );	

		
		
	}
}
