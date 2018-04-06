package frontend.view.pages;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import frontend.view.pages.items.CourseItem;
import sharedobjects.Course;

@SuppressWarnings("rawtypes")
public class CoursePage extends Page 
{

	private static final long serialVersionUID = 1L;
	
	private Course course;
	
	public CoursePage(Course course)
	{
		super();
		this.course = course;
		this.setName(COURSE_PAGE + course.getId());
		this.header.setTitle(course.getName());
		
		CourseNavigationBar bar = new CourseNavigationBar();
		body = new JPanel();
		body.setLayout(new BorderLayout());
		body.add(bar, BorderLayout.EAST);
		add(body);
	}
	
	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Testing");
		frame.add(new CoursePage(new Course(3, "ENGG 233", true)));
		frame.setSize(1600, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
}
