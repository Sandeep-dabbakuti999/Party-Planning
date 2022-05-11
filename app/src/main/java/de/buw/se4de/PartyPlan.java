// Assignment - Worksheet 2
// ======================== Group 8 ========================================
// ======================== Party planner ==================================
// Import package and Date library
package de.buw.se4de;
import java.util.Date;

public class PartyPlan {
	// Create variables for class
	private int date, time, age, vacancy, ID, occupied;
	private String location, partyType, partyTitle, descript; 
	
	// Party class constructor
	public PartyPlan(int date, int time, String location, String partyType, String partyTitle, int age, int vacancy,String descript, int ID) {
	    this.date = date;
	    this.time = time;
	   	this.location = location;
	   	this.partyType = partyType;
	   	this.partyTitle = partyTitle;
    	this.age = age;	    	
    	this.vacancy = vacancy;
	   	this.descript = descript;
		this.ID = ID;
		this.occupied = 0;
	}
	
	// function to get the ID
	public int getID() {
        return this.ID;
    }
	
	// function to get the Date
	public int getDate() {
        return this.date;
    }
	
	// function to get the time
	public int getTime() {
        return this.time;
    }
	
	// function to get the location
	public String getLocation() {
		return this.location;
	}
	
	// function to get the Party type
	public String getPartyType() {
		return this.partyType;
	}

	// function to get the Party title
	public String getPartyTitle() {
		return this.partyTitle;
	}
	
	// function to get the Age
	public int getAge() {
		return this.age;
	}
	
	// function to get the Vacancy
	public int getVacancy() {
		return this.vacancy;
	}
	
	// function to get the occupied
	public int getOcuppied() {
		return this.occupied;
	}
	
	// function to get the description
	public String getDescription() {
		return this.descript;
	}
	
	// function to get the occupation
	public void UploadOccupied() {
		this.occupied++;
	}	

}
