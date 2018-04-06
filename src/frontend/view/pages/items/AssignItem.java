package frontend.view.pages.items;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import sharedobjects.Assignment;

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
		System.out.println("Assignment is " + assignment.getActive());
		assignmentName = new JLabel(assignment.getTitle());
		dueDate = new JLabel(assignment.getDue_date());
		
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
