package frontend.view.pages;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import frontend.interfaces.Colours;

public class Header extends JPanel implements GUIConstants, Colours
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NavigationBar navigationBar;

	public Header()
	{
		this.setPreferredSize(new Dimension(WINDOW_WIDTH, 100));
		this.setBackground(ACCENT_COLOR);
		navigationBar = new NavigationBar();
		this.add(navigationBar);
	}
	
	public void setBackButtonListener(ActionListener listener)
	{
		navigationBar.setBackButtonListener(listener);
	}
	
	public void setHomeButtonListener(ActionListener listener)
	{
		navigationBar.setHomeButtonListener(listener);
	}
	
	public void setCoursesButtonListener(ActionListener listener)
	{
		navigationBar.setCoursesButtonListener(listener);
	}
}
