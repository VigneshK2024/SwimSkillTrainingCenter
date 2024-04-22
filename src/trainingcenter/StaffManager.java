package trainingcenter;

import java.util.Scanner;

public class StaffManager {
	private Scanner staffOption;
	private SwimmingLearner swimmingLearner;
	private BookingManager bookingManager;
	private ReportGenerator reportGenerator;
	private ReviewManager reviewManager;
	private SwimmingCoach swimmingCoach;

	public void setSwimmingLearner(SwimmingLearner swimmingLearner) {
		this.swimmingLearner = swimmingLearner;
	}

	public void setBookingManager(BookingManager bookingManager) {
		this.bookingManager = bookingManager;
	}

	public void setReportGenerator(ReportGenerator reportGenerator) {
		this.reportGenerator = reportGenerator;
	}

	public void setReviewManager(ReviewManager reviewManager) {
		this.reviewManager = reviewManager;
	}

	public StaffManager() {
		staffOption = new Scanner(System.in);
		this.swimmingCoach = new SwimmingCoach();
	}

	public void staffMenu() {
		String staffChoice;
		do {
			System.out.println("\nStaff menu");
			System.out.println("------------------------------");
			System.out.println("1. Show timetable\n2. Generate report\n3. Show Lesson Reviews\n4. Coach details"
					+ "\n5. Learner details\n6. All bookings\n7.Signout");
			System.out.print("Select your choice : ");
			staffChoice = staffOption.nextLine().trim();
			switch (staffChoice) {
			case "1" -> {
				swimmingLearner.showTimetable("", "");
			}
			case "2" -> {
				reportGenerator.reportMenu();
			}
			case "3" -> {
				reviewManager.ratingDetails(reviewManager.getReviewsByUser("", "", ""));
			}
			case "4" -> {
				swimmingCoach.coachDetails();
			}
			case "5" -> {
				swimmingLearner.showLearners();
			}
			case "6" -> {
				bookingManager.learnerBookingDetails(bookingManager.getLearnerBookingDetails("", ""));
			}
			case "7" -> {
				return;
			}
			default -> {

			}
			}
		} while (!staffChoice.equals("7"));
	}

	// staff login
	public void staffLogin(String staffUsername, String staffPassword) {
		String username;
		String password;
		System.out.println("\nStaff sign in");
		System.out.println("------------------------------");
		// Get username from the staff
		System.out.print("Enter Username : ");
		username = staffOption.nextLine().trim();
		// Validate entered username is not empty
		if (!username.isEmpty()) {
			// Get password from staff
			System.out.print("Enter Password : ");
			password = staffOption.nextLine().trim();
			// Validate entered password is not empty
			if (!password.isEmpty()) {
				if (staffUsername.equalsIgnoreCase(username) && staffPassword.equalsIgnoreCase(password)) {
					System.out.println("Success ! you signed in successfully");
					staffMenu();
				} else {
					// print error message
					System.out.println("\nError! Incorrect username or password");
				}
			} else {
				// print error message
				System.out.println("\nError ! You entered a wrong password");
			}
		} else {
			// print error message
			System.out.println("\nError ! You entered a wrong username");
		}
	}

}
