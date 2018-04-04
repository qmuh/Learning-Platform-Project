package frontend.view.pages;

import java.util.Hashtable;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

class NavigationBar extends JPanel implements NavigationBarButtons
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Hashtable<Integer, JButton> navigationButtons;
	
	public NavigationBar()
	{
		navigationButtons = new Hashtable<Integer, JButton>();
		
		navigationButtons.put(NAVBAR_LOGO, new JButton("Home"));
		navigationButtons.put(NAVBAR_COURSE, new JButton("Course"));
		navigationButtons.put(NAVBAR_GRADE, new JButton("Grades"));
		navigationButtons.put(NAVBAR_SUBMISSION, new JButton("Submission"));
		navigationButtons.put(NAVBAR_ASSIGNMENT, new JButton("Assignment"));
		
		this.addButtons();
	}
	
	private void addButtons()
	{
		Set<Integer> keys = navigationButtons.keySet();
		
		for(Integer key: keys)
		{
			this.add(navigationButtons.get(key));
		}
	}
	
	public JButton getButton(String buttonName)
	{
		return navigationButtons.get(buttonName);
	}
}
