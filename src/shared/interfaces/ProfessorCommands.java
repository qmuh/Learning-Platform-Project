package shared.interfaces;

/**
 * An interface that stores all available professor commands.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 7, 2018
 */
public interface ProfessorCommands extends UserCommands
{
	public static final String RECEIVE_COURSES = "COURSES";

	public static final String RECEIVE_STUDENT_BY_ID = "STUDENTBYID";

	public static final String RECEIVE_STUDENT_BY_LASTNAME = "STUDENTBYLAST";

	public static final String RECEIVE_ALL_STUDENTS = "ALLSTUDENTS";

	public static final String RECEIVE_ALL_ENROLLED_STUDENTS = "ALLENROLLED";

	public static final String RECEIVE_ALL_ASSIGNMENTS = "ALLASSIGNMENTS";
	
	public static final String RECEIVE_STUDENT_IS_ENROLLED = "STUDENTISENROLLED";
	
	public static final String RECEIVE_STUDENT_ASSIGNMENT = "STUDENTASSIGNMENT";

	public static final String INSERT_COURSE = "COURSE";

	public static final String INSERT_ENROLLMENT = "ENROLL";

	public static final String INSERT_UNENROLLMENT = "UNENROLL";

	public static final String INSERT_ASSIGNMENT = "ASSIGNMENT";

	public static final String INSERT_GRADE = "GRADE";
	
	public static final String MODIFY_COURSE_ACTIVE = "COURSEACTIVE";

	public static final String MODIFY_COURSE_INACTIVE = "COURSEINACTIVE";

	public static final String MODIFY_ASSIGNMENT_ACTIVE = "ASSIGNACTIVE";

	public static final String MODIFY_ASSIGNMENT_INACTIVE = "ASSIGNINACTIVE";
}
