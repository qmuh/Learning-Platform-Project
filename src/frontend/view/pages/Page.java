package frontend.view.pages;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.view.pages.components.BoxList;
import frontend.view.pages.components.Footer;

public abstract class Page <T extends Box, U> extends JPanel implements PageNames
{

	private static final long serialVersionUID = 1L;
	protected Header header;
	private Footer footer;
	protected JPanel body;
	protected ArrayList<U> itemList;
	protected BoxList<T> itemDisplay;
//	protected StudentGUI studentGUI;
//	protected ProfessorGUI professorGUI;
	
	public Page()
	{
		this.setLayout(new BorderLayout());
		itemDisplay = new BoxList<T>();
		body = new JPanel();
		header = new Header();
		footer = new Footer();
		this.add(header, BorderLayout.NORTH);
		this.add(footer, BorderLayout.SOUTH);
		this.add(body, BorderLayout.CENTER);
	}
	
	public abstract void displayPage();
	
	protected JPanel createLabel(String label, Font f)
	{
		JPanel labelPanel = new JPanel();
		JLabel theLabel = new JLabel(label);
		theLabel.setFont(f);
		labelPanel.add(theLabel);
		return labelPanel;
	}
	
	protected JButton createButton(JButton button, String name, Font f)
	{
		button.setText(name);
		button.setFont(f);
		return button;
	}
	
	public void setBoxList(BoxList<T> boxList)
	{
		this.itemDisplay = boxList;
	}
	
	public void setHomeButtonListener(ActionListener listener)
	{
		header.setHomeButtonListener(listener);
	}
	
	public void setBackButtonListener(ActionListener listener)
	{
		header.setBackButtonListener(listener);
	}

	public void addToBoxList(T item ) 
	{
		itemDisplay.add(item);
	}
}
