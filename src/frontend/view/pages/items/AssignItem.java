package frontend.view.pages.items;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import frontend.interfaces.WondrisInfo;
import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WLabel;
import shared.objects.Assignment;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
abstract public class AssignItem extends GeneralItem implements WondrisInfo
{

	private static final long serialVersionUID = 1L;
	
	protected Assignment assignment;

	protected WLabel assignmentName;
	
	protected WLabel dueDate;

	public AssignItem(Assignment assignment)
	{
		super(BoxLayout.X_AXIS, Integer.toString(assignment.getId()));
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
		
		this.assignment = assignment;
		this.assignmentName = new WLabel(assignment.getTitle());
		this.dueDate = new WLabel(assignment.getDueDate());
		
		this.setBorder(BorderFactory.createEtchedBorder());
		this.add(createTheAssignment());
	}

	abstract protected JPanel createTheAssignment();
	
	@Override
	public int getId()
	{
		return assignment.getId();
	}
}
