package frontend.view.pages;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import frontend.view.pages.items.CourseItem;
import sharedobjects.Course;

public class CoursePage extends Page<CourseItem, Course>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Course course;
	private CourseTable courseTable;
	
	public CoursePage()
	{
		super();
		courseTable = new CourseTable();
		body = courseTable;
		setName(COURSE_PAGE);
		this.add(body);
	}

	@Override
	public void displayPage()
	{
		((CourseTable) this.body).setBoxList(itemDisplay);
		//courseTable.setBoxList(itemDisplay);
		
		System.out.println("hello friends");
	}
	
	public void setNewCourseListener(ActionListener listener)
	{
		((CourseTable)body).setNewCourseListener(listener);
	}
}
