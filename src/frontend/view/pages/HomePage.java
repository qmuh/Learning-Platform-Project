package frontend.view.pages;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import frontend.view.pages.components.BoxList;
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
		setName(HOME_PAGE);
		body = new CoursePageTable();
		this.add(body);
	}

	@Override
	public void displayPage()
	{
		((CoursePageTable) this.body).setBoxList(itemDisplay);
	}

	public void setNewCourseListener(ActionListener listener)
	{
		((CoursePageTable) body).setNewCourseListener(listener);
	}

}
