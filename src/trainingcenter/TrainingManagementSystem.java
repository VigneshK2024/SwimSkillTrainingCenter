package trainingcenter;

import java.util.Scanner;

public class TrainingManagementSystem {
	private Scanner userOption;
	private StaffManager staffManager;
	private BookingManager bookingManager;
	private ReportGenerator reportGenerator;
	private TimetableManager timetableManager;
	private String staffUsername = "Staff123";
	private String staffPassword = "S12345";
	private SwimmingLearner swimmingLearner;
	private ReviewManager reviewManager;
	
	public StaffManager getStaffManager() {
		return staffManager;
	}

	public BookingManager getBookingManager() {
		return bookingManager;
	}

	public ReportGenerator getReportGenerator() {
		return reportGenerator;
	}

	public TimetableManager getTimetableManager() {
		return timetableManager;
	}

	public SwimmingLearner getSwimmingLearner() {
		return swimmingLearner;
	}

	public ReviewManager getReviewManager() {
		return reviewManager;
	}


	public TrainingManagementSystem() {
		this.userOption = new Scanner(System.in);
		this.staffManager = ManagerFactory.createStaffManager();
		this.bookingManager = ManagerFactory.createBookingManager();
		this.reportGenerator = ManagerFactory.createReportGenerator();
		this.timetableManager = ManagerFactory.createTimetableManager();
		this.swimmingLearner = ManagerFactory.createSwimmingLearner();
		this.reviewManager = ManagerFactory.createReviewManager();
		this.reviewManager = new ReviewManager();
		swimmingLearner.setTimetableManager(timetableManager);
		swimmingLearner.setBookingManager(bookingManager);
		swimmingLearner.setReviewManager(reviewManager);
		bookingManager.setReviewManager(reviewManager);
		bookingManager.setSwimmingLearner(swimmingLearner);
		bookingManager.setTimetableManager(timetableManager);
		reviewManager.setBookingManager(bookingManager);
		reviewManager.setSwimmingLearner(swimmingLearner);
		reviewManager.setTimetableManager(timetableManager);
		reportGenerator.setBookingManager(bookingManager);
		reportGenerator.setReviewManager(reviewManager);
		reportGenerator.setSwimmingLearner(swimmingLearner);
		staffManager.setBookingManager(bookingManager);
		staffManager.setReportGenerator(reportGenerator);
		staffManager.setReviewManager(reviewManager);
		staffManager.setSwimmingLearner(swimmingLearner);
		
	}

	public void appManagement() {
		String userChoice = null;
		do {
			System.out.println("\nMain menu");
			System.out.println("------------------------------");
			System.out.println("1. Sign in\n2. Create an account\n3. Quit");
			System.out.print("Select one choice from above menu : ");
			userChoice = userOption.nextLine().trim();
			switch (userChoice) {
			case "1" -> {
				userSignin();
			}
			case "2" -> {
				swimmingLearner.createAccount();
			}
			case "3" -> {
				System.exit(0);
			}
			default -> {
				System.out.println("Error ! you entered an invalid choice");
			}
			}

		} while (!userChoice.equals("3"));

	}

	// user login
	public void userSignin() {
		String roleType = null;
		String username;
		do {
			System.out.println("\nSub menu");
			System.out.println("------------------------------");
			System.out.println("1. Sign in as staff\n2. Sign in as learner\n3. Back");
			System.out.print("Enter your sign type : ");
			roleType = userOption.nextLine().trim();
			switch (roleType) {
			case "1" -> {
				staffManager.staffLogin(staffUsername, staffPassword);
			}
			case "2" -> {
				username = swimmingLearner.learnerSingin();
				if (!username.isEmpty()) {
					System.out.println("Success ! you signed in successfully");
					swimmingLearner.learnerManager(username);
				}
			}
			case "3" -> {
				return;
			}
			default -> {
				System.out.println("Error ! you entered an invalid role type");
			}
			}
		} while (!roleType.equals("3"));

	}

	public static void main(String[] args) {
		TrainingManagementSystem training = new TrainingManagementSystem();
		training.appManagement();
	}

}
