package shared.objects;

import java.io.Serializable;
import java.util.Random;

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

	/**
	 * Used to add new courses to the system by the professor
	 */
	
	/** Constructor used to initialize courses from a pre-made text file
	 * @param courseID
	 * @param profID
	 * @param courseName
	 * @param isActive
	 */
	public Course(int courseID, int profID, String courseName, Boolean isActive)
	{
		id = courseID;
		prof_id = profID;
		name = courseName;
		active = isActive;
	}
	
	/** Used to create a course that is used by the professor
	 * @param profID
	 * @param courseName
	 * @param isActive
	 */
	public Course( int profID, String courseName, Boolean isActive)
	{
		Random random = new Random();
		id = random.nextInt(500) + 300;
		prof_id = profID;
		name = courseName;
		active = isActive;
	}
	
	public int getId()
	{
		return id;
	}

	public int getProf_id()
	{
		return prof_id;
	}

	public String getName()
	{
		return name;
	}

	public Boolean getActive()
	{
		return active;
	}

	public void setActive() 
	{
		if(active == true)
		{
			active = false;
		}
		else
			active = true;
	}
}
