package trainingcenter;


public class ManagerFactory {

    //Create a new instance of StaffManager.
    public static StaffManager createStaffManager() {
        return new StaffManager();
    }

    //Create a new instance of BookingManager.
    public static BookingManager createBookingManager() {
        return new BookingManager();
    }

    //Create a new instance of ReportGenerator.
    public static ReportGenerator createReportGenerator() {
        return new ReportGenerator();
    }

    //Create a new instance of SwimmingLesson.
    public static SwimmingLesson createSwimmingLesson() {
        return new SwimmingLesson();
    }

    //Create a new instance of SwimmingCoach.
    public static SwimmingCoach createSwimmingCoach() {
        return new SwimmingCoach();
    }

    //Create a new instance of TimetableManager.
    public static TimetableManager createTimetableManager() {
        return new TimetableManager();
    }

    // Create a new instance of SwimmingLearner.
    public static SwimmingLearner createSwimmingLearner() {
        return new SwimmingLearner();
    }

    //Create a new instance of ReviewManager.
    public static ReviewManager createReviewManager() {
        return new ReviewManager();
    }
}
