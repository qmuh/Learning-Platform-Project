package frontend.view.pages.course;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import frontend.view.pages.Page;
import frontend.view.pages.components.CourseNavigationBar;
import frontend.view.pages.components.PageNavigator;
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
		body = new JPanel();
		body.setLayout(new BorderLayout());
		setPageTitle("Welcome to " + course.getName());
		add(body);
	}

	public void setCourseNavigationBar(CourseNavigationBar courseNavigationBar)
	{
		this.courseNavigationBar = courseNavigationBar;
		body.add(courseNavigationBar, BorderLayout.EAST);
		System.out.println("STUFF");
	}

	protected void setPageTitle(String s)
	{
		bodyCenter = new JPanel(new BorderLayout());
		bodyCenter.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		JPanel theTitle = new JPanel(new GridLayout(1, 1));
		JLabel title = new JLabel(s);
		title.setFont(TITLE_FONT);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		theTitle.add(title);
		theTitle.setBorder(
				BorderFactory.createMatteBorder(0, 0, 2, 0, BACKGROUND_COLOUR));
		bodyCenter.add(theTitle, BorderLayout.NORTH);
		body.add(bodyCenter, BorderLayout.CENTER);
	}

	public void createSidebarListeners(Course course,
			PageNavigator pageNavigator)
	{
		courseNavigationBar.createListeners(course, pageNavigator);
	}

	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub

	}
}
