package frontend.view.pages.items;

import javax.swing.Box;
import javax.swing.BoxLayout;

import sharedobjects.Grade;

public class GradeItem extends Box
{
	
	private static final long serialVersionUID = 1L;

	private Grade grade;
	
	public GradeItem(Grade grade)
	{
		super(BoxLayout.X_AXIS);
		this.grade = grade;
	}
}
