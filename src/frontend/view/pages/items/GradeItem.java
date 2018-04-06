package frontend.view.pages.items;

import javax.swing.Box;
import javax.swing.BoxLayout;

import sharedobjects.Grade;

public class GradeItem extends GeneralItem
{
	
	private static final long serialVersionUID = 1L;

	private Grade grade;
	
	public GradeItem(Grade grade)
	{
		super(BoxLayout.X_AXIS, Integer.toString(grade.getId()));
		this.grade = grade;
	}

	@Override
	public int getId()
	{
		return grade.getId();
	}
}
