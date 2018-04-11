package frontend.view.pages.items;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WButtonActivatable;
import shared.objects.Assignment;

public final class AssignItemProfessor extends AssignItem
{

	private static final long serialVersionUID = 1L;

	private WButtonActivatable activeButton;

	public AssignItemProfessor(Assignment assignment)
	{
		super(assignment);
	}

	private JPanel createActivateButton()
	{
		JPanel buttonPanel = new JPanel();
		activeButton = new WButtonActivatable(assignment.getActive());
		
		activeButton.setPreferredSize(new Dimension(200, 40));
		
		buttonPanel.add(activeButton);
		return buttonPanel;
	}

	public WButton getActiveButton()
	{
		return activeButton;
	}

	@Override
	protected JPanel createTheAssignment()
	{
		JPanel theAssignment = new JPanel(new BorderLayout());
		theAssignment.setPreferredSize(new Dimension(500, 50));
		theAssignment.add(assignmentName, BorderLayout.WEST);
		theAssignment.add(createActivateButton(), BorderLayout.CENTER);
		theAssignment.add(dueDate, BorderLayout.EAST);
		return theAssignment;
	}
}
