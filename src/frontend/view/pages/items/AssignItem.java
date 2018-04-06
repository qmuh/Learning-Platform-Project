package frontend.view.pages.items;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import sharedobjects.Assignment;

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
	
	private JCheckBox active;
	private JLabel assignmentName;
	private JLabel dueDate;
	
	public AssignItem(Assignment assignment)
	{
		super(BoxLayout.X_AXIS, Integer.toString(assignment.getId()));
		
		active = new JCheckBox();
		active.setSelected(assignment.getActive());
		assignmentName = new JLabel(assignment.getTitle());
		dueDate = new JLabel(assignment.getDue_date());
		
		this.add(active);
		this.add(assignmentName);
		this.add(dueDate);
		
		this.assignment = assignment;
	}
	
	
	@Override
	public int getId()
	{
		return assignment.getId();
	}

}
