package frontend.view.pages.items.assignment;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;


import frontend.view.pages.components.customSwing.WButton;
import shared.objects.Assignment;

public class AssignItemStudent extends AssignItem
{
	private static final long serialVersionUID = 1L;
	private JTextField uploadField;
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

	public JTextField getUploadField()
	{
		return uploadField;
	}
	
	@Override
	protected JPanel createTheAssignment()
	{
		JPanel theAssignment = new JPanel();
		theAssignment.setPreferredSize(new Dimension(500, 50));
		theAssignment.setLayout(new GridLayout(1, 3));

		JPanel uploadPanel = new JPanel(new GridLayout(2, 1));
		uploadPanel.add(createUploadFieldPanel(), 0);
		uploadPanel.add(createButtonPanel(), 1);
		
		theAssignment.add(assignmentName, 0);
		theAssignment.add(dueDate, 1);
		theAssignment.add(uploadPanel, 2);
		return theAssignment;
	}
	
	private JPanel createButtonPanel()
	{
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		JPanel uploadPanel = new JPanel();
		JPanel submitPanel = new JPanel();
		upload = new WButton("Browse");
		submit = new WButton("Submit");
		uploadPanel.add(upload);
		submitPanel.add(submit);
		buttonPanel.add(uploadPanel, 0);
		buttonPanel.add(submitPanel, 1);
		return buttonPanel;
	}
	
	private JPanel createUploadFieldPanel()
	{
		JPanel uploadFieldPanel = new JPanel(new GridBagLayout());
		uploadField = new JTextField(10);
		uploadField.setFont(TEXT_FONT);
		uploadField.setEditable(false);
		uploadFieldPanel.add(uploadField, new GridBagConstraints());
		return uploadFieldPanel;
	}

}
