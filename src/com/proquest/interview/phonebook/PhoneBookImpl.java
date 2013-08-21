package com.proquest.interview.phonebook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;

import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImpl implements PhoneBook {
	public List people;
	
	@Override
	public void addPerson(Person newPerson) {
		
		 if (people == null) {
			people = new ArrayList(); 
		 }
		 
		 people.add(newPerson);
		//TODO: write this method
		Connection cn;
		try {
			cn = DatabaseUtil.getConnection();
			Statement stmt = cn.createStatement();
			stmt.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('" + newPerson.name + "','"  +   newPerson.phoneNumber  + 
					       "', '" +  newPerson.address   + "')");
			cn.commit();
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Override
	public Person findPerson(String firstName, String lastName) {
		//TODO: write this method
		Connection cn;
		Person person = new Person();
		
		try {
			cn = DatabaseUtil.getConnection();
			String selectSQL = "SELECT NAME, PHONENUMBER, ADDRESS FROM PHONEBOOK WHERE NAME = ?";
			PreparedStatement preparedStatement = cn.prepareStatement(selectSQL);
			preparedStatement.setString(0, firstName + " " + lastName);
			ResultSet rs = preparedStatement.executeQuery(selectSQL );
			while (rs.next()) {
				person.name = rs.getString("NAME");
				person.phoneNumber = rs.getString("PHONENUMBER");	
				person.address = rs.getString("ADDRESS");	
			}
			
			
			
			cn.commit();
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
		
		
	}
	
	public static void main(String[] args) {
		DatabaseUtil.initDB();  //You should not remove this line, it creates the in-memory database

		/* TODO: create person objects and put them in the PhoneBook and database
		 * John Smith, (248) 123-4567, "1234 Sand Hill Dr, Royal Oak, MI"
		 * Cynthia Smith, (824) 128-8758, 875 Main St, Ann Arbor, MI
		*/ 
		
		PhoneBookImpl phoneBookImpl = new PhoneBookImpl();
		Person newPerson1 = new Person();
		newPerson1.name = "John Smith";
		newPerson1.address = "1234 Sand Hill Dr, Royal Oak, MI";
		newPerson1.phoneNumber = "(248) 123-4567";
		phoneBookImpl.addPerson(newPerson1);
		
		Person newPerson2 = new Person();
		newPerson2.name = "Cynthia Smith";
		newPerson2.address = "875 Main St, Ann Arbor, MI";
		newPerson2.phoneNumber = "(248) 123-4567";
		phoneBookImpl.addPerson(newPerson2);
				
		// TODO: print the phone book out to System.out
		
		Iterator it = (Iterator) phoneBookImpl.people.iterator();
		while(it.hasNext()) {
			Person person = (Person) it.next();
			System.out.println("Name : " + person.name + " : Address : " + person.address + " : phone : "  + person.phoneNumber);
		}
		// TODO: find Cynthia Smith and print out just her entry
	    it = (Iterator) phoneBookImpl.people.iterator();
		while(it.hasNext()) {
			Person person = (Person) it.next();
			if ("Cynthia Smith".equals(person.name)) {
			   System.out.println("Found Name : " + person.name + " : Address : " + person.address + " : phone : "  + person.phoneNumber);
			}
		}
		
		// TODO: insert the new person objects into the database
		Person newPerson3 = new Person();
		newPerson3.name = "Cynthia Smith";
		newPerson3.address = "67 Western, Seety, NY";
		newPerson3.phoneNumber = "(248) 888-4567";
		phoneBookImpl.addPerson(newPerson3);

	
	
	
	}
}
