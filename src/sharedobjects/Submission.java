package sharedobjects;

import java.io.Serializable;

public class Submission implements Serializable
{
	/**
	 * The id for the submission
	 */
	private int id;
	
	/**
	 * The id for the assignment which the submission this is attributed with
	 */
	private int assign_id;
	
	/**
	 * The id for the student who submits the file
	 */
	private int student_id;
	
	/**
	 * Pathway from where the file comes from 
	 */
	private String path;
	
	/**
	 * The grade for the submission
	 */
	private int grade;
	
	/**
	 * Comment made by the submitter/marker?
	 */
	private String comment;
	
	/**
	 * Title of the file in the submission
	 */
	private String title;
	
	/**
	 * The time stamo for the file submission
	 */
	private String timestamp;
}
