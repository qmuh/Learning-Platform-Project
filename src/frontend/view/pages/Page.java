package frontend.view.pages;

import javax.swing.JFrame;
import javax.swing.JPanel;


class Page extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Header header;
	
	private Footer footer;
	
	public Page()
	{
		header = new Header();
		footer = new Footer();
		this.add(header);
		this.add(footer);
		
	}

	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		
		frame.add(new Page());
		frame.setVisible(true);
	}
}
