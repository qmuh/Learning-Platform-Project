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

	/** 
	 * @param b
	 */
	public WButtonActivatable(boolean b)
	{
		this(ACTIVATE, DEACTIVATE, b);
	}

	public WButtonActivatable(String active, String inactive)
	{
		this(active, inactive, true);
	}

	public WButtonActivatable(String active, String inactive,
			boolean initialState)
	{
		this.active = active;
		this.inactive = inactive;
		this.isActive = initialState;
		this.setActive(initialState);
	}

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

	public void toggleActive()
	{
		setActive(!isActive);
	}
}
