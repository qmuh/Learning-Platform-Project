package frontend.view.pages.items;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

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

	private JCheckBox assignmentActive;
	private JLabel assignmentName;
	private JLabel dueDate;

	public AssignItem(Assignment assignment)
	{
		super(BoxLayout.X_AXIS, Integer.toString(assignment.getId()));
		assignmentActive = new JCheckBox();
		assignmentActive.setSelected(assignment.getActive());
		assignmentName = new JLabel(assignment.getTitle() + "  ");
		assignmentName.setFont(TEXT_FONT);
		dueDate = new JLabel(assignment.getDue_date());
		dueDate.setFont(TEXT_FONT);

		this.add(assignmentActive);
		this.add(assignmentName);
		this.add(dueDate);

		this.assignment = assignment;
	}

	public void setActiveCheckboxListener(ActionListener listener)
	{
		assignmentActive.addActionListener(listener);
	}

	@Override
	public int getId()
	{
		return assignment.getId();
	}

}
