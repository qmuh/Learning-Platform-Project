package frontend.view.pages;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import frontend.ProfessorGUI;
import frontend.StudentGUI;
import frontend.components.BoxList;

// TODO: T extends Box U ??
public class Page extends JPanel 
{

	private static final long serialVersionUID = 1L;
	private Header header;
	private Footer footer;
	protected char type;
	protected ArrayList<?> itemList;
	protected BoxList<?> itemDisplay;
	protected StudentGUI studentGUI;
	protected ProfessorGUI professorGUI;
	
	public Page(BoxList<?> boxList)
	{
		header = new Header();
		footer = new Footer();
		this.add(header);
		this.add(footer);
		itemDisplay = boxList;
	}

	
//	public static void main(String[] args)
//	{
//		JFrame frame = new JFrame();
//		
//		frame.add(new Page(new BoxList()));
//		frame.setVisible(true);
//	}
}
