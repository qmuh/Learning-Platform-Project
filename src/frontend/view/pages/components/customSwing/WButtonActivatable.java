package frontend.view.pages.components.customSwing;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1
 * @since April 11, 2018
 */
public class WButtonActivatable extends WButton
{
	private static final long serialVersionUID = 1L;

	public static final String ACTIVATE = "Activate";

	public static final String DEACTIVATE = "Deactivate";

	private boolean isActive;
	
	private String active;
	private String inactive;

	public WButtonActivatable()
	{
		this(true);
	}
	
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
