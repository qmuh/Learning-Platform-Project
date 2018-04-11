package frontend.view.pages.items;

import java.awt.GridLayout;

import javax.swing.JPanel;

import frontend.view.pages.components.customSwing.WLabel;
import shared.objects.Assignment;

public class AssignItemStudent extends AssignItem
{

	public AssignItemStudent(Assignment assignment)
	{
		super(assignment);
	}

	@Override
	protected JPanel createTheAssignment()
	{
		JPanel theAssignment = new JPanel(new GridLayout(1, 2));
		assignmentName.setHorizontalAlignment(WLabel.LEFT);
		dueDate.setHorizontalAlignment(WLabel.RIGHT);
		
		theAssignment.add(assignmentName, 0);
		theAssignment.add(dueDate, 1);
		return theAssignment;
	}

}
