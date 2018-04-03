package sharedobjects;

import java.io.Serializable;

public class StudentEnrollment implements Serializable
{
	
	/**
	 * The identification for enrolling a student to a class
	 */
	private int id;

	/**
	 * The id for the student who is enrolling
	 */
	private int student_id;
	
	/**
	 * The id for the course which the student is being enrolled into
	 */
	private int course_id;
	
	/**
	 * No idea ?????
	 */
	private Boolean enrolling;
}
