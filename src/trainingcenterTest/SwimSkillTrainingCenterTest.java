package trainingcenterTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import trainingcenter.BookingManager;
import trainingcenter.ReviewManager;
import trainingcenter.SwimmingCoach;
import trainingcenter.SwimmingLearner;
import trainingcenter.SwimmingLesson;
import trainingcenter.TimetableManager;
import trainingcenter.TrainingManagementSystem;

class SwimSkillTrainingCenter {
	private TrainingManagementSystem training;
	private SwimmingLearner swimmingLearner;
	private BookingManager bookingManager;
	private ReviewManager reviewManager;
	private SwimmingCoach swimmingCoach;
	private TimetableManager timetableManager;

	public SwimSkillTrainingCenter() {
		this.training = new TrainingManagementSystem();
		this.swimmingLearner = training.getSwimmingLearner();
		this.bookingManager = training.getBookingManager();
		this.reviewManager=training.getReviewManager();
		this.swimmingCoach=new SwimmingCoach();
		this.timetableManager=training.getTimetableManager();
	}

	

	// test reserved lesson
	@Test
	public void testReservedLesson() {
		String username = "BRO123";
		String lessonId = "SLT42";
		System.out.println("Test 1 : reserved lesson by learner");
		System.out.println("Learner username: " + username);
		System.out.println("Lesson id       : " + lessonId);
		bookingManager.setUserOption(new Scanner(lessonId));
		boolean result = bookingManager.reservedLesson(username);
		assertTrue(result, "Lesson reservation should succeed");
		// show booking details
		bookingManager.learnerBookingDetails(bookingManager.getLearnerBookingDetails(username, ""));

	}
	
	//test get lesson by trainer
			@Test
			public void testGetTimetableByCoach() {
				String coachUsername="JS123";
				System.out.println("\nTest 2 :attended reserved lesson by learner");
				System.out.println("Coach name: " + swimmingCoach.getCoachByUsername(coachUsername).getCoachFullname());
				timetableManager.setUserOption(new Scanner(coachUsername));
				List<SwimmingLesson>result=timetableManager.getTimetableByCoach();
				assertNotNull(result);
				//print lesson details
				timetableManager.lessonTimetable(result);
			}

	//test change lesson
	@Test
	public void testChangeReservedLesson() {
		String username = "BRO123";
		String newLessonId = "SLT40";
		String bookingNo="BMI01";
		System.out.println("\nTest 3 :changed reserved lesson by learner");
		System.out.println("Learner username    : " + username);
		System.out.println("new Lesson id       : " + newLessonId);
		//by default booking to change lesson
		bookingManager.setUserOption(new Scanner("SLT42"));
		bookingManager.reservedLesson(username);
		swimmingLearner.setLearnerOption(new Scanner("5"));
		bookingManager.setUserOption(new Scanner(bookingNo+"\n"+newLessonId));
		bookingManager.changeReservedLesson(username);
		String status=bookingManager.getBookingByBookingNo(bookingNo).getBookingStatus();
		String lessonId=bookingManager.getBookingByBookingNo(bookingNo).getLessonId();
		assertEquals(status,"Changed");
		assertEquals(newLessonId,lessonId);
	}
	
	// test learner signin
		@Test
		public void testLearnerSignin_ValidCredentials_Success() {
			String username = "BRO123";
			String password = "12345";
			System.out.println("\nTest 4 : Learner signin with valid credentials");
			System.out.println("Learner username: " + username);
			System.out.println("Learner password: " + password);
			swimmingLearner.setLearnerOption(new Scanner(username + "\n" + password));
			String result = swimmingLearner.learnerSingin();
			assertNotNull(result);
			System.out.println("\nSuccess! You signed in successfully");
		}
		
		//test attend lesson
		@Test
		public void testAttendReservedLesson() {
			String username = "BRO123";
			String bookingNo="BMI01";
			String rating="5";
			String review="Great, needs personalization";
			System.out.println("\nTest 5 :attended reserved lesson by learner");
			System.out.println("Learner username    : " + username);
			System.out.println("Booking Number      : " + bookingNo);
			System.out.println("Rating              : "+rating);
			System.out.println("Review              : "+review);
			//by default booking to attend lesson
			bookingManager.setUserOption(new Scanner("SLT42"));
			bookingManager.reservedLesson(username);
			reviewManager.setUserOption(new Scanner(rating+"\n"+review));
			bookingManager.setUserOption(new Scanner(bookingNo));
			bookingManager.attendReservedLesson(username);
			String status=bookingManager.getBookingByBookingNo(bookingNo).getBookingStatus();
			assertEquals(status,"Attended");
		}
		
		
		
		
		

}
