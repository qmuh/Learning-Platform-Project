package frontend.view.pages;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import frontend.view.ProfessorGUI;
import frontend.view.StudentGUI;
import frontend.view.pages.items.CourseItem;
import frontend.components.BoxList;

// TODO: T extends Box U ??
public abstract class Page <T extends Box, U> extends JPanel implements PageNames
{

	private static final long serialVersionUID = 1L;
	private Header header;
	private Footer footer;
	protected JPanel body;
	protected ArrayList<U> itemList;
	protected BoxList<T> itemDisplay;
//	protected StudentGUI studentGUI;
//	protected ProfessorGUI professorGUI;
	
	public Page()
	{
		header = new Header();
		footer = new Footer();
		this.add(header);
		this.add(footer);
	}
	
	public abstract void displayPage();
	
	public void setBoxList(BoxList<T> boxList)
	{
		this.itemDisplay = boxList;
	}
	
	public void setHomeButtonListener(ActionListener listener)
	{
		header.setHomeButtonListener(listener);
	}
	
	public void setCoursesButtonListener(ActionListener listener)
	{
		header.setCoursesButtonListener(listener);
	}

	public void addToBoxList(T item ) 
	{
		itemDisplay.add(item);
	}
	
//	public static void main(String[] args)
//	{
//		JFrame frame = new JFrame();
//		frame.setSize(GUIConstants.WINDOW_SIZE);
//		frame.add(new Page(new BoxList()));
//		frame.setVisible(true);
//	}
}
