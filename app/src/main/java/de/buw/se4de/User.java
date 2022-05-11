// Assignment - Worksheet 2
// ======================== Group 8 ========================================
// ======================== Party planner ==================================
// Import package and Random library
package de.buw.se4de;
import java.util.Random;

public class User {
	// Create variables for class
	private int ID;
	private String Name;
	private String Password;
	private long Telephone;
	private int Age;
	private String Location;
	
	// User class constructor
	User(int ID,String name, long Tel, int age, String loc){
		this.ID = ID;
		this.Name = name;
		this.Telephone = Tel;
		this.Age = age;
		this.Location = loc;
		
		//Create a random password for the use
		Random rand = new Random();
		Integer aux = (rand.nextInt(1000000)+ID);
		this.Password = aux.toString();

	}
	
	// function to get the ID
	public int getID() {
		return ID;
	}

	// function to get the Name
	public String getName() {
		return Name;
	}

	// function to get the Password
	public String getPassword() {
		return Password;
	}

	// function to get the Telephone number
	public long getTelephone() {
		return Telephone;
	}

	// function to get the Age
	public int getAge() {
		return Age;
	}

	// function to get the Location
	public String getLocation() {
		return Location;
	}

}