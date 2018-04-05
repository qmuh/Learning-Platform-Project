package frontend.view.pages;

import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JPanel;

import frontend.interfaces.Colours;

public class Header extends JPanel implements GUIConstants, Colours
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private NavigationBar navigationBar;
	
	public Header(Hashtable<Integer, JButton> buttons)
	{
		this.setPreferredSize(new Dimension(WINDOW_WIDTH, 100));
		this.setBackground(ACCENT_COLOR);
		navigationBar = new NavigationBar(buttons);
		this.add(navigationBar);
	}
}
