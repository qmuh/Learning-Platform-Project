package frontend.view.pages.items.student;

import javax.swing.BoxLayout;

import frontend.view.pages.items.GeneralItem;
import shared.objects.Student;

/**
 * 
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class StudentItem extends GeneralItem
{
	private static final long serialVersionUID = 1L;
	private Student student;

	public StudentItem(Student student)
	{
		super(BoxLayout.X_AXIS, Integer.toString(student.getId()));
		this.student = student;
	}

	@Override
	public int getId()
	{
		return student.getId();
	}

}
