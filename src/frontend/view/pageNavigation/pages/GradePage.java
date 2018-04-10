package frontend.view.pageNavigation.pages;

import frontend.view.pages.items.GradeItem;
import shared.objects.Course;
import shared.objects.Grade;

/**
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version 1.0
 * @since April 6, 2018
 */
public class GradePage extends CoursePage<GradeItem, Grade>
{
	private static final long serialVersionUID = 1L;
	private Course course;

	public GradePage(Course course)
	{
		super(course);

	}

	@Override
	public void displayPage()
	{
		// TODO Auto-generated method stub

	}

}
