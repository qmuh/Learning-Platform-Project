package frontend.view.pages.items;

import javax.swing.Box;
import javax.swing.BoxLayout;

import sharedobjects.Course;

public class CourseItem extends Box
{

	private Course course;
	public CourseItem(Course course)
	{
		super(BoxLayout.X_AXIS);
		this.course = course;
	}
}
