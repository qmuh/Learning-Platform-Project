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
import frontend.components.BoxList;

// TODO: T extends Box U ??
public class Page <T extends Box, U> extends JPanel 
{

	private static final long serialVersionUID = 1L;
	private Header header;
	private Footer footer;
	protected char type;
	protected ArrayList<U> itemList;
	protected BoxList<T> itemDisplay;
	protected StudentGUI studentGUI;
	protected ProfessorGUI professorGUI;
	
	public Page(BoxList<T> boxList)
	{
		header = new Header();
		footer = new Footer();
		this.add(header);
		this.add(footer);
		itemDisplay = boxList;
	}
	
	public void setHomeButtonListener(ActionListener listener)
	{
		header.setHomeButtonListener(listener);
	}
	
	public void setCoursesButtonListener(ActionListener listener)
	{
		header.setCoursesButtonListener(listener);
	}
	
	
//	public static void main(String[] args)
//	{
//		JFrame frame = new JFrame();
//		frame.setSize(GUIConstants.WINDOW_SIZE);
//		frame.add(new Page(new BoxList()));
//		frame.setVisible(true);
//	}
}
