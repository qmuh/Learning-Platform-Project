package frontend.view.pages;

import javax.swing.JPanel;

class Header extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private NavigationBar navigationBar;
	
	public Header()
	{
		navigationBar = new NavigationBar();
		this.add(navigationBar);
	}
}
