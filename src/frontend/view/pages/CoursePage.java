package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JPanel;

import shared.objects.Course;

public class CoursePage<T extends Box, U> extends Page<T, U>
{

	private static final long serialVersionUID = 1L;

	protected Course course;
	private CourseNavigationBar courseNavigationBar;

	public CoursePage(Course course)
	{
		super();

		this.course = course;
		this.setName(COURSE_PAGE + course.getId());
		this.header.setTitle(course.getName());

		courseNavigationBar = new CourseNavigationBar();
		body = new JPanel();
		body.setLayout(new BorderLayout());
		body.add(courseNavigationBar, BorderLayout.EAST);
		add(body);
	}

	public void setEnrollmentButtonListener(ActionListener listener)
	{
		courseNavigationBar.setEnrollmentButtonListener(listener);
	}

	public void setAssignmentButtonListener(ActionListener listener)
	{
		courseNavigationBar.setAssignmentButtonListener(listener);
	}

	public void setGradesButtonListener(ActionListener listener)
	{
		courseNavigationBar.setGradesButtonListener(listener);
	}

	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub

	}
}
