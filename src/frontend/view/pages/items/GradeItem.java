package frontend.view.pages.items;

import javax.swing.BoxLayout;

import shared.objects.Grade;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
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
