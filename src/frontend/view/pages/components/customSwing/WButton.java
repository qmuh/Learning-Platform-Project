package frontend.view.pages.components.customSwing;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.plaf.ButtonUI;

import frontend.interfaces.ColourPalette;
import frontend.interfaces.WondrisInfo;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class WButton extends JButton implements WondrisInfo, ColourPalette
{
	/**
	 * The version of the class.
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	public WButton()
	{
		super();
		defaultSettings();
	}

	public WButton(String string)
	{
		super(string);
		defaultSettings();
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
	/**
	 * Returns the unique identification number of this button.
	 * 
	 * @return
	 */
	public int getId()
	{
		return id;
	}
}
