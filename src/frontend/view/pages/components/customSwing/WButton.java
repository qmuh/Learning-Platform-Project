package frontend.view.pages.components.customSwing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisInfo;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 2
 * @since April 6, 2018
 */
public class WButton extends JButton implements WondrisInfo, ColourPalette
{
	/**
	 * The version of the class.
	 */
	private static final long serialVersionUID = 2L;

	public WButton()
	{
		super();
		defaultSettings();
	}

	public WButton(String text)
	{
		super(text);
		defaultSettings();
	}

	public WButton(BufferedImage read)
	{
		this();
		ImageIcon icon = new ImageIcon(read);
		this.setIcon(icon);
	}

	private void defaultSettings()
	{
		this.setFont(TEXT_FONT);
		this.setFocusPainted(false);
		this.setOpaque(true);
		this.setBackground(SECONDARY_COLOR);
	}

	@Override
	public void setEnabled(boolean b)
	{
		super.setEnabled(b);
		this.setContentAreaFilled(b);
	}

}
