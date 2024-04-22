package trainingcenter;

import java.util.ArrayList;
import java.util.List;

public class SwimmingCoach {
	private String coachUsername;
	private String coachFullname;
	private String coachEmail;
	private String coachContact;
	private String coachGender;
	private String coachExperience;
	private String coachAge;
	private List<SwimmingCoach> swimmingCoaches;

	public String getCoachUsername() {
		return coachUsername;
	}

	public String getCoachFullname() {
		return coachFullname;
	}

	public String getCoachEmail() {
		return coachEmail;
	}

	public String getCoachContact() {
		return coachContact;
	}

	public String getCoachGender() {
		return coachGender;
	}

	public String getCoachExperience() {
		return coachExperience;
	}

	public String getCoachAge() {
		return coachAge;
	}

	public List<SwimmingCoach> getSwimmingCoaches() {
		return swimmingCoaches;
	}

	// create parameterized constructor to save coach details
	public SwimmingCoach(String coachFullname, String coachUsername, String coachEmail, String coachContact,
			String coachGender, String coachAge, String coachExperience) {
		super();
		this.coachUsername = coachUsername;
		this.coachFullname = coachFullname;
		this.coachEmail = coachEmail;
		this.coachContact = coachContact;
		this.coachGender = coachGender;
		this.coachExperience = coachExperience;
		this.coachAge = coachAge;
	}

	public SwimmingCoach() {
		swimmingCoaches = new ArrayList<>();
		predefinedCoaches();
	}

	// save predefined coach details
	public void predefinedCoaches() {
		swimmingCoaches.add(
				new SwimmingCoach("John Smith", "JS123", "john@gmail.com", "041234567891", "Male", "31", "1 years"));
		swimmingCoaches.add(new SwimmingCoach("Emma Johnson", "EJ123", "emma@gmail.com", "041234567892", "Female", "32",
				"2 years"));
		swimmingCoaches.add(new SwimmingCoach("Michael Brown", "MB123", "michael@gmail.com", "041234567893", "Male",
				"33", "3 years"));
		swimmingCoaches.add(new SwimmingCoach("Olivia Taylor", "OT123", "olivia@gmail.com", "041234567894", "Female",
				"34", "4 years"));
		swimmingCoaches.add(
				new SwimmingCoach("David Wilson", "DW123", "david@gmail.com", "041234567895", "Male", "35", "5 years"));
		swimmingCoaches.add(new SwimmingCoach("Sarah Evans", "SE123", "sarah@gmail.com", "041234567896", "Female", "36",
				"6 years"));
	}

	// get coach by username
	public SwimmingCoach getCoachByUsername(String username) {
		for (int id = 0; id < swimmingCoaches.size(); id++) {
			if (swimmingCoaches.get(id).getCoachUsername().equalsIgnoreCase(username)) {
				return swimmingCoaches.get(id);
			}
		}
		return null;
	}

	// print coach details
	public void coachDetails() {
		if (!swimmingCoaches.isEmpty()) {
			System.out.println("\nCoach details");
			System.out.println("------------------------------");
			System.out.println(
					"--------------------------------------------------------------------------------------------"
							+ "---------------------------------------------");
			System.out.printf("! %-12s ! %-20s  ! %-20s ! %-15s  ! %-12s  ! %-12s  ! %-15s  !\n", "Username",
					"Full name", "Email", "Experience", "Age", "Gender", "Contact");
			System.out.println(
					"--------------------------------------------------------------------------------------------"
							+ "---------------------------------------------");
			for (int id = 0; id < swimmingCoaches.size(); id++) {
				SwimmingCoach coachData = swimmingCoaches.get(id);
				String username = coachData.getCoachUsername();
				String fullname = coachData.getCoachFullname();
				String email = coachData.getCoachEmail();
				String age = coachData.getCoachAge();
				String experience = coachData.getCoachExperience();
				String gender = coachData.getCoachGender();
				String contact = coachData.getCoachContact();
				// Print coach details
				System.out.printf("! %-12s ! %-20s  ! %-20s ! %-15s  ! %-12s  ! %-12s  ! %-15s  !\n", username,
						fullname, email, experience, age, gender, contact);
			}
			System.out.println(
					"--------------------------------------------------------------------------------------------"
							+ "---------------------------------------------");
		}
	}

	// validation of coach username exist
	public boolean coachUsernameExist(String username) {
		for (SwimmingCoach coachData : swimmingCoaches) {
			if (coachData.getCoachUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		return false;
	}

}
