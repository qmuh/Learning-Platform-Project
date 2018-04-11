package frontend.view.pages.components.customSwing;

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
