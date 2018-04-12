package frontend.interfaces;

import java.awt.Font;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 * Used for info that is used to set up how everything looked
 */
public interface WondrisInfo
{
	/**
	 * The name of the whole application
	 */
	public static final String TITLE = "Wondris Learning Platform";

	/**
	 * Name made by group
	 */
	public static final String NAME = "Wondris";

	/**
	 * The title size font
	 */
	public static final int TITLE_FONT_SIZE = 50;

	/**
	 * The sub title size
	 */
	public static final int SUB_TITLE_FONT_SIZE = 30;

	/**
	 * The text size font
	 */
	public static final int TEXT_FONT_SIZE = 28;

	/**
	 * The title font setup
	 */
	public static final Font TITLE_FONT = new Font("Century Gothic", Font.PLAIN,
			TITLE_FONT_SIZE);

	/**
	 * The subtitle font setup
	 */
	public static final Font SUB_TITLE_FONT = new Font("Century Gothic",
			Font.BOLD, SUB_TITLE_FONT_SIZE);

	/**
	 * The text font setup
	 */
	public static final Font TEXT_FONT = new Font("Century Gothic", Font.PLAIN,
			TEXT_FONT_SIZE);

}
