package frontend.view.pages;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import frontend.view.pages.items.CourseItem;
import shared.objects.Course;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class HomePage extends Page<CourseItem, Course>
{
	private static final long serialVersionUID = 1L;

	public HomePage()
	{
		super();
		this.header.setTitle("Home");
		setName(HOME_PAGE);
		body = new HomePageCoursesTable();
		this.add(body);
	}

	@Override
	public void displayPage()
	{
		((HomePageCoursesTable) this.body).setBoxList(itemDisplay);
	}

	public void setNewCourseListener(ActionListener listener)
	{
		((HomePageCoursesTable) body).setNewCourseListener(listener);
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Testing");
		frame.setSize(1600, 1000);
		frame.add(new HomePage());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
