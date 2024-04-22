package trainingcenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TimetableManager {
	private Scanner userOption;
	private SwimmingCoach swimmingCoach;
	public List<SwimmingLesson> swimmingLessons;

	public List<SwimmingLesson> getSwimmingLessons() {
		return swimmingLessons;
	}
	
	public void setUserOption(Scanner userOption) {
		this.userOption=userOption;
	}

	public TimetableManager() {
		swimmingLessons = new ArrayList<>();
		userOption = new Scanner(System.in);
		this.swimmingCoach = new SwimmingCoach();
		addPredefinedLesson();
	}

	// add predefined lesson details
	public void addPredefinedLesson() {
		swimmingLessons.add(
				new SwimmingLesson("SLT01", "Water Wizards", "MB123", "Wednesday", "4pm to 5pm", 4, 5, "22 May 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT02", "Water Wizards", "OT123", "Wednesday", "5pm to 6pm", 4, 1, "22 May 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT03", "Water Wizards", "DW123", "Wednesday", "6pm to 7pm", 4, 2, "22 May 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT04", "Swim Sensei", "JS123", "Friday", "4pm to 5pm", 4, 3, "24 May 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT05", "Swim Sensei", "EJ123", "Friday", "5pm to 6pm", 4, 4, "24 May 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT06", "Swim Sensei", "MB123", "Friday", "6pm to 7pm", 4, 5, "24 May 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT07", "Swim Squad", "OT123", "Saturday", "2pm to 3pm", 4, 5, "25 May 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT08", "Swim Squad", "JS123", "Saturday", "3pm to 4pm", 4, 2, "25 May 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT09", "Glide & Glimmer", "EJ123", "Monday", "4pm to 5pm", 4, 4, "27 May 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT10", "Glide & Glimmer", "MB123", "Monday", "5pm to 6pm", 4, 3, "27 May 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT11", "Glide & Glimmer", "OT123", "Monday", "6pm to 7pm", 4, 5, "27 May 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT12", "Water Wizards", "DW123", "Wednesday", "4pm to 5pm", 4, 1, "29 May 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT13", "Water Wizards", "JS123", "Wednesday", "5pm to 6pm", 4, 2, "29 May 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT14", "Water Wizards", "EJ123", "Wednesday", "6pm to 7pm", 4, 1, "29 May 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT15", "Swim Sensei", "MB123", "Friday", "4pm to 5pm", 4, 4, "31 May 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT16", "Swim Sensei", "OT123", "Friday", "5pm to 6pm", 4, 1, "31 May 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT17", "Swim Sensei", "DW123", "Friday", "6pm to 7pm", 4, 3, "31 May 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT18", "Swim Squad", "JS123", "Saturday", "2pm to 3pm", 4, 2, "01 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT19", "Swim Squad", "EJ123", "Saturday", "3pm to 4pm", 4, 3, "01 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT20", "Glide & Glimmer", "MB123", "Monday", "4pm to 5pm", 4, 1, "03 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT21", "Glide & Glimmer", "OT123", "Monday", "5pm to 6pm", 4, 5, "03 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT22", "Glide & Glimmer", "DW123", "Monday", "6pm to 7pm", 4, 2, "03 Jun 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT23", "Swim Sensei", "SE123", "Wednesday", "4pm to 5pm", 4, 4, "05 Jun 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT24", "Swim Sensei", "JS123", "Wednesday", "5pm to 6pm", 4, 3, "05 Jun 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT25", "Swim Sensei", "SE123", "Wednesday", "6pm to 7pm", 4, 5, "05 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT26", "Glide & Glimmer", "SE123", "Friday", "4pm to 5pm", 4, 1, "07 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT27", "Glide & Glimmer", "EJ123", "Friday", "5pm to 6pm", 4, 5, "07 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT28", "Glide & Glimmer", "DW123", "Friday", "6pm to 7pm", 4, 2, "07 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT29", "Swim Squad", "SE123", "Saturday", "2pm to 3pm", 4, 4, "08 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT30", "Swim Squad", "SE123", "Saturday", "3pm to 4pm", 4, 4, "08 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT31", "Glide & Glimmer", "MB123", "Monday", "4pm to 5pm", 4, 3, "10 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT32", "Glide & Glimmer", "DW123", "Monday", "5pm to 6pm", 4, 2, "10 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT33", "Glide & Glimmer", "SE123", "Monday", "6pm to 7pm", 4, 2, "10 Jun 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT34", "Water Wizards", "SE123", "Wednesday", "4pm to 5pm", 4, 1, "12 Jun 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT35", "Water Wizards", "OT123", "Wednesday", "5pm to 6pm", 4, 1, "12 Jun 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT36", "Water Wizards", "DW123", "Wednesday", "6pm to 7pm", 4, 3, "12 Jun 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT37", "Swim Sensei", "DW123", "Friday", "4pm to 5pm", 4, 4, "14 Jun 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT38", "Swim Sensei", "JS123", "Friday", "5pm to 6pm", 4, 5, "14 Jun 2024"));
		swimmingLessons.add(
				new SwimmingLesson("SLT39", "Swim Sensei", "EJ123", "Friday", "6pm to 7pm", 4, 2, "14 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT40", "Swim Squad", "MB123", "Saturday", "2pm to 3pm", 4, 1, "15 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT41", "Swim Squad", "OT123", "Saturday", "3pm to 4pm", 4, 1, "15 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT42", "Glide & Glimmer", "DW123", "Monday", "4pm to 5pm", 4, 2, "17 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT43", "Glide & Glimmer", "JS123", "Monday", "5pm to 6pm", 4, 3, "17 Jun 2024"));
		swimmingLessons
				.add(new SwimmingLesson("SLT44", "Glide & Glimmer", "EJ123", "Monday", "6pm to 7pm", 4, 4, "17 Jun 2024"));
	}

	// show lesson timetable
	public void lessonTimetable(List<SwimmingLesson> swimmingLessons) {
		if (!swimmingLessons.isEmpty()) {
			System.out.println("\nLesson timetable");
			System.out.println("------------------------------\n");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------"
							+ "---------------------------------------------------------------------");
			System.out.printf(
					"! %-10s  ! %-20s  ! %-13s  ! %-12s  ! %-10s  ! %-20s  ! %-10s  ! %-10s  ! %-5s  ! %-15s !\n",
					"Lesson id", "Lesson name", "Lesson day", "Lesson time", "Grade", "Coach name", "Experience",
					"Coach gender", "Capacity", "Lesson date");
			System.out.println(
					"-----------------------------------------------------------------------------------------------------"
							+ "---------------------------------------------------------------------");
			for (SwimmingLesson lessonObj : swimmingLessons) {
				String lessonId = lessonObj.getLessonId();
				String lessonName = lessonObj.getLessonName();
				int lessonCapacity = lessonObj.getLessonCapacity();
				String lessonDay = lessonObj.getLessonDay();
				String lessonDate = lessonObj.getLessonDate();
				String lessonTime = lessonObj.getLessonTime();
				int grade = lessonObj.getLessonGrade();
				// get coach object
				SwimmingCoach coachObj = swimmingCoach.getCoachByUsername(lessonObj.getCoachUsername());
				String coachName = coachObj.getCoachFullname();
				String coachExp = coachObj.getCoachExperience();
				String coachGender = coachObj.getCoachGender();
				// print lesson details
				System.out.printf(
						"! %-10s  ! %-20s  ! %-13s  ! %-12s  ! %-10s  ! %-20s  ! %-10s  ! %-12s  ! %-6s  ! %-15s !\n",
						lessonId, lessonName, lessonDay, lessonTime, "Grade " + grade, coachName, coachExp, coachGender,
						"\t" + lessonCapacity, lessonDate);
			}
			System.out.println(
					"-----------------------------------------------------------------------------------------------------"
							+ "---------------------------------------------------------------------");
		}
	}

	// filter timetable by day
	public List<SwimmingLesson> getTimetableByDay() {
		String selectDay;
		String day = null;
		// create list to store filter timetable
		List<SwimmingLesson> timetable = new ArrayList<>();
		System.out.println("\n1. Monday\t2. Wednesday");
		System.out.println("3. Friday\t4. Saturday");
		System.out.println("Select one day to show timetable : ");
		selectDay = userOption.nextLine().trim();
		switch (selectDay) {
		case "1" -> {
			day = "Monday";
		}
		case "2" -> {
			day = "Wednesday";
		}
		case "3" -> {
			day = "Friday";
		}
		case "4" -> {
			day = "Saturday";
		}
		default -> {
			System.out.println("Error ! invalide day selection");
		}
		}
		// filter timetable by day
		if (day != null) {
			for (SwimmingLesson lessonData : swimmingLessons) {
				if (lessonData.getLessonDay().equalsIgnoreCase(day)) {
					timetable.add(lessonData);
				}
			}
		}
		return timetable;
	}

	// filter timetable by grade
	public List<SwimmingLesson> getTimetableByGrade() {
		String grade;
		// create list to store filter timetable
		List<SwimmingLesson> timetable = new ArrayList<>();
		System.out.println("Enter grade (1 to 5) to show timetable : ");
		grade = userOption.nextLine().trim();
		if (grade.isEmpty() || !grade.matches("[1-5]")) {
			System.out.println("Error ! invalid grade selection");
		} else {
			// filter timetable by grade
			for (SwimmingLesson lessonData : swimmingLessons) {
				if (lessonData.getLessonGrade() == Integer.parseInt(grade)) {
					timetable.add(lessonData);
				}
			}
		}
		return timetable;
	}

	// filter timetable by grade
	public List<SwimmingLesson> getTimetableByCoach() {
		String username;
		// create list to store filter timetable
		List<SwimmingLesson> timetable = new ArrayList<>();
		// show coach details to selection
		swimmingCoach.coachDetails();
		System.out.println("Enter username to show timetable : ");
		username = userOption.nextLine().trim();
		if (username.isEmpty() || !swimmingCoach.coachUsernameExist(username)) {
			System.out.println("Error ! this username does not exist");
		} else {
			// filter timetable by coach
			for (SwimmingLesson lessonData : swimmingLessons) {
				if (lessonData.getCoachUsername().equalsIgnoreCase(username)) {
					timetable.add(lessonData);
				}
			}
		}
		return timetable;
	}

	// get lesson by id
	public SwimmingLesson getLessonByLessonId(String lessonId) {
		for (int id = 0; id < swimmingLessons.size(); id++) {
			if (swimmingLessons.get(id).getLessonId().equalsIgnoreCase(lessonId)) {
				return swimmingLessons.get(id);
			}

		}
		return null;
	}
	
	//update seat after booking lesson
	public void updateSeatAfterBookLesson(String lessonId) {
		//get current lesson capacity
		int currentCapacity=getLessonByLessonId(lessonId).getLessonCapacity();
		//update lesson capacity
		getLessonByLessonId(lessonId).setLessonCapacity(currentCapacity-1);
	}
	
	//update seat after cancel lesson
	public void updateSeatAfterCancelLesson(String lessonId) {
		int currentCapacity=getLessonByLessonId(lessonId).getLessonCapacity();
		//update lesson capacity
		getLessonByLessonId(lessonId).setLessonCapacity(currentCapacity+1);
	}
	
	//update seat after change lesson
	public void updateSeatAfterChangeLesson(String oldLessonId,String newLessonId) {
		//get old lesson current capacity
		int currentCapacity=getLessonByLessonId(oldLessonId).getLessonCapacity();
		//update lesson capacity
		getLessonByLessonId(oldLessonId).setLessonCapacity(currentCapacity+1);
		//get new lesson capacity
		int currentCapacity1=getLessonByLessonId(newLessonId).getLessonCapacity();
		//update lesson capacity
		getLessonByLessonId(newLessonId).setLessonCapacity(currentCapacity1-1);
	}

}
