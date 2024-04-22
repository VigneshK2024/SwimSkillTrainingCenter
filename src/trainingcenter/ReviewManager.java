package trainingcenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReviewManager {
	private BookingManager bookingManager;

	public void setCoachUsername(String coachUsername) {
		this.coachUsername = coachUsername;
	}

	private TimetableManager timetableManager;
	private SwimmingLearner swimmingLearner;
	private SwimmingCoach swimmingCoach;
	private Scanner userOption;
	private String ratingId;
	private String bookingNo;
	private String lessonId;
	private String learnerUsername;
	private String coachUsername;
	private int learnerRating;
	private String learnerReview;
	private List<ReviewManager> learnerReviews;

	public String getRatingId() {
		return ratingId;
	}

	public String getBookingNo() {
		return bookingNo;
	}

	public String getLessonId() {
		return lessonId;
	}

	public String getLearnerUsername() {
		return learnerUsername;
	}
	
	public void setUserOption(Scanner userOption) {
		this.userOption=userOption;
	}

	public String getCoachUsername() {
		return coachUsername;
	}

	public int getLearnerRating() {
		return learnerRating;
	}

	public String getLearnerReview() {
		return learnerReview;
	}

	public void setBookingManager(BookingManager bookingManager) {
		this.bookingManager = bookingManager;
	}

	public void setTimetableManager(TimetableManager timetableManager) {
		this.timetableManager = timetableManager;
	}

	public void setSwimmingLearner(SwimmingLearner swimmingLearner) {
		this.swimmingLearner = swimmingLearner;
	}

	// create parameterized constructor to save learner rating data
	public ReviewManager(String ratingId, String bookingNo, String lessonId, String learnerUsername,
			String coachUsername, int learnerRating, String learnerReview) {
		this.ratingId = ratingId;
		this.bookingNo = bookingNo;
		this.lessonId = lessonId;
		this.learnerUsername = learnerUsername;
		this.coachUsername = coachUsername;
		this.learnerRating = learnerRating;
		this.learnerReview = learnerReview;
	}

	// create default constructor
	public ReviewManager() {
		userOption = new Scanner(System.in);
		learnerReviews = new ArrayList<>();
		this.swimmingCoach = new SwimmingCoach();
	}

	// get review of attended lesson from learner
	public void learnerFeedback(String bookingNo) {
		boolean saveRating = false;
		String rating;
		String review;
		System.out.println("\nLearner reviews for attended lesson");
		System.out.println("------------------------------");

		System.out.println("\n1. Very dissatisfied\n2. Dissatisfied\n3. Ok\n4. Satisfied\n5. Very Satisfied\n");
		do {
			// get rating from learner
			System.out.print("\nEnter rating for attended lesson : ");
			rating = userOption.nextLine().trim();
			// validation of rating is not empty
			if (rating.isEmpty() || !rating.matches("[1-5]")) {
				System.out.print("Error : You entered an invalid rating ");
			} else {
				System.out.print("Enter reviews for this lesson : ");
				review = userOption.nextLine().trim();
				if (review.isEmpty()) {
					System.out.println("Error : You entered an invalid review ");
				} else {
					if (!rating.isEmpty() && !review.isEmpty()) {
						learnerRating=Integer.parseInt(rating);
						// save learner rating
						saveLearnerRatingDetails(bookingNo, review, learnerRating);
						saveRating = true;
					}
				}
			}
		} while (!saveRating);
	}

	// save learner rating data
	public void saveLearnerRatingDetails(String bookingNo, String review, int rating) {
		int id = learnerReviews.size() + 1;
		String ratingId;
		// get booking object from booking number
		BookingManager bookingObj = bookingManager.getBookingByBookingNo(bookingNo);
		// get username from booking
		String username = bookingObj.getLearnerUsername();
		String coachUsername = bookingObj.getCoachUsername();
		String lessonId = bookingObj.getLessonId();
		if (id >= 0 && id <= 9) {
			ratingId = "RI0" + id;
		} else {
			ratingId = "RI" + id;
		}

		// create object to save data
		ReviewManager reviewData = new ReviewManager(ratingId, bookingNo, lessonId, username, coachUsername, rating,
				review);
		// add object in list
		learnerReviews.add(reviewData);

	}

	// print rating details
	public void ratingDetails(List<ReviewManager> learnerReviews) {
		if (!learnerReviews.isEmpty()) {
			// show heading
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------"
							+ "---------------------------------------------------------------------------------");
			System.out.printf("! %-15s  ! %-15s  ! %-15s  ! %-15s  ! %-15s  ! %-15s ! %-15s  ! %-15s ! %-30s !\n",
					"Rating Id", "Learner", "Coach", "Lesson Id", "Lesson Name", "Lesson Date", "Lesson Day", "Rating",
					"Review");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------"
							+ "---------------------------------------------------------------------------------");
			for (ReviewManager reviewData : learnerReviews) {
				String ratingId = reviewData.getRatingId();
				String learnerName = swimmingLearner.getSwimmingLearnersByUsername(reviewData.getLearnerUsername())
						.getLearnerFullname();
				String coachName = swimmingCoach.getCoachByUsername(reviewData.getCoachUsername()).getCoachFullname();
				String lessonId = reviewData.getLessonId();
				// get lesson object
				SwimmingLesson lessonObj = timetableManager.getLessonByLessonId(lessonId);
				String lessonName = lessonObj.getLessonName();
				String lessonDate = lessonObj.getLessonDate();
				String lessonDay = lessonObj.getLessonDay();
				int rating = reviewData.getLearnerRating();
				String review = reviewData.getLearnerReview();
				System.out.printf("! %-15s  ! %-15s  ! %-15s  ! %-15s  ! %-15s  ! %-15s ! %-15s  ! %-14s ! %-30s !\n",
						ratingId, learnerName, coachName, lessonId, lessonName, lessonDate, lessonDay, "\t" + rating,
						review);
			}
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------"
							+ "--------------------------------------------------------------------------------");
		} else {
			System.out.println("Error ! There are no reviewData available");
		}
	}

	// get review data by learner and coach
	public List<ReviewManager> getReviewsByUser(String learnerUsername, String coachUsername, String month) {
		String reviewMonthName;
		String reportMonth = null;
		if(!month.isEmpty()) {
			reportMonth=month.substring(0,3);
		}
		
		// create list to store review data
		List<ReviewManager> reviewDetails = new ArrayList<>();
		for (ReviewManager reviewData : learnerReviews) {
			// get lesson object from review data
			SwimmingLesson lessonObj = timetableManager.getLessonByLessonId(reviewData.getLessonId());
			reviewMonthName = lessonObj.getLessonDate().substring(3, 6);
			if (!coachUsername.isEmpty()) {
				// get review data by coach
				if ((reviewData.getCoachUsername().equalsIgnoreCase(coachUsername)&& reviewMonthName.equalsIgnoreCase(reportMonth))||
						(reviewData.getCoachUsername().equalsIgnoreCase(coachUsername)&& reportMonth==null)) {
					//add review by coach
					reviewDetails.add(reviewData);
				}
			} else if (!learnerUsername.isEmpty()) {
				if (reviewData.getLearnerUsername().equalsIgnoreCase(learnerUsername)) {
					//add review by learner
					reviewDetails.add(reviewData);
				}
			} else {
				//add all review details
				reviewDetails.add(reviewData);
			}
		}
		//return review data
		return reviewDetails;
	}
	
	//calculate coach average rating
	public double coachRating(String coachUsername, String month) {
		double averageCoachRating = 0;
		int totalRating = 0;
		int totalLessons = 0;
		// get filter review list
		List<ReviewManager> coachReviews = getReviewsByUser("", coachUsername, month);
		if (!coachReviews.isEmpty()) {
			for (ReviewManager reviewData : coachReviews) {
				totalRating += reviewData.getLearnerRating();
				totalLessons++;
			}
			// Calculate average rating
			averageCoachRating = totalRating / totalLessons;

		}

		return averageCoachRating;
	}

}
