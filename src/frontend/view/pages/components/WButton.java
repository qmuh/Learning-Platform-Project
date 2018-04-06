package frontend.view.pages.components;

import javax.swing.JButton;

public class WButton extends JButton
{
	/**
	 * The version of the class.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The identification number of the button used for identification.
	 * @see HasButtons interface.
	 */
	private static int WBUTTON_ID = 0;
	
	private int id;
	
	public WButton()
	{
		super();
		id = WBUTTON_ID++;
	}

	public WButton(String string)
	{
		super(string);
		id = WBUTTON_ID++;
	}
	
	/**
	 * Returns the unique identification number of this button.
	 * @return
	 */
	public int getId()
	{
		return id;
	}
}
