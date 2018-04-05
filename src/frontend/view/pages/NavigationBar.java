package frontend.view.pages;

import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Set;

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
	
	public NavigationBar(Hashtable<Integer, JButton> buttons)
	{
		home = new JButton("Home");
		courses = new JButton("Course");
		
		buttons.put(NAVBAR_LOGO, home);
		buttons.put(NAVBAR_COURSE, courses);
		
		this.addButtons();
	}
	
	private void addButtons()
	{
		this.add(home);
		this.add(courses);
	}
	
	public JButton getButton(Integer buttonName)
	{
		return navigationButtons.get(buttonName);
	}
	
	public void setListener(Integer buttonKey, ActionListener listener)
	{
		((JButton)navigationButtons.get(buttonKey)).addActionListener(listener);
	}
}
