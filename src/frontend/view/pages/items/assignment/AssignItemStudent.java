package frontend.view.pages.items.assignment;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WLabel;
import shared.objects.Assignment;

public class AssignItemStudent extends AssignItem
{

	private WButton upload;
	private WButton submit;

	public AssignItemStudent(Assignment assignment)
	{
		super(assignment);
	}

	public WButton getUpload()
	{
		return upload;
	}

	public WButton getSubmit()
	{
		return submit;
	}

	@Override
	protected JPanel createTheAssignment()
	{
		JPanel theAssignment = new JPanel();
		theAssignment.setLayout(new BorderLayout());
		assignmentName.setHorizontalAlignment(WLabel.LEFT);
		dueDate.setHorizontalAlignment(WLabel.RIGHT);

		upload = new WButton("Upload");
		submit = new WButton("Submit");

		JPanel uploadPanel = new JPanel(new GridLayout(2, 1));
		uploadPanel.add(upload, 0);
		uploadPanel.add(submit, 1);

		theAssignment.add(assignmentName, BorderLayout.WEST);
		theAssignment.add(dueDate, BorderLayout.CENTER);
		theAssignment.add(uploadPanel, BorderLayout.EAST);
		return theAssignment;
	}

}
