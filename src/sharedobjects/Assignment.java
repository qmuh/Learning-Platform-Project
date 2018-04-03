package sharedobjects;
import java.io.Serializable;

public class Assignment implements Serializable
{
	
	/**
	 * The id for the assignment
	 */
	private int id;
	
	/**
	 * The id for the course which the assignment is assigned to
	 */
	private int course_id;
	
	/**
	 * The name of the assignment
	 */
	private String title;
	
	/**
	 * The pathway for the file
	 */
	private String path;
	
	/**
	 * Decides whether a student can see the assignment
	 */
	private Boolean active;
	
	/**
	 * The due date for the assignment
	 */
	private String due_date;
	
	
	
}
