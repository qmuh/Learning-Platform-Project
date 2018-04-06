package frontend.view.pages;

import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JPanel;

import frontend.interfaces.ColorPalette;
import frontend.interfaces.WondrisInfo;


public class NavigationBar extends JPanel implements NavigationBarButtons, WondrisInfo, ColorPalette
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton home;
	private JButton back;
	
	public NavigationBar()
	{
		setBackground(ACCENT_COLOR);
		home = new JButton("Home");
		back = new JButton("Back");
		home.setFont(TEXT_FONT);
		back.setFont(TEXT_FONT);
		this.addButtons();
	}
	
	private void addButtons()
	{
		this.add(back);
		this.add(home);
	}
	
	public void setBackButtonListener(ActionListener listener)
	{
		back.addActionListener(listener);
	}
	
	public void setHomeButtonListener(ActionListener listener)
	{
		home.addActionListener(listener);
	}
	
}
