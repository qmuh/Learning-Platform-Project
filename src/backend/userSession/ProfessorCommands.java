package backend.userSession;

public interface ProfessorCommands extends UserCommands
{
	public static final String RETRIEVE_COURSES = "COURSES";

	public static final String RETRIEVE_STUDENT_BY_ID = "STUDENTBYID";

	public static final String RETRIEVE_STUDENT_BY_LASTNAME = "STUDENTBYLAST";

	public static final String RETRIEVE_ALL_STUDENTS = "ALLSTUDENTS";

	public static final String RETRIEVE_ALL_ENROLLED_STUDENTS = "ALLENROLLED";

	public static final String INSERT_COURSE = "COURSE";
	
	public static final String INSERT_ENROLLMENT = "ENROLL";
	
	public static final String INSERT_UNENROLLMENT = "UNENROLL";
	
	public static final String INSERT_ASSIGNMENT = "ASSIGNMENT";
}
