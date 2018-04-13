package frontend.view.pages.assignment;

import java.awt.BorderLayout;
import java.awt.Component;

import frontend.interfaces.WondrisInfo;
import frontend.view.pages.course.CoursePage;
import frontend.view.pages.items.assignment.AssignItem;
import shared.objects.Assignment;
import shared.objects.Course;
import shared.objects.User;

/** The abstract assignment page 
 *
 * @author Trevor Le (30028725), Qasim Muhammad (30016415), Jimmy Truong
 *         (30017293)
 * @version
 * @since April 4, 2018
 */
abstract public class AssignmentPage extends CoursePage<AssignItem, Assignment>
		implements WondrisInfo
{
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/** The assignment page
	 * @param course The chosen course
	 * @param user The associated user
	 */
	public AssignmentPage(Course course, User user)
	{
		super(course, user);
		this.setName(ASSIGNMENT_PAGE + course.getId());
		this.setPageTitle("Assignments");
		this.bodyCenter.add(createAssignmentPanel(), BorderLayout.CENTER);
	}

	/**
	 * Creates the appearance of the assignment panel.
	 * 
	 * @return the component to place in the assignment page body
	 */
	abstract protected Component createAssignmentPanel();

	
}
