package sharedobjects;

import java.io.Serializable;

public class Course implements Serializable
{
	/**
	 * The id for the course
	 */
	private int id;
	
	/**
	 * The id for the professor who will teach the course
	 */
	private int prof_id;
	
	/**
	 * The name of the course
	 */
	private String name;
	
	/**
	 * Decision for deciding whether to show if the class is active
	 */
	private Boolean active;
}
