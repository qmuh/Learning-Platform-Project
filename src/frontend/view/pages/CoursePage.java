package frontend.view.pages;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import frontend.view.pages.items.CourseItem;
import sharedobjects.Course;

public class CoursePage extends Page<CourseItem, Course>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Course course;
	
	public CoursePage()
	{
		super();
		this.body = new CourseTable();
		this.add(body);
		setName(COURSE_PAGE);
	}

	@Override
	public void displayPage()
	{
		((CourseTable) this.body).setBoxList(itemDisplay);
	}
	
	public void setNewCourseListener(ActionListener listener)
	{
		((CourseTable)body).setNewCourseListener(listener);
	}
}
