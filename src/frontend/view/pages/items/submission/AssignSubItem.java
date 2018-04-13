package frontend.view.pages.items.submission;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import frontend.view.pages.items.GeneralItem;
import shared.objects.Assignment;

/**
 * Provides a class that stores assignment submissions inside of a container on
 * a page.
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 13, 2018
 */
abstract public class AssignSubItem extends GeneralItem
{
	/**
	 * The version of the class.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The assignment that this object groups submissions by.
	 */
	private Assignment assignment;

	public AssignSubItem(Assignment assignment)
	{
		super(BoxLayout.Y_AXIS, Integer.toString(assignment.getId()));

		this.assignment = assignment;
		// extract assignment title
		MatteBorder matte = new MatteBorder(2, 2, 2, 2, Color.BLACK);
		TitledBorder titledBorder = new TitledBorder(matte);
		titledBorder.setTitle(assignment.getTitle());
		titledBorder.setTitleFont(SUB_TITLE_FONT);

		this.setBorder(titledBorder);
	}

	abstract public void addSubmission(SubmitItem submitItem);

	@Override
	public int getId()
	{
		return assignment.getId();
	}

}
