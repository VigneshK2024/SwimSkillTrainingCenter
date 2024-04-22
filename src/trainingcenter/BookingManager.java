package trainingcenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookingManager {
	private TimetableManager timetableManager;
	private SwimmingLearner swimmingLearner;
	private ReviewManager reviewManager;
	private SwimmingCoach swimmingCoach;
	private Scanner userOption;
	private String bookingNo;
	private String learnerUsername;
	private String lessonId;
	private String coachUsername;
	private String bookingStatus = "Booked";
	private String bookingDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
	private List<BookingManager> bookingDetails;

	public String getBookingNo() {
		return bookingNo;
	}
	
	public void setUserOption(Scanner userOption) {
		this.userOption=userOption;
	}

	public String getLearnerUsername() {
		return learnerUsername;
	}

	public String getLessonId() {
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}

	public String getCoachUsername() {
		return coachUsername;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public List<BookingManager> getBookingDetails() {
		return bookingDetails;
	}

	public void setTimetableManager(TimetableManager timetableManager) {
		this.timetableManager = timetableManager;
	}

	public void setSwimmingLearner(SwimmingLearner swimmingLearner) {
		this.swimmingLearner = swimmingLearner;
	}

	public void setReviewManager(ReviewManager reviewManager) {
		this.reviewManager = reviewManager;
	}

	// create parameterized constructor to store learner booking details in list
	public BookingManager(String bookingNo, String learnerUsername, String lessonId, String coachUsername) {
		this.bookingNo = bookingNo;
		this.learnerUsername = learnerUsername;
		this.lessonId = lessonId;
		this.coachUsername = coachUsername;

	}

	// create default constructor
	public BookingManager() {
		userOption = new Scanner(System.in);
		bookingDetails = new ArrayList<>();
		this.swimmingCoach = new SwimmingCoach();
	}

	// booking manager menu
	public void bookingMenu(String username) {
		String learnerChoice;

		do {
			System.out.println("\nBooking manager");
			System.out.println("------------------------------");
			System.out.println(
					"1. My booking lesson\n2. Change booked lesson\n3. Attend booked lesson\n4. Cancelled booked lesson\n5. Back");
			System.out.print("Select one choice from above list : ");
			learnerChoice = userOption.nextLine().trim();
			switch (learnerChoice) {
			case "1" -> {
				learnerBookingDetails(getLearnerBookingDetails(username, ""));
			}
			case "2" -> {
				changeReservedLesson(username);
			}
			case "3" -> {
				attendReservedLesson(username);
			}

			case "4" -> {
				cancelReservedLesson(username);
			}
			case "5" -> {
				return;
			}
			default -> {

			}
			}

		} while (!learnerChoice.equals("5"));

	}

	// lesson book by learner
	public boolean reservedLesson(String username) {
		String lessonId;
		int lessonGrade;
		int learnerGrade;
		System.out.println("\nBooking lesson");
		System.out.println("------------------------------");
		// Select lesson to book a class
		System.out.print("Enter lesson ID to book a class: ");
		lessonId = userOption.nextLine().trim();
		if (lessonId.isEmpty() || !lessonIdExist(lessonId)) {
			System.out.println("Error: This lesson ID does not exist.");
		} else {
			// Validation for duplicate booking
			if (selectDuplicateLesson(username, lessonId)) {
				System.out.println("Error: You have already booked this lesson.");
			} else {
				// Get lesson grade
				lessonGrade = timetableManager.getLessonByLessonId(lessonId).getLessonGrade();
				// Get learner grade
				learnerGrade = swimmingLearner.getSwimmingLearnersByUsername(username).getLearnerGrade();
				// Validate lesson level is suitable for learner
				if (!(lessonGrade == learnerGrade || lessonGrade == (learnerGrade + 1))) {
					System.out.println("Error: This lesson is not suitable for you.");
					System.out.println("Your level : "+learnerGrade);
				} else {
					// Validate lesson has available capacity to book lesson
					if (!availableLessonCapacity(lessonId)) {
						System.out.println("Error: This lesson does not have available capacity.");
					} else {
						saveBookingDetails(username, lessonId);
						return true;
					}
				}
			}
		}
		return false;
	}

	// change reserved lesson
	public void changeReservedLesson(String username) {
		String newLessonId;
		String bookingNo;
		int lessonGrade;
		int learnerGrade;
		String status;
		// get booking details by learner
		List<BookingManager> learnerBookings = getLearnerBookingDetails(username, "");
		// show bookings
		learnerBookingDetails(learnerBookings);
		if (!learnerBookings.isEmpty()) {
			System.out.println("\nChange lesson");
			System.out.println("------------------------------");
			System.out.print("\nEnter the booking number to change the lesson: ");
			bookingNo = userOption.nextLine().trim();
			if (bookingNo.isEmpty() || !bookingNoExist(username, bookingNo)) {
				System.out.println("Error: The entered booking number is invalid.");
			} else {
				// get booking status
				status = getBookingByBookingNo(bookingNo).getBookingStatus();
				// validation of selected booking not attended
				if (status.equalsIgnoreCase("Attended")) {
					System.out.println("Error: You have already attended this class.");
				} else if (status.equalsIgnoreCase("Cancelled")) {
					System.out.println("Error: You have already cancelled this class.");
				} else {
					// change lesson
					swimmingLearner.showTimetable(username, "Change");
					// select lesson to change class
					System.out.print("Enter the new lesson ID to change the lesson: ");
					newLessonId = userOption.nextLine().trim();
					if (newLessonId.isEmpty() || !lessonIdExist(newLessonId)) {
						System.out.println("Error: This lesson ID does not exist.");
					} else {
						// Validation for duplicate booking
						if (selectDuplicateLesson(username, newLessonId)) {
							System.out.println("Error: You have already booked this lesson.");
						} else {
							// Get lesson grade
							lessonGrade = timetableManager.getLessonByLessonId(newLessonId).getLessonGrade();
							// Get learner grade
							learnerGrade = swimmingLearner.getSwimmingLearnersByUsername(username).getLearnerGrade();
							// Validate lesson level is suitable for learner
							if (!(lessonGrade == learnerGrade || lessonGrade == (learnerGrade + 1))) {
								System.out.println("Error: This lesson is not suitable for you.");
							} else {
								// Validate lesson has available capacity to book lesson
								if (!availableLessonCapacity(newLessonId)) {
									System.out.println("Error: This lesson does not have available capacity.");
								} else {
									// get coach username from lesson id
									String coachUsername = timetableManager.getLessonByLessonId(newLessonId)
											.getCoachUsername();
									// update booking
									updateLearnerBooking(bookingNo, newLessonId, coachUsername);
									// print message after change lesson
									System.out.println("\nNew lessson updated successfully");
								}
							}
						}
					}
				}
			}
		}
	}

	// update learner booking
	public void updateLearnerBooking(String bookingNo, String newLessonId, String coachUsername) {
		String updateStatus = "Changed";
		// update lesson after change
		// get booking object to update details
		BookingManager bookingObj = getBookingByBookingNo(bookingNo);
		// get old lesson to update seat
		String oldLessonId = bookingObj.getLessonId();
		// update new lesson in booking
		bookingObj.setLessonId(newLessonId);
		// update booking status
		bookingObj.setBookingStatus(updateStatus);
		// update lesson capacity
		timetableManager.updateSeatAfterChangeLesson(oldLessonId, newLessonId);
	}

	// get learner bookings
	public List<BookingManager> getLearnerBookingDetails(String username, String month) {
		String bookingMonthName;
		String reportMonth = null;
		if (!month.isEmpty()) {
			reportMonth = month.substring(0, 3);
		}
		List<BookingManager> learnerBookings = new ArrayList<>();
		for (BookingManager bookingData : bookingDetails) {
			// get lesson object by lesson id
			SwimmingLesson lessonObj = timetableManager.getLessonByLessonId(bookingData.getLessonId());
			bookingMonthName = lessonObj.getLessonDate().substring(3, 6);
			if ((bookingData.getLearnerUsername().equalsIgnoreCase(username)
					&& bookingMonthName.equalsIgnoreCase(reportMonth))
					|| (bookingData.getLearnerUsername().equalsIgnoreCase(username) && reportMonth == null)||username.isEmpty()&&reportMonth==null) {
				learnerBookings.add(bookingData);
			}
		}
		return learnerBookings;
	}

	// print booking details
	public void learnerBookingDetails(List<BookingManager> learnerBookings) {
		if (!learnerBookings.isEmpty()) {
			// Show heading
			System.out.println(
					"\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
							+ "--------------");
			System.out.printf(
					"! %-10s ! %-12s  ! %-12s  ! %-12s  ! %-15s  ! %-12s ! %-12s  ! %-15s  ! %-15s  ! %-15s ! %-15s  ! %-15s !\n",
					"Booking No", "Learner", "Learner Grade", "Booking Date", "Status", "Lesson ID", "Lesson Name",
					"Lesson Grade", "Coach", "Le--sson Date", "Lesson Day", "Lesson Time");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
							+ "--------------");

			// Show bookingDatas
			for (BookingManager bookingData : learnerBookings) {
				// get bookingData details
				String bookingNo = bookingData.getBookingNo();
				String lessonCode = bookingData.getLessonId();
				String learnerName = swimmingLearner.getSwimmingLearnersByUsername(bookingData.getLearnerUsername())
						.getLearnerFullname();
				int learnerGrade = swimmingLearner.getSwimmingLearnersByUsername(bookingData.getLearnerUsername())
						.getLearnerGrade();
				String bookingDate = bookingData.getBookingDate();
				String status = bookingData.getBookingStatus();
				String lessonName = timetableManager.getLessonByLessonId(bookingData.getLessonId()).getLessonName();
				int lessonGrade = timetableManager.getLessonByLessonId(bookingData.getLessonId()).getLessonGrade();
				String coachName = swimmingCoach.getCoachByUsername(bookingData.getCoachUsername()).getCoachFullname();
				String lessonDate = timetableManager.getLessonByLessonId(bookingData.getLessonId()).getLessonDate();
				String lessonDay = timetableManager.getLessonByLessonId(bookingData.getLessonId()).getLessonDay();
				String lessonTime = timetableManager.getLessonByLessonId(bookingData.getLessonId()).getLessonTime();
				// Print bookingData details
				System.out.printf(
						"! %-10s ! %-12s  ! %-13s  ! %-12s  ! %-15s  ! %-12s ! %-12s  ! %-15s  ! %-15s  ! %-15s ! %-15s  ! %-15s !\n",
						bookingNo, learnerName, "Grade " + learnerGrade, bookingDate, status, lessonCode, lessonName,
						"  Grade " + lessonGrade, coachName, lessonDate, lessonDay, lessonTime);
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
							+ "--------------");
		} else {
			System.out.println("Error : There are no bookingData available");
		}
	}

	// get booking object by booking no
	public BookingManager getBookingByBookingNo(String bookingNo) {
		for (int id = 0; id < bookingDetails.size(); id++) {
			if (bookingDetails.get(id).getBookingNo().equalsIgnoreCase(bookingNo)) {
				return bookingDetails.get(id);
			}

		}
		return null;
	}

	// save booking details
	public void saveBookingDetails(String username, String lessonId) {
		// generate booking id
		int nextBookingId = bookingDetails.size() + 1;
		String bookingNo = String.format("BMI%02d", nextBookingId);
		// update lesson capacity
		timetableManager.updateSeatAfterBookLesson(lessonId);
		// get coach from lesson id
		String coachUsername = timetableManager.getLessonByLessonId(lessonId).getCoachUsername();
		// create bookingManger object
		BookingManager bookingData = new BookingManager(bookingNo, username, lessonId, coachUsername);
		// add booking object in booking Details
		bookingDetails.add(bookingData);
	}

	// cancel reserved lesson
	public void cancelReservedLesson(String username) {
		String bookingNo;
		String status;
		// get booking details by learner
		List<BookingManager> learnerBookings = getLearnerBookingDetails(username, "");
		// show bookings
		learnerBookingDetails(learnerBookings);
		if (!learnerBookings.isEmpty()) {
			System.out.println("\nCancel booked lesson");
			System.out.println("------------------------------");
			System.out.print("\nEnter the booking number to cancel booked lesson : ");
			bookingNo = userOption.nextLine().trim();
			if (bookingNo.isEmpty() || !bookingNoExist(username, bookingNo)) {
				System.out.println("Error: The entered booking number is invalid.");
			} else {
				// get booking status
				status = getBookingByBookingNo(bookingNo).getBookingStatus();
				// validation of selected booking not attended
				if (status.equalsIgnoreCase("Attended")) {
					System.out.println("Error: You have already attended this class.");
				} else if (status.equalsIgnoreCase("Cancelled")) {
					System.out.println("Error: You have already cancelled this class.");
				} else {
					// cancel lesson
					updateCancelledBooking(bookingNo);
					// print message after cancelled booking
					System.out.println("\nSelected lesson cancelled succcessfully");
				}
			}
		}
	}

	// attend reserved class
	public void attendReservedLesson(String username) {
		String bookingNo;
		String status;
		// get booking details by learner
		List<BookingManager> learnerBookings = getLearnerBookingDetails(username, "");
		// show bookings
		learnerBookingDetails(learnerBookings);
		if (!learnerBookings.isEmpty()) {
			System.out.println("\nAttend booked lesson");
			System.out.println("------------------------------");
			System.out.print("\nEnter the booking number to attend booked lesson : ");
			bookingNo = userOption.nextLine().trim();
			if (bookingNo.isEmpty() || !bookingNoExist(username, bookingNo)) {
				System.out.println("Error: The entered booking number is invalid.");
			} else {
				// get booking status
				status = getBookingByBookingNo(bookingNo).getBookingStatus();
				// validation of selected booking not attended
				if (status.equalsIgnoreCase("Attended")) {
					System.out.println("Error: You have already attended this class.");
				} else if (status.equalsIgnoreCase("Cancelled")) {
					System.out.println("Error: You have already cancelled this class.");
				} else {
					// cancel lesson
					updateAttendedBooking(bookingNo);
					// print message after cancelled booking
					System.out.println("\nSelected booking attended succcessfully");
				}
			}
		}
	}

	// update booking after attend class
	public void updateAttendedBooking(String bookingNo) {
		String updateStatus = "Attended";
		// get reviews from learner after attended lesson
		reviewManager.learnerFeedback(bookingNo);
		// get booking object
		BookingManager bookingObj = getBookingByBookingNo(bookingNo);
		// update booking status
		bookingObj.setBookingStatus(updateStatus);
		String username = bookingObj.getLearnerUsername();
		// get lesson object
		SwimmingLesson lessonObj = timetableManager.getLessonByLessonId(bookingObj.getLessonId());
		int lessonGrade = lessonObj.getLessonGrade();
		String lessonId = lessonObj.getLessonId();
		// update learner grade
		swimmingLearner.updateGrade(username, lessonGrade);
		// Update lesson capacity
		timetableManager.updateSeatAfterCancelLesson(lessonId);
	}

	// update booking and lesson after cancel lesson
	public void updateCancelledBooking(String bookingNo) {
		String updateStatus = "Cancelled";
		// get booking object by booking no.
		BookingManager bookingObj = getBookingByBookingNo(bookingNo);
		// update booking status
		bookingObj.setBookingStatus(updateStatus);
		// get lesson id from booking to update capacity
		String lessonId = bookingObj.getLessonId();
		// update capacity
		timetableManager.updateSeatAfterCancelLesson(lessonId);

	}

	// calculate learner booking details
	public Map<String, Integer> countLearnerBookingDetails(String username, String month) {
		int learnerBooking = 0;
		int learnerCancelled = 0;
		int learnerAttended = 0;
		// create list to store learner booking count
		Map<String, Integer> countData = new HashMap<>();
		for (BookingManager bookingData : getLearnerBookingDetails(username, month)) {
			if (bookingData.getBookingStatus().equalsIgnoreCase("Booked")) {
				learnerBooking++;
			} else if (bookingData.getBookingStatus().equalsIgnoreCase("Attended")) {
				learnerAttended++;
			} else if (bookingData.getBookingStatus().equalsIgnoreCase("Cancelled")) {
				learnerCancelled++;
			}
		}

		// add learner booking details in list
		countData.put("book", learnerBooking);
		countData.put("attend", learnerAttended);
		countData.put("cancel", learnerCancelled);

		return countData;
	}

	// validation of valid lesson id
	public boolean lessonIdExist(String lessonId) {
		List<SwimmingLesson> lessonDetails = timetableManager.getSwimmingLessons();
		for (SwimmingLesson lessonData : lessonDetails) {
			if (lessonData.getLessonId().equalsIgnoreCase(lessonId)) {
				return true;
			}
		}
		return false;
	}

	// Validation of duplicate booking
	public boolean selectDuplicateLesson(String username, String lessonId) {
		for (BookingManager bookingData : bookingDetails) {
			if (bookingData.getLearnerUsername().equalsIgnoreCase(username)
					&& bookingData.getLessonId().equalsIgnoreCase(lessonId)) {
				if (!(bookingData.getBookingStatus().equalsIgnoreCase("Attended")
						|| bookingData.getBookingStatus().equalsIgnoreCase("Attended"))) {
					return true;
				}
			}
		}
		return false;
	}

	/// validation of lessonCapacity
	public boolean availableLessonCapacity(String lessonId) {
		List<SwimmingLesson> lessonDetails = timetableManager.getSwimmingLessons();
		int lessonCapacity;
		for (SwimmingLesson lessonObj : lessonDetails) {
			if (lessonObj.getLessonId().equalsIgnoreCase(lessonId)) {
				lessonCapacity = lessonObj.getLessonCapacity();
				if (lessonCapacity > 0 && lessonCapacity <= 4) {
					return true;
				}
			}
		}
		return false;
	}

	// validation of booking no exist
	public boolean bookingNoExist(String username, String bookingNo) {
		BookingManager bookingObj = getBookingByBookingNo(bookingNo);
		if (bookingObj != null) {
			if (bookingObj.getLearnerUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		return false;
	}

}
