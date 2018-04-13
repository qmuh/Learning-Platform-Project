package frontend.view.pages.items.submission;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import frontend.view.pages.items.GeneralItem;
import shared.objects.Assignment;

abstract public class AssignSubItem extends GeneralItem
{
	private static final long serialVersionUID = 1L;

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
