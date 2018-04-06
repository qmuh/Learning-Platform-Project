package frontend.view.pages.items;

import javax.swing.Box;
import javax.swing.BoxLayout;

import com.sun.javafx.image.impl.General;

import sharedobjects.Assignment;

public class AssignItem extends GeneralItem
{

	private static final long serialVersionUID = 1L;
	private Assignment assignment;
	public AssignItem(Assignment assignment)
	{
		super(BoxLayout.X_AXIS, Integer.toString(assignment.getId()));
		this.assignment = assignment;
	}
	@Override
	public int getId()
	{
		return assignment.getId();
	}

}
