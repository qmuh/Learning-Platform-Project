package frontend.view.pages.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisDirectories;
import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WLabel;
import frontend.view.pages.interfaces.GUIConstants;
import shared.objects.User;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class Header extends JPanel
		implements GUIConstants, ColourPalette, WondrisInfo, WondrisDirectories
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NavigationBar navigationBar;
	private JPanel headerTitle;
	private WLabel logo;

	public Header()
	{
		this.setLayout(new GridLayout(1, 2));
		this.setPreferredSize(new Dimension(WINDOW_WIDTH, 100));
		navigationBar = new NavigationBar();
		this.add(createHeaderTitle(), 0);
		this.add(navigationBar, 1);
	}

	private JPanel createHeaderTitle()
	{
		headerTitle = new JPanel(new GridLayout(1, 2));
		headerTitle.setBackground(ACCENT_COLOR);
		headerTitle.add(createTheLogo(), 0);
		headerTitle.add(createTitle(""), 1);
		return headerTitle;
	}
	
	private WLabel createTheLogo()
	{
		try {
			logo = new WLabel(ImageIO.read(new File(DIR_GRAPHICS + "wondrisLogoWhiteSmall.png")));
			return logo;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public WLabel getLogo()
	{
		return logo;
	}
	
	public JLabel createTitle(String title)
	{
		JLabel theTitle = new JLabel(title);
		theTitle.setFont(TITLE_FONT);
		theTitle.setForeground(Color.WHITE);
		return theTitle;
	}

	public void setTitle(String title)
	{
		headerTitle.remove(1);
		headerTitle.add(createTitle(title), 1);
	}
	
	public void setNamePanel(User user)
	{
		navigationBar.setNamePanel(user);
	}

	public WButton getHomeButton()
	{
		return navigationBar.getHomeButton();
	}

	public WButton getBackButton()
	{
		return navigationBar.getBackButton();
	}

	public WButton getGearButton()
	{
		return navigationBar.getGearButton();
	}
}
