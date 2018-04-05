package frontend.components;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JPanel;

public class BoxList <T extends Box> extends JPanel
{

	public BoxList()
	{
		
	}
	
	public void addItem(T boxItem) 
	{
		
	}
	
	public void setItems(ArrayList<T> boxItems) 
	{
		for (T item : boxItems)
		{
			this.add(item);
		}
	}
}
