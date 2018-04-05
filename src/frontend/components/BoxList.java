package frontend.components;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class BoxList <T extends Box> extends JPanel
{

	public BoxList()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void addItem(T boxItem) 
	{
		this.add(boxItem);
	}
	
	public void setItems(ArrayList<T> boxItems) 
	{
		for (T item : boxItems)
		{
			this.add(item);
		}
	}
}
