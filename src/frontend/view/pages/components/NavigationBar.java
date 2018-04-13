package frontend.view.pages.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisDirectories;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WLabel;
import shared.interfaces.UserInfo;
import shared.objects.User;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class NavigationBar extends JPanel
		implements WondrisInfo, ColourPalette, WondrisDirectories, UserInfo
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WButton homeButton;
	private WButton backButton;
	private WButton gearButton;
	private JPanel namePanel;

	public NavigationBar()
	{
		try
		{
			homeButton = new WButton(
					ImageIO.read(new File(DIR_GRAPHICS + "home.png")));
			backButton = new WButton(
					ImageIO.read(new File(DIR_GRAPHICS + "back.png")));
			gearButton = new WButton(
					ImageIO.read(new File(DIR_GRAPHICS + "gear.png")));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		this.add(createHomeBackPanel(), BorderLayout.WEST);
		this.add(createNamePanel(), BorderLayout.CENTER);
		this.add(createGearPanel(), BorderLayout.EAST);
	}

	private JPanel createNamePanel()
	{
		namePanel = new JPanel();
		namePanel.setBackground(ACCENT_COLOR);
		namePanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
		return namePanel;
	}
	
	private JPanel createHomeBackPanel()
	{
		JPanel thePanel = new JPanel();
		thePanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
		thePanel.setBackground(ACCENT_COLOR);
		thePanel.add(backButton);
		thePanel.add(homeButton);
		return thePanel;
	}

	private JPanel createGearPanel()
	{
		JPanel thePanel = new JPanel(new BorderLayout());
		thePanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 10));
		thePanel.setBackground(ACCENT_COLOR);
		JPanel gearPanel = new JPanel();
		gearPanel.setBackground(ACCENT_COLOR);
		gearPanel.add(gearButton);
		thePanel.add(gearPanel, BorderLayout.EAST);
		return thePanel;
	}
	
	public void setNamePanel(User user)
	{
		String userType = null;
		if (user.getUserType().equals(USER_PROFESSOR))
		{
			userType = USER_PROFESSOR_TITLE;
		} else if (user.getUserType().equals(USER_STUDENT))
		{
			userType = USER_STUDENT_TITLE;
		}
		userType += " " + user.getFirstName() + " " + user.getLastName();
		WLabel name = new WLabel(userType, Color.WHITE);
		namePanel.add(name);
	}

	public WButton getHomeButton()
	{
		return homeButton;
	}

	public WButton getBackButton()
	{
		return backButton;
	}

	public WButton getGearButton()
	{
		return gearButton;
	}
}
