package trainingcenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwimmingLearner {
	private TimetableManager timetableManager;
	private BookingManager bookingManager;
	private ReviewManager reviewManager;
	private Scanner learnerOption;
	private String learnerUsername;
	private String learnerFullname;
	private String learnerContact;
	private String learnerGender;
	private int learnerAge;
	private int learnerGrade;
	private String learneremail;
	private String learnerPassword;
	private List<SwimmingLearner> swimmingLearners;
	
	public void setLearnerOption(Scanner learnerOption) {
		this.learnerOption=learnerOption;
	}

	public String getLearnerUsername() {
		return learnerUsername;
	}

	public String getLearnerFullname() {
		return learnerFullname;
	}

	public String getLearnerContact() {
		return learnerContact;
	}

	public String getLearnerGender() {
		return learnerGender;
	}

	public int getLearnerAge() {
		return learnerAge;
	}

	public int getLearnerGrade() {
		return learnerGrade;
	}
	
	public void setLearnerGrade(int learnerGrade) {
		this.learnerGrade=learnerGrade;
	}

	public String getLearneremail() {
		return learneremail;
	}

	public String getLearnerPassword() {
		return learnerPassword;
	}

	public List<SwimmingLearner> getSwimmingLearners() {
		return swimmingLearners;
	}

	public void setTimetableManager(TimetableManager timetableManager) {
		this.timetableManager = timetableManager;
	}
	
	public void setBookingManager(BookingManager bookingManager) {
		this.bookingManager = bookingManager;
	}
	
	public void setReviewManager(ReviewManager reviewManager) {
		this.reviewManager = reviewManager;
	}

	// learner panel
	public void learnerManager(String username) {
		String learnerChoice;
		do {
			System.out.println("\nLearner menu");
			System.out.println("------------------------------");
			System.out.println("1. Show Timetable\n2. Booking Manager\n3. Show Atteded Lesson Review\n4. Signout");
			System.out.print("Select your choice : ");
			learnerChoice = learnerOption.nextLine().trim();
			switch (learnerChoice) {
			case "1" -> {
				showTimetable(username,"Book");
			}
			case "2" -> {
				bookingManager.bookingMenu(username);
			}
			case "3" -> {
				reviewManager.ratingDetails(reviewManager.getReviewsByUser(username, "", ""));
			}
			default -> {
				System.out.println("Error ! you entered an invalid choice");
			}
			}
		} while (!learnerChoice.equals("4"));
	}

	public void showTimetable(String username,String updateStatus) {
		String learnerChoice;
		do {
			System.out.println("\nTimetable manager");
			System.out.println("------------------------------");
			System.out.println("1. All timetable\n2. Filter timetable by day\n3. Filter timetable by grade"
					+ "\n4. Filter timetable by coach");
			if(!updateStatus.isEmpty()) {
				System.out.println("5. "+updateStatus+" lesson");
			}else {
				System.out.println("5. Back");
			}
			System.out.print("Select your choice : ");
			learnerChoice = learnerOption.nextLine();
			switch (learnerChoice) {
			case "1" -> {
				timetableManager.lessonTimetable(timetableManager.swimmingLessons);
			}
			case "2" -> {
				List<SwimmingLesson> timetable = timetableManager.getTimetableByDay();
				// print filter timetable
				timetableManager.lessonTimetable(timetable);
			}
			case "3" -> {
				List<SwimmingLesson> timetable = timetableManager.getTimetableByGrade();
				// print filter timetable
				timetableManager.lessonTimetable(timetable);
			}
			case "4" -> {
				List<SwimmingLesson> timetable = timetableManager.getTimetableByCoach();
				// print filter timetable
				timetableManager.lessonTimetable(timetable);
			}
			case "5" -> {
				if(updateStatus.equalsIgnoreCase("Book")){
					if(bookingManager.reservedLesson(username)) {
						System.out.println("\nYour lesson reserved successfully");
					}
				}else if(updateStatus.equalsIgnoreCase("Change")){
					return;
				}else {
					return;
				}
			}
			default -> {
				System.out.println("Error ! you entered an invalid choice");
			}
			}
		} while (!learnerChoice.equals("5"));
	}

	// create parameterized constructor to add learner information
	public SwimmingLearner(String learnerUsername, String learnerFullname, String learnerContact, String learnerGender,
			int learnerAge, int learnerGrade, String learneremail, String learnerPassword) {
		super();
		this.learnerUsername = learnerUsername;
		this.learnerFullname = learnerFullname;
		this.learnerContact = learnerContact;
		this.learnerGender = learnerGender;
		this.learnerAge = learnerAge;
		this.learnerGrade = learnerGrade;
		this.learneremail = learneremail;
		this.learnerPassword = learnerPassword;
	}

	// create default constructor
	public SwimmingLearner() {
		learnerOption = new Scanner(System.in);
		swimmingLearners = new ArrayList<>();
		learnerDetails();
	}

	// learner singin
	public String learnerSingin() {
		String username;
		String password;
		boolean learnerSignin = false;
		System.out.println("\nLearner sign in");
		System.out.println("------------------------------");
		// Get username from the learner
		System.out.print("Enter Username : ");
		username = learnerOption.nextLine().trim();
		// Validate entered username is not empty
		if (!username.isEmpty()) {
			// Get password from learner
			System.out.print("Enter Password : ");
			password = learnerOption.nextLine().trim();
			// Validate entered password is not empty
			if (!password.isEmpty()) {
				for (SwimmingLearner learnerObj : swimmingLearners) {
					// Validate username and password
					if (learnerObj.getLearnerUsername().equalsIgnoreCase(username)
							&& learnerObj.getLearnerPassword().equals(password)) {
						// return username after successfully singin
						username = learnerObj.getLearnerUsername();
						learnerSignin = true;
					}
				}
				if (!learnerSignin) {
					// print error message
					System.out.println("\nError! Incorrect username or password");
					username = "";
				}
			} else {
				// print error message
				System.out.println("\nError ! You entered a wrong password");
				username = "";
			}
		} else {
			// print error message
			System.out.println("\nError ! You entered a wrong username");
			username = "";
		}
		return username;
	}

	// create learner new account
	public void createAccount() {
		String username;
		String fullname = null;
		String emailId = null;
		String emergencyContact = null;
		String age = null;
		String grade = null;
		String gender = null;
		int minimumAge = 4;
		int maximumAge = 11;
		int minimumGrade = 0;
		int maximumGrade = 5;
		String password = null;
		boolean learnerRegister = false;
		System.out.println("\nNew learner register");
		System.out.println("------------------------------");
		do {

			// Get learner name
			if (fullname == null) {
				System.out.print("Learner Name : ");
				fullname = learnerOption.nextLine().trim();
				if (fullname.isBlank() || fullname.matches("\\d+") || fullname.length() < 3) {
					System.out.println("Error ! fullname should be minimum 3 characters");
					fullname = null;
				} else {
					if (duplicateLearnerName(fullname)) {
						System.out.println("Error ! this learner name already registered");
						fullname = null;
					}
				}
				// Get learner email
			} else if (emailId == null) {

				System.out.print("Learner Email : ");
				emailId = learnerOption.nextLine().trim();
				if (emailId.isEmpty() || !emailId.matches("-(.+)@(\\S+)$")) {
					System.out.println("Error ! Invalid email id.");
					emailId = null;
				} else {
					if (duplicateLearnerEmail(emailId)) {
						System.out.println("Error ! this learner email already registered");
						emailId = null;
					}
				}
				// Get learner gender
			} else if (gender == null) {
				System.out.print("Learner Gender (Male/Female) : ");
				gender = learnerOption.nextLine().trim();
				if (gender.isEmpty()) {
					System.out.println("Error ! Gender cannot be empty");
					gender = null;
				} else {
					if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("m")
							&& !gender.equalsIgnoreCase("female") && !gender.equalsIgnoreCase("f")) {
						System.out.println("Error ! Gender should be Male or Female ");
						gender = null;
					} else {
						if (gender.equalsIgnoreCase("M")) {
							gender = "Male";
						} else if (gender.equalsIgnoreCase("F")) {
							gender = "Female";
						}
					}
				}
				// Get learner contact number
			} else if (emergencyContact == null) {
				System.out.print("Learner Contact : ");
				emergencyContact = learnerOption.nextLine().trim();
				if (emergencyContact.isEmpty() || !emergencyContact.matches("\\d+")) {
					System.out.println("Error ! Invalid contact number.");
					emergencyContact = null;
				}
				// Get learner age
			} else if (age == null) {
				System.out.print("Learner Age : ");
				age = learnerOption.nextLine().trim();
				if (age.isEmpty() || !age.matches("[0-9]+")) {
					System.out.println("Error ! invalid learner age ");
					age = null;
				} else {
					learnerAge = Integer.parseInt(age);
					if (learnerAge < minimumAge || learnerAge > maximumAge) {
						System.out.println("Error ! Learner age should be 4 to 11yr ");
						age = null;
					}
				}
				// Get learner grade
			} else if (grade == null) {
				System.out.print("Learner Grade (1 to 5) : ");
				grade = learnerOption.nextLine().trim();
				if (grade.isEmpty() || !grade.matches("\\d")) {
					System.out.println("Error ! invalid learner grade");
					grade = null;
				} else {
					learnerGrade = Integer.parseInt(grade);
					if (learnerGrade <= minimumGrade || learnerGrade > maximumGrade) {
						System.out.println("Error ! Learner grade should be 1 to 5.");
						grade = null;
					}
				}
				// Get learner password
			} else if (password == null) {
				System.out.print("Learner Password : ");
				password = learnerOption.nextLine().trim();
				if (password.isEmpty()) {
					System.out.println("Error ! Password cannot be empty.");
					password = null;
				}
			} else {

				// Generate learner username
				username = fullname.substring(0, 3).toUpperCase().concat("123");
				// Create learner object
				SwimmingLearner learnerAddData = new SwimmingLearner(username, fullname, emergencyContact, gender,
						learnerAge, learnerGrade, emailId, password);
				// save learner data
				saveLearnerDetails(learnerAddData);
				System.out.println("\nSuccess ! New account created successfully");
				System.out.println("Your username : " + username);
				System.out.println("Your password : " + password);
				System.out.println("\n=> Use above username and password for login");
				learnerRegister = true;
			}

		} while (!learnerRegister);
	}

	// show learner details
	public void learnerDetails() {
	    SwimmingLearner learner1 = new SwimmingLearner("BRO123", "Oliver Davies", "041235618123", "Male", 7, 1, "oliver@gmail.com", "Learner1");
	    saveLearnerDetails(learner1);

	    SwimmingLearner learner2 = new SwimmingLearner("ALN123", "Charlotte Evans", "041235628123", "Female", 4, 5, "charlotte@gmail.com", "Learner2");
	    saveLearnerDetails(learner2);

	    SwimmingLearner learner3 = new SwimmingLearner("ALX123", "Harry Thompson", "041235638123", "Male", 5, 3, "harry@gmail.com", "Learner3");
	    saveLearnerDetails(learner3);

	    SwimmingLearner learner4 = new SwimmingLearner("WLM123", "Amelia Harris", "041235648123", "Female", 6, 4, "amelia@gmail.com", "Learner4");
	    saveLearnerDetails(learner4);

	    SwimmingLearner learner5 = new SwimmingLearner("HNR123", "Jack Robinson", "041235658123", "Male", 9, 2, "jack@gmail.com", "Learner5");
	    saveLearnerDetails(learner5);

	    SwimmingLearner learner6 = new SwimmingLearner("JNS123", "Grace Walker", "041235668123", "Female", 8, 5, "grace@gmail.com", "Learner6");
	    saveLearnerDetails(learner6);

	    SwimmingLearner learner7 = new SwimmingLearner("HRR123", "Thomas Green", "041235678123", "Male", 11, 3, "thomas@gmail.com", "Learner7");
	    saveLearnerDetails(learner7);

	    SwimmingLearner learner8 = new SwimmingLearner("BLL123", "Emily Wilson", "041235688123", "Female", 5, 1, "emily@gmail.com", "Learner8");
	    saveLearnerDetails(learner8);

	    SwimmingLearner learner9 = new SwimmingLearner("BKR123", "Henry Carter", "041235698123", "Male", 4, 2, "henry@gmail.com", "Learner9");
	    saveLearnerDetails(learner9);

	    SwimmingLearner learner10 = new SwimmingLearner("TMS123", "Sophie King", "041235178123", "Female", 10, 4, "sophie@gmail.com", "Learner10");
	    saveLearnerDetails(learner10);

	    SwimmingLearner learner11 = new SwimmingLearner("JON123", "William Hill", "041235278123", "Male", 9, 1, "william@gmail.com", "Learner11");
	    saveLearnerDetails(learner11);

	    SwimmingLearner learner12 = new SwimmingLearner("ADM123", "Jessica Bailey", "041235378123", "Female", 8, 3, "jessica@gmail.com", "Learner12");
	    saveLearnerDetails(learner12);

	    SwimmingLearner learner13 = new SwimmingLearner("DVS123", "Ethan Clarke", "041235478123", "Male", 6, 3, "ethan@gmail.com", "Learner13");
	    saveLearnerDetails(learner13);

	    SwimmingLearner learner14 = new SwimmingLearner("FST123", "Isabella Phillips", "041235578123", "Female", 7, 2, "isabella@gmail.com", "Learner14");
	    saveLearnerDetails(learner14);

	    SwimmingLearner learner15 = new SwimmingLearner("WGN123", "Olivia Lewis", "041235688823", "Female", 8, 4, "olivia@gmail.com", "Learner15");
	    saveLearnerDetails(learner15);
	}

	
	//show learner details
	public void showLearners() {
		  if (!swimmingLearners.isEmpty()) {
			  System.out.println("\nLearner details");
				System.out.println("------------------------------");
	            System.out.println("------------------------------------------------------------------------------------"
	            		+ "---------------------------------------------");
	            System.out.printf("! %-12s  ! %-18s  ! %-22s  ! %-10s  ! %-12s  ! %-12s  ! %-15s !\n",
	                    "Username", "Full name","Email", "Grade", "Age", "Gender","Contact");
	            System.out.println("------------------------------------------------------------------------------------"
	            		+ "---------------------------------------------");
	            for (SwimmingLearner learnerObj : swimmingLearners) {
	                String username = learnerObj.getLearnerUsername();
	                String fullname = learnerObj.getLearnerFullname();
	                String email = learnerObj.getLearneremail();
	                int age = learnerObj.getLearnerAge();
	                int grade = learnerObj.getLearnerGrade();
	                String gender = learnerObj.getLearnerGender();
	                String contact = learnerObj.getLearnerContact();
	                
	                //print learner details
	                System.out.printf("! %-12s  ! %-18s  ! %-22s  ! %-10s  ! %-12s  ! %-12s  ! %-15s !\n",
	                        username,fullname, email, "Grade " + grade, age + " year", gender,
	                        contact);
	            }
	            System.out.println("------------------------------------------------------------------------------------"
	            		+ "---------------------------------------------");
	        }		
	}

	// save learner details
	public void saveLearnerDetails(SwimmingLearner learnerAddData) {
		if (learnerAddData != null) {
			swimmingLearners.add(learnerAddData);
		}
	}

	// get learner object by username
	public SwimmingLearner getSwimmingLearnersByUsername(String username) {
		for (int id = 0; id < swimmingLearners.size(); id++) {
			if (swimmingLearners.get(id).getLearnerUsername().equalsIgnoreCase(username)) {
				return swimmingLearners.get(id);
			}
		}
		return null;
	}
	
	
	//Update learner grade
	public void updateGrade(String username, int lessonGrade) {
		int learnerGrade;
		for (SwimmingLearner learnerObj : swimmingLearners) {
			if (learnerObj.getLearnerUsername().equalsIgnoreCase(username)) {
				learnerGrade = learnerObj.getLearnerGrade();
				if (lessonGrade > learnerGrade) {
					learnerObj.setLearnerGrade(lessonGrade);
				}
			}
		}

	}

	// validation of duplicate learner name
	public boolean duplicateLearnerName(String fullname) {
		for (SwimmingLearner learnerData : swimmingLearners) {
			if (learnerData.getLearnerFullname().equalsIgnoreCase(fullname)) {
				return true;
			}
		}
		return false;
	}

	// validation of duplicate learner emailid
	public boolean duplicateLearnerEmail(String emailId) {
		for (SwimmingLearner learnerData : swimmingLearners) {
			if (learnerData.getLearneremail().equalsIgnoreCase(emailId)) {
				return true;
			}
		}
		return false;
	}

}
