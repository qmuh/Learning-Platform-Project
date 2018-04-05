package frontend.view.pages;

import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JPanel;


public class NavigationBar extends JPanel implements NavigationBarButtons
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton home;
	private JButton courses;
	
	public NavigationBar()
	{
		home = new JButton("Home");
		courses = new JButton("Course");
		
		this.addButtons();
	}
	
	private void addButtons()
	{
		this.add(home);
		this.add(courses);
	}
	
	public void setHomeButtonListener(ActionListener listener)
	{
		home.addActionListener(listener);
	}
	
	public void setCoursesButtonListener(ActionListener listener)
	{
		courses.addActionListener(listener);
	}
	
}
