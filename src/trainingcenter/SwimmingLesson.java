package trainingcenter;


public class SwimmingLesson {
	private String lessonId;
	private String lessonName;
	private String lessonDate;
	private String lessonTime;
	private String lessonDay;
	private int lessonGrade;
	private int lessonCapacity;
	private String coachUsername;
	
	
	public String getLessonId() {
		return lessonId;
	}

	public String getLessonName() {
		return lessonName;
	}

	public String getLessonDate() {
		return lessonDate;
	}

	public String getLessonTime() {
		return lessonTime;
	}

	public String getLessonDay() {
		return lessonDay;
	}

	public int getLessonGrade() {
		return lessonGrade;
	}

	public int getLessonCapacity() {
		return lessonCapacity;
	}

	public String getCoachUsername() {
		return coachUsername;
	}
	
	public void setLessonCapacity(int lessonCapacity) {
		this.lessonCapacity=lessonCapacity;
	}
	
	//create parameterized constructor to add lesson details in list
	public SwimmingLesson(String lessonId, String lessonName, String coachUsername,String lessonDay, String lessonTime, int lessonCapacity,
			int lessonGrade, String lessonDate ) {
		this.lessonId = lessonId;
		this.lessonName = lessonName;
		this.lessonDate = lessonDate;
		this.lessonTime = lessonTime;
		this.lessonDay = lessonDay;
		this.lessonGrade = lessonGrade;
		this.lessonCapacity = lessonCapacity;
		this.coachUsername = coachUsername;
	}
	
	//create default constructor
	public SwimmingLesson() {
	}
	
	
}
