package frontend.view.pages.components;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.mysql.jdbc.Buffer;

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

	private WButton homeButton;
	private WButton backButton;
	private WButton gearButton;

	public NavigationBar()
	{
		homeButton = new WButton();
		backButton = new WButton();
		gearButton = new WButton();
		try
		{
			BufferedImage image = ImageIO.read(new File("home.png"));
			ImageIcon icon = new ImageIcon(image);
			homeButton.setIcon(icon);
			image = ImageIO.read(new File("back.png"));
			icon = new ImageIcon(image);
			backButton.setIcon(icon);
			image = ImageIO.read(new File("gear.png"));
			icon = new ImageIcon(image);
			gearButton.setIcon(icon);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		this.setLayout(new GridLayout(1, 2));
		this.addButtons();
	}

	private void addButtons()
	{
		this.add(createHomeBackPanel());
		this.add(createGearPanel());
	}
	
	private JPanel createHomeBackPanel()
	{
		JPanel thePanel = new JPanel();
		thePanel.setBackground(ACCENT_COLOR);
		thePanel.add(backButton);
		thePanel.add(homeButton);
		return thePanel;
	}

	private JPanel createGearPanel()
	{
		JPanel thePanel = new JPanel(new BorderLayout());
		thePanel.setBackground(ACCENT_COLOR);
		JPanel gearPanel = new JPanel();
		gearPanel.setBackground(ACCENT_COLOR);
		gearPanel.add(gearButton);
		thePanel.add(gearPanel, BorderLayout.EAST);
		return thePanel;
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
