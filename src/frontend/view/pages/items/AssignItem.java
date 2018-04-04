package frontend.view.pages.items;

import javax.swing.Box;
import javax.swing.BoxLayout;

import sharedobjects.Assignment;

public class AssignItem extends Box
{

	private static final long serialVersionUID = 1L;
	private Assignment assignment;
	public AssignItem(Assignment assignment)
	{
		super(BoxLayout.X_AXIS);
		this.assignment = assignment;
	}

}
