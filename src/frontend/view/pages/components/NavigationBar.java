package frontend.view.pages.components;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.customSwing.WButton;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class NavigationBar extends JPanel implements WondrisInfo, ColourPalette
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WButton home;
	private WButton back;

	public NavigationBar()
	{
		setBackground(ACCENT_COLOR);
		home = new WButton("Home");
		back = new WButton("Back");
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
