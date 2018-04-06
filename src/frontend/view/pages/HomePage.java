package frontend.view.pages;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import frontend.view.pages.items.CourseItem;
import sharedobjects.Course;

public class HomePage extends Page<CourseItem, Course>
{
	private static final long serialVersionUID = 1L;

	public HomePage()
	{
		super();
		this.header.setTitle("Home");
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

	public static void main(String[] args) {
		JFrame frame = new JFrame("Testing");
		frame.setSize(1600, 1000);
		frame.add(new HomePage());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
