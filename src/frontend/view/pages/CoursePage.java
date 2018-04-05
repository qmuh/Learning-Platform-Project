package frontend.view.pages;

import javax.swing.JLabel;

import frontend.components.BoxList;
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
		setName(COURSE_PAGE);
		this.add(new JLabel("Jimmy finally started to code"));
	}
}
