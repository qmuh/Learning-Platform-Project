package frontend.view.pages.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.interfaces.ColorPalette;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.GUIConstants;
import frontend.view.pages.NavigationBar;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class Header extends JPanel implements GUIConstants, ColorPalette, WondrisInfo
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NavigationBar navigationBar;

	public Header()
	{
		this.setLayout(new GridLayout(1, 3));
		this.setPreferredSize(new Dimension(WINDOW_WIDTH, 100));
		this.setBackground(ACCENT_COLOR);
		navigationBar = new NavigationBar();
		this.add(createTitle(NAME), 0);
		this.add(createTitle(""), 1);
		this.add(navigationBar, 2);
	}
	
	public JLabel createTitle(String title) {
		JLabel theTitle = new JLabel(title);
		theTitle.setFont(TITLE_FONT);
		theTitle.setForeground(Color.WHITE);
		return theTitle;
	}
	
	public void setTitle(String title) {
		this.remove(1);
		this.add(createTitle(title), 1);
	}
	
	public void setBackButtonListener(ActionListener listener)
	{
		navigationBar.setBackButtonListener(listener);
	}
	
	public void setHomeButtonListener(ActionListener listener)
	{
		navigationBar.setHomeButtonListener(listener);
	}
}
