package frontend.view.pages.items;

import javax.swing.Box;

public abstract class GeneralItem extends Box
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GeneralItem(int axis, String name)
	{
		super(axis);
		
		this.setName(name);
	}
	
	public abstract int getId();

}
