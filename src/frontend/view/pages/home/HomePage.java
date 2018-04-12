package frontend.view.pages.home;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import frontend.view.pages.Page;
import frontend.view.pages.items.course.CourseItem;
import shared.objects.Course;
import shared.objects.User;

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
	private HomePageCoursesTable homePageCoursesTable;

	public HomePage(User user)
	{
		super(user);
		this.header.setTitle("Home");
		this.setName(HOME_PAGE);
		homePageCoursesTable = new HomePageCoursesTable();
		this.body = homePageCoursesTable;
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

	public void enableActiveLabel()
	{
		homePageCoursesTable.enableActiveLabel();
	}
}
