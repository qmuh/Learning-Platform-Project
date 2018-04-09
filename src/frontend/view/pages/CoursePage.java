package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shared.objects.Course;

public class CoursePage<T extends Box, U> extends Page<T, U>
{

	private static final long serialVersionUID = 1L;

	protected Course course;
	protected JPanel bodyCenter;
	private CourseNavigationBar courseNavigationBar;

	public CoursePage(Course course)
	{
		super();
		this.course = course;
		this.setName(COURSE_PAGE + course.getId());
		this.header.setTitle(course.getName());
		bodyCenter = new JPanel();
		courseNavigationBar = new CourseNavigationBar();
		body = new JPanel();
		body.setLayout(new BorderLayout());
		setPageTitle("Welcome to " + course.getName());
		body.add(courseNavigationBar, BorderLayout.EAST);
		add(body);
	}

	protected void setPageTitle(String s)
	{
		bodyCenter = new JPanel(new BorderLayout());
		JPanel theTitle = new JPanel(new GridLayout(1, 1));
		JLabel title = new JLabel(s);
		title.setFont(TITLE_FONT);
		title.setHorizontalAlignment(JLabel.LEFT);
		theTitle.add(title);
		theTitle.setBorder(
				BorderFactory.createMatteBorder(0, 0, 2, 0, BACKGROUND_COLOUR));
		bodyCenter.add(theTitle, BorderLayout.NORTH);
		body.add(bodyCenter, BorderLayout.CENTER);
	}

	public void setEnrollmentButtonListener(ActionListener listener)
	{
		courseNavigationBar.setEnrollmentButtonListener(listener);
	}

	public void setAssignmentButtonListener(ActionListener listener)
	{
		courseNavigationBar.setAssignmentButtonListener(listener);
	}

	public void setSubmissionButtonListener(ActionListener listener)
	{
		courseNavigationBar.setSubmissionButtonListener(listener);
	}

	public void setMyEmailButtonListener(ActionListener listener)
	{
		courseNavigationBar.setMyEmaillButtonListener(listener);
	}

	public void setComposeEmailButtonListener(ActionListener listener)
	{
		courseNavigationBar.setComposeEmailButtonListener(listener);
	}

	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub

	}
}
