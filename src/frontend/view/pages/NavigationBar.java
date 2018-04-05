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
	private JButton back;
	
	public NavigationBar()
	{
		home = new JButton("Home");
		courses = new JButton("Course");
		back = new JButton("Back");
		
		this.addButtons();
	}
	
	private void addButtons()
	{
		this.add(back);
		this.add(home);
		this.add(courses);
	}
	
	public void setBackButtonListener(ActionListener listener)
	{
		back.addActionListener(listener);
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
