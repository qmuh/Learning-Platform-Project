package frontend.components;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JPanel;

public class BoxList <T extends Box>
{

	private JPanel scrollBox;
	
	public BoxList()
	{
		scrollBox = new JPanel();
	}
	
	public void addItem(T boxItem) 
	{
		
	}
	
	public void setItems(ArrayList<T> boxItems) 
	{
		scrollBox.removeAll();
		scrollBox.revalidate();
		scrollBox.repaint();
		
	}
}
