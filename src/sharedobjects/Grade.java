package sharedobjects;

import java.io.Serializable;

public class Grade implements Serializable
{

	/**
	 * The id for the student who holds this grade
	 */
	private int student_id;
	
	/**
	 * The rounded value of the string
	 */
	private int grade;
	
	/**
	 * The name of the student who holds this grade
	 */
	private String student_name;
	
	/**
	 * Name for the assignment which this grade is attributed too
	 */
	private String assign_name;
}
