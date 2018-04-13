package frontend.view.pages.components.customSwing;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import frontend.interfaces.WondrisInfo;

/**
 * Extends from label and is used instead of labels here
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class WLabel extends JLabel implements WondrisInfo
{

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/** The WLabel constructor
	 * @param text
	 * @param alignment
	 */
	public WLabel(String text, int alignment)
	{
		this.setFont(TEXT_FONT);
		this.setForeground(Color.BLACK);
	}

	public WLabel(String text, Font font, Color color, int alignment)
	{
		super(text, alignment);
		this.setFont(font);
		this.setForeground(color);
	}

	public WLabel(String text)
	{
		this(text, TEXT_FONT);
	}

	public WLabel()
	{
		this("");
	}

	public WLabel(String text, Font font)
	{
		this(text, font, Color.BLACK, CENTER);
	}

	public WLabel(String text, Color color)
	{
		this(text, TEXT_FONT, color, CENTER);
	}

	public WLabel(String text, Font font, int right)
	{
		this(text, font, Color.BLACK, right);
	}
	
	public WLabel(BufferedImage read)
	{
		this();
		ImageIcon icon = new ImageIcon(read);
		this.setIcon(icon);
	}
}
