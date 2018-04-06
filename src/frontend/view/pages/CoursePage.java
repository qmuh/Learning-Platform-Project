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

	public CoursePage(String courseName)
	{
		super();
		this.setName(COURSE_PAGE);
		this.header.setTitle(courseName);
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
		frame.add(new CoursePage("The Course"));
		frame.setSize(1600, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
}
