package shared.objects;

import java.io.Serializable;
import java.util.Random;

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

	/**
	 * Constructor for assignments, used by the professor, they dont set ID
	 * 
	 * @param courseID
	 * @param assignTitle
	 * @param pathway
	 * @param isActive
	 * @param due
	 */
	public Assignment(int assignID, int courseID, String assignTitle,
			String pathway, Boolean isActive, String due)
	{
		id = assignID;
		course_id = courseID;
		title = assignTitle;
		path = pathway;
		active = isActive;
		due_date = due;
	}

	public Assignment(int courseID, String assignTitle, String pathway,
			Boolean isActive, String due)
	{
		Random random = new Random();
		id = random.nextInt(5000) + 2500;
		course_id = courseID;
		
		for (int i = 0; i < assignTitle.length(); i++)
		{
			if(assignTitle.charAt(i) == '.')
			{
				assignTitle = assignTitle.substring(0, i);
				break;
			}
		}
		
		title = assignTitle;
		path = pathway;
		active = isActive;
		due_date = due;
	}

	public void setActive()
	{
		if (active)
			active = false;
		else
			active = true;
	}

	public int getId()
	{
		return id;
	}

	public int getCourse_id()
	{
		return course_id;
	}

	public String getTitle()
	{
		return title;
	}

	public String getPath()
	{
		return path;
	}

	public Boolean getActive()
	{
		return active;
	}

	public String getDueDate()
	{
		return due_date;
	}

	public void setPath(String string)
	{
		path = string;
		
	}

	public String getDir()
	{
		String path ="";
		for (int i = 0; i < path.length(); i++)
		{
			
		}
	}

}
