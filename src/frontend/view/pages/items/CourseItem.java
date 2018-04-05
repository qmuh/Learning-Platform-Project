package frontend.view.pages.items;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import sharedobjects.Course;

public class CourseItem extends Box
{

	private Course course;
	public CourseItem(Course course)
	{
		super(BoxLayout.X_AXIS);
		this.course = course;
		
		this.add(new JLabel(course.getName()));
		this.add(new JButton("View"));
		
	}
}
