package trainingcenter;

import java.util.List;
import java.util.Scanner;

public class ReportGenerator {
	private Scanner userOption;
	private SwimmingCoach swimmingCoach;
	private SwimmingLearner swimmingLearner;
	private BookingManager bookingManager;
	private ReviewManager reviewManager;

	public void setSwimmingLearner(SwimmingLearner swimmingLearner) {
		this.swimmingLearner = swimmingLearner;
	}

	public void setBookingManager(BookingManager bookingManager) {
		this.bookingManager = bookingManager;
	}

	public void setReviewManager(ReviewManager reviewManager) {
		this.reviewManager = reviewManager;
	}

	// create constructor
	public ReportGenerator() {
		userOption = new Scanner(System.in);
		this.swimmingCoach = new SwimmingCoach();
	}

	public void reportMenu() {
		String monthName;
		String choice;
		// select month to generate report
		System.out.print("\nEnter one month name to generate report : ");
		monthName = userOption.nextLine().trim();
		if (monthName.isEmpty() || monthName(monthName) == null) {
			System.out.println("Error ! entered incorrect month name");
		} else {
			monthName = monthName(monthName);
			System.out.println("1. generate learner booking report");
			System.out.println("2. Generate coach rating report");
			System.out.print("Enter one choice to generate report : ");
			choice = userOption.nextLine().trim();
			switch (choice) {
			case "1" -> {
				generateLearnerMonthlyReport(monthName);
			}
			case "2" -> {
				generateCoachMonthlyReport(monthName);
			}
			default -> {
				System.out.println("Error ! entered invalid choice");
			}

			}
		}

	}

	// generate coach monthly report
	public void generateCoachMonthlyReport(String month) {
		boolean generateReport = false;
		System.out.println("\nCoach rating report");
		System.out.println("------------------------------");
		System.out.println("Report month         : " + month);
		// get all coach details
		List<SwimmingCoach> swimmingCoaches = swimmingCoach.getSwimmingCoaches();
		for (int id = 0; id < swimmingCoaches.size(); id++) {
			String coachUsername = swimmingCoaches.get(id).getCoachUsername();
			// get average rating by coach id
			double coachRating = reviewManager.coachRating(coachUsername, month);
			if (coachRating != 0) {
				SwimmingCoach coachData = swimmingCoach.getCoachByUsername(coachUsername);
				// print coach details
				System.out.println("Coach name           : " + coachData.getCoachFullname());
				System.out.println("Coach Experience     : " + coachData.getCoachExperience());
				System.out.println("Coach average rating : " + coachRating);
				// print coach review details
				System.out.println();
				// get review of coach
				reviewManager.ratingDetails(reviewManager.getReviewsByUser("", coachUsername, month));
				generateReport = true;
			}
		}
		if (!generateReport) {
			System.out.println("Error ! No report available");
		}
	}

	// public void generate learner booking report
	public void generateLearnerMonthlyReport(String month) {
		int book = 0;
		int cancel = 0;
		int attend = 0;
		boolean generateReport = false;
		System.out.println("\nLearner booking report");
		System.out.println("------------------------------");
		System.out.println("Report month        : " + month);
		// get all learner details to fetch booking details
		for (SwimmingLearner learnerData : swimmingLearner.getSwimmingLearners()) {
			// get learner userame
			String username = learnerData.getLearnerUsername();
			book = bookingManager.countLearnerBookingDetails(username, month).get("book");
			attend = bookingManager.countLearnerBookingDetails(username, month).get("attend");
			cancel = bookingManager.countLearnerBookingDetails(username, month).get("cancel");
			// validation of book attend and cancel all are not 0
			if (!(book == 0 && attend == 0 && cancel == 0)) {
				// get swimming object from username
				SwimmingLearner learnerObj = swimmingLearner.getSwimmingLearnersByUsername(username);
				System.out.println("Learner name        : " + learnerObj.getLearnerFullname());
				System.out.println("Learner Age         : " + learnerObj.getLearnerAge());
				System.out.println("Learner Grade       : " + learnerObj.getLearnerGrade());
				System.out.println("Total booked lesson : " + book);
				System.out.println("Total attend lesson : " + attend);
				System.out.println("Total cancel lesson : " + cancel);
				// print student booking details
				List<BookingManager> learnerBookings = bookingManager.getLearnerBookingDetails(username, month);
				bookingManager.learnerBookingDetails(learnerBookings);
				generateReport = true;
			}

		}
		if (!generateReport) {
			System.out.println("Error ! No report available");
		}

	}

	// validation of valid month name
	public String monthName(String monthName) {
		// store month name in array
		String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		for (String month : months) {
			if (month.equalsIgnoreCase(monthName) || month.substring(0, 3).equalsIgnoreCase(monthName)) {
				return month;
			}
		}
		return null;
	}

}
