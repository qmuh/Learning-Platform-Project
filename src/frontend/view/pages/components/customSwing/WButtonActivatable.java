package frontend.view.pages.components.customSwing;

/** THe button that shows whether something is active or not
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1
 * @since April 11, 2018
 */
public class WButtonActivatable extends WButton
{
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Used for activate
	 */
	public static final String ACTIVATE = "Activate";

	/**
	 * Used for deactivate
	 */
	public static final String DEACTIVATE = "Deactivate";

	/**
	 * Checks if something is active or not
	 */
	private boolean isActive;

	/**
	 * The active string
	 */
	private String active;
	
	/**
	 * The inactive string
	 */
	private String inactive;

	/**
	 * The constructor for the WButtongActivatable
	 */
	public WButtonActivatable()
	{
		this(true);
	}

	/** Sets the button as active or inactive
	 * @param b
	 */
	public WButtonActivatable(boolean b)
	{
		this(ACTIVATE, DEACTIVATE, b);
	}

	/** Sets the constructor with both strings
	 * @param active The active string
	 * @param inactive The inactive string
	 */
	public WButtonActivatable(String active, String inactive)
	{
		this(active, inactive, true);
	}

	/** Sets the constructor with strings and a boolean
	 * @param active The active string 
	 * @param inactive The inactive string
	 * @param initialState The state , active or inactive
	 */
	public WButtonActivatable(String active, String inactive,
			boolean initialState)
	{
		this.active = active;
		this.inactive = inactive;
		this.isActive = initialState;
		this.setActive(initialState);
	}

	/** Sets the active parameter
	 * @param b The boolean to set
	 */
	public void setActive(boolean b)
	{
		isActive = b;
		if (b)
		{
			this.setText(inactive);
			this.setBackground(CONTRAST_COLOR);
		} else
		{
			this.setText(active);
			this.setBackground(BACKGROUND_COLOUR);
		}
	}

	/**
	 * Makes the active or inactive the opposite
	 */
	public void toggleActive()
	{
		setActive(!isActive);
	}
}
