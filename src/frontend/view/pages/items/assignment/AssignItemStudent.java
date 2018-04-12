package frontend.view.pages.items.assignment;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WLabel;
import shared.objects.Assignment;

public class AssignItemStudent extends AssignItem
{

	private WButton upload;

	public AssignItemStudent(Assignment assignment)
	{
		super(assignment);
	}

	public WButton getUpload()
	{
		return upload;
	}

	@Override
	protected JPanel createTheAssignment()
	{
		JPanel theAssignment = new JPanel();
		theAssignment.setLayout(new BorderLayout());
		assignmentName.setHorizontalAlignment(WLabel.LEFT);
		dueDate.setHorizontalAlignment(WLabel.RIGHT);

		upload = new WButton("Upload");

		JPanel uploadPanel = new JPanel();
		uploadPanel.add(upload);

		theAssignment.add(assignmentName, BorderLayout.WEST);
		theAssignment.add(dueDate, BorderLayout.CENTER);
		theAssignment.add(uploadPanel, BorderLayout.EAST);
		return theAssignment;
	}

}
