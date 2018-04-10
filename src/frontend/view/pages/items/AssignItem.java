package frontend.view.pages.items;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frontend.view.pages.components.customSwing.WButton;
import shared.objects.Assignment;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class AssignItem extends GeneralItem
{

	private static final long serialVersionUID = 1L;
	private Assignment assignment;

	private WButton activeButton;
	private JLabel assignmentName;
	private JLabel dueDate;

	public AssignItem(Assignment assignment)
	{
		super(BoxLayout.X_AXIS, Integer.toString(assignment.getId()));
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
		this.assignment = assignment;
		assignmentName = new JLabel(assignment.getTitle());
		assignmentName.setFont(TEXT_FONT);
		dueDate = new JLabel(assignment.getDueDate());
		dueDate.setFont(TEXT_FONT);
		this.setBorder(BorderFactory.createEtchedBorder());
		this.add(createTheAssignment());
	}
	
	private JPanel createTheAssignment()
	{
		JPanel theAssignment = new JPanel(new GridLayout(1, 3));
		theAssignment.setPreferredSize(new Dimension(500, 50));
		theAssignment.add(createLabelPanel(assignmentName, assignment.getTitle(), TEXT_FONT), 0);
		theAssignment.add(createActivateButton(), 1);
		theAssignment.add(createLabelPanel(dueDate, assignment.getDueDate(), TEXT_FONT), 2);
		return theAssignment;
	}
	
	public JPanel createLabelPanel(JLabel label, String s, Font f)
	{
		JPanel labelPanel = new JPanel();
		label = new JLabel(s);
		label.setFont(f);
		label.setHorizontalAlignment(JLabel.LEFT);
		labelPanel.add(label);
		return labelPanel;
	}
	
	private JPanel createActivateButton()
	{
		JPanel buttonPanel = new JPanel();
		activeButton = new WButton();
		activeButton.setPreferredSize(new Dimension(200, 40));
		if(assignment.getActive())
		{
			activeButton.setText("DEACTIVATE");
			activeButton.setBackground(CONTRAST_COLOR);
		} else
		{
			activeButton.setText("ACTIVATE");
			activeButton.setBackground(BACKGROUND_COLOUR);
		}
		buttonPanel.add(activeButton);
		return buttonPanel;
	}

	public void setAssignmentActiveButtonListener(ActionListener listener)
	{
		activeButton.addActionListener(listener);
	}

	@Override
	public int getId()
	{
		return assignment.getId();
	}

}
