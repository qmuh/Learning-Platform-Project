package shared.objects;

import java.io.Serializable;
import java.util.Random;

/**
 * Provides a class to represent an assignment object.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
public class Assignment implements Serializable
{

	/**
	 * The version of this class.
	 */
	private static final long serialVersionUID = 1L;

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
	 * Used to reconstruct an assignment object from the database.
	 * 
	 * @param courseID
	 *            the identification number of the course
	 * @param assignTitle
	 *            the assignment title
	 * @param pathway
	 *            the file path for the assignment stored on the server
	 * @param isActive
	 *            whether the assignment is active
	 * @param due
	 *            the due date of the assignment
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

	/**
	 * Used by a professor to construct an assignment object with a randomized
	 * assignment id. Assignments constructed by this constructor should be sent
	 * to the database to be stored.
	 * 
	 * @param courseID
	 *            the identification number of the course
	 * @param assignTitle
	 *            the title of the assignment
	 * @param pathway
	 *            the path to store the assignment
	 * @param isActive
	 *            whether the assignment is active
	 * @param due
	 *            the due date of the assignment
	 */
	public Assignment(int courseID, String assignTitle, String pathway,
			Boolean isActive, String due)
	{
		Random random = new Random();
		id = random.nextInt(5000) + 2500;
		course_id = courseID;

		for (int i = 0; i < assignTitle.length(); i++)
		{
			if (assignTitle.charAt(i) == '.')
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
		String tosend = "";
		for (int i = path.length() - 1; i >= 0; i--)
		{
			if (path.charAt(i) == '/')
			{
				tosend = path.substring(0, i);
				break;
			}
		}

		return tosend;
	}

}
