package frontend.interfaces;

import java.awt.Color;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018 Sets up the color choice for the application
 */
public interface ColourPalette
{
	/**
	 * The chosen background color
	 */
	public static final Color BACKGROUND_COLOUR = new Color(137, 207, 240);

	/**
	 * The chosen secondary color
	 */
	public static final Color SECONDARY_COLOR = new Color(129, 149, 248);

	/**
	 * The chosen tertiary color
	 */
	public static final Color TERTIARY_COLOR = new Color(113, 157, 217);

	/**
	 * Chosen color for contrast to work with everything
	 */
	public static final Color CONTRAST_COLOR = new Color(217, 180, 0);

	/**
	 * The chosen accent color
	 */
	public static final Color ACCENT_COLOR = new Color(50, 50, 127);
}
