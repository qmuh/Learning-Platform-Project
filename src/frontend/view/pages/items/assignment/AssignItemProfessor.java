package frontend.view.pages.items.assignment;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import frontend.view.pages.components.customSwing.WButton;
import frontend.view.pages.components.customSwing.WButtonActivatable;
import shared.objects.Assignment;

/**
 * Provides a class that stores assignment items on a page from a professor's
 * perspective.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
public final class AssignItemProfessor extends AssignItem
{

	/**
	 * The version of the class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The button that specifies whether an assignment is activated.
	 */
	private WButtonActivatable activeButton;

	public AssignItemProfessor(Assignment assignment)
	{
		super(assignment);
	}

	private JPanel createActivateButton()
	{
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		activeButton = new WButtonActivatable(assignment.getActive());

		activeButton.setPreferredSize(new Dimension(200, 40));

		buttonPanel.add(activeButton, new GridBagConstraints());
		return buttonPanel;
	}

	public WButton getActiveButton()
	{
		return activeButton;
	}

	@Override
	protected JPanel createTheAssignment()
	{
		JPanel theAssignment = new JPanel(new GridLayout(1, 3));
		theAssignment.setBorder(BorderFactory.createEtchedBorder());
		theAssignment.setPreferredSize(new Dimension(500, 50));
		theAssignment.add(assignmentName, 0);
		theAssignment.add(createActivateButton(), 1);
		theAssignment.add(dueDate, 2);
		return theAssignment;
	}

}
