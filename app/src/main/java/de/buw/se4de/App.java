// Assignment - Worksheet 2
// ======================== Group 8 ========================================
// ======================== Party planner ==================================
// Import package, Date library, Parse Exception, Date format, Arraylist and 
// scanner library
package de.buw.se4de;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class App {

	// Main function of the program

	public static void main(String[] args) throws ParseException  {
		// Initialize variables
		
		// Number of max users;
		int Nusersr=100;		
		//Create array of users
		User[] users = new User[Nusersr];
		// Initialize ID
		int ID = 0;
		
		//Initialize variables to create a user
		String Name = "";
		String Password = "";
		long Telephone = 0;
		int Age = 0;
		String Location = "";
		
		// Initialize the array list of the party class
		ArrayList<PartyPlan> PartyList = new ArrayList<PartyPlan>();
			
		// Create a scanner for reading the users input
		Scanner scanner = new Scanner(System.in);
	
		// Initilize the variable to enter to the loop
		String Selection="1";
		
		// Main loop for selectiong a new user, old user or exit the program
		while(Selection.equals("1") || Selection.equals("2")) {
		
		//Ask for the value to the user
		
		System.out.println(new App().getGreeting());
		System.out.println("Please select:");
		System.out.println("1) New User");
		System.out.println("2) Old User");
		System.out.println("3) Exit");
		
		Selection = scanner.nextLine();
		
		// Verify the selection of the correct selection of the user
		
		while(Validation(Selection)) {
			System.out.println("Please select a correct option:");
			System.out.println("1) New User");
			System.out.println("2) Old User");
			System.out.println("3) Exit");
			Selection = scanner.nextLine();
		}

		//Evaluate the condition of the user
		
		if(Selection.equals("1")) {
			
			//Ask and store the variables of the new user
			System.out.println("Welcome New User! We are happy that you are going to join us.");
			System.out.println("Please fill in the following information");
			System.out.println("Name: ");
			Name = scanner.nextLine();
			System.out.println("Telephone number: ");
			Telephone = scanner.nextLong();
			scanner.nextLine();
			System.out.println("Age: ");
			Age = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Location: ");
			Location = scanner.nextLine();
			
			//Create a new object with the varibles provided
			User newuser = new User(ID,Name, Telephone, Age, Location);
			users[ID] = newuser;
			
			//Final message and log in details
			System.out.println("Thank you for your registration.");
			System.out.println("Your login credentials are:");
			System.out.println("ID: "+users[ID].getID());
			System.out.println("Password: "+users[ID].getPassword());
			System.out.println("Please Log in as an Old User.");
			
			//Increment the ID number for the next user
			ID++;
			
		} else if (Selection.equals("2")) {

			// Interface to log in

			System.out.println("Welcome Again!");
			System.out.println("Please enter your ID: ");
			int IDaux;
			IDaux = scanner.nextInt();
			scanner.nextLine();
			
			// Evaluate if the ID provided is available
			if(IDaux < ID) {

				//Store and compare the password provided
				System.out.println("Please enter your Password: ");
				String auxPassword;
				auxPassword = scanner.nextLine();
				
				if(users[IDaux].getPassword().equals(auxPassword)) {
					
					//Initilize the selection for entering to the group
					String PartySelection = "1";

					// Welcome message for an old user
					System.out.println("Welcome again "+users[IDaux].getName());
					
					while(!PartySelection.equals("3")){
					
					// Shows the possible selections
					System.out.println("Please select:");
					System.out.println("1) Search for the Party");
					System.out.println("2) Host a Party");
					System.out.println("3) Log out");
					PartySelection = scanner.nextLine();
					
						if(PartySelection.equals("1")){
								
								// Select the options for search a party

								String SearchSelection = "1";
								int SelectedParty = 0;
								
								while(!SearchSelection.equals("3")){
									
									// Select the options to search a party
									System.out.println("Select the option for searching for the Party");
									System.out.println("1) Quick search");
									System.out.println("2) Filter Search");
									System.out.println("3) Go to Homepage");
									SearchSelection = scanner.nextLine();
									
									if(SearchSelection.equals("1")){

											// Quick Search

											Date currdate = new Date();
											SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
											String formatedCurrDate = newFormat.format(currdate);
											String formattedDate = "";
											//System.out.println(formatedCurrDate);

											// Variable that help to cuount if there is one party that match the specification
											int aux=0;

											for (int i = 0; i < PartyList.size(); i++) {
												formattedDate = dateFormat(PartyList.get(i).getDate());
												//System.out.println(formattedDate);
												if (formattedDate.equals(formatedCurrDate) && PartyList.get(i).getLocation().equals(users[IDaux].getLocation())) {
													System.out.println(i+") Party Title: " + PartyList.get(i).getPartyTitle() + "; party genre: " + PartyList.get(i).getPartyType() + "; time: " + PartyList.get(i).getTime() +
															"; age limit: " + PartyList.get(i).getAge() + "; notes left from the party host: " + PartyList.get(i).getDescription());
													System.out.println();
													aux++;
												}
												
											}
											
											// Select the party
											try {
												//Check if there is a new party
												if (aux != 0){
															// Select the party
													System.out.println("Please select the number of the party that you want to assist");
													SelectedParty = scanner.nextInt();
													scanner.nextLine();
													formattedDate = dateFormat(PartyList.get(SelectedParty).getDate());
													if(SelectedParty >= 0 && SelectedParty < PartyList.size()) {
														if (PartyList.get(SelectedParty).getVacancy() == PartyList.get(SelectedParty).getOcuppied() && SelectedParty < PartyList.size() && formattedDate.equals(formatedCurrDate) && PartyList.get(SelectedParty).getLocation().equals(users[IDaux].getLocation())) {
															System.out.println("Sorry this party is full. Please try another");
														} else if(SelectedParty >= 0 && SelectedParty < PartyList.size() && formattedDate.equals(formatedCurrDate) && PartyList.get(SelectedParty).getLocation().equals(users[IDaux].getLocation())) {
															if(users[IDaux].getAge() >= PartyList.get(SelectedParty).getAge()) { 
															System.out.println("Thanks for using PartyPlanner!! Your slot had been booked.");
															PartyList.get(SelectedParty).UploadOccupied();
															} else {
																System.out.println("Sorry you are too young for this party");
															}
														} else {
															System.out.println("You have select a wrong number. Please try again");
														}
													} else {
														System.out.println("You have select a wrong number. Please try again");
													}
												} else {
													System.out.println("There are no party of your preference");
												}
											} catch (Exception e) {
													
												System.err.println("Error occurs. Please enter again.");
												scanner.next(); // clear scanner wrong input
													
											}
											
									} else if(SearchSelection.equals("2")) {

											// Filter Search

											System.out.println("Advanecd filter...please set your preferences...");
											try {
												
												System.out.println("Date? (Format:YYYYMMDD)");
												int preferred_date = scanner.nextInt();
												String formatted_preferred_date = dateFormat(preferred_date);
													
												System.out.println("Time? (Format:hhmm)");
												int preferred_time = scanner.nextInt();
												
												System.out.println("Location?");
												scanner.nextLine();
												String preferred_loc = scanner.nextLine();
													
												System.out.println("Party type? (Drink & Dance / Potluck)");
												String preferred_partyType = scanner.nextLine();
												
												String formattedDate = "";

												// Variable that help to cuount if there is one party that match the specification
												int aux=0;
												
												for (int i = 0; i < PartyList.size(); i++) {
													
													formattedDate = dateFormat(PartyList.get(i).getDate());
													//System.out.println(formattedDate);
													if (formattedDate.equals(formatted_preferred_date) && PartyList.get(i).getLocation().equals(preferred_loc)
															&& PartyList.get(i).getTime() == preferred_time && PartyList.get(i).getPartyType().equals(preferred_partyType)) {
														
														System.out.println(i+") Party Title: " + PartyList.get(i).getPartyTitle() + "; party genre: " + PartyList.get(i).getPartyType() + "; time: " + PartyList.get(i).getTime() +
																"; age limit: " + PartyList.get(i).getAge() + "; notes left from the party host: " + PartyList.get(i).getDescription());
														System.out.println();
														aux++;
														
													}
													
												}
												
												//Check if there is a new party
												if (aux != 0){
													// Select the party
												
													System.out.println("Please select the number of the party that you want to assist");
													SelectedParty = scanner.nextInt();
													scanner.nextLine();
													formattedDate = dateFormat(PartyList.get(SelectedParty).getDate());

													if(SelectedParty >= 0 && SelectedParty < PartyList.size()) {
														if (PartyList.get(SelectedParty).getVacancy() == PartyList.get(SelectedParty).getOcuppied() && formattedDate.equals(formatted_preferred_date) && PartyList.get(SelectedParty).getLocation().equals(preferred_loc)
														&& PartyList.get(SelectedParty).getTime() == preferred_time && PartyList.get(SelectedParty).getPartyType().equals(preferred_partyType)) {
															System.out.println("Sorry this party is full. Please try another");
														} else if(SelectedParty >= 0 && SelectedParty < PartyList.size() && formattedDate.equals(formatted_preferred_date) && PartyList.get(SelectedParty).getLocation().equals(preferred_loc)
														&& PartyList.get(SelectedParty).getTime() == preferred_time && PartyList.get(SelectedParty).getPartyType().equals(preferred_partyType)) {
															if(users[IDaux].getAge() >= PartyList.get(SelectedParty).getAge()) { 
																System.out.println("Thanks for using PartyPlanner!! Your slot had been booked.");
																PartyList.get(SelectedParty).UploadOccupied();
															} else {
																System.out.println("Sorry you are too young for this party");
															}
														} else {
															System.out.println("You have select a wrong number. Please try again");
														}
														
														} else {
															System.out.println("You have select a wrong number. Please try again");
													}
												} else {
													System.out.println("There are no party of your preference");
												}
											} catch (Exception e) {
													
												System.err.println("Error occurs. Please enter again.");
												scanner.next(); // clear scanner wrong input
													
											}
											
											
									} else if(!SearchSelection.equals("3")){
												System.out.println("Please select the correct  number");
									}
								
								}
			
						} else if (PartySelection.equals("2")){
							// Host a party
							try {
									
									//Fill the information to host a party
									System.out.println("Date? (Format:YYYYMMDD, 0 to exit program)");
									int date = scanner.nextInt();
									if(date == 0) {
										  break;
									  }
									
									System.out.println("Time? (Format:hhmm)");
									int time = scanner.nextInt();
									
									System.out.println("Location?");
									scanner.nextLine();
									String loc = scanner.nextLine();
									
									System.out.println("Party type? (Drink & Dance / Potluck)");
									String partyType = scanner.nextLine();
									
									System.out.println("Party tile?");
									String partyTitle = scanner.nextLine();
									
									System.out.println("Age Limit?");
									int age = scanner.nextInt();
									
									System.out.println("Set vacancy?");
									int vacancy = scanner.nextInt();
									
									System.out.println("Short description?");
									scanner.nextLine();
									String descript = scanner.nextLine();
									
									// Save the information in an object
									PartyPlan host = new PartyPlan(date, time, loc, partyType, partyTitle, age, vacancy, descript, IDaux);
									PartyList.add(host);
									
									System.out.println("Congratulations!! Your party has been loaded");
										
								} catch (Exception e) {
										
										System.err.println("Error occurs. Please enter again.");
										scanner.next(); // clear scanner wrong input
										
								}       
						
						} else if (!PartySelection.equals("3")){
								System.out.println("Please select a correct number");
						}
						
					}
					System.out.println("Good bye!! We hope see you soon.");
					
				} else {
					System.out.println("Wrong password!.");
					System.out.println("Please check your credentials and try again.");
				}
			} else {
				System.out.println("The entered ID doesn't exist!");
			}
		} else {
			System.out.println("Good bye!!");
		}		
		
		}
		
		//Close the scanner
		scanner.close();
		
	}
	
	// Validation of the selection of the user
	static Boolean Validation(String Selection) {
		Boolean Valid;
		if(Selection.equals("1") || Selection.equals("2") || Selection.equals("3")) {
			Valid = false;
		} else {
			Valid = true;
		}
		return Valid;
	}
	
	// Welcome message at the beginning of the program
	public String getGreeting() {
		return "Welcome to PartyPlanner APP!";
	}
	
	// Change a integer value to a date type
	public static String dateFormat(int date) throws ParseException {
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
		Date dateformat = originalFormat.parse(Integer.valueOf(date).toString());
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formatedDate = newFormat.format(dateformat);
		return formatedDate;
	}
	
}